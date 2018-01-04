#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

/* On voudrait que le père n’attende pas sans fin (en laissant un
 * zombie, d’ailleurs) */

void sigchld_handler(int arg) {
    printf("Mon fils s’est terminé\n");
    wait(NULL);
}

int main() {
    int fds[2];
    unsigned int val;
    int res;
    struct sigaction sa;

    assert(pipe(fds) != -1);

    sa.sa_handler = &sigchld_handler;
    sa.sa_flags = 0;
    /* sa.sa_flags = SA_RESTART; */
    assert(sigemptyset(&sa.sa_mask) == 0);
    assert(sigaction(SIGCHLD, &sa, NULL) == 0);

    switch (fork()) {
    case -1:
        exit(EXIT_FAILURE);

    case 0:                    /* Fils */
        /* calcule une valeur */
        printf("Le fils commence un long calcul\n");
        sleep(2);
        val = 0x123456;

        /* mais il crashe avant d’envoyer son résultat ! */
        assert(0);

        assert(write(fds[1], &val, sizeof(unsigned int)) == sizeof(unsigned int));
        printf("Fin du fils, val (du fils) = %x\n", val);
        exit(EXIT_SUCCESS);

    default:                   /* Père */
        printf("Attend que le fils envoie une information\n");
        res = read(fds[0], &val, sizeof(unsigned int));
        printf("read a retourné %d, val (du père) = %x\n", res, val);
        if (res == -1)
            perror("lecture");
    }

    return 0;
}
