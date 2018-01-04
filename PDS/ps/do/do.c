#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <assert.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

#include "makeargv.h"

static int opt_and     = 1; /* default and option is true*/
static int opt_or      = 0;
static int opt_kill    = 0;
static int opt_circuit = 0;

void usage (char *prog)
{
    printf("usage : %s [-a |-o |-c |-k] command...Choose either -a or -o", prog);
}

int true()
{
    exit(EXIT_SUCCESS);
}

int false()
{
    exit(EXIT_FAILURE);
}

int createprocess(char * command)
{
    pid_t pid;
    char ** cmd_arg;
    pid = fork();

    switch (pid) {
        case -1:
            fprintf(stderr, "%s\n", "Fork error");
            perror(NULL);
            exit(EXIT_FAILURE);

        case 0:
            cmd_arg = makeargv(command);
            if (execvp(cmd_arg[0], cmd_arg) == -1) {
                fprintf(stderr, "%s\n", "exec error");
                perror(NULL);
            }
            freeargv(cmd_arg);
            exit(EXIT_FAILURE);

        default:
            return pid;
    }
}

int mdo(int argc, char **argv)
{
    int i, status;
    pid_t * processes;
    int result = 0;

    processes = malloc(sizeof(pid_t) * argc);
    if (! processes) {
        fprintf(stderr, "%s\n", "Malloc error");
        exit(EXIT_FAILURE);
    }

    for (i = 0; i < argc; i++) {
        processes[i] = createprocess(argv[i]);
    }

    if (opt_and) {
        result = EXIT_SUCCESS;
        for (i = 0; i < argc; i++) {
            if (wait(&status) == -1) {
                perror("wait() failure ");
            }
            if (WIFEXITED(status)) {
                if (WEXITSTATUS(status) == EXIT_FAILURE) {
                    result = EXIT_FAILURE;
                    break;
                }
            }
        }
    }

    if (opt_or) {
        for (i = 0; i < argc; i++) {
            if (wait(&status) == -1) {
                perror("wait() failure ");
            }
            if (WIFEXITED(status)) {
                if (WEXITSTATUS(status) == EXIT_SUCCESS) {
                    result = EXIT_SUCCESS;
                    break;
                }
            }
        }
    }

    if (opt_circuit) {
        if (opt_kill) {
            for (i = 0; i < argc; i++) {
                kill(processes[i], SIGKILL);
            }
        }
    }
    else {
        for (i = 0; i < argc; i++) {
            wait(NULL);
        }
    }

    free(processes);
    return result;
}


int main(int argc, char **argv)
{
    int ch;

    while ((ch = getopt(argc, argv, "oack")) != -1) {
        switch(ch) {
            case 'o':
                opt_or  = 1;
                opt_and = 0;
                break;
            case 'a':
                opt_and = 1;
                opt_or  = 0;
                break;
            case 'k':
                opt_kill = 1;
                break;
            case 'c':
                opt_circuit = 1;
                break;
            default:
                break;
        }
    }
    if (opt_circuit == 0) {
        opt_kill = 0;
    }

    if (optind >= argc) {
        usage(argv[0]);
        exit(EXIT_FAILURE);
    }

    argc -= optind;
    argv += optind;

    return mdo(argc, argv);
}
