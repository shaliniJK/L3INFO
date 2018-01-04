#include <assert.h>
#include <stdlib.h>
#include <unistd.h>

/* Petite commande qui crée un fichier ordinaire (vide) temporaire
 * puis un lien physique vers ce même i-nœud et un lien symbolique
 * pointant sur ce fichier temporaire.
 * Finit en supprimant le fichiertemporaire et montre ainsi une des
 * différences entre physique et symbolique */

int main() {
    system("touch fichiertemporaire");

    assert(link("fichiertemporaire", "lien-physique") != -1);
    assert(symlink("fichiertemporaire", "lien-symbolique") != -1);

    assert(unlink("fichiertemporaire") != -1);

    return 0;
}
