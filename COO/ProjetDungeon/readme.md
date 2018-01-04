# *Projet Donjon*
---
![Lille 1 - Logo](http://www.univ-lille1.fr/digitalAssets/38/38040_logo-trans.png)

**Jayjaywantee KOODUN**  & **Thi-Ngoc-Anh TRAN**
**L3S5 INFO - Groupe 6**
---

### I. Introduction

L’objectif de ce projet est la réalisation d’un ~jeu de donjon~, inspiré du fameux jeu ~Dungeons & Dragons~.
Le joueur s’aventure dans un donjon qui est peuplé de monstres et tente de traverser le donjon pour trouver la salle de sortie.

> En tant que joueur, vous serez amené à réaliser des choix tout au long du jeu pour traverser les salles du donjon et interagir avec les Monsters et les Items qui s'y trouvent. Vous avez autant de chances de sortir vivant que de perdre sa vie dans le donjon.


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
$ java -jar target/COO-dungeon-1.0-SNAPSHOT.jar
```

---
### III. Explications

On a réalisé ce projet tout en essayant de respecter au mieux les *SOLID* principles, l’objectif étant de réaliser un jeu avec des possibilités qu’on peut enrichir.

 #### __SOLID__

La conception actuel des interfaces  `Action`, `Item` et la classe `Monster`   permet d’ajouter une nouvelle Action ( `Action Rest` ), un nouveau Item ( `Item Weapon` ) ou plusieurs nouveaux types de `Monster` sans que l’on change l’implantation actuelle des classes qui existent déjà.

On a écrit des méthodes simples ayant des responsabilités uniques autant que possible dans nos classes.

Pour simplifier et par manque de temps, nos sous-classes de `Monster` créent des Monster avec des valeurs qu’on a “hardcode” en quelque sort. Il est cependant tout à fait possible, en gardant en tête la principe du polymorphisme, d’ajouter des méthodes d’instantiation différentes pour les Monster dans les sous classes. Cela nous permettrai d’avoir un `Monster Witch` , par e.g, avec des différents nombres de vie ou force.
Les classes sont de cette façon extensibles sans qu’on vient modifier les méthodes.


 #### __Design Patterns__

-  Factory :

Le Factory Pattern nous a permis de créer des instances des objects `Room`, `Item` et `Monster` plus rapidement, et surtout sans exposer la logique d’instantiation d’un objet dans une classe qui utilise l’objet.
Aussi, cela nous évite de devoir aller changer manuellement chaque ligne de code avec un `new Object(...)`  si on modifie la classe de cet objet.

    -  *Simple Factory*
Un simple Factory dans notre jeu est le `RoomFactory` qui nous permet de créer des instances de Room.

    - *Abstract Factory*
Les interfaces  `MonsterFactory` avec sa méthode `CreateMonster` , et `ItemFactory` avec sa méthode `CreateItem`  sont des  exemples parfaits des *abstract factory*.
On a ainsi plusieurs sous-classes de  `MonsterFactory` pour créer les différentes instances de Monster (Witch, Bokoblin, …) avec leurs factory dédiés (WitchFactory, BokoblinFactory, …).
De même, on a des Factory pour chaque type d’Item que l’on peut ajouter dans le jeu.


- Template Method :
On a utilisé le Template method surtout pour les classes de test.

---
### IV. Diagramme UML du projet

![alt text](./Uml_dungeon.png?raw=true "Projetct Dungeon")




---
### V.  Améliorations possibles et remarques

- __Améliorations envisagées__
	1. Gestion d’inventaire - Inventory Object
On peut ajouter un objet Inventory pour stocker les Items que le Player récupère dans les salles s’il n’a point envie de les utiliser immédiatement.

	2. Max life, health and gold possible to carry
On envisageait aussi d’ajouter des attributs final et static à la classe `Player` pour limiter les valeurs d’attributs `Gold, LifePoints, StrengthPoints` du Player à des valeurs maximums qu’il peut atteindre.

	3. Ajouter des Item Weapon pour combattre les Monster

- __Remarques sur ce qu’on aurait pu mieux travailler__

1. L’implantation du `OneArmedBandit` aurait pu être mieux pour permettre la création aléatoire d’un `Item`.

2. Il se peut qu’on aurait pu utiliser le *Template pattern*  pour simplifier encore la méthode `execute()`  des classes Action.

3. On aurait pu mieux concevoir les classes de tests avec le *Template pattern* et utiliser le concept des *Mock Object* pour tester si le jeu est conforme au le cahier des charges précisé pour ce jeu.

Au niveau des tests, on n'arrive pas à tester la méthode `execute(Player)` des classes `Action` et la méthode `act()` de la classe `Player`. On aurait pu faire les tests beaucoup plus simple que ce qu'ils sont à présent.


