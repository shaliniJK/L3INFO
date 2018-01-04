# Les règles de compilation peuvent utiliser les variables suivantes
# Consultez la documentation de make pour en savoir plus (et en
# trouver d’autres)
#
# $@  Le nom de la cible
# $<  Le nom de la première dépendance
# $^  La liste des dépendances

EXEC = mtail mtail-simple
CC      = gcc

CFLAGS  = -Wall -Wextra -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

.PHONY: mtail mtail-simple clean test
all : $(EXEC)

mtail : mtail.o
	$(CC) $(LDFLAGS) -o $@ $^

mtail-simple : mtail-simple.o
	$(CC) $(LDFLAGS) -o $@ $^

%.o: %.c
	$(CC) $(CFLAGS) -c $<

clean:
	rm *.o

fclean :
	rm -f $(EXEC)

test :
	script -a "session.txt"
	./tests.sh >> session.txt
