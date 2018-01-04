# Signaux
ALL := sigaction multiraise
ALL += bombarde-emet bombarde-ecoute
ALL += sourdeoreille-hdlr-int sourdeoreille-hdlr-tstp
ALL += sourdeoreille-ign-int sourdeoreille-mask-int
ALL += sourdeoreille-hdlr-autres
ALL += enfants

# Tubes
ALL += tube-pere-fils
ALL += tube-sync
ALL += tube-boucle-rw
ALL += tube-read-infini
ALL += tube-read-close
ALL += tube-read-sig
ALL += tube-write-dans-vide
ALL += tube-write-dans-vide-pb
ALL += tube-write-close
ALL += tube-write-sig
ALL += tube-write-sigign
ALL += tube-seek
ALL += tube-entrelacements
ALL += fifo

# Synchronisation
ALL += sync-mmap
ALL += sync-mmap-fork
# Les deux exemples suivants sont les plus importants sur ce thème !
ALL += sync-mmap-pb
ALL += sync-mmap-sem
ALL += sync-mmap-tube
ALL += sync-prodcons
ALL += sync-philosophes
ALL += sync-sighandler
ALL += sync-sighandler-sem
ALL += sync-sighandler-mask
ALL += sync-flock-ecri
ALL += sync-flock-lect
ALL += Synch.class

# Sockets
# Où on voit que les sockets réseaux, après la mise en place (un peu
# fastidieuse), sont utilisables très naturellement comme des tubes ou
# des fichiers
ALL += socket-serveur socket-client

all: $(ALL)

%: %.c
# On change les arguments pour la compilation des exemples :
# - l’utilisation des sémaphores demande la bibliothèque pthread, d’où
#   l’option -pthread à la fin de la ligne
# - l’option MAP_ANONYMOUS de mmap n’est pas disponible en
#   _XOPEN_SOURCE=500, active _GNU_SOURCE à la place (active bien plus
#   d’extension que juste _XOPEN_SOURCE=500, mais c’est le seul usage
#   dans les exemples vus)
	gcc -Wall -Wextra -ansi -pedantic -Wno-unused-parameter -D_GNU_SOURCE -g $< -o $@ -pthread

%.class: %.java
	javac $^

clean:
	rm -f $(ALL)

.PHONY: all clean
