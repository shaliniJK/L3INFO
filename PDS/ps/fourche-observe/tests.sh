#!/bin/bash

cmd=./multif

echo "----------- Multif -------------"

echo "Test: ./multif true "
$cmd true

echo "Test: ./multif false "
$cmd false

echo "Test: ./multif true true "
$cmd true true

echo "Test: ./multif true false"
$cmd true false

echo "Test: ./multif false false "
$cmd false false

echo "Test: ./multif false false false "
$cmd false false false

echo "Test: ./multif true true false "
$cmd true true false

echo "Test: ./multif true true true "
$cmd true true true
echo "--------------------------------"


echo "----------- Course -------------"

echo "Test: ./course "
./course
