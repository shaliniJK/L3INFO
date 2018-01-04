#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <pthread.h>
#include <assert.h>
#include <signal.h>

#define NB_THREADS 4

pthread_t thread_ids[NB_THREADS];

void *attente(void *num) {
    int dernier = (*(int *) num == NB_THREADS - 1);
    thread_ids[*(int *) num] = pthread_self();
    pthread_detach(pthread_self());
    fprintf(stderr, "Thread %d prêt\n", *(int *) num);

    free(num);

    while (1) {
        sleep(3);
        if (dernier)
            break;
    }

    /* Le dernier veut arrêter le thread 1 : */

    if (thread_ids[1]) {
        fprintf(stderr, "Arrête le thread 1\n");
        assert(pthread_cancel(thread_ids[1]) == 0);
        thread_ids[1] = 0;
        usleep(100000);
        system("ps -Tm");
    }

    return NULL;
}

int main() {
    int i;
    int *num;
    pthread_t threadid;

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

