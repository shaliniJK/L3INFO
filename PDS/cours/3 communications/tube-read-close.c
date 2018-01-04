#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

/* On voudrait que le père n’attende pas sans fin (en laissant un
 * zombie, d’ailleurs) */

int main() {
    int fds[2];
    unsigned int val;
    int res;

    assert(pipe(fds) != -1);

    switch (fork()) {
    case -1:
        exit(EXIT_FAILURE);

    case 0:                    /* Fils */
        /* calcule une valeur */
        close(fds[0]);

        printf("Le fils commence un long calcul\n");
        sleep(2);
        val = 0x123456;

        /* mais il crashe avant d’envoyer son résultat ! */
        assert(0);

        assert(write(fds[1], &val, sizeof(unsigned int)) == sizeof(unsigned int));
        printf("Fin du fils, val (du fils) = %x\n", val);
        exit(EXIT_SUCCESS);

    default:                   /* Père */
        close(fds[1]);

        printf("Attend que le fils envoie une information\n");
        res = read(fds[0], &val, sizeof(unsigned int));
        printf("read a retourné %d, val (du père) = %x\n", res, val);
    }

    return 0;
}

