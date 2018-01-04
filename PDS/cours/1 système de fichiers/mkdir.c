#include <assert.h>
#include <sys/stat.h>
#include <sys/types.h>

/* Petite commande qui crée un répertoire (comme la commande mkdir
 * elle-même, d’ailleurs) */

int main(int argc, char *argv[]) {
    assert(argc > 1);

    assert( mkdir(argv[1], S_IRUSR | S_IWUSR | S_IXUSR |
                           S_IRGRP | S_IWGRP | S_IXGRP |
                           S_IROTH | S_IWOTH | S_IXOTH ) != -1);
    /* Ces droits peuvent être limités par umask (voir la page de
     * manuel de mkdir et celle d’umask */

    return 0;
}
