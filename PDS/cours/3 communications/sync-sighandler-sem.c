#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <assert.h>
#include <semaphore.h>

#define ITER 1000000

/* Sémaphore à la rescousse ? */

typedef struct mempartagee {
    int c1, c2, c3;
    sem_t sem;
} mempartagee;

mempartagee *m;

void usr1(int arg) {
    assert(sem_wait(&m->sem) != -1);
    m->c1++;
    assert(sem_post(&m->sem) != -1);
    m->c2++;
}

int main() {
    int i;
    struct sigaction sa;

    m = (mempartagee *) malloc(sizeof(mempartagee));
    assert(m != NULL);

    sem_init(&m->sem, 1, 1);

    sa.sa_handler = &usr1;
    sa.sa_flags = 0;
    assert(sigemptyset(&sa.sa_mask) == 0);
    assert(sigaction(SIGUSR1, &sa, NULL) == 0);

    switch (fork()) {
    case -1:
        exit(EXIT_FAILURE);

    case 0:                    /* Fils */
        /* Le fils ne partage pas de données, il sert juste à envoyer
         * des signaux au père */
        for (i = 0; i < ITER; i++) {
            kill(getppid(), SIGUSR1);
        }
        exit(EXIT_SUCCESS);
    }

    for (i = 0; i < ITER; i++) {
        assert(sem_wait(&m->sem) != -1);
        m->c1++;
        assert(sem_post(&m->sem) != -1);
        m->c3++;
    }

    if (wait(NULL) == -1) {
        perror("wait");
        assert(0);
    }
    printf("%d + %d = %d = %d ?\n", m->c2, m->c3, m->c2 + m->c3, m->c1);

    return 0;
}
