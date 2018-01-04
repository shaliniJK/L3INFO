#include <sys/types.h>
#include <unistd.h>
#include <sys/mman.h>
#include <stdlib.h>
#include <assert.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <semaphore.h>

#define ITER 1000000

typedef struct mempartagee_s {
    int c1, c2, c3;
} mempartagee;

int main() {
    mempartagee *ptr;
    int i;
    int fds[2];
    char jeton;

    ptr = mmap(NULL, sizeof(mempartagee),
               PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
    assert(ptr != MAP_FAILED);

    assert(pipe(fds) != -1);
    jeton = '.';
    write(fds[1], &jeton, 1);

    switch (fork()) {
    case -1:
        exit(EXIT_FAILURE);

    case 0:                    /* Fils */
        for (i = 0; i < ITER; i++) {
            assert(read(fds[0], &jeton, 1) == 1);
            ptr->c1++;
            assert(write(fds[1], &jeton, 1) == 1);
            ptr->c2++;
        }
        exit(EXIT_SUCCESS);
    }

    for (i = 0; i < ITER; i++) {
        assert(read(fds[0], &jeton, 1) == 1);
        ptr->c1++;
        assert(write(fds[1], &jeton, 1) == 1);
        ptr->c3++;
    }

    wait(NULL);

    printf("%d + %d = %d = %d\n", ptr->c2, ptr->c3, ptr->c2 + ptr->c3, ptr->c1);

    return 0;
}

