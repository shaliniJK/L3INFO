#include <stdlib.h>
#include <stdint.h>
#include <assert.h>
#include <pthread.h>
#include <stdio.h>
#include <unistd.h>

#include "pile.h"
#include "tri.h"
#include "rapide.h"
#include "main.h"

unsigned long seuil_bloc_long = 1;

base_t *tableau;

/* struct pour la pile */
typedef struct donnee_s {
    pile * stack;
    int cmpt_thread; /* le nombre de threads en train de découper un bloc */
} donnee_t;

donnee_t donnee;

int nb_occupes; /* le nombre de blocs dans la pile à un certain moment */

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond_pile_vide = PTHREAD_COND_INITIALIZER;
pthread_cond_t cond_pile_remplie = PTHREAD_COND_INITIALIZER;


#define min(a,b) (((a)<(b))?(a):(b))
#define max(a,b) (((a)>(b))?(a):(b))

/* Étape élémentaire du tri rapide : découpe le bloc b en 0, 1 ou 2 blocs
 * Dans le cas normal, découpe en 2 blocs, les éléments inférieurs au
 * pivot, et ceux supérieurs au pivot
 * Si un bloc contient moins de 1 élément, il n’est pas retourné
 */
int rapide_decoupebloc(bloc_t b, bloc_t bret[2]) {
    pos_t g, d;
    base_t pivot, tmp;
    bloc_t b1, b2;
    int nb_ret = 0;

    if(b.debut >= b.fin) {
        /* Arrive uniquement dans le cas d’un tri d’un tableau de
         * taille 1 au départ */
        assert (b.debut == b.fin);
        return 0;
    }

    /* Définit une petite macro pour échanger deux cases de tableau en
     * passant par la variable tmp */
#define echange(p1,p2)                     \
    do {                                   \
        tmp         = tableau[p1];         \
        tableau[p1] = tableau[p2];         \
        tableau[p2] = tmp;                 \
    } while(0)

    pivot = tableau[b.debut];
    g = b.debut + 1;
    d = b.fin;

    while (g < d) {
        while (g < d && tableau[g] <= pivot)
            g++;
        while (d > g && tableau[d] > pivot)
            d--;
        if (g < d)
            echange(g, d);
    }

    b1.debut = b.debut;
    b2.fin = b.fin;

    if (tableau[g] <= pivot) {
        echange(g, b.debut);
        b1.fin   = g - 1;
        b2.debut = min(g + 1, b2.fin);
    } else if (g > b.debut + 1) {
        echange(g - 1, b.debut);
        b1.fin   = max(g - 2, b1.debut);
        b2.debut = g;
    } else {                    /* sinon le pivot est le plus petit, donc déjà bien placé */
        b1.fin   = b.debut;
        b2.debut = b.debut + 1;
    }

    if (b1.debut < b1.fin)
        bret[nb_ret++] = b1;
    if (b2.debut < b2.fin)
        bret[nb_ret++] = b2;

    return nb_ret;
}

/* Effectue un tri rapide séquentiel */
void rapide_seq(bloc_t bloc_init) {
    pile p;
    int i, nb_blocs;
    bloc_t bloc;
    bloc_t blocs[2];

    init_pile(&p);
    empile(&p, bloc_init);

    /* Principe du tri rapide séquentiel :
     * tant qu’il y a des blocs à trier, dépile un bloc, le découpe en
     * (au maximum) deux sous-blocs non-encore triés et les empile */
    do {
        bloc = depile(&p);
        nb_blocs = rapide_decoupebloc(bloc, blocs);
        for (i = 0; i < nb_blocs; i++)
            empile(&p, blocs[i]);
    } while (!pile_vide(&p));
}

int thread_decoupe_plus() {
    int res;
    assert(pthread_mutex_lock(&mutex) == 0);
    res = (donnee.cmpt_thread == 0);
    assert(pthread_mutex_unlock(&mutex) == 0);
    return res;
}


void * rapide_thread() {
    bloc_t bloc;
    bloc_t blocs[2];
    int i, nb_blocs;

    do {
        /* depile un bloc */
        assert(pthread_mutex_lock(&mutex) == 0);
        /* si pile vide, attendre un bloc disponible */
        while (nb_occupes == 0)
            assert(pthread_cond_wait(&cond_pile_remplie, &mutex) == 0);

        bloc = depile(donnee.stack);
        nb_occupes--;

        assert(pthread_cond_signal(&cond_pile_vide) == 0);
        assert(pthread_mutex_unlock(&mutex) == 0);

        /* decoupe un bloc */
        assert(pthread_mutex_lock(&mutex) == 0);
        donnee.cmpt_thread++;
        nb_blocs = rapide_decoupebloc(bloc, blocs);
        assert(pthread_mutex_unlock(&mutex) == 0);

        assert(pthread_mutex_lock(&mutex) == 0);
        donnee.cmpt_thread--;
        assert(pthread_mutex_unlock(&mutex) == 0);

        /* empile les nouveaux blocs */
        for (i = 0; i < nb_blocs; i++) {
            assert(pthread_mutex_lock(&mutex) == 0);
            /* si pile est remplie, attendre avant de pouvoir empiler de nouveau */
            while (nb_occupes == TAILLE_PILE)
                assert(pthread_cond_wait(&cond_pile_vide, &mutex) == 0);

            empile(donnee.stack, blocs[i]);
            nb_occupes++;
            assert(pthread_cond_signal(&cond_pile_remplie) == 0);
            assert(pthread_mutex_unlock(&mutex) == 0);
        }

    } while ((nb_occupes != 0) && ! thread_decoupe_plus());
}


void rapide(pos_t taille, unsigned int nb_threads) {
    bloc_t bloc;
    pthread_t * threads;
    unsigned int i;

    bloc.debut = 0;
    bloc.fin   = taille - 1;

    // if (nb_threads == 1 || taille < seuil_bloc_long) {
    /* algo sequentiel pour les blocs de taille < valeur seuil   */
    if (nb_threads == 1) {
        rapide_seq(bloc);
        return;
    }

    assert(nb_threads > 1);
    threads = malloc(sizeof(pthread_t) * nb_threads);
    if (! threads) {
        fprintf(stderr, "Malloc error !\n");
    }

    /* creer la pile et empiler le bloc dans la pile -- donnee partagée */
    init_pile(donnee.stack);
    empile(donnee.stack, bloc);


    nb_occupes = 1;
    donnee.cmpt_thread = 0;

    /* create n threads */
    for (i = 0; i < nb_threads; i++) {
        if (pthread_create((threads + i), NULL, rapide_thread, NULL) != 0) {
            fprintf(stderr, "Error when creating thread !\n");
        }
    }

    rapide_thread();

    /* join threads */
    for (i = 0; i < nb_threads; i++) {
        pthread_join(*(threads + i), NULL);
    }

    free(threads);
    return;
}