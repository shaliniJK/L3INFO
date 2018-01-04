#   `maccess`

Ce répertoire correspond aux exercices de la section
[Vérifier les droits d’accès... et expliquer](http://www.fil.univ-lille1.fr/~hym/e/pds/tp/tdfs-cmd.html#access).

Ce répertoire contient **exclusivement** les fichiers :
`README.md`, `Makefile`, `prlimit.c`, `maccess.c` et `tests.sh`.



### Travail effectué

1. La fonction `prlimit`
2. La fonction `maccess`
3. Le script `tests.sh`

On a fait tout ce qui était demandé dans ce TP et les fonctions `prlimit` et `maccess` fonctionnent bien.

### Tests

Le script `tests.sh` contient des commandes pour tester la fonction `maccess`.
L'option verbose (-v) est toujours selectionnée dans les exemples que nous avons mis.

Pour lancer les tests, effectuer la commande suivante:

````
$ make test
````

Cette commande crée le fichier `session.txt` dans lequel vous retrouverez les résultats du script dans le fichier à la fin de la session, soit en frappant `Ctrl-D` sur le clavier ou en éxécutant `exit` sur le terminal.


### Explications

Le fichier `session.txt` qui est produit après l'éxécution de la commande `make test` permet de visualiser le résultat de ce qui est produit sur le terminal en éxécutant le script `tests.sh`.

Nous n'avons toutefois pas pu produire toutes les erreurs pouvant causant l'échec de la fonction `access()`. Les erreurs ayant les codes `ELOOP` et `ENAMETOOLONG` n'ont pu être produites parce que les exemples ne contiennent pas de _pathname_ qui est trop long ou qui inclue trop de liens symboliques.

