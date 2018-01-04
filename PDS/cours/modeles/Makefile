### Modèle pour vos Makefile

## Compilateur C et éditeur de liens
CC      = gcc

## Options pour la compilation .c -> .o
CFLAGS  = -Wall -Wextra -ansi -pedantic
#  Spécifie la version de la norme POSIX à respecter
CFLAGS += -D_XOPEN_SOURCE=500
#  Active les informations de débogage
CFLAGS += -g

## Options de l’édition de liens (*.o -> binaire)
LDFLAGS  = -g

## Première cible
#  « make » sans argument construit sa première cible
#  Indiquez en dépendance le ou les binaires à construire
all: exemple


## Compilation séparée
#  Le .o doit être recompilé dès que le .c ou le .h (s'il existe) change
#  Comme la 1re règle implicite ne s’applique pas s'il n'y a pas de
#  .h, on en donne une variante sans le .h
%.o: %.c %.h
	${CC} ${CFLAGS} -c $<
%.o: %.c
	${CC} ${CFLAGS} -c $<

## Édition de liens
#  Définissez une règle par binaire que vous voulez créer
#  La commande devrait en général être identique pour chaque binaire
exemple: a.o b.o
	${CC} ${LDFLAGS} -o $@ $^

clean:
	rm *.o || true

realclean: clean
	rm exemple || true

## Liste des pseudo-cibles
#  Ces cibles ne correspondent pas à des fichiers que l'on veut créer,
#  juste à des séquences que l'on veut pouvoir déclencher
.PHONY: all clean realclean
