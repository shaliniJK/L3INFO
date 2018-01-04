#include <assert.h>
#include <ctype.h>
#include <errno.h>
#include <math.h>
#include <pthread.h>
#include <stdarg.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

/* Exemple de création de thread */

void *fonction_thread(void *arg) {
    int i;

    for (i = 0; i < 10; i++) {
        printf("thread: %d\n", i);
    }

    /* Essayer avec et sans la ligne suivante */
    /* exit(EXIT_SUCCESS); */

    return NULL;
}

int main() {
    pthread_t tid;
    int i;

    assert(pthread_create(&tid, NULL, &fonction_thread, NULL) == 0);

    for (i = 0; i < 10; i++) {
        printf("main:   %d\n", i);
    }

    /* Essayer avec et sans la ligne suivante */
    /* sleep(1); */

    return 1;
}
