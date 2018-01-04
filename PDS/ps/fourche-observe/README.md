#   `multif`, course et observation

Ce répertoire correspond à l’exercice `multif` de la section
[Clonage de processus](http://www.fil.univ-lille1.fr/~hym/e/pds/tp/tdps-fork.html#multif)
et aux exercices de la section
[Gestion de processus](http://www.fil.univ-lille1.fr/~hym/e/pds/tp/tdps-ps.html).

Ce répertoire contient **exclusivement** les fichiers :
`README.md`, `Makefile`, `multif.c`, `course.c`, `observe.c` et
`tests.sh`.

Vous éditerez ce fichier pour qu’il contienne un compte-rendu du
travail effectué (qu’est-ce qui marche, qu’est-ce qui ne marche pas,
etc.).

## Binomes : *Jayjaywantee Koodun* et *Thi-Ngoc-Anh TRAN*


## Travail effectué
- `multif.c`
- `course.c`
- `observe.c`


## Ce qui marche

On a réalisé tout ce qui était demandé dans ce TP.

Le programme `multif.c` marche. Le programme `course.c` permet de créer de 10 processus fils qui affichent chacun un message, et on observe à la fin lequel des processus termine dans quel ordre.


## Exercice 8 : *Observation de processus*

__Q 8.1__
> Ce programme permet d'afficher les processus actifs du terminal. Ensuite, après chaque 5 secondes les processus fils affichent chacun un message avec son PID respectif.

__Q 8.2__ Que se passe-t-il si vous tuez le processus père ?
> Les processus fils sont récuperés par le père du père et sont toujours en exécution.

__Q 8.2__
> On peut voir que le nombre de processus actifs affiché avec la commande `ps` diminue par un quand on tue un processus fils.



