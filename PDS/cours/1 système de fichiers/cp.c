#include <assert.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

#define TAILLE 4096

/* Commande qui copie un fichier ordinaire (son premier argument) vers
 * son deuxième argument */

int main(int argc, char *argv[]) {
    int fd_lect, fd_ecr;
    ssize_t lus;
    unsigned char tampon[TAILLE];

    assert(argc > 2);
    fd_lect = open(argv[1], O_RDONLY);
    assert(fd_lect != -1);
    fd_ecr = open(argv[2], O_WRONLY|O_CREAT|O_TRUNC, 0666);
    assert(fd_ecr != -1);

    while ((lus = read(fd_lect, tampon, TAILLE)) > 0) {
        assert(write(fd_ecr, tampon, lus) == lus);
    }
    assert(lus != -1);

    close(fd_ecr);
    close(fd_lect);

    return 0;
}
