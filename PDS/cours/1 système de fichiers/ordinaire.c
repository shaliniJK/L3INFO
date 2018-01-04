#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

/* Commande qui détermine si le chemin donné en argument est un
 * fichier ordinaire */

int main(int argc, char *argv[]) {
    /* struct stat *buf; */
    struct stat st;

    /* assert( vérifier qu’il y a un argument ) */
    assert(argc > 1);

    /* buf = (struct stat *)malloc(sizeof(struct stat)); */
    /* assert(buf != NULL); */

    /* assert(stat(argv[1], buf) != -1); */
    assert(stat(argv[1], &st) != -1);

    /* if(S_ISREG(buf -> st_mode)) */
    if(S_ISREG(st.st_mode))
        printf("Fichier ordinaire\n");

    return 0;
}
