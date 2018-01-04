/* Commande qui crée un fichier dans le répertoire /tmp et dont le nom
 * est donné en argument sur la ligne de commande
 */

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <string.h>

int main(int argc, char **argv) {
    int fd;
    char nomfich[512];

    assert(argc >= 2);

    /* strcpy(nomfich, "/tmp/"); */
    /* strncat(nomfich, argv[1], 506); */

    snprintf(nomfich, 512, "/tmp/%s", argv[1]);

    fd = creat(nomfich, S_IRWXU);

    if (fd == -1) {
        perror("créer");
        exit(EXIT_FAILURE);
    } else
        exit(EXIT_SUCCESS);
}
