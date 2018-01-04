#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <assert.h>
#include <pthread.h>

long mcompteur_gc(char* buffer, unsigned long size, int nthread);

typedef struct {
    char *block;
    unsigned long size;
    unsigned long result;
} thread_arg_t;


unsigned long compteur_gc(char *bloc, unsigned long taille) {
    unsigned long i, cptr = 0;

    for (i = 0; i < taille; i++)
        if (bloc[i] == 'G' || bloc[i] == 'C')
            cptr++;

    return cptr;
}

void * wrapper(void *arg) {
    thread_arg_t * th_arg = (thread_arg_t *) arg;
    th_arg->result = compteur_gc(th_arg->block, th_arg->size);
    return NULL;
}

long mcompteur_gc(char* buffer, unsigned long size, int nthread)
{
    int i;
    pthread_t * threads;
    thread_arg_t * t_thread_args;
    unsigned long segment_size;
    unsigned long result = 0;

    segment_size = size/nthread;

    /* create pointer to all threads */
    threads = malloc(sizeof(pthread_t) * nthread);
    if (! threads) {
        fprintf(stderr, "Malloc error");
        exit(EXIT_FAILURE);
    }

    /* create pointer to of all thread arguments */
    t_thread_args = malloc(sizeof(thread_arg_t) * nthread);
    if (! t_thread_args) {
        fprintf(stderr, "Malloc error");
        exit(EXIT_FAILURE);
    }

    /* initialise thread arguments */
    for (i = 0; i < nthread; i++) {
        t_thread_args[i].result = 0;
        t_thread_args[i].block  = &(buffer[i * segment_size]);
        t_thread_args[i].size   = (i == nthread - 1) ? size - (nthread * i) : segment_size;

        pthread_create(&threads[i], NULL, wrapper, &t_thread_args[i]);
    }

    /* wait for all threads */
    for (i = 0; i < nthread; i++) {
        pthread_join(threads[i], NULL);
        result += t_thread_args[i].result;
    }

    free(threads);
    free(t_thread_args);
    return result;
}

int main(int argc, char *argv[]) {
    struct stat st;
    int fd;
    char *tampon;
    int lus, nthread;
    unsigned long cptr = 0;
    off_t taille = 0;
    struct timespec debut, fin;

    // assert(argc > 1);

    if (argc < 3) {
        printf("USAGE : %s [NomFichierGenome] [nbThreads] \n", argv[0]);
        exit(EXIT_FAILURE);
    }

    nthread = atoi(argv[2]);

    if (nthread < 0) {
        printf("Nombre de threads invalide\n");
        exit(EXIT_FAILURE);
    }


    /* Quelle taille ? */
    assert(stat(argv[1], &st) != -1);
    tampon = malloc(st.st_size);
    assert(tampon != NULL);

    /* Chargement en mémoire */
    fd = open(argv[1], O_RDONLY);
    assert(fd != -1);
    while ((lus = read(fd, tampon + taille, st.st_size - taille)) > 0)
        taille += lus;
    assert(lus != -1);
    assert(taille == st.st_size);
    close(fd);

    /* Calcul proprement dit */
    assert(clock_gettime(CLOCK_MONOTONIC, &debut) != -1);
    /*cptr = compteur_gc(tampon, taille);*/
    cptr = mcompteur_gc(tampon, taille, nthread);
    assert(clock_gettime(CLOCK_MONOTONIC, &fin) != -1);

    /* Affichage des résultats */
    printf("Nombres de GC:   %ld\n", cptr);
    printf("Taux de GC:      %lf\n", ((double) cptr) / ((double) taille));

    fin.tv_sec  -= debut.tv_sec;
    fin.tv_nsec -= debut.tv_nsec;
    if (fin.tv_nsec < 0) {
        fin.tv_sec--;
        fin.tv_nsec += 1000000000;
    }
    printf("Durée de calcul: %ld.%09ld\n", fin.tv_sec, fin.tv_nsec);
    printf("(Attention: très peu de chiffres après la virgule sont réellement significatifs !)\n");

    return 0;
}