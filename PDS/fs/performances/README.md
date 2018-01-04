#   Performances des entrées/sorties

Ce répertoire correspond aux exercices de la section
[Performances des entrées/sorties](http://www.fil.univ-lille1.fr/~hym/e/pds/tp/tdfs-perfio.html).

Vous éditerez ce fichier pour qu’il contienne un compte-rendu du
travail effectué. Il pourra ainsi contenir des graphiques indiquant
les résultats de vos expériences.


### Travail effectué

 __1__ La fonction `mcat-scd`

 __2__ La fonction `mcat-scs`

 __3__ La fonction `mcat-lib`


### Ce qu'on n'arrive pas à faire

On n'a pas reussi à comparer les temps d'execution du mcat-li et mcat-scs. Et on a pas reussi à faire la fonction mcat-osync.

### Pour tester

Les différentes versions de la commande `cat` fonctionnnent selon l'original. Vous pouvez les tester avec la commande `make`.

Pour executer le test, executer la commande `make test`.



- gnuplot run.gnu

![alt text](./mcat.png?raw=true "run.gnu")

- mcat-tm.dat

```
#buffersize real user sys
1 309.19 27.86 281.12
2 154.03 14.86 139.16
4 80.71 7.26 73.44
8 39.08 3.46 35.61
16 19.58 1.82 17.74
32 9.75 0.86 8.87
64 5.09 0.37 4.71
128 2.49 0.25 2.23
256 1.27 0.06 1.20
512 0.66 0.03 0.62
1024 0.39 0.02 0.36
2048 0.25 0.02 0.22
4096 0.18 0.00 0.18
8192 0.15 0.00 0.15
16384 0.14 0.00 0.14
32768 0.14 0.00 0.13
65536 0.13 0.00 0.13
131072 0.14 0.00 0.14
262144 0.16 0.00 0.16
524288 0.16 0.00 0.16
1048576 0.15 0.00 0.14
2097152 0.20 0.00 0.19
4194304 0.20 0.00 0.20
8388608 0.20 0.00 0.20
```

