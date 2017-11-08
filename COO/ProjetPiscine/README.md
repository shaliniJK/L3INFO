# *Projet Piscine*
---
![Lille 1 - Logo](http://www.univ-lille1.fr/digitalAssets/38/38040_logo-trans.png)

**Jayjaywantee KOODUN**  & **Thi-Ngoc-Anh TRAN**
**L3S5 INFO - Groupe 6**
---

### I. Introduction

L’objectif de ce projet est la réalisation d’une application de simulation d'utilisation de ressources partagées connue sous le nom du *problème de la piscine*, en utilisant les notions générales d'actions et d'ordonnanceur d'actions.


---
### II. HowTo

Assurez vous d’être placé dans la racine de ce répertoire pour l’exécution des commandes suivants.

#### 1. __Récupérer le dépôt git de ce projet__

Clonez ce dépôt avec la commande suivante :
```
$ git clone https://gitlab-etu.fil.univ-lille1.fr/tran/COO.git
```

Si vous avez déjà cloné ce dépôt, faites simplement un `git pull` pour récupérer les dernières modifications.


#### 2. __Compilations et génération de la documentation__

On utilise principalement l’outil `maven` pour compiler le projet et  générer la documentation (que l’on trouve dans le répertoire `target/docs`).

* Compilation : `mvn package`
* Documentation : `mvn javadoc:javadoc`

Enfin, pour supprimer le code compilé, effectuez la commande `mvn clean` .


#### 3. __Execution du programme__

Assurez vous d’avoir bien compilé le projet avec un `mvn package`, ensuite lancez le programme avec la commande suivante :
```
$ java -jar target/ProjetPiscine-1.0-SNAPSHOT.jar

```

---
### III. Explications

Nous avons conçu l'architecture de ce projet en essayant de partager le maximum de code possible pour implémenter les différents types actions.

#### __SOLID__
On a essayé d'appliquer les SOLID principles tant bien que possible afin de pouvoir ajouter d'autres types d'actions sans modifier nos classes existantes.

####  __Design Patterns__

- Composite Pattern :
On a utilisé le Composite Pattern pour concevoir les classes des actions et les schedulers. Le but était de permettre la methode `doStep()` d'être partagée par tous les actions alors que la méthode `reallyDoStep()` serait implémentée selon les types d'actions. De même, la méthode `nextAction()` pour les classes schedulers réflètent cette même ideologie.

- Template Method :
On a utilisé le Template method surtout pour réaliser les classes de test pour les actions, qui contiennent chacune avec une méthode `createAction` (pour tous les types d'actions). Ensuite, uniquement pour les classes de tests scheduler on a la méthode `createScheduler`. Cela permet de tester en effet que tous les actions vérifient au cahier des charges et fonctionnent correctement que ce soit le type de action et scheduler.

---
### IV. Diagramme UML du projet

![Alt text](ProjetPiscineUml.png?raw=true "Uml Project Piscine")

---
### V.  Améliorations possibles et remarques

On aurait pu écrire plus de tests et améliorer la qualité des classes de tests.




