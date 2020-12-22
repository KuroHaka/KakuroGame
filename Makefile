OUT = building
MAIN_PATH = src/Main.java
SOURCE_DIR = src
BUILT_JAR = Kakuro.jar
IMAGES = interficie/icones

default: all

all: compile jars

compile:
	javac $(MAIN_PATH) -cp $(SOURCE_DIR) -d $(OUT)
	mkdir $(OUT)/$(IMAGES)
	cp $(SOURCE_DIR)/$(IMAGES)/* $(OUT)/$(IMAGES)

compile-verbose:
	javac $(MAIN_PATH) -verbose -cp $(SOURCE_DIR) -d $(OUT)

jar:
	jar cvfe $(BUILT_JAR) Main -C $(OUT) .

jar-verbose: # TODO 
	echo TODO

clean:
	rm -r $(OUT)
	rm $(BUILT_JAR)
