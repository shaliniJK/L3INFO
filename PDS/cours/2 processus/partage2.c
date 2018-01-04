#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

/* Partage ? */
/* Est-ce que la position dans le fichier ouvert est partagée entre
 * les deux processus ? */

int main() {
    pid_t pid;
    int fd, lus;
    char tampon[4];

    fd = open("alphabet", O_RDONLY);
    assert(fd != -1);

    printf("début");
    fflush(stdout);

    switch(pid = fork()) {
        case -1:
            exit(EXIT_FAILURE);

        case 0:
            /* fils */
            lus = read(fd, tampon, 3);
            assert(lus != -1);
            tampon[lus] = '\0';
            printf("fils: \"%s\"\n", tampon);
            exit(EXIT_SUCCESS);

        default:
            /* père */
            lus = read(fd, tampon, 3);
            assert(lus != -1);
            tampon[lus] = '\0';
            printf("père: \"%s\"\n", tampon);
    }

    return 0;
}
