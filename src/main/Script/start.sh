#!/bin/bash
cd cedms
mkdir -p foo
git pull
javac -d bin -classpath src/main/java/ src/main/java/ac/cr/tec/ce1103/cedms/App$
java -cp bin ac.cr.tec.ce1103.cedms.App 1234567890123200 7200 client