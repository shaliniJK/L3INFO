#include <unistd.h>
#include <assert.h>
#include <stdio.h>

/* Commande qui lance « ls -al » */

int main() {
    char *argv[3];
    argv[0] = "ls";
    argv[1] = "-al";
    argv[2] = NULL;

    execv("/bin/ls", argv);
    printf("Jamais affiché (sauf erreur du exec) !\n");
    assert(0);

    return 0;
}

