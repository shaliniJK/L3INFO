#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <pthread.h>
#include <assert.h>
#include <signal.h>

#define NB_THREADS 4

pthread_t thread_ids[NB_THREADS];

void sig_handler(int sig) {
    int i;

    for (i = 0; i < NB_THREADS; i++) {
        if (pthread_equal(pthread_self(), thread_ids[i]))
            break;
    }

    fprintf(stderr, "Traitant de signal dans le thread %d\n", i);
}

void *attente(void *num) {
    sigset_t mask;

    thread_ids[*(int *) num] = pthread_self();
    pthread_detach(pthread_self());
    fprintf(stderr, "Thread %d prêt\n", *(int *) num);

    /* Le bloc de code suivant permet de garantir que le thread 0
     * (le thread initial) ne recevra pas le signal SIGINT ; la
     * fonction sig_handler sera donc exécutée par un des autres
     * threads */

    sigemptyset(&mask);
    sigaddset(&mask, SIGINT);
    if (*(int *) num == 0)
        pthread_sigmask(SIG_BLOCK, &mask, NULL);


    free(num);

    while (1)
        sleep(10);

    return NULL;
}

int main() {
    int i;
    int *num;
    pthread_t threadid;
    struct sigaction sa;

    sa.sa_handler = &sig_handler;
    sa.sa_flags = 0;
    sigemptyset(&sa.sa_mask);
    assert(sigaction(SIGINT, &sa, NULL) != -1);

    for (i = 1; i < NB_THREADS; i++) {
        num = malloc(sizeof(int));
        assert(num);
        *num = i;
        pthread_create(&threadid, NULL, &attente, num);
    }

    system("ps -Tm");

    thread_ids[0] = pthread_self();
    num = malloc(sizeof(int));
    *num = 0;
    attente(num);

    return 0;
}
