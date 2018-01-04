#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>

/* Partage ? */
/* Est-ce que la variable i est partagée entre les deux processus ? */

int main() {
    pid_t pid;
    int i = 123;

    switch(pid = fork()) {
        case -1:
            exit(EXIT_FAILURE);

        case 0:
            /* fils */
            i+=2;
            printf("fils: %d (mon père: %d)\n", getpid(), getppid());
            printf("fils: i = %d (adresse: %p)\n", i, (void*)&i);
            exit(EXIT_SUCCESS);

        default:
            /* père */
            i++;
            printf("père: %d (mon père: %d)\n", getpid(), getppid());
            printf("père: i = %d (adresse: %p)\n", i, (void*)&i);
    }

    return 0;
}
