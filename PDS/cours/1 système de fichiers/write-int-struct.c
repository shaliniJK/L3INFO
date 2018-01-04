#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <assert.h>
#include <unistd.h>
#include <string.h>

/* Commande qui écrit directement des données dans un fichier */
/* Utiliser « od -t x1 fichier » ou d’autres commandes similaires pour
 * visualiser le contenu du fichier tel quel */

struct test_s {
    unsigned int i;
    /* Comparer les deux possibilités suivantes : qu’est-ce qui est
     * écrit dans le fichier */
    char *chaine;
    /* char chaine[1234]; */
} s;

int main(int argc, char *argv[]) {
    int fd, res;

    assert(argc > 1);
    fd = open(argv[1], O_WRONLY | O_CREAT | O_EXCL, 0666);
    assert(fd != -1);

    /* s.i = 0x12345678; */
    s.i = 12345678;

    s.chaine = "abcdefghijklmnopqrstuvwxyz";
    /* strncpy(s.chaine, "abcdefghijklmnopqrstuvwxyz", 1233); */
    /* s.chaine[1233] = '\0'; */

    res = write(fd, &s, sizeof(struct test_s));
    assert(res == sizeof(struct test_s));

    close(fd);

    return 0;
}
