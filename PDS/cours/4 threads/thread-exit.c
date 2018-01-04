#include <pthread.h>
#include <assert.h>
#include <stdlib.h>

int main() {
    int *p;
    p = malloc(10);
    assert(p != NULL);

    pthread_exit(p);

    return 100;
}
