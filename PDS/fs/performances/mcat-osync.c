#include <stdio.h>
#include <assert.h>
#include <stdlib.h>

static int MCAT_BUFSIZ = 16;


int mcat_osync(const char * path)
{
    return 0;
}

int main (int argc, char **argv)
{
    if (argc < 2) {
        printf("usage : ./mcat-osync filename");
        exit(EXIT_FAILURE);
    }

    mcat_osync(argv[1]);
	return 0;
}
