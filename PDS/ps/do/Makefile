CC      = gcc

CFLAGS  = -Wall -Wextra -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

LDFLAGS = -g

tout: args

# Pour construire le programme binaire « args », il est nécessaire de
# faire l’édition des liens de tous les .o qui contiennent son code
# « args » dépend donc de « args.o » mais aussi de « makeargv.o »
args: makeargv.o args.o

on: on.o

do: do.o makeargv.o

# Règles implicites pour la compilation et l’édition de liens
%.o: %.c
	${CC} ${CFLAGS} -c $<

%: %.o
	${CC} ${LDFLAGS} -o $@ $^

%: %.c

# Règles de nettoyage
clean:
	rm -f core *.o

realclean: clean
	rm -f args

test :
	script -a "session.txt"
	./tests.sh >> session.txt

.PHONY: tout clean realclean
