/* mshell - a job manager */

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <signal.h>
#include <sys/wait.h>
#include <errno.h>


#include "jobs.h"
#include "common.h"
#include "sighandlers.h"

/*
 * wrapper for the sigaction function
 */
int sigaction_wrapper(int signum, handler_t * handler)
{
    struct sigaction action;

    sigemptyset(&action.sa_mask);
    action.sa_handler = handler;
    action.sa_flags   = SA_RESTART;

    if (sigaction(signum, &action, NULL) < 0) {
        fprintf(stderr, "%s\n", "sigaction error ");
        perror(NULL);
        exit(EXIT_FAILURE);
    }
    return 0;
}

/*
 * sigchld_handler - The kernel sends a SIGCHLD to the shell whenever
 *     a child job terminates (becomes a zombie), or stops because it
 *     received a SIGSTOP or SIGTSTP signal. The handler reaps all
 *     available zombie children
 */
void sigchld_handler(int sig)
{
    pid_t pid, wait_pid;
    struct job_t * job;
    int status;

    pid = jobs_fgpid();

    if (verbose)
        printf("sigchld_handler: entering\n");

    while ((wait_pid = waitpid(pid, &status, WNOHANG|WUNTRACED)) != -1) {
        if (WIFEXITED(status) || WIFSIGNALED(status)) {
            jobs_deletejob(wait_pid);

            if (verbose)
                printf("sigchld_handler : Removing terminated child\n");
        }

        if (WIFSTOPPED(status)) {
            job = jobs_getjobpid(pid);
            if (job != NULL)
                job->jb_state = ST;

            if (verbose)
                printf("sigchld_handler : Child stopped by signal %i\n", WSTOPSIG(status));
        }
    }

    if (verbose)
        printf("sigchld_handler: exiting\n");

    return;
}


/*
 * sigint_handler - The kernel sends a SIGINT to the shell whenever the
 *    user types ctrl-c at the keyboard.  Catch it and send it along
 *    to the foreground job.
 */
void sigint_handler(int sig)
{
    pid_t current_fgpid;

    if (verbose)
        printf("sigint_handler: entering\n");

    while ((current_fgpid = jobs_fgpid()) > 0) {
        kill(-current_fgpid, SIGINT);

        if (verbose)
            printf("sigint_handler: sending SIGINT signal to %i\n", current_fgpid);
    }

    if (verbose)
        printf("sigint_handler: exiting\n");

    return;
}

/*
 * sigtstp_handler - The kernel sends a SIGTSTP to the shell whenever
 *     the user types ctrl-z at the keyboard. Catch it and suspend the
 *     foreground job by sending it a SIGTSTP.
 */
void sigtstp_handler(int sig)
{
    pid_t current_fgpid;
    struct job_t * job;

    if (verbose)
        printf("sigtstp_handler: entering\n");

    while ((current_fgpid = jobs_fgpid()) > 0) {
        job = jobs_getjobpid(current_fgpid);

        if (job->jb_state != ST) {
            job->jb_state = ST;
            kill(-current_fgpid, SIGTSTP);

            if (verbose)
                printf("sigtstp_handler: sending SIGTSTP signal to %i\n", current_fgpid);
        }
    }


    if (verbose)
        printf("sigtstp_handler: exiting\n");

    return;
}
