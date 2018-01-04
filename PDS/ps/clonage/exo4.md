## Exo 4 : tri fourche

```
void trif (void(*f1)(void), void(*f2)(void), void(*f3)(void)) {
    switch(fork()) {
        case -1 :
            perror("error");
            exit(EXIT_FAILURE);
        case 0 :
            f1();
            exit(EXIT_SUCCESS);
    }

    switch(fork()) {
        case -1 :
            perror("error");
            exit(EXIT_FAILURE);
        case 0 :
            f2();
            exit(EXIT_SUCCESS);
    }

    switch(fork()) {
        case -1 :
            perror("error");
            exit(EXIT_FAILURE);
        case 0 :
            f3();
            exit(EXIT_SUCCESS);
    }
    wait(NULL);     /* annonce au père que fils 1 est fini */
    wait(NULL);     /* annonce au père que fils 2 est fini */
    wait(NULL);     /* annonce au père que fils 3 est fini */
}

static void f(int seconds, const char *fname) {
    sleep(seconds);
    fprintf(stderr, "Fonction %s() exécutée par le processus "
            "de pid %d\n", fname, getpid());
}

static void fa() { f(4, "fa"); }
static void fb() { f(2, "fb"); }
static void fc() { f(3, "fc"); }

int main() {
    trif(fa, fb, fc);
    fprintf(stderr, "terminaison de main()\n");

    exit(EXIT_SUCCESS);
}

```