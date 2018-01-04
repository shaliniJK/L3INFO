#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <assert.h>

/* On veut exécuter deux appels à la fonction « fonct » de façon
 * concurrente */

int fonct(char c, unsigned int nb) {
    unsigned int i;

    for (i = 0; i < nb; i++) {
        putchar(c);
    }

    return 1234;
}

struct fonct_arg_s {
    char c;
    unsigned int nb;
};

#define ARG ((struct fonct_arg_s *) arg)

void *fonct_th(void * arg) {
    char c = ARG -> c;
    unsigned int nb = ARG -> nb;
    int *p_res;

    int res = fonct(c, nb);

    p_res = (int *)malloc(sizeof(int));
    *p_res = res;
    /* pthread_exit(p_res); */
    /* ou, équivalent : */
    return p_res;
}

int main() {
    pthread_t tid;
    struct fonct_arg_s * arg;
    int *p_res;
    /* on ne veut pas donner une adresse non initialisée à
     * pthread_join, sinon le comportement du programme n’est plus
     * défini (segfault par exemple) : */
    /* int **pp_res; */

    /* printf("%p\n", (void *)pp_res); */
    arg = malloc(sizeof(struct fonct_arg_s));
    assert(arg != NULL);
    arg -> c = '.';
    arg -> nb = 100000;

    /* printf("%p\n", (void *)pp_res); */
    assert(pthread_create(&tid, NULL, &fonct_th, arg) == 0);
    fonct('|', 100000);
    assert(pthread_join(tid, (void **)&p_res) == 0);
    printf("Résultat du sous-thread: %d\n", *p_res);
    /* assert(pthread_join(tid, (void **)pp_res) == 0); */
    /* printf("Résultat du sous-thread: %d\n", **pp_res); */

    return 0;
}
