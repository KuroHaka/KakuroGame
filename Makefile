## To get Java JDK 11.* on Ubuntu:
## sudo apt-get install openjdk-11-jdk
##

OUT = dist

entrega: compile jars

###################### COMPILACIO .java -> .class ################

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
        domini/tauler/*.class \
        domini/tauler/casella/*.class \
        domini/algoritme/*.class \
	presistencia/Dades.class \

jars: compile
	cd $(OUT); \
	jar cvfe ../Driver_tauler.jar domini.tauler.Driver_tauler $(COMPILATS)
	cd $(OUT); \
	jar cvfe ../Driver_casella.jar domini.tauler.casella.Driver_casella $(COMPILATS)
	cd $(OUT); \
	jar cvfe ../Driver_Algoritme.jar domini.algoritme.Driver_Algoritme $(COMPILATS)

###################### DEFAULTS I ALTRES ########################

default: all

all: compile jars

clean:
	rm -r dist
	rm Driver_tauler.jar
	rm Driver_casella.jar
	rm Driver_Algoritme.jar





