all: créer créer2 environnement

créer: créer.c
	gcc -Wall -Wextra -ansi -pedantic -g créer.c -o créer

créer2: créer2.c
	gcc -Wall -Wextra -ansi -pedantic -D_XOPEN_SOURCE=500 -g créer2.c -o créer2

environnement: environnement.c
	gcc -Wall -Wextra -ansi -pedantic -Wno-unused-parameter -g environnement.c -o environnement
