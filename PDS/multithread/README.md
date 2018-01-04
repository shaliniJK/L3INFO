#   Processus légers

Ce dépôt correspond au TP de PDS « [Applications à des exemples
concrets](http://www.fil.univ-lille1.fr/~hym/e/pds/tp/tdth-concrets.html) ».


## *Jayjaywantee KOODUN* et *Thi-Ngoc-Anh TRAN*


##  Contenu initial du dépôt

Ce répertoire contient deux squelettes de code.


### Calcul du taux G / C

`compteur-gc.c`
:   base pour le compteur de bases G et C

`aleazard.c`
:   générateur d’un « génome » aléatoire


### Tri rapide multithreadé

`pile.[ch]`
:   implémentation simple d’une pile,

`tri.[ch]`
:   fonctions de base permettant de charge ou afficher un tableau
    ainsi que vérifier qu’un tableau est trié,

`rapide.[ch]`
:   implémentation du tri rapide séquentiel, où vous ajouterez votre
    implémentation multithreadé,

`main.[ch]`
:   une fonction `main`, avec un `getopt` pour toute une série
    d’options qui vous aideront à tester et mettre au point votre
    code.



### Ce qu'on fait
- `compteur-gc.c`

On a fait la fonction `compteur-gc`. On peut tester la fonction avec le fichier `tests.sh` qui dirige la sortie des tests dans un fichier `session.txt`.


- `rapide.c`

On a écrit la fonction rapide mais on arrive pas à la fonctionner.



### Ce qui ne marche pas

Le programme `compteur-gc` ne compile pas avec l'integration continue sur gitlab.

On n'arrive pas à générer la courbe de temps pour la partie experimentation. On a inclut quand même en commentaire le code pour générer la courbe de temps dans le `tests.sh`

Le fonction rapide ne fonctionne pas.