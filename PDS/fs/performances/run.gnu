set title "Temps et vitesse d'execution"
set logscale x
set xlabel "Buffer size en octets"
set logscale y
set ylabel "temps d'éxécution"
set style data linespoints
set term png
set output "mcat.png"
plot "mcat-tm.dat" using 1:2 title "real", \
     "mcat-tm.dat" using 1:3 title "user", \
     "mcat-tm.dat" using 1:4 title "sys"
