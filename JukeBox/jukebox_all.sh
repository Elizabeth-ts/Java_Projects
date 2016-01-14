#!/bin/sh
# Takes Command line argument of the class to be executed
echo "Starting compilation..."
javac -cp   jl1.0.jar Song.java Database.java Jukebox.java -Xlint:unchecked
echo "Compilation Complete."
echo
echo "Execution"
echo "========="
java  -cp .:jl1.0.jar $1
