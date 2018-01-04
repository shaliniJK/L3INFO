#!/bin/bash

# declare test directory name as variable
test_dir=./testdir

# create directory for tests
setup() {
    mkdir $test_dir
}

# remove directory for tests
remove_setup() {
    rm -rf $test_dir
}

# test for all permissions
test_all_permissions()
{
    echo "Testing for all permissions"
    touch $test_dir/fichier.txt
    chmod 700 $test_dir/fichier.txt
    ./maccess -rwxv $test_dir/fichier.txt && echo "OK" || echo "FAIL"
    rm -f $test_dir/fichier.txt
}

# test for no permissions
test_no_permissions()
{
    echo "Testing for no permissions"
    touch $test_dir/fichier.txt
    chmod 000 $test_dir/fichier.txt
    ./maccess -rv $test_dir/fichier.txt && echo "FAIL" || (
        ./maccess -wv $test_dir/fichier.txt && echo "FAIL" || (
            ./maccess -xv $test_dir/fichier.txt && echo "FAIL" || echo "OK"
        )
    )
    rm -f $test_dir/fichier.txt
}

# test for non-existent file
test_non_existent_file()
{
    echo "Testing for non-existent file"
    ./maccess -rv $test_dir/monfichier && echo "FAIL" || (
        ./maccess -wv $test_dir/monfichier && echo "FAIL" || (
            ./maccess -xv $test_dir/monfichier && echo "FAIL" || echo "OK"
        )
    )
}

# test for only one permission
test_only_xflag_file()
{
    echo "Testing for file with only executable permissions"
    touch $test_dir/fichier.txt
    chmod 100 $test_dir/fichier.txt
    ./maccess -rv $test_dir/fichier.txt && echo "FAIL" || (
        ./maccess -wv $test_dir/fichier.txt && echo "FAIL" || (
            ./maccess -xv $test_dir/fichier.txt && echo "OK" || echo "FAIL"
        )
    )
}

main()
{
    setup
    test_all_permissions
    test_no_permissions
    test_only_xflag_file
    test_non_existent_file
    remove_setup
 }

main
exit