CC      = gcc

CFLAGS  = -Wall -Wextra
CFLAGS += -g
CFLAGS += -pthread

LDFLAGS = -pthread

TOUS := aleazard compteur-gc tri-rapide

tout: ${TOUS}

tri-rapide: pile.o tri.o rapide.o main.o
	${CC} ${LDFLAGS} -o $@ $^

%.o: %.c
	${CC} ${CFLAGS} -c $<

%: %.o
	${CC} ${LDFLAGS} -o $@ $^

%: %.c

# Configure des cibles pour l’intégration continue
compteur-gc-ci: CFLAGS+=-Werror
compteur-gc-ci: compteur-gc

tri-rapide-ci: CFLAGS+=-Werror
tri-rapide-ci: tri-rapide

clean:
	rm -f core *.o
	rm -f *.adn res.dat

realclean: clean
	rm -f ${TOUS}

test:
	script -a "session.txt"
	./tests.sh > session.txt

.PHONY: all clean realclean
