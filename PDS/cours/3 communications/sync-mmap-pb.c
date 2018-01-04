#include <assert.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define ITER 1000000

typedef struct mempartagee_s {
    int c1, c2, c3;
} mempartagee;

int main() {
    mempartagee *ptr;
    int i;

    printf("Problème des accès concurrents\n");

    ptr = mmap(NULL, sizeof(mempartagee),
               PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
    assert(ptr != MAP_FAILED);

    switch (fork()) {
    case -1:
        exit(EXIT_FAILURE);

    case 0:                    /* Fils */
        for (i = 0; i < ITER; i++) {
            ptr->c1++;
            ptr->c2++;
        }
        exit(EXIT_SUCCESS);
    }

    for (i = 0; i < ITER; i++) {
        ptr->c1++;
        ptr->c3++;
    }

    wait(NULL);

    printf("%d + %d = %d = %d\n", ptr->c2, ptr->c3, ptr->c2 + ptr->c3, ptr->c1);

    return 0;
}
