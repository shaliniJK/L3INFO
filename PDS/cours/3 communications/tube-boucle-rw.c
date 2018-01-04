#include <assert.h>
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define TAILLE 10485760

/* Essayer cette commande avec les arguments de 0 à 8 */

unsigned char *tampon;
unsigned long compteur;

void boucle_rw(int fd_r, int taille_r, int fd_w, int taille_w) {
    assert(taille_w < TAILLE);
    assert(taille_r < TAILLE);

    /* boucle infinie */
    for (compteur = 0;; compteur++) {
        printf("%ld: ", compteur);
        fflush(stdout);
        printf("w%ld ", write(fd_w, tampon, taille_w));
        fflush(stdout);
        printf("r%ld\n", read(fd_r, tampon, taille_r));
        fflush(stdout);
    }
}

int main(int argc, char *argv[]) {
    int fds[2];
    long l;

    assert(pipe(fds) != -1);
    assert(argc > 1);
    tampon = (unsigned char *) malloc(TAILLE * sizeof(unsigned char));
    assert(tampon != NULL);

    switch (l = strtol(argv[1], NULL, 10)) {
    case 0:
        boucle_rw(fds[0], 1, fds[1], 1);
        break;
    case 1:
        boucle_rw(fds[0], 2, fds[1], 1);
        break;
    case 2:
        boucle_rw(fds[0], 5, fds[1], 2);
        break;
    case 3:
        boucle_rw(fds[0], 5, fds[1], 0);
        break;
    case 4:
        boucle_rw(fds[0], 10, fds[1], 20);
        break;
    case 5:
        boucle_rw(fds[0], 1, fds[1], 1024);
        break;
    case 6:
        boucle_rw(fds[0], 1, fds[1], 2048);
        break;
    case 7:
        boucle_rw(fds[0], 1, fds[1], 65536);
        break;
    case 8:
        boucle_rw(fds[0], 1, fds[1], 65537);
        /* strace */
        break;
    default:
        fprintf(stderr, "Cas non défini l = %ld\n", l);
    }

    return 0;
}
