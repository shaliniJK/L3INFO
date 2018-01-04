#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>

/* Commande pour tester l’utilisation d’une fifo */
/* À exécuter avec strace, pour visualiser quels appels systèmes sont
 * bloquants */

#define TAILLE_TAMPON 4096

int main(int argc, char *argv[]) {
    char tampon[TAILLE_TAMPON];
    int lus;
    int fdin, fdout;

    assert(argc >= 3);

    if (!strcmp(argv[1], "-"))
        fdin = STDIN_FILENO;
    else {
        fdin = open(argv[1], O_RDONLY);
        assert(fdin != -1);
    }

    if (!strcmp(argv[2], "-"))
        fdout = STDOUT_FILENO;
    else {
        fdout = open(argv[2], O_WRONLY);
        assert(fdout != -1);
    }

    while ((lus = read(fdin, tampon, TAILLE_TAMPON)) > 0) {
        assert(write(fdout, tampon, lus) == lus);
    }

    if (lus == -1) {
        perror("lecture");
        exit(EXIT_FAILURE);
    }

    return 0;
}
