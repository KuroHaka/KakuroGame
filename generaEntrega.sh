# FONTS : src
rm -r ENTREGA/FONTS/src
cp -r src ENTREGA/FONTS/
# FONTS : scripts drivers
rm -r ENTREGA/FONTS/build
cp Compilacio_drivers.bat ENTREGA/FONTS/
cp Execucio_drivers.bat ENTREGA/FONTS/
#FONTS : dades
rm -r ENTREGA/FONTS/dades
mkdir ENTREGA/FONTS/dades
cp dades/exemple.txt ENTREGA/FONTS/dades
#FONTS : tests
# rm -r ENTREGA/FONTS/test
# cp -r test ENTREGA/FONTS/

# EXE : executables (drivers) de les classes + jocs de prova
# Classe Algoritme
# cp -r src/domini/algoritme/* ENTREGA/EXE/algoritme

#ENTREGA: tar
#S'ha de fer el ZIP de l'entrega. CARPETA ENTREGA
tar -czvf 1aENTREGA_9.2.tar.gz ENTREGA