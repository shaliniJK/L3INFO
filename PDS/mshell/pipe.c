/* mshell - a job manager */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

#include "pipe.h"
#include "cmd.h"
#include "jobs.h"


void do_pipe(char *cmds[MAXCMDS][MAXARGS], int nbcmd, int bg)
{
    int i;
    pid_t *processes; /* all processes */
    int pipefd[MAXCMDS][2];

    if (nbcmd < 2) {
        fprintf(stderr, "%s\n", "Insufficient arguments");
        exit(EXIT_FAILURE);
    }

    /* initialise processes array */
    processes = malloc(sizeof(pid_t) * nbcmd);
    if (! processes) {
        fprintf(stderr, "%s\n", "Malloc error");
        exit(EXIT_FAILURE);
    }


    for (i = 0; i < nbcmd - 1; i++) {
        /* create individual pipe */
        /* or use pipe2(pipefd[i], O_CLOEXEC) to close on exec */
        if (pipe(pipefd[i]) == -1) {
            fprintf(stderr, "%s\n", "Pipe error");
            perror(NULL);
            exit(EXIT_FAILURE);
        }

        switch((processes[i] = fork())) {
        case -1:
            fprintf(stderr, "%s\n", "Fork error");
            perror(NULL);
            exit(EXIT_FAILURE);

        case 0:
            if (i > 0) {
                close(pipefd[i - 1][0]);
                dup2(pipefd[i - 1][1], STDIN_FILENO);
                close(pipefd[i - 1][1]);
            }
            close(pipefd[i][0]);
            dup2(pipefd[i][1], STDOUT_FILENO);
            close(pipefd[i][1]);

            if (execvp(cmds[i][0], cmds[0]) == -1) {
                fprintf(stderr, "%s\n", "exec error");
                perror(NULL);
                exit(EXIT_FAILURE);
            }

        default:
            close(pipefd[i][1]);
            if (i > 0) {
                close(pipefd[i-1][0]);
            }
            jobs_addjob(processes[0], (bg == 1 ? BG : FG) , cmds[0][0]);
        }
    }

    /* final process */
    /* close command nbcmd-2 from previous iteration above, no next process to pipe output to */

    switch((processes[nbcmd - 1] = fork())) {
        case -1:
            fprintf(stderr, "%s\n", "Fork error");
            perror(NULL);
            exit(EXIT_FAILURE);

        case 0:
            close(pipefd[nbcmd - 2][0]);
            dup2(pipefd[nbcmd - 2][1], STDIN_FILENO);
            close(pipefd[nbcmd - 2][1]);

            if (execvp(cmds[nbcmd - 1][0], cmds[nbcmd - 1]) == -1) {
                fprintf(stderr, "%s\n", "exec error");
                perror(NULL);
            }

        default:
            jobs_addjob(processes[nbcmd - 1], (bg == 1 ? BG : FG) , cmds[nbcmd - 1][0]);
    }

    /* close all unused pipes */
    for (i = 0; i < nbcmd - 1; i++) {
        close(pipefd[i][0]);
        close(pipefd[i][1]);
    }

    /* wait end of all processes */
    for (i = 0; i < nbcmd; i++) {
        if (verbose)
            printf("Waiting children \n");

        waitfg(processes[i]);

        if (verbose)
            printf("End waiting children \n");
    }

    fflush(stdout);
    free(processes);

    return;
}

