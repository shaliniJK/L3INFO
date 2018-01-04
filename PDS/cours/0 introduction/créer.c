/* Commande qui crée un fichier /tmp/test
 * On utilise pour cela l’appel système creat
 */

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>

int main() {
    int fd;

    fd = creat("/tmp/test", S_IRWXU);

    if (fd == -1) {
        perror("créer");
        exit(EXIT_FAILURE);
    } else
        exit(EXIT_SUCCESS);
}
