#!/bin/bash
set -e
rm -rf src/dungeoncrawlerbasic/*.class
mkdir -p build/run
javac -d build/run src/dungeoncrawlerbasic/*.java
CLASSPATH=build/run java dungeoncrawlerbasic.DungeonCrawlerBasic

