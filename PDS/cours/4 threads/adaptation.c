#include <stdio.h>
#include <stdlib.h>

/* On veut exécuter deux appels à la fonction « fonct » de façon
 * concurrente */

int fonct(char c, unsigned int nb) {
    unsigned int i;

    for (i = 0; i < nb; i++) {
        putchar(c);
    }

    return 1234;
}

int main() {
    fonct('|', 100000);
    fonct('.', 100000);

    return 0;
}
