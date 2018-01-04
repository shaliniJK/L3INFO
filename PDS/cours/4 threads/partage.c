#include <assert.h>
#include <pthread.h>
#include <stdio.h>
#include <unistd.h>

/* Partage ? */

void *thread_auxiliaire(void *adr) {
    int *p = adr;

    printf("auxi: %lx\n", pthread_self());

    printf("auxi: %d\n", *p);
    usleep(200000);
    printf("auxi: %d (devrait être 124)\n", *p);
    (*p)++;
    printf("auxi: %d (devrait être 125)\n", *p);

    return NULL;
}

int main() {
    pthread_t tid;
    int i = 123;
    int *p = &i;

    assert(pthread_create(&tid, NULL, &thread_auxiliaire, p) == 0);

    printf("main: %lx\n", pthread_self());

    printf("main: %d\n", *p);
    usleep(100000);
    (*p)++;
    printf("main: %d (devrait être 124)\n", *p);
    usleep(200000);
    printf("main: %d (devrait être 125)\n", *p);

    return 0;
}
