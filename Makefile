## To get Java JDK 11.* on Ubuntu:
## sudo apt-get install openjdk-11-jdk
##

###################### COMPILACIO .java -> .class ################
OUT = dist# build

JAVAS = \
        src/domini/tauler/*.java \
        src/domini/tauler/casella/*.java \
        src/domini/algoritme/*.java \
        src/interficie/testing/Mock_Presentacio_stdio.java

CLASSES = $(JAVAS:.java=.class)

.SUFFIXES: .java .class
.java.class:
	javac -d $(OUT) -cp src $*.java

compile: $(CLASSES)

###################### EXECUTABLES .class -> .jar ################

COMPILATS = \
        $(OUT)/domini/tauler/*.class \
        $(OUT)/domini/tauler/casella/*.class \
        $(OUT)/domini/algoritme/*.class \
        $(OUT)/interficie/testing/Mock_Presentacio_stdio.class

DRIVERS = \
        $(OUT)/domini/tauler/Driver_tauler.class \
        $(OUT)/domini/tauler/casella/Driver_casella.class \
        $(OUT)/domini/algoritme/*.class

JARS = $(DRIVERS:.class=.jar)

.SUFFIXES: .class .jar
.class.jar:
	jar cvfe $*.jar %class% $(CLASSES)

jars: $(JARS)

test: compile
	java -Djava.io.tmpdir=/tmp
	jar cvfe jars/Driver_casella.jar $(COMPILATS)
	java -Djava.io.tmpdir=/tmp

default: compile jars

clean:
	rm -r dist