#!/bin/bash

# Test One Command:
./do -a true  || echo "Erreur: and true a renvoyé false"
./do -o true  || echo "Erreur: or true a renvoyé false"

./do -a false  || echo "Erreur: and false a renvoyé true"
./do -o false  || echo "Erreur: or false a renvoyé true"

# Test mode -a && 1 command fails
./do -a true false  || echo "Erreur: and true false a renvoyé true"

# Test mode -a && all commands pass
./do -a true true  || echo "Erreur: and true true a renvoyé false"

# Test mode -o && 1 command fails
./do -o true false  || echo "Erreur: or true false a renvoyé false"
./do -o false false  || echo "Erreur: or false false a renvoyé true"

# Test mode -o && all commands pass
./do -o true true  || echo "Erreur: or true true a renvoyé false"

# Test mode -a -c && one command fails faster than termination of the rest
./do -a -c true false true  || echo "Erreur: and -c true false true a renvoyé true"

# Test mode -a -c && no command fails
./do -a -c true true true  || echo "Erreur: and -c true true true a renvoyé false"

# les cas similaires quand elles n’échouent pas ou que le mode est -o -c ;
./do -o -c true false true  || echo "Erreur: or -c true false true a renvoyé false"

# les cas similaires en ajoutant -k au mode.
./do -a -c -k true true true  || echo "Erreur: and -c -k true true true a renvoyé false"

./do -o -c -k true true true  || echo "Erreur: and -c -k true true true a renvoyé false"

# ./do -o true true  || echo "Erreur: or true true a renvoyé false"
# ./do -o false false && echo "Erreur: or false false a renvoyé true"


exit 0
