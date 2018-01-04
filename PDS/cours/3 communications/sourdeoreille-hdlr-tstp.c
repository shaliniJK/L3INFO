#include <stdio.h>
#include <signal.h>
#include <assert.h>
#include <unistd.h>

void echo(int sig) {
    fprintf(stderr, "Signal reçu : %d\nMême pas mal\n", sig);
}

int main() {
    struct sigaction sa;

    sa.sa_handler = &echo;
    sa.sa_flags = 0;
    assert(sigemptyset(&sa.sa_mask) == 0);
    assert(sigaction(SIGTSTP, &sa, NULL) == 0);

    fprintf(stderr, "avant (%d)\n", getpid());
    pause();
    fprintf(stderr, "après\n");

    return 0;
}
