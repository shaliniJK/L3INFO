#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    int fds[2];
    int val;

    assert(pipe(fds) != -1);

    switch (fork()) {
        case -1: exit(EXIT_FAILURE);

        case 0: /* Fils */
            val = 0x12345678;
            assert(write(fds[1], &val, sizeof(int)) == sizeof(int));
            exit(EXIT_SUCCESS);

        default: /* Père */
            sleep(10);
            printf("val avant: %x\n", val);
            assert(read(fds[0], &val, sizeof(int)) == sizeof(int));
            printf("val après: %x\n", val);
    }

    return 0;
}
