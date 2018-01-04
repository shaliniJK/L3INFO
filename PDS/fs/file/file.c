#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/uio.h>
#include <assert.h>

static int BUFFER_SIZE = 16;

struct KFILE {
    int position = 0;
    int fd;
    char buffer[BUFFER_SIZE];
    int index = 0;
    int valid_size; /* ? ? ? */
    int valid=0;
    flag readable;
    flag writable;
}

/* should return EOF if unable to write */
int kputc(int ch, KFILE * kfile)
{
    if (kfile->position >= BUFFER_SIZE) {
        int i = kflush(kfile);
        if (i==-1) return '\0';
    }
    kfile->buffer[kfile->position] = ch;
    kfile->position++;
    return ch;
}

int kflush(KFILE * kfile)
{
    int i = write(kfile->fd,kfile->buffer,BUFSIZE);
    if (i!=-1) {
      kfile->position=0;
      kfile->index+=BUFSIZE;
    }
    return i;
}

KFILE * kopen(const char * path)
{
    KFILE * kfile   = (KFILE *) malloc(sizeof(KFILE));
    kfile->position = 0;
    kfile->index    = 0;
    kfile->fd       = open(path, O_WRONLY|O_CREATE, 0644);
    assert(kfile->fd != -1);
    return kfile;
}

int kclose(KFILE * kfile)
{
    int i = kflush(kfile);
    if (i==-1) return i;
    i = close(kfile->fd);
    if (i!=-1) free(kfile);
    return i;
}

int kgetc(KFILE *file)
{
    assert(kfile->fd) > 0;
    if (kfile->position < kfile->valid) {
        kfile->position++;
        kfile->index += BUFFER_SIZE;
        return kfile->buffer[kfile->position-1];
    }
    kfile->valid = read(kfile->fd, kfile->buffer, BUFFER_SIZE);
    if (kfile->valid > 0) {
        return kfile->buffer[kfile->position++];
    }
}