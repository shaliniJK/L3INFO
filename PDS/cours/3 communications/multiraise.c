#include <assert.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void handler(int sig) {
    fprintf(stderr, "Je traite le signal %d\n", sig);
}

int main() {
    int sig;
    struct sigaction sa;

    sa.sa_handler = &handler;
    sa.sa_flags = 0;
    assert(sigfillset(&sa.sa_mask) == 0);
    for (sig = 1; sig < 32; sig++)
        if (sig != SIGKILL && sig != SIGSTOP)
            assert(sigaction(sig, &sa, NULL) == 0);

    for (sig = 1; sig < 32; sig++)
        if (sig != SIGKILL) {
            fprintf(stderr, "Je m’envoie le signal %d\n", sig);
            raise(sig);
        }

    return 0;
}
