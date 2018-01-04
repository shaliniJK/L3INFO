#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <dirent.h>
#include <stdio.h>
#include <assert.h>
#include <limits.h>

/* Commande qui affiche toutes les entrées d’un répertoire
 * Si une entrée est un lien symbolique, affiche aussi la cible du
 * lien */

int main(int argc, char *argv[]) {
    DIR *rep;
    struct dirent *de;
    struct stat st;
    char chemin[PATH_MAX], chemin2[PATH_MAX];
    int res;

    assert(argc > 1);

    rep = opendir(argv[1]);
    assert(rep != NULL);

    while ((de = readdir(rep)) != NULL) {
        snprintf(chemin, PATH_MAX, "%s/%s", argv[1], de->d_name);

        printf("%s", de->d_name);
        assert(lstat(chemin, &st) != -1);
        if (S_ISLNK(st.st_mode)) {
            res = readlink(chemin, chemin2, PATH_MAX - 1);
            assert(res != -1);
            chemin2[res] = '\0';
            printf(" -> %s\n", chemin2);
        } else
            printf("\n");
    }

    assert(closedir(rep) != -1);

    return 0;
}
