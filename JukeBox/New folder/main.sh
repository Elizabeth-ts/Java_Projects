#!/bin/sh
javac -cp jl1.0.jar Song.java Database.java JukeBox.java -Xlint:unchecked
ls *mp3 > sonList.txt
jar xf jl1.0.jar