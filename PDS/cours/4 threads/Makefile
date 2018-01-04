ALL := thread-création
ALL += partage
ALL += visupiles
ALL += adaptation
ALL += adaptation-join
ALL += adaptation-join-res
ALL += adaptation-detach
ALL += thread-exit

ALL += compteurs
ALL += compteurs-sem
ALL += compteurs-mutex
ALL += compteurs-mutex-recursif

ALL += prodcons-mut-cond

ALL += sighandler
ALL += arrêt-thread-sig
ALL += arrêt-thread-cancel

ALL += visupiles-fork

all: $(ALL)

# Édition de liens
%: %.o
	gcc $^ -o $@ -pthread

# Compilation
# L’initialisation des mutex récursifs n’est pas bien normalisée (le
# _NP signifie Non-Posix), on utilise une règle de compilation moins
# stricte (on active les extensions GNU à la norme POSIX)
compteurs-mutex-recursif.o: compteurs-mutex-recursif.c
	gcc -Wall -Wextra -D_GNU_SOURCE -c -g $< -pthread

%.o: %.c
	gcc -Wall -Wextra -ansi -pedantic -D_XOPEN_SOURCE=500 -c -g $< -pthread

%: %.c

clean:
	rm -f $(ALL)

.PHONY: all clean

