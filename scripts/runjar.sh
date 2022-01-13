#!/bin/sh


export PATH
set -e


cd "$(dirname "${0}")"
cd ./../
cd ./target

find . -type f -name "kronos*.jar" -exec java -jar {} +
