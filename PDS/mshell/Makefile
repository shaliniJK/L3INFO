# mshell - a job manager
CC      = gcc

CFLAGS  = -Wall -Wextra -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

LDFLAGS = -g

EXEC    = mshell
MODULES = common sighandlers jobs cmd pipe mshell
OBJS    = $(addsuffix .o, $(MODULES))

all: $(EXEC)

mshell: $(OBJS)

# Compile mshell failing on warnings
strict: CFLAGS+=-Werror -Wno-unused
strict: mshell

# gcc -MM generated dependencies
cmd.o: cmd.c jobs.h common.h
common.o: common.c
jobs.o: jobs.c jobs.h common.h
pipe.o: pipe.c pipe.h jobs.h common.h
mshell.o: mshell.c cmd.h sighandlers.h jobs.h common.h pipe.h
sighandlers.o: sighandlers.c jobs.h common.h sighandlers.h

# Implicit rules
%.o: %.c
	${CC} ${CFLAGS} -c $<

%: %.o
	${CC} ${LDFLAGS} -o $@ $^

%: %.c

clean:
	rm -f core *.o

realclean: clean
	rm -f mshell

.PHONY: all clean realclean strict
