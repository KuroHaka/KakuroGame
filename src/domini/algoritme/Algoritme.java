package domini.algoritme;

import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;

import domini.tauler.casella.CasellaBlanca;
import domini.tauler.casella.CasellaNegra;

public class Algoritme {
    Combinacions combinacions = new Combinacions();


    public TaulerEnunciat generarKakuro(int x, int y, int numeroBlanques){
        
        return null;
    }

    public TaulerComencat resoldreKakuro(TaulerEnunciat t){
        for(int j = 0; j<t.getDimY(); j++) {
            for (int i = 0; i < t.getDimX(); i++) {
                if(t.esNegra(i,j)){
                    CasellaNegra casellaNegra= (CasellaNegra) t.getCasella(i,j);
                    if(casellaNegra.getFila()!=null){
                        colocarBlanquesHoritzontals(casellaNegra, t);
                    }
                }
            }
        }
        return null;
    }

    private int numBlanquesHoritzontalsConsecuents(CasellaNegra casellaNegra, TaulerEnunciat te){
        //falta comparar id casellaNegra amb TaulerEnunciat TODO
        int y = casellaNegra.getCoordY();
        int x = casellaNegra.getCoordX() + 1;
        int cont = 0;
        while(te.esBlanca(x, y)){
            x++;
            cont++;
        }
        return cont;
    }

    private void colocarBlanquesHoritzontals(CasellaNegra casellaNegra, TaulerEnunciat te){
        int y = casellaNegra.getCoordY();
        int x = casellaNegra.getCoordX() + 1;
        int[] fila = combinacions.getCombinacios(casellaNegra.getFila(), numBlanquesHoritzontalsConsecuents(casellaNegra, te))[0];
        for(int n = 0; n<fila.length; n++){
            ((CasellaBlanca) te.getCasella(x, y)).setValor(fila[n]);
            x++;
        }
    }
}
