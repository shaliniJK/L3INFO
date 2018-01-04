#include <assert.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#define TAILLE 4096

/* Commande qui affiche sur la sortie standard le contenu de son
 * premier argument (fichier ordinaire) */

int main(int argc, char *argv[]) {
    int fd_lect;
    /* int i; */
    ssize_t lus;
    unsigned char tampon[TAILLE];

    assert(argc > 1);
    fd_lect = open(argv[1], O_RDONLY);
    assert(fd_lect != -1);

    while ((lus = read(fd_lect, tampon, TAILLE)) > 0) {
        /* BUG ! printf("%s", tampon); */

        /* for (i = 0; i < lus; i++) { */
        /*     printf("%c", tampon[i]); */
        /* ou alors putchar */
        /* } */

        assert(write(STDOUT_FILENO, tampon, lus) == lus);
    }
    assert(lus != -1);

    close(fd_lect);

    return 0;
}

