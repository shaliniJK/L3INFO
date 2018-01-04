# $@  Le nom de la cible
# $<  Le nom de la première dépendance
# $^  La liste des dépendances

EXEC = multif course observe
CC   = gcc

CFLAGS  = -Wall -Wextra -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

.PHONY: multif course observe clean test
all : $(EXEC)

multif : multif.o
	$(CC) $(LDFLAGS) -o $@ $^

course : course.o
	$(CC) $(LDFLAGS) -o $@ $^

observe : observe.o
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
