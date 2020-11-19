# FONTS : src
rm -r ENTREGA/FONTS/src
cp -r src ENTREGA/FONTS/
cp Makefile ENTREGA/FONTS/
# EXE : executables drivers
make clean
make all
rm ENTREGA/EXE/algoritmes
rm ENTREGA/EXE/tauler
rm ENTREGA/EXE/casella
mkdir ENTREGA/EXE/algoritmes
mkdir ENTREGA/EXE/tauler
mkdir ENTREGA/EXE/casella
# EXE : executables jocs de prova
# ENTREGA: tar
# S'ha de fer el ZIP de l'entrega. CARPETA ENTREGA
