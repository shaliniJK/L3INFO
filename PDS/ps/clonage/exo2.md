## Exo 2

```
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main() {
    int i;
    pid_t pid;

    for (i = 0; i < 3; i++) {
        pid = fork();
        if (pid == -1) {        /* erreur */
            perror("erreur fork");
            exit(EXIT_FAILURE);
        } else if (pid == 0) {  /* fils */
            fprintf(stderr, "fils : %d\n", i);
        } else {                /* pere */
            fprintf(stderr, "pere : %d\n", i);
        }
    }
    exit(EXIT_SUCCESS);
}
```

#### Q1.
i = 0   fork()  pere    -> pere_0
                        -> child_0
i = 1   fork()  pere_0   -> pere_1
                         -> child_1
                child_0  -> pere_1
                         -> child_1

#### Q2.
            0           1           2
pere    -> pere_0   -> pere_1   -> pere_2
                                -> child_2
                    -> child_1  -> pere_2
                                -> child_2
        -> child_0  -> pere_1   -> pere_2
                                -> child_2
                    -> child_1  -> pere_2
                                -> child_2
Donc 8 processus en tout

#### Q3. 
Ajouter des getpid(), getppid()
```
int i;
    pid_t pid;

    for (i = 0; i < 3; i++) {
        pid = fork();
        if (pid == -1) {        /* erreur */
            perror("erreur fork");
            exit(EXIT_FAILURE);
        } else if (pid == 0) {  /* fils */
            fprintf(stderr, "fils : %d (pid=%d, ppid=%d)\n", i,getpid(), getppid());
        } else {                /* pere */
            fprintf(stderr, "pere : %d (pid=%d, ppid=%d)\n", i,getpid(), getppid());
        }
    }
    exit(EXIT_SUCCESS);
```