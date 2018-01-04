#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>

static int N_CHILDREN;

void displayprocess()
{
    if (system("ps -a") == -1) {
        fprintf(stderr, "%s\n", "system failure when calling ps");
        exit(EXIT_FAILURE);
    }
}

/* display child pid & sleep */
void todo()
{
    while (1) {
        printf("Child of PID %d still alive\n", getpid());
        sleep(5);
    }
}

void wait_children()
{
    int i, status;
    pid_t pid;
    for (i = 0; i < N_CHILDREN; i++) {
        printf("Press any key to launch the wait\n");
        fflush(stdout);
        getc(stdin);
        displayprocess();
        if ((pid = wait(&status)) == -1) {
            perror("wait() failure ");
            exit(EXIT_FAILURE);
        }
        if (WIFEXITED(status)) {
            printf("Child process of PID %d terminated on an exit(%d)\n", pid, WEXITSTATUS(status));
        } else if (WIFSIGNALED(status))
            printf("Child process terminated on a signal\n");
    }
}


int main (int argc, char** argv)
{
    int i;
    pid_t pid;

    if (argc < 2) {
        printf("Usage: ./observe [number of child processes]");
        exit(EXIT_FAILURE);
    }
    N_CHILDREN = atoi(argv[1]);

    if (N_CHILDREN <= 0) {
        printf("The number of child processes given is invalid");
        exit(EXIT_FAILURE);
    }

    /* create children */
    for (i = 0; i < N_CHILDREN; i++) {
        pid = fork();

        switch(pid) {
            case -1:
                fprintf(stderr, "%s\n", "Fork error ");
                perror(NULL);
                exit(EXIT_FAILURE);
            case 0:
                todo();
            default:
               /* kill(pid, SIGTERM);*/
               displayprocess();
        }
    }
    kill(pid, SIGTERM);
    displayprocess();

    /* wait for all children */
    wait_children();
    displayprocess();

    return EXIT_SUCCESS;
}

