set logscale x
set logscale y
set dgrid3d 30,30
set pm3d

set term png
set output "res.png"
splot "res.dat" using 1:2:3 with lines
