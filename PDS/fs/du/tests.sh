#!/bin/bash

test_dir=./testdir

setup()
{
    mkdir $test_dir
    touch $test_dir/fichier.txt
    mkdir $test_dir/home
    mkdir $test_dir/assets
    mkdir $test_dir/assets/js
    touch $test_dir/home/file.txt
    touch $test_dir/assets/js/app.js
}

remove_setup()
{
    rm -rf $test_dir
}

# test when no file specified
test_current_directory()
{
    echo "Test current directory"
    echo "-------- du standard --------"
    du -B 512
    echo "--------     mdu     --------"
    ./mdu
    echo "-----------------------------"
}

# test real size and files specified
test_real_size()
{
    echo "Test real size with file specified"
    echo "Hello Starshine! The Earth says hello!" > $test_dir/fichier.txt
    echo "-------- du standard --------"
    du -B 512 $test_dir/fichier.txt
    echo "--------      mdu     --------"
    ./mdu $test_dir/fichier.txt
    echo "------------------------------"
}

# test apparent size and files specified
test_apparent_size()
{
    echo "Test apparent size with file specified "
    echo "-------- du standard --------"
    du -b $test_dir/fichier.txt
    echo "--------      mdu     --------"
    ./mdu -b $test_dir/fichier.txt
    echo "------------------------------"
}

# test expand directory
test_expand_directory()
{
    echo "Test expand directory"
    echo "var hello = 'Hello World !'" > $test_dir/assets/js/app.js
    echo "-------- du standard --------"
    du -B 512  $test_dir/assets/
    echo "--------      mdu     --------"
    ./mdu $test_dir/assets/
    echo "------------------------------"
}

# test not follow links
test_not_follow_links()
{
    echo "Test not follow links"
    echo "The Graveyard Book" >> $test_dir/fichier.txt
    ln -s $test_dir/fichier.txt $test_dir/mylink
    chmod +x $test_dir/fichier.txt
    echo "-------- du standard --------"
    du -L -b $test_dir/mylink
    echo "--------      mdu     --------"
    ./mdu -L -b $test_dir/mylink
    echo "------------------------------"
}

# test follow links
test_follow_links()
{
    echo "Test follow links"
    echo "The Ocean at the End of the Lane" > $test_dir/home/file.txt
    ln -s $test_dir/home/file.txt $test_dir/assets/linkfile
    echo "-------- du standard --------"
    du -B 512 $test_dir/assets
    echo "--------      mdu     --------"
    ./mdu $test_dir/assets
    echo "------------------------------"
}


main()
{
    setup
    test_current_directory
    test_real_size
    test_apparent_size
    test_expand_directory
    test_follow_links
    test_not_follow_links
    remove_setup
}

main
exit 0
