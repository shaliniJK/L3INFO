#include <assert.h>
#include <unistd.h>

int main() {
    close(STDOUT_FILENO);
    execlp("ls", "ls", "-al", NULL);
    assert(0);

    return 0;
}
