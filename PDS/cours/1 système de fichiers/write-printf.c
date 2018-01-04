#include <stdio.h>
#include <unistd.h>

/* Commande qui illustre l’existence de tampon mémoire dans
 * l’interface FILE utilisée par fprintf, contrairement aux appels
 * systèmes (read, write) */

int main() {
    if(1) {

        write(STDOUT_FILENO, "a", 1);

        /* printf("b"); est équivalent à fprintf(stdout, "b"); */
        fprintf(stdout, "b");
        /* Essayer avec et sans la ligne suivante */
        /* fflush(stdout); */

        write(STDOUT_FILENO, "c", 1);

    } else {

        write(STDERR_FILENO, "a", 1);

        /* printf("b"); est équivalent à fprintf(stdout, "b"); */
        fprintf(stderr, "b");
        /* Essayer avec et sans la ligne suivante */
        /* fflush(stderr); */

        write(STDERR_FILENO, "c", 1);

    }

    return 0;
}

