ALL := ordinaire
ALL += ls liens mkdir
ALL += cp cat seek seek2 write-printf
ALL += printf-null close-stdout write-int-struct

all: $(ALL)

ordinaire: ordinaire.c
	gcc -Wall -Wextra -ansi -pedantic -g $< -o $@

%: %.c
	gcc -Wall -Wextra -ansi -pedantic -D_XOPEN_SOURCE=500 -g $< -o $@

clean:
	rm -f $(ALL)

.PHONY: all clean
