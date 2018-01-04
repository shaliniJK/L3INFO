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

    int res;
};

#define ARG ((struct fonct_arg_s *) arg)

void *fonct_th(void * arg) {
    ARG -> res = fonct(ARG -> c, ARG -> nb);
    return NULL;
}

int main() {
    pthread_t tid;
    struct fonct_arg_s * arg;

    arg = malloc(sizeof(struct fonct_arg_s));
    assert(arg != NULL);
    arg -> c = '.';
    arg -> nb = 100000;

    assert(pthread_create(&tid, NULL, &fonct_th, arg) == 0);
    fonct('|', 100000);
    assert(pthread_join(tid, NULL) == 0);

    return 0;
}
