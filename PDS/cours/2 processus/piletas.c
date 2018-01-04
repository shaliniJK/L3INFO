#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

/* gdb
 * complétion des commandes (tab)
 * - break (numéro de ligne, nom de fonction, etc.)
 * - backtrace
 * - frame
 * - print / display <expr>
 * - info (proc, frame, variables, etc.)
 * - run, next, step, continue
 */

/* comparer les positions en mémoire des varlocale des différents
 * appels récursifs imbriqués */
void f(int n) {
    int varlocale = 0x123456;

    varlocale++;

    if (n > 0) f(n-1);
}

int globale = 10;

int main() {
    char * buff;
    char c;

    /* comparer la « carte » de la mémoire avant et après malloc */
    buff = (char*) malloc(0x456 * sizeof(char));

    c = buff[0];
    buff[1] = c;

    f(3);

    return 0;
}

