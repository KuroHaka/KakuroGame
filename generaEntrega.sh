
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
cp dades/* ENTREGA/FONTS/dades

#FONTS : tests
rm -r ENTREGA/FONTS/test
cp -r test ENTREGA/FONTS/

# EXE : executables drivers
./Compilacio_drivers.bat
cp Genera_executables_drivers.bat build/exec.bat
rm build/*.jar
cd build
./exec.bat
cd ..
mkdir ENTREGA/EXE/drivers
cp build/*.jar ENTREGA/EXE/drivers
rm build/*.jar
cp scripts_drivers/* ENTREGA/EXE/drivers
mkdir ENTREGA/EXE/drivers/dades
cp dades/* ENTREGA/EXE/drivers/dades

# EXE : executables jocs de prova
mkdir ENTREGA/EXE/tests

#ENTREGA: tar
#S'ha de fer el ZIP de l'entrega. CARPETA ENTREGA
tar -czvf 1aENTREGA_9.2.tar.gz ENTREGA