#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <assert.h>

static int BUFSIZE = 16;

void tail_simple(const char *path, int ntail);

void usage (const char *prog)
{
    printf("usage : %s [-n N] [-N] file", prog);
}

int count_lines(int fd)
{
    ssize_t bytes_read;
    int nblines = 0;
    int i;
    char buffer[BUFSIZE];

    while ((bytes_read = read(fd, buffer, BUFSIZE)) > 0) {
        if (bytes_read == -1)
            perror("Error : ");

        for (i = 0; i < bytes_read; i++) {
            if (buffer[i] == '\n') {
                nblines++;
            }
        }
        if (bytes_read < BUFSIZE && buffer[bytes_read - 1] != '\n') {
            nblines++;
        }
    }
    lseek(fd, 0, SEEK_SET);
    return nblines;
}

void tail_simple(const char *path, int ntail)
{
    int fd;
    int nblines, i;
    ssize_t bytes_read;
    char buffer[BUFSIZE];
    int current_line = 0;

    fd = open(path, O_RDONLY);
    if (fd == -1)
        perror("Error : ");

    nblines = count_lines(fd);

    while ((bytes_read = read(fd, buffer, BUFSIZE)) > 0) {
        if (bytes_read == -1)
            perror("Error : ");

        for (i = 0; i < bytes_read; i++) {
            if (buffer[i] == '\n') {
                current_line++;
            }
        }
        if ((current_line >= nblines - ntail) || (nblines - ntail <= 0) ) {
            assert(write(STDOUT_FILENO, buffer, bytes_read) != -1);
        }
    }
    close(fd);
}


int main(int argc, char **argv)
{
    int ch;
    int ntail = 0;

    while ((ch = getopt(argc, argv, "n:0123456789")) != -1) {
        switch(ch) {
            case 'n':
                ntail = atoi(optarg);
                break;

            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                ntail = ntail*10 + (ch - '0');

            case '?':
            default:
                usage(argv[0]);
                exit(EXIT_FAILURE);
        }
    }
    if (ntail == 0)
        ntail = 10;

    argc -= optind;
    argv += optind;

    tail_simple(*argv, ntail);
    return EXIT_SUCCESS;
}

