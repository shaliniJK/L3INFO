#   `du`

Ce répertoire correspond aux exercices de la section
[Parcours d’une hiérarchie](http://www.fil.univ-lille1.fr/~hym/e/pds/tp/tdfs-cmd.html#du).

Ce répertoire contient les fichiers :
`README.md`, `Makefile`, `mdu.c` et soit `tests.sh` soit
`session.txt`.


### Travail effectué

1. La fonction `du_file`
2. Le script `tests.sh`

### Travail non effectué

On arrive pas à tester l'option `-L` avec les tests que l'on a écrit depuis le fichier `tests.sh` parce que ça se termine par une erreur à chaque fois.
Toutefois si on teste un lien symbolique manuellement, on arrive bien à distinguer que les valeurs sont différentes selon qu'on a choisit les options `-L -b`.


### Tests

Le script `tests.sh` contient des commandes pour tester la fonction `mdu`.

Pour lancer les tests, effectuer la commande suivante:

````
$ make test
````

Pour tester les liens, effectuer les commandes suivantes:

````
$ ln -s Makefile mylink
$ ./mdu -L mylink
$ ./mdu Makefile
$ ./mdu -b Makefile
$ ./mdu -b -L mylink
````



### Explications

Le fichier `session.txt` qui est produit après l'éxécution de la commande `make test` permet de visualiser le résultat de ce qui est produit sur le terminal en éxécutant le script `tests.sh`.

### Réponse à l'exercice 14

Le calcul à plusieurs fois de la taille d'un noeud ayant plusieurs références dans une hiérarchie est génant parce que c'est inutile de faire le même calcul à chaque fois et cela ne fait qu'augmenter le temps de calcul.

On peut résoudre cette situation en mémorisant dans un tableau une structure qui décrit la taille et l'inoeud des noeuds que l'on a déjà rencontré lors de la calcul de taille d'un fichier désigné.

