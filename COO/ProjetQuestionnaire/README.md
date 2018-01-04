# *Projet Questionnaire*
---
![Lille 1 - Logo](http://www.univ-lille1.fr/digitalAssets/38/38040_logo-trans.png)

**Jayjaywantee KOODUN**  & **Thi-Ngoc-Anh TRAN**
**L3S5 INFO - Groupe 6**
---

### I. Introduction

L’objectif de ce projet est la réalisation d’une Questionnaire à partir d'un fichier text qui contient des questions et les réponses associées.Le Questionnaire peut être exécuté en mode console ou graphique selon le choix de l'utilisateuer.

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
$ java -jar target/ProjetQuestionnaire-1.0-SNAPSHOT.jar

```

---
### III. Explications

#### __SOLID__
On a essayé de minimiser les dépendences entres les différentes classes tant que possible avec les SOLID principles.

####  __Design Patterns__

- Factory Pattern :
On a utilisé le *Factory Pattern* avec les classes *QuestionnaireFactory* et *AnswerPanelFactory*, pour créer des instances des objects questionnaires ou de réponses graphiques.

- Singleton :
Le design pattern *Singleton* a été appliqué dans la classe AnswerFactory pour permettre de simplifier la création des instances de Answers.

- Double Dispatch :
On a utilisé le pattern *Double Dispatch* pour réduire tout dépendence entre la partie graphique d'une réponse (*AnswerPanel*) et le modèle de la réponse (*Answer*). Cela nous a permis en effet d'avoir une méthode *createView(AnswerPanelFactory factory)* dans chaque Answer qui permet au factory de créer le modèle graphique qui convient pour chaque réponse.

---
### IV. Diagramme UML du projet

![Alt text](QuestionnaireUML.png?raw=true "UML Project Questionnaire")

---
### V.  Améliorations possibles et remarques

On aurait pu amélioré la présentation de la partie graphique avec des formes plus attirantes. En ce qui concerne la partie modèle de ce projet, la façon de créer les différentes types d'Answer aurait pu être simplifiée.
On aurait pu inclure plus de tests également et améliorer la qualité des tests.



