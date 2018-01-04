#include <assert.h>
#include <unistd.h>
#include <stdio.h>

int main() {
    int res;
    assert(close(STDOUT_FILENO) != -1);
    res = printf("abc");
    fprintf(stderr, "%d\n", res);
    res = fflush(stdout);
    fprintf(stderr, "%d\n", res);
    return 0;
}
