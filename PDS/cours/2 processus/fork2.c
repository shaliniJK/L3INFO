#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <assert.h>

/* Commande qui crée un nouveau processus (qui affiche un message) */
/* Observer les différents états des processus père et fils suivant
 * l’ordre de terminaison :
 * que se passe-t-il si le fils se termine avant le père ? (que doit
 * faire le père quand son fils est terminé ?)
 * que se passe-t-il si le père se termine avant le fils ?
 */

int main() {
    pid_t pid;

    switch (pid = fork()) {
        case -1: exit(EXIT_FAILURE);

        case 0: /* Fils */
            printf("Je suis le fils (moi: %d, mon père: %d)\n",
                    getpid(), getppid());
            sleep(10);
            /* sleep(10); */
            printf("Je suis le fils (moi: %d, mon père: %d)\n",
                    getpid(), getppid());
            exit(EXIT_SUCCESS);
            /* exit(EXIT_FAILURE); */
            /* assert(0); */

        default: /* Père */
            printf("Je suis le père (moi: %d, mon père: %d)\n",
                    getpid(), getppid());
            sleep(10);
            sleep(10);
    }

    return 0;
}

