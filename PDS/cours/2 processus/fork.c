#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <assert.h>

/* Commande qui crée un nouveau processus (qui affiche un message) */

int main() {
    pid_t pid, pid2;
    int status;

    switch (pid = fork()) {
        case -1: exit(EXIT_FAILURE);

        case 0: /* Fils */
            printf("Je suis le fils (moi: %d, mon père: %d)\n",
                    getpid(), getppid());
            exit(EXIT_SUCCESS);
            /* exit(EXIT_FAILURE); */
            /* assert(0); */

        default: /* Père */
            printf("Je suis le père (moi: %d, mon père: %d)\n",
                    getpid(), getppid());
            pid2 = wait(&status);
            assert(pid == pid2);
            if(WIFEXITED(status))
                printf("Mon fils s’est terminé sur un exit(%d)\n", WEXITSTATUS(status));
            else if(WIFSIGNALED(status))
                printf("Mon fils s’est terminé sur un signal\n");
    }

    return 0;
}
