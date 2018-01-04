#include <sys/types.h>
#include <unistd.h>
#include <sys/mman.h>
#include <stdlib.h>
#include <assert.h>
#include <stdio.h>

int main() {
    void *ptr;
    char cmd[1000];

    printf("Avant \"mmap\" :\n");
    snprintf(cmd, 1000, "../outils/mem.sh %d", getpid());
    system(cmd);

    ptr = mmap(NULL, 123, PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
    assert(ptr != MAP_FAILED);

    printf("\n\"mmap\" a retourné : %p\n", ptr);
    printf("Après \"mmap\" :\n");
    system(cmd);

    return EXIT_SUCCESS;
}
