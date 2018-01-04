## Exo 1

```
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main() {
    pid_t pid;

    printf("A: ce message s'affiche combien de fois ?\n");
    fflush(stdout);

    pid = fork();
    if (pid == 0) {
        /* qui suis-je, le pere ou le fils ? */
        printf("je suis le ...\n");
    } else {
        /* qui suis-je, le pere ou le fils ? */
        printf("je suis le ...\n");
    }

    printf("B: ce message s'affiche combien de fois ?\n");
    fflush(stdout);

    exit(EXIT_SUCCESS);
}
```

#### Q1. Repondre aux question dans le code
- A : 1 fois
- si (pid==0) fils
- si (pid!=0) pere
- B : 2 fois

- 2 processus en tout
printf A -> fork()  ->   pid==0 -> printf B
                    ->   pid!=0 -> printf B

#### Q2.
Non, on peut pas déterminer l'ordre d'apparition des messages.

#### Q3.
```
pid_t pid;

    printf("A: ce message s'affiche combien de fois ?\n");
    fflush(stdout);

    pid = fork();
    if (pid == 0) {
        /* qui suis-je, le pere ou le fils ? */
        printf("je suis le ...\n");
    } else {
        /* qui suis-je, le pere ou le fils ? */
        wait(NULL);
        printf("je suis le ...\n");
    }

    printf("B: ce message s'affiche combien de fois ?\n");
    fflush(stdout);

    exit(EXIT_SUCCESS);
```

wait(NULL) ~ attendre la fin du processus fils, 
wait récupère zero (exit 0 du fils) 
=> affiche fils avant, ensuit père

#### Q4.
ajoute une case de -1 -> EXIT_FAILURE

```
pid_t pid;

    printf("A: ce message s'affiche combien de fois ?\n");
    fflush(stdout);

    pid = fork();
    if (pid == 0) {
        /* qui suis-je, le pere ou le fils ? */
        printf("je suis le ...\n");
    } else if (pid > 0) {
        /* qui suis-je, le pere ou le fils ? */
        wait(NULL);
        printf("je suis le ...\n");
    } else {
        printf("Fork error\n");
        exit(EXIT_FAILURE);
    }

    printf("B: ce message s'affiche combien de fois ?\n");
    fflush(stdout);

    exit(EXIT_SUCCESS);
```

#### Q5.
fflush(stdout) pour vider le buffer sortir standard