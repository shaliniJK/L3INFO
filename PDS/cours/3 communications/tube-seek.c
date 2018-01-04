#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    int fds[2];
    off_t res;

    assert(pipe(fds) != -1);

    res = lseek(fds[0], 0, SEEK_CUR);
    if (res == -1)
        perror("seek");
    return 0;
}
