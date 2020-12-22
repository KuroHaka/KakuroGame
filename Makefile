OUT = building
MAIN_PATH = src/Main.java
SOURCE_DIR = src
BUILT_JAR = Kakuro.jar
IMAGES = interficie/icones

default: all

all: compile jar

compile:
	javac $(MAIN_PATH) -verbose -cp $(SOURCE_DIR) -d $(OUT)
	mkdir $(OUT)/$(IMAGES)
	cp $(SOURCE_DIR)/$(IMAGES)/* $(OUT)/$(IMAGES)

jar:
	jar cvfe $(BUILT_JAR) Main -C $(OUT) .

clean:
	rm -r $(OUT)
	rm $(BUILT_JAR)
