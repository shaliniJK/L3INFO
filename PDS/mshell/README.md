#   Gestionnaire de travaux

Ce dépôt correspond au TP de PDS
« [mshell](http://www.fil.univ-lille1.fr/~hym/e/pds/tp/tdjobs.html) ».

Ce répertoire contient **exclusivement** les fichiers :
-   un `Makefile` ;
-   `mshell.c` : contient entre autres le `main()` ;
-   `cmd.c`, `cmd.h` : contient les commandes `fg`, `bg`, `stop`...
-   `jobs.c`, `jobs.h` : contient la bibliothèque gérant les
    structures de données associées aux jobs ;
-   `sighandlers.c`, `sighandlers.h` : contient les traitants de
    signaux ;
-   `common.c`, `common.h` : contient des données et fonctions
    communes aux différents fichiers.


## Binomes : *Jayjaywantee Koodun* et *Thi-Ngoc-Anh TRAN*

## Travail effectué
- `pipe.c`
- `sighandlers.c`
- `cmd.c`

## Ce qui marche

On a réalisé tout ce qui était demandé dans ce TP.

Le mshell fonctionne bien, sauf un peut lent lorsqu'on enchaîne multiples commandes.

