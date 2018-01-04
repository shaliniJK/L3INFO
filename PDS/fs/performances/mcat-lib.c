#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <sys/uio.h>
#include <fcntl.h>
#include <unistd.h>


int mcat_lib(const char * path)
{
    FILE * fp;
    int c;

    fp = fopen(path, "r");
    while ((c = fgetc(fp)) != EOF) {
        fputc(c, stdout);
    }
    return 0;
}

int main (int argc, char **argv)
{
    if (argc < 2) {
        printf("usage : ./mcat-lib filename");
        exit(EXIT_FAILURE);
    }

    mcat_lib(argv[1]);
	return 0;
}
