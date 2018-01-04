#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define TAILLE 65537

int main() {
    int fds[2];
    unsigned int val;
    int res;
    char tampon[TAILLE];

    assert(pipe(fds) != -1);

    switch (fork()) {
    case -1:
        exit(EXIT_FAILURE);

    case 0:                    /* Fils */
        close(fds[0]);

        /* calcule une valeur */
        printf("Le fils commence un long calcul\n");
        sleep(2);
        val = 0x123456;
        printf("Le fils finit son long calcul et essaye d’écrire\n");

        res = write(fds[1], tampon, TAILLE);
        printf("Fin du fils, write a retourné %d\n", res);
        if (res == -1)
            perror("écriture");
        exit(EXIT_SUCCESS);

    default:                   /* Père */
        close(fds[1]);

        printf("Fais des choses mais crashe avant d’attendre le résultat du fils\n");
        assert(0);

        res = read(fds[0], &val, sizeof(unsigned int));
        printf("read a retourné %d, val (du père) = %x\n", res, val);
    }

    return 0;
}

