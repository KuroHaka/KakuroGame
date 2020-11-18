### Generació de L'entrega

- Tots els `index.txt` han d'estar aa la carpeta corresponent

- Tots els documents pertinents han d'estar a `ENTREGA/DOC`

- Executar `generaEntrega.sh` :

 - Copia `src` , `test` i `dades` a `ENTREGA/FONTS/`
 - Genera scripts per compilar tots els drivers de `ENTREGA/FONTS/`
 - Compila el codi de `src`
 - Genera els arxius `.jar` de cada Driver i els posa a `ENTREGA/EXE/drivers/`
 - Genera scripts per executar cada Driver de `ENTREGA/EXE/drivers/`
 - Empaqueta en `1aENTREGA_9.2.tar.gz` tota la carpeta `ENTREGA`

- Executar `cleanEntrega.sh` per borrar executables i compilacions locals.