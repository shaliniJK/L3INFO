#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <assert.h>
#include <unistd.h>

/* Redirection de la sortie standard du processus avant de faire appel
 * à exec */

int main() {
    int fd;

    fd = open("/tmp/log", O_WRONLY | O_CREAT | O_TRUNC, 0666);
    assert(dup2(fd, STDOUT_FILENO) != -1);
    close(fd);
    execlp("ls", "ls", "-al", NULL);
    assert(0);

    return 0;
}
