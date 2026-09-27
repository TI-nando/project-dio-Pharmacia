#!/bin/bash
# Compila e executa o projeto sem precisar de Maven.
set -e
cd "$(dirname "$0")"

find src -name "*.java" > sources.txt
rm -rf out
mkdir out

javac -encoding UTF-8 -d out @sources.txt
java -Dfile.encoding=UTF-8 -cp out com.farmacia.Main
