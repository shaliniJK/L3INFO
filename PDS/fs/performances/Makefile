# Les règles de compilation peuvent utiliser les variables suivantes
# Consultez la documentation de make pour en savoir plus (et en
# trouver d’autres)
#
# $@  Le nom de la cible
# $<  Le nom de la première dépendance
# $^  La liste des dépendances

EXEC = mcat-scd mcat-scs mcat-lib mcat-osync
CC      = gcc

CFLAGS  = -Wall -Wextra -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

.PHONY: mdu clean test
all : $(EXEC)

mcat-scd : mcat-scd.o
	$(CC) $(LDFLAGS) -o $@ $^

mcat-scs : mcat-scs.o
	$(CC) $(LDFLAGS) -o $@ $^

mcat-lib : mcat-lib.o
	$(CC) $(LDFLAGS) -o $@ $^

mcat-osync : mcat-osync.o
	$(CC) $(LDFLAGS) -o $@ $^

%.o: %.c
	$(CC) $(CFLAGS) -c $<

clean:
	rm *.o

fclean :
	rm -f $(EXEC)

test :
	./mcat.sh
