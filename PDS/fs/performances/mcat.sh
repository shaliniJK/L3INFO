#! /bin/sh -uf
# mcat -- campagne d'appels à mcat-scd
######################################

# le répertoire de test
mkdir test_dir
touch test_dir/bigfile
chmod +x test_dir/bigfile

# La commande à tester
MCAT=./mcat-scd

# le fichier à lui mettre en entrée
MCAT_INPUT=test_dir/bigfile
dd if=/dev/zero of=$MCAT_INPUT bs=131072 count=4000

# Le fichier de temps à générer
TIME_FILE=mcat-tm.dat

# la commande gnu time
TIME_CMD="/usr/bin/time"
# TIME_CMD=gtime

# les options de cette commande
TIME_OPT="-f %e %U %S"

# initialisation du fichier de résultats
rm -f $TIME_FILE && echo "#buffersize real user sys" > $TIME_FILE

# test with different buffer sizes
for size in `awk 'BEGIN { for( i=1; i<=8388608; i*=2 ) print i }'`;
do
    export MCAT_BUFSIZE=$size
    $TIME_CMD -f "$MCAT_BUFSIZE %e %U %S" $MCAT $MCAT_INPUT > /dev/null 2>> $TIME_FILE
    # $TIME_CMD "$MCAT_BUFSIZE $TIME_OPT" $MCAT $MCAT_INPUT > /dev/null 2>> $TIME_FILE
    # TIME=$($TIME_CMD $TIME_OPT $MCAT $MCAT_INPUT 2>&1)
    # echo "$MCAT_BUFSIZE $TIME" >> $TIME_FILE
done

# generate the graph
gnuplot run.gnu

# supprimer le répertoire de test et le bigfile
rm -rf test_dir

exit 0