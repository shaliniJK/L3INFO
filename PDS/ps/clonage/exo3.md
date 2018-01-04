## Exo 3 : Quatre fils

#### Q1. Un programme affiche les entiers de 0 à 3 par 4 processus différents. L'exécution de ce programme se termine après l'affichage des 4 entiers.
```
    int i;
    pid_t pid;

    for (i = 0; i < 4; i++) {
        pid = fork();
        if (pid == -1) {        /* erreur */
            perror("erreur fork");
            exit(EXIT_FAILURE);
        } else if (pid == 0) {  /* fils */
            printf("%d\n",i);
            exit(EXIT_SUCCESS);
        } else {                /* pere */
            wait(NULL);
        }
    }
    exit(EXIT_SUCCESS);
```

#### Q2. Ajouter wait(NULL) dans else.