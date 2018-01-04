#include <errno.h>
#include <stdlib.h>
#include <string.h>

#define DELIMITERS " \t"

char **makeargv(const char *s) {
    int error;
    int i;
    int numtokens;
    int skip;
    char *t;
    char **argv;

    if (s == NULL) {
        errno = EINVAL;
        return NULL;
    }

    /* Skip initial delimiters */
    skip = strspn(s, DELIMITERS);
    t = strdup(s + skip);
    if (t == NULL)
        return NULL;

    /* count the number of tokens in s */
    numtokens = 0;
    if (strtok(t, DELIMITERS) != NULL)
        for (numtokens = 1; strtok(NULL, DELIMITERS) != NULL; numtokens++);

    /* create argument array for ptrs to the tokens */
    argv = (char **) malloc((numtokens + 1) * sizeof(char *));
    if (argv == NULL) {
        error = errno;
        free(t);
        errno = error;
        return NULL;
    }
    /* insert pointers to tokens into the argument array */
    if (numtokens == 0)
        free(t);
    else {
        strcpy(t, s + skip);
        argv[0] = strtok(t, DELIMITERS);
        for (i = 1; i < numtokens; i++)
            argv[i] = strtok(NULL, DELIMITERS);
    }
    /* put in the final NULL pointer */
    argv[numtokens] = NULL;

    return argv;
}

void freeargv(char **argv) {
    if (argv == NULL)
        return;
    if (*argv != NULL)
        free(*argv);
    free(argv);
}
