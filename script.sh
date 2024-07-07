#!/bin/bash

if ls *.class 1> /dev/null 2>&1; then
    echo "Cleaning directory..."
    rm *.class
    echo "Compiling..."
    javac Main.java
    echo "Executing Program..."
    java Main
else
    echo "Compiling..."
    javac Main.java
    echo "Executing Program..."
    java Main
fi