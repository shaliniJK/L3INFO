# Les règles de compilation peuvent utiliser les variables suivantes
# Consultez la documentation de make pour en savoir plus (et en
# trouver d’autres)
#
# $@  Le nom de la cible
# $<  Le nom de la première dépendance
# $^  La liste des dépendances

EXEC = which
CC      = gcc

CFLAGS  = -Wall -Wextra -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

.PHONY: clean
all : $(EXEC)

which: which.o
	$(CC) $(LDFLAGS) -o $@ $^

%.o: %.c
	$(CC) $(CFLAGS) -c $<

clean:
	rm *.o

fclean :
	rm -f $(EXEC)

