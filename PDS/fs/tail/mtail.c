#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/uio.h>
#include <string.h>

static int BUFSIZE = 512;

/* Déclaration des fonctions */
void tac_rec(int fd);
void tac_lseek(int fd);
void tac_pread(int fd);
void rec_read(int fd, off_t pos, int ntail);
void tail_efficace(const char *path, int ntail);

void rec_read2(int fd, off_t pos, int ntail);
void tail_efficace2(const char *path, int ntail);

void usage (const char *prog)
{
    printf("usage : %s [-n] N file", prog);
}

ssize_t size(const char* path)
{
    FILE *file;
    ssize_t size;

    file = fopen(path, "rb");
    if(file == NULL)
        perror("Error :");

    fseek(file, 0, SEEK_END);
    size = ftell(file);
    fclose(file);
    return size;
}


int lines_in_buffer(const char *buffer, int bufsize, int ntail)
{
    int linesread = 0, i;

    for (i = bufsize - 1; i >= 0 && linesread < ntail; i--){
      if (buffer[i] == '\n'){
        linesread++;
      }
    }
    if (linesread > ntail) {
        return i;
    }
    return 0;
}

/* pareil comme en TD ?*/
void tac_rec(int fd) {
    int status;
    char * c;

    status = read(fd, &c, 1);
    assert(status = -1);
    if (status > 0) {
        tac_rec(fd);
        write(STDOUT_FILENO, c, 1);
    }
}

void tac_lseek(int fd)
{
    int status;
    char c;
    status = lseek(fd, 0, SEEK_END);
    assert(status != 1);   /* or != -1 */
    status = lseek(fd, -1, SEEK_CUR);

    do {
        status = read(fd, &c, 1);
        assert(status != -1);
        write(STDOUT_FILENO, &c, 1);
    } while (lseek(fd, -2, SEEK_CUR) != -1);
}

void tac_pread(int fd)
{
    int status;
    int pos;
    char c;

    status = lseek(fd, 0, SEEK_END);
    assert(status != 1);
    status = lseek(fd, -1, SEEK_CUR);
    assert(status != 1);
    pos = status;

    while(pread(fd, &c, 1, pos) != -1) {
        write(STDOUT_FILENO, &c, 1);
        pos--;
    }
}



void tail_efficace(const char *path, int ntail)
{
    off_t current_pos, offset;
    int fd;
    ssize_t file_size;

    file_size = size(path);

    fd = open(path, O_RDONLY);
    if (fd == -1)
        perror("Error : ");

    offset = (ssize_t) BUFSIZE;
    current_pos = lseek(fd, offset, SEEK_SET);
    assert(current_pos != 1);
    rec_read(fd, current_pos, ntail);

    close(fd);
}


void rec_read(int fd, off_t pos, int ntail)
{
    char buffer[BUFSIZE];
    ssize_t bytes_read;
    int index, i;
    off_t current_pos;
    int nlines = 0;

    while ((bytes_read = read(fd, buffer, BUFSIZE)) > 0) {
        if (bytes_read == -1)
            perror("Error : ");

        for (i = 0; i < bytes_read; i++) {
            if (buffer[i] == '\n') {
                nlines++;
            }
        }
        if (bytes_read < BUFSIZE && buffer[bytes_read - 1] != '\n') {
            nlines++;
        }
    }

    index = lines_in_buffer(buffer, BUFSIZE, ntail);
    if (nlines > ntail) {
        /* skip certain lines, then start reading --
         read in buffer how many lines to skip & get index  */

        assert(lseek(fd, (off_t) index, SEEK_END) != -1);
        tac_rec(fd);
    }
    else {
        current_pos = lseek(fd, -(pos + BUFSIZE), SEEK_END);
        assert(current_pos != 1);
        write(1, buffer + index + 1, current_pos - index - 1);
        rec_read(fd, current_pos, ntail - nlines);
    }
}

void tail_efficace2(const char *path, int ntail)
{
    int fd = open(path, O_RDONLY);
    if (fd == -1)
        perror("Error : ");

    rec_read2(fd, 0, ntail);

    close(fd);
}


void rec_read2(int fd, off_t pos, int ntail)
{
    char buf[1];
    char buffer[BUFSIZE];
    ssize_t bytes_read;

    if (ntail==-1) {
        lseek(fd,pos+1,SEEK_END);
        while ((bytes_read = read(fd, buffer, BUFSIZE)) > 0) {
            assert(write(STDOUT_FILENO, buffer, bytes_read) != -1);}
    } else {
        lseek(fd,pos-1,SEEK_END);
        assert((bytes_read=read(fd,buf,1))>0);
        if (strcmp(buf,"\n")==0) ntail--;
        rec_read2(fd,pos-1,ntail);
    }
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

    tail_efficace(*argv, ntail);

    printf("\n *** tail_efficace version 2 *** \n");
    tail_efficace2(*argv, ntail);
    return EXIT_SUCCESS;

}

