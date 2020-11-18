@echo off
title Generacio Executables Drivers

cls
echo.
echo Generacio Executables - /dist
echo.

SET nom=Driver_CasellaBlanca
SET include=domini/drivers/%nom%.class domini/tauler/casella/CasellaBlanca.class domini/tauler/casella/Casella.class
call :jarcvfe

SET nom=Driver_CasellaNegra
SET include=domini/drivers/%nom%.class domini/tauler/casella/CasellaNegra.class domini/tauler/casella/Casella.class
call :jarcvfe

SET nom=Driver_Combinacions
SET include=domini/drivers/%nom%.class domini/algoritme/Combinacions.class
call :jarcvfe

SET nom=Driver_TaulerEnunciat
SET include=domini/drivers/%nom%.class domini/tauler/Tauler.class domini/tauler/TaulerEnunciat.class domini/tauler/casella/CasellaBlanca.class domini/tauler/casella/CasellaNegra.class domini/tauler/casella/Casella.class presistencia/Dades.class
call :jarcvfe

SET nom=Driver_TaulerComencat
SET include=domini/drivers/%nom%.class domini/tauler/Tauler.class domini/tauler/TaulerComencat.class domini/tauler/casella/CasellaBlanca.class domini/tauler/casella/CasellaNegra.class domini/tauler/casella/Casella.class presistencia/Dades.class
call :jarcvfe

SET shared=domini/algoritme/*.class domini/algoritme/Combinacions.class domini/tauler/Tauler.class domini/tauler/TaulerComencat.class domini/tauler/TaulerEnunciat.class domini/tauler/casella/CasellaBlanca.class domini/tauler/casella/CasellaNegra.class domini/tauler/casella/Casella.class presistencia/Dades.class

SET nom=Driver_Algoritme_generarKakuro
SET include=domini/drivers/%nom%.class %shared%
call :jarcvfe

SET nom=Driver_Algoritme_resoldreKakuro
SET include=domini/drivers/%nom%.class %shared%
call :jarcvfe

SET nom=Driver_Algoritme_validaSolucioKakuro
SET include=domini/drivers/%nom%.class %shared%
call :jarcvfe

SET nom=Driver_Algortime_validaFormatEnunciat
SET include=domini/drivers/%nom%.class %shared%
call :jarcvfe

echo.
echo Done.
pause
goto :eof

:jarcvfe
SET jar=%nom%.jar
SET class=domini.drivers.%nom%
echo. %class%
jar cvfe %jar% %class% %include%
echo.