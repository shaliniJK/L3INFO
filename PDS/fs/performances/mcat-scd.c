#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <sys/uio.h>
#include <fcntl.h>
#include <unistd.h>

int mcat_scd(const char * path, int buffer_size)
{
    char * buffer;
    int fd;
    ssize_t bytes_read;

    buffer = (char *) malloc(buffer_size * sizeof(char));
    assert(buffer != NULL);

    fd = open(path, O_RDONLY);
    assert(fd != -1);

    while ((bytes_read = read(fd, buffer, buffer_size)) > 0) {

        assert(write(STDOUT_FILENO, buffer, bytes_read) == bytes_read);
    }
    assert(bytes_read != -1);

    close(fd);
    free(buffer);
    return 0;
}

int main (int argc, char **argv)
{
    int buffer_size;
    if (argc < 2) {
        printf("usage : ./mcat-scd filename");
        exit(EXIT_FAILURE);
    }

    /* converts value of MCAT_BUFSIZE as environment variable from string to integer */
    buffer_size = atoi(getenv("MCAT_BUFSIZE"));
    assert(buffer_size > 0);

    mcat_scd(argv[1], buffer_size);
	return 0;
}
