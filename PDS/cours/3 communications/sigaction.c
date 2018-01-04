#include <assert.h>
#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <stdlib.h>

/* Commande qui affiche un message quand l’utilisateur tape Ctrl-C */

void affiche_message(int sig) {
    fprintf(stderr, "Traitant du signal %d\n", sig);

    /* exit(12); */
}

int main() {
    struct sigaction sa;

    sa.sa_handler = &affiche_message;
    assert(sigemptyset(&sa.sa_mask) != -1);
    sa.sa_flags = 0;
    assert(sigaction(SIGINT, &sa, NULL) != -1);

    while(1)
        pause();

    /* sleep(4); */

    return 0;
}
