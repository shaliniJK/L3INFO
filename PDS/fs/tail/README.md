#   `tail`

Ce répertoire correspond aux exercices de la section
[Afficher la fin d’un
fichier](http://www.fil.univ-lille1.fr/~hym/e/pds/tp/tdfs-cmd.html#tail)
(versions simpliste et efficace de `tail`).

Ce répertoire devra contenir **exclusivement** les fichiers :
`README.md`, `Makefile`, `mtail.c` et soit `tests.sh` soit
`session.txt`.


## Travail effectué

__1__ `mtail_simple.c`

__2__ `mtail.c`


### Ce qu'on n'arrive pas à faire

On n'arrive pas à tester la fonction `mtail` avec les options de type `-N`. Si on récupère bien le N, on n'arrive plus à récuperer les arguments `argv` dans ce cas.

On n'arrive pas à bien faire valider le `mtail`. En effet, on a des segmentation faults qu'on ne sait plus comment corriger.

Vous pouvez retrouvez un tracé des résultats dans le fichier `session.txt`, généré avec la commande `make test`.


### Pour tester
Vous pouvez compiler les sources avec la commande `make`.

Pour executer le test, executer la commande `make test`. Le script `tests.sh` sera lancé automatiquement.


### Version utile de tail

La méthode de lire la fin du fichier ne fonctionne que pour des descripteurs de fichiers dont on peut positionner le curseur avec lseek (on ne peut pas faire¸ ça avec le clavier, les tubes, et les FIFOS en général).

On peut implémenter une fonction `tail_fifo` qui fonctionne quand fd est un descripteur
d’une fifo et utilisere pour cela un tourniquet à nb_entrées pour y conserver les dernières nb lignes lues.