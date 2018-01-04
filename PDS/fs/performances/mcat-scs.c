#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <sys/uio.h>
#include <fcntl.h>
#include <unistd.h>


/* changer selon taille optimale */
static int MCAT_BUFSIZ = 10000;

int mcat_scs(const char * path)
{
    char buffer[MCAT_BUFSIZ];
    int fd;
    ssize_t bytes_read;

    fd = open(path, O_RDONLY);
    assert(fd != -1);

    while ((bytes_read = read(fd, buffer, MCAT_BUFSIZ)) > 0) {

        assert(write(STDOUT_FILENO, buffer, bytes_read) == bytes_read);
    }
    assert(bytes_read != -1);
    return 0;
}

int main (int argc, char **argv)
{
    if (argc < 2) {
        printf("usage : ./mcat-scs filename");
        exit(EXIT_FAILURE);
    }

    mcat_scs(argv[1]);
	return 0;
}
