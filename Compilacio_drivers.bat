@echo off
mode con cols=70 lines=20
title Compilador
cls
echo.
echo Compilador - /build
echo.
echo domini.tauler
javac -d build -cp src src/domini/tauler/*.java
echo domini.tauler.casella
javac -d build -cp src src/domini/tauler/casella/*.java
echo domini.algoritme
javac -d build -cp src src/domini/algoritme/*.java
echo interficie.testing.Mock_Presentacio
javac -d build -cp src -nowarn src/interficie/testing/Mock_Presentacio_stdio.java
echo domini.drivers
javac -d build -cp src src/domini/drivers/*.java
echo.
echo Done.
pause