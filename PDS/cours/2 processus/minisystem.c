#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <assert.h>
#include <stdio.h>

/* Version maison de la fonction system */
int systemaison(char *lignecommande) {
    pid_t pid;
    int status;

    switch (pid = fork()) {
        case -1:
            return -1;

        case 0:
            /* fils */
            execl("/bin/sh", "sh", "-c", lignecommande, NULL);
            assert(0);
            exit(EXIT_FAILURE);
    }

    /* père */
    assert(waitpid(pid, &status, 0) == pid);

    return status;
}

int main() {
    int status;

    status = systemaison("ls -al");

    if(WIFEXITED(status))
        printf("Exit avec %d\n", WEXITSTATUS(status));

    return 0;
}
