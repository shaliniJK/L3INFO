ALL := fork partage1 partage2
ALL += fork2 fork-exit exec execv
ALL += dup close-exec
ALL += piletas
ALL += minisystem

all: $(ALL)

%: %.c
	gcc -Wall -Wextra -ansi -pedantic -D_XOPEN_SOURCE=500 -g $< -o $@

clean:
	rm -f $(ALL)

.PHONY: all clean

