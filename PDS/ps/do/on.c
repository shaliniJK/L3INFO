#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <assert.h>
#include <sys/types.h>
#include <sys/wait.h>

void usage (char *prog) 
{
    printf("usage : %s [pred] do [cmd]", prog);
}
 

int parse_arguments(int argc, char *argv[]) 
{
    int ido;

    for (ido = 1; ido < argc; i++) {
        if (! strcmp(argv[ido], "do")) {
            break;
        }
        if (ido == argc) {
            exit(EXIT_FAILURE);
        }
        if (ido == 1) {
            usage(argv[1]);
            exit(EXIT_FAILURE);
        }
        if (ido == argc - 1) {
            usage(argv[1]);
            exit(EXIT_FAILURE);
        }
        argv[ido] = (char *) NULL;
    }
    return ido;
}



int main(int argc, char *argv[])
{
    int ido;
    pid_t pid;
    int status;
    char *pred;
    char *cmd;

    ido = parse_arguments(argc, argv);

    pid = fork();
    
    switch(pid)
    {
        case -1:
            fprintf(stderr, "%s\n", "Fork error");
            perror(NULL);
            exit(EXIT_FAILURE);
        case 0:
            execvp(argv[1], argv + 1);
            exit(EXIT_FAILURE);
        default:
            wait(&status);
    }

    if (WIFEXITED(status) && WEXITSTATUS(status)) {
        execvp(argv[ido - 1], argv[ido + 1]);
        exit(EXIT_FAILURE);
    } else
        exit(EXIT_FAILURE);
    
}




