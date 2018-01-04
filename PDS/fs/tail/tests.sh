#!/bin/bash

test_dir=./testdir

tail_cmd=tail
mtail_cmd=./mtail

mkdir $test_dir
touch $test_dir/file1
chmod +x $test_dir/file1
touch $test_dir/file2
chmod +x $test_dir/file2
touch $test_dir/file3
chmod +x $test_dir/file3

for (( i=1; i<=20; i++ ))
do
echo "$i" >> $test_dir/file1
done


for (( j=1; j<=5; j++ ))
do
echo "$j" >> $test_dir/file2
echo "$j" >> $test_dir/file3
done

echo "\0" >> $test_dir/file3


echo "----------- Files in test directory -------------"
ls -l $test_dir
echo "-------------------------------------------------"
echo ""

# test file without options
echo "----------------------------------"
echo "tail standard (without options)"
tail $test_dir/file1
echo "----------------------------------"
echo "mtail (without options)"
./mtail $test_dir/file1
echo "----------------------------------"
echo ""
diff <($tail_cmd $test_dir/file1) <($mtail_cmd $test_dir/file1)

# Test file with options
echo "----------------------------------"
echo "tail standard, option -n 5"
tail -n 5 $test_dir/file1
echo "----------------------------------"
echo "mtail, option -n 5"
./mtail -n 5 $test_dir/file1
echo "----------------------------------"
echo ""
diff <($tail_cmd -n 5 $test_dir/file1) <($mtail_cmd -n 5 $test_dir/file1)

# test file containing less than number of lines asked for
echo "----------------------------------"
echo "tail standard, option -n 22, file contains less lines than asked"
tail -n 22 $test_dir/file2
echo "----------------------------------"
echo "mtail, option -n 22, file contains less lines than asked"
./mtail -n 22 $test_dir/file2
echo "----------------------------------"
echo ""
diff <($tail_cmd -n 22 $test_dir/file2) <($mtail_cmd -n 22 $test_dir/file2)


# test files of which last byte is an end of line
echo "----------------------------------"
echo "tail standard, last byte of file: end of line"
tail $test_dir/file3
echo "----------------------------------"
echo "mtail, last byte of file: end of line"
./mtail $test_dir/file3
echo "----------------------------------"
echo ""

diff <($tail_cmd $test_dir/file3) <($mtail_cmd $test_dir/file3)

# test files of which last octet is different

# test different types of file


# Test file with options and mtail-simple
echo "----------------------------------"
echo "tail standard, option -n 5"
tail -n 5 $test_dir/file1
echo "----------------------------------"
echo "mtail-simple, option -n 5"
./mtail-simple -n 5 $test_dir/file1
echo "----------------------------------"
echo ""
diff <($tail_cmd -n 5 $test_dir/file1) <(./mtail-simple -n 5 $test_dir/file1)


echo "----------------------------------"
echo "      Test Over"
echo "----------------------------------"

rm -rf $test_dir

exit 0

