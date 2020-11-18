@echo off
title Execucio de Drivers
:menu
mode con cols=60 lines=15
cls
echo.
echo ==== Menu d.Execucio de drivers ====
echo.
echo   1- Driver de Tauler
echo   2- Driver de CasellaBlanca
echo   3- Driver de CasellaNegra
echo.
set choice=
set /p choice= Opcio (numero):  ||GOTO:menu
cls
SET driv=
if %choice%==1 ( SET driv=Tauler && goto exec)
if %choice%==2 ( SET driv=CasellaBlanca && goto exec)
if %choice%==3 ( SET driv=CasellaNegra && goto exec)
goto menu
:exec
mode con cols=100 lines=40
cls
echo.
echo  Driver %driv%
echo.
java -cp build domini.drivers.Driver_%driv%
echo.
echo Done.
pause
goto menu