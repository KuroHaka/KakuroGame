# FONTS : src
rm -r ENTREGA/FONTS/src
cp -r src ENTREGA/FONTS/
# FONTS : scripts drivers
cp Compilacio_drivers.bat ENTREGA/FONTS/
cp Driver_Tauler.bat ENTREGA/FONTS/

# EXE : executables (drivers) de les classes + jocs de prova

# Classe Algoritme
# cp -r src/domini/algoritme/* ENTREGA/EXE/algoritme



#rm -r ENTREGA/FONTS/
# javac arxiu.java
# jar cvf arxiu.jar
# to execute: java -jar arxiu.jar arxiu.class


#ZIP de Entrega
#S'ha de fer el ZIP de l'entrega. CARPETA ENTREGA
tar -czvf 1aENTREGA_9.2.tar.gz ENTREGA