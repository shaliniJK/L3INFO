#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <assert.h>

/* Commande qui écrit un octet à la position absolue 1Go */

/* Si le fichier ne contient pas déjà 1Go de données (le fichier
 * n’existait pas avant, par exemple), crée un fichier creux (les
 * blocs d’octets nuls ne sont pas réellement écrits sur le disque) */

int main(int argc, char *argv[]) {
    int fd;
    off_t off;

    assert(argc > 1);
    fd = open(argv[1], O_WRONLY|O_CREAT, 0666);
    assert(fd != -1);

    off = lseek(fd, 1 << 30, SEEK_END);
    printf("Position actuelle: %ld\n", off);
    assert(write(fd, "a", 1) != -1);

    close(fd);

    return 0;
}

