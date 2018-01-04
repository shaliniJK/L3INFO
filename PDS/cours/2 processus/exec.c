#include <unistd.h>
#include <assert.h>
#include <stdio.h>

/* Commande qui lance « ls -al » */

int main() {
    execl("/bin/ls", "ls", "-al", NULL);
    /* execl("ls", "ls", "-al", NULL); */
    /* execlp("ls", "ls", "-al", NULL); */

    /* Il manque l’argument dans la version suivante : */
    /* execlp("ls", "-al", NULL); */

    printf("Jamais affiché (sauf erreur du exec) !\n");
    assert(0);

    return 0;
}
