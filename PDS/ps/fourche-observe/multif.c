#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <assert.h>
#include <sys/types.h>
#include <sys/wait.h>

typedef int (*func_t) (char *);
int multif (func_t f[], char *args[], int n);

static func_t *functions;
static char* *arguments;

int testfunc(char* value)
{
    if (strcmp(value, "true") == 0)
        return EXIT_SUCCESS;
    if (strcmp(value, "false") == 0)
        return EXIT_FAILURE;
    return EXIT_FAILURE;
}

int multif (func_t f[], char *args[], int n)
{
    int i;
    int status;
    pid_t pid;
    int result = EXIT_FAILURE;

    for (i = 0; i < n; i++) {
        pid = fork();

        switch(pid)
        {
            case -1:
                fprintf(stderr, "%s\n", "Fork error");
                perror(NULL);
                exit(EXIT_FAILURE);
            case 0:
                exit(f[i](args[i]));
            /*default:
                break;*/
        }
    }

    for (i = 0; i < n; i++) {
        if (wait(&status) == -1) {
            perror("wait() failure ");
            exit(EXIT_FAILURE);
        }
        if (WEXITSTATUS(status) == EXIT_FAILURE) {
            return EXIT_FAILURE;
        } else
            result = result & WEXITSTATUS(status);
        /*if (WIFEXITED(status) && WEXITSTATUS(status)) {
            result = result & WEXITSTATUS(status);
        }*/
    }
    return result;
}


int main(int argc, char** argv)
{
    int i;
    int result;

    if (argc < 2) {
        printf("Arguments missing !");
        exit(EXIT_FAILURE);
    }

    functions = (func_t*)malloc((argc - 1) * sizeof(func_t));
    assert(functions != NULL);
    arguments = (char**)malloc((argc - 1) * sizeof(char*));
    assert(arguments != NULL);

    for (i = 0; i < argc - 1; i++) {
        functions[i] = testfunc;
        arguments[i] = argv[i+1];
    }

    result = multif(functions, arguments, argc - 1);
    printf("Result : %i\n", result);
    free(functions);
    free(arguments);
    return result;
}
