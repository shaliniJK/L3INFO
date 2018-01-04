#include <stdio.h>
#include <signal.h>
#include <assert.h>
#include <unistd.h>

void echo(int sig) {
    fprintf(stderr, "Signal reçu : %d ; même pas mal\n", sig);
}

int main() {
    struct sigaction sa;

    sa.sa_handler = &echo;
    sa.sa_flags = 0;
    assert(sigemptyset(&sa.sa_mask) == 0);
    assert(sigaction(SIGTSTP, &sa, NULL) == 0);
    assert(sigaction(SIGINT, &sa, NULL) == 0);
    assert(sigaction(SIGTERM, &sa, NULL) == 0);
    /* assert(sigaction(SIGSTOP, &sa, NULL) == 0); */
    /* assert(sigaction(SIGKILL, &sa, NULL) == 0); */
    assert(sigaction(SIGSEGV, &sa, NULL) == 0);

    fprintf(stderr, "avant (%d)\n", getpid());
    /* Essayer en décommentant la ligne suivante : */
    /* *((int *)0) = 12; */
    pause();
    fprintf(stderr, "après\n");

    return 0;
}
