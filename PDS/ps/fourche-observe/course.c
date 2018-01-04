#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define children 10
#define count 100000000


int main (void)
{
    int i, j;
    pid_t pid;

    /* create children processes */
    for (i = 0; i < children; i++) {
        pid = fork();

        switch(pid)
        {
            case -1:
                fprintf(stderr, "%s\n", "Fork error");
                perror(NULL);
                exit(EXIT_FAILURE);
            case 0:
                for (j = 0; j < count; j++) {}

                printf("Hello there from child of PID %d\n", getpid());
                fflush(stdout);

                for (j = 0; j < count; j++) {}
                exit(EXIT_SUCCESS);
            default:
                break;
        }
    }

    /* wait for all children to return */
    while ((pid = wait(NULL)) != -1) {
        printf("Child process of PID %d terminated\n", pid);
    }

    exit(EXIT_SUCCESS);
}

