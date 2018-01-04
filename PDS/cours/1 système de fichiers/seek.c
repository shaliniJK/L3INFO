#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>

/* Commande qui déplace la tête de lecture de son entrée standard à 10
 * octets avant la fin…
 *
 * Essayer :
 * $ ./seek
 * $ ./seek < seek.c
 * et interpréter les résultats */

int main() {
    off_t off;

    off = lseek(STDIN_FILENO, -10, SEEK_END);
    printf("Position actuelle: %ld\n", off);

    return 0;
}
