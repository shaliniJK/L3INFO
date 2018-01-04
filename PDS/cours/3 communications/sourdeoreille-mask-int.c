#include <stdio.h>
#include <signal.h>
#include <assert.h>
#include <unistd.h>

void echo(int sig) {
    fprintf(stderr, "Signal reçu : %d\nMême pas mal\n", sig);
}

int main() {
    struct sigaction sa;
    sigset_t mask;

    sa.sa_handler = &echo;
    sa.sa_flags = 0;
    assert(sigemptyset(&sa.sa_mask) == 0);
    assert(sigaction(SIGINT, &sa, NULL) == 0);

    sigemptyset(&mask);
    sigaddset(&mask, SIGINT);
    assert(sigprocmask(SIG_BLOCK, &mask, NULL) != -1);

    sleep(5);

    assert(sigprocmask(SIG_UNBLOCK, &mask, NULL) != -1);

    while(1)
        pause();

    return 0;
}
