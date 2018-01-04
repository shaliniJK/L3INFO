/* mshell - a job manager */

#ifndef _CMD_H_
#define _CMD_H_

#define BOLD "\033[00;01m"
#define NORM "\033[00;00m"

extern void waitfg(pid_t pid);
extern void do_exit();
extern void do_jobs();
extern void do_help();
extern void do_bg(char **argv);
extern void do_fg(char **argv);
extern void do_kill(char **argv);
extern void do_stop(char **argv);

#endif
