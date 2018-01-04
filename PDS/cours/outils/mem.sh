#!/bin/sh

align() {
    if which column > /dev/null
    then column -s ' ' -t
    else cat
    fi
}

if [ -z "$1" ]; then
    echo "Usage: mem.sh <pid>"
    echo "Affiche une « carte » de la mémoire virtuelle du processus <pid>"
    exit 1
fi

sed 's/  */ /g' /proc/"$1"/maps | cut -d ' ' -f 1,2,6 | align
