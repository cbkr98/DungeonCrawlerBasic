#!/bin/bash
set -e
javac src/dungeoncrawlerbasic/*.java
CLASSPATH=src java dungeoncrawlerbasic.DungeonCrawlerBasic

