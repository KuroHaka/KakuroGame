## To get Java JDK 11.* on Ubuntu:
## sudo apt-get install openjdk-11-jdk
##

OUT = build

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
	jar cvfe ../Driver_Tauler.jar domini.tauler.Driver_tauler $(COMPILATS)
	cd $(OUT); \
	jar cvfe ../Driver_Casella.jar domini.tauler.casella.Driver_Casella $(COMPILATS)
	cd $(OUT); \
	jar cvfe ../Driver_Algoritme.jar domini.algoritme.Driver_Algoritme $(COMPILATS)
	cd $(OUT); \
	jar cvfe ../Driver_Combinacions.jar domini.algoritme.Driver_Combinacions $(COMPILATS)

###################### DEFAULTS I ALTRES ########################

default: all

all: compile jars

clean:
	rm -r $(OUT)
	rm Driver_Tauler.jar
	rm Driver_Casella.jar
	rm Driver_Algoritme.jar
	rm Driver_Combinacions.jar

