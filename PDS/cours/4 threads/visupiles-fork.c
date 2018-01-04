#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <assert.h>
#include <pthread.h>
#include <sys/types.h>
#include <sys/wait.h>

void *fonct(void *p_attente) {
    printf("auxi: %lx\n", pthread_self());
    printf("auxi: adresse de l’argument: %p\n", (void*)&p_attente);
    fflush(stdout);

    if (p_attente != NULL)
        usleep(*(unsigned long *) p_attente);
    return NULL;
}

int main() {
    pthread_t thread_id;
    unsigned long attente = 300000;
    char visupiles[1000];

    /* Pour visualiser les piles des threads */
    snprintf(visupiles, 1000, "../outils/mem.sh %d", getpid());

    printf("main: %lx\n", pthread_self());
    system(visupiles);

    printf("\nmain: création d’auxi\n");
    assert(pthread_create(&thread_id, NULL, &fonct, &attente) == 0);

    system(visupiles);

    printf("\nps -Tm:\n");
    fflush(stdout);
    system("ps -Tm");

    switch (fork()) {
    case -1:
        exit(EXIT_FAILURE);

    case 0:                    /* Fils */
        printf("\nfils: pid = %d\n", getpid());
        snprintf(visupiles, 1000, "../outils/mem.sh %d", getpid());
        system(visupiles);
        printf("\nps -Tm:\n");
        fflush(stdout);
        system("ps -Tm");
        exit(EXIT_SUCCESS);

    default:                   /* Père */
        wait(NULL);
    }

    assert(pthread_join(thread_id, NULL) == 0);

    printf("\nFin de main\n");

    return EXIT_SUCCESS;
}
