#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#include "makeargv.h"

/* Démonstration de l’utilisation des fonctions makeargv et freeargv */

int main(int argc, char *argv[]) {
    int i;

    for (i = 1; i < argc; i++) {
        /* traiter argv[i] */
        char **cmdargv;
        char **arg;

        /* création du argv de l'argument i */
        cmdargv = makeargv(argv[i]);
        assert(cmdargv != NULL);

        /* test: affichage */
        fprintf(stderr, "[%s]\t%% ", cmdargv[0]);
        for (arg = cmdargv; *arg; arg++)
            fprintf(stderr, "%s ", *arg);
        fprintf(stderr, "\n");

        /* libération mémoire */
        freeargv(cmdargv);
    }

    exit(EXIT_SUCCESS);
}
