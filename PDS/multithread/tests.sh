#!/bin/bash

rm -rf testdir
rm *.adn
mkdir testdir

# test on a simple file
echo "Creation de fichier adn de taille 25"

./aleazard 25 > test.adn
echo "Contenue du fichier adn : "
cat test.adn
echo ""
echo "Lancement du compteur avec 3 threads"
./compteur-gc test.adn 3
echo "Ok"

#### Main test

TIME_FILE=res.dat
TIME_CMD="/usr/bin/time"
TIMEFORMAT=%R


# # generate adn files
# for size in `awk 'BEGIN { for( i=1000; i<=1000000000; i*=10 ) print i }'`; do
#     ./aleazard $size > ${size}.adn
# done


# rm -f $TIME_FILE && echo "taille thread temps " > $TIME_FILE

# # do calculations + save into res.dat
# for size in `awk 'BEGIN { for( i=1000; i<=1000000000; i*=10 ) print i }'`; do
#     for nthread in `awk 'BEGIN { for( i=1; i<=64; i*=2 ) print i }'`; do
# 		echo -n "$size $nthread " >> $TIME_FILE

#         ntest=2
# 		sum=0
# 		for i in `awk 'BEGIN { for( i=1; i<=2; i++ ) print i }'`; do
# 			# result_time=`$TIME_CMD "%e" ./compteur-gc ${size}.adn $nthread`
# 			result_time=`time ./compteur-gc ${size}.adn $nthread`

# 			echo "$result_time"

# 			sum=`echo "$sum + $result_time" | bc -l`
# 		done

# 		echo "$sum / $ntest" | bc -l >> res.dat

#     done
# done

rm -rf testdir
rm *.adn