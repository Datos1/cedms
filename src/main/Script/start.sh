#!/bin/bash
cd cedms
git pull origin server
javac -d bin -classpath src/main/java/ src/main/java/ac/cr/tec/ce1103/cedms/App.java
java -cp bin ac.cr.tec.ce1103.cedms.App 1234567890123200 7200 client
