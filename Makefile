OUT = building
MAIN_PATH = src/Main.java
SOURCE_DIR = src
BUILT_JAR = Kakuro.jar

default: all

all: compile jars

compile:
	javac $(MAIN_PATH) -cp $(SOURCE_DIR) -d $(OUT)

compile-verbose:
	javac $(MAIN_PATH) -verbose -cp $(SOURCE_DIR) -d $(OUT)

jars: # TODO 
	cd $(OUT); \
	jar cvfe ../$(BUILT_JAR) Main

clean:
	rm -r $(OUT)
	rm $(BUILT_JAR)

# javac -d $(OUT) -cp src Main.java
# javac -sourcepath src Main.java