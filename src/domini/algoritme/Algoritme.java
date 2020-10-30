package domini.algoritme;

import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;

import domini.tauler.casella.CasellaBlanca;
import domini.tauler.casella.CasellaNegra;

import java.util.Iterator;
import java.util.Set;

enum Direccio{
    HORITZONTAL, VERTICAL
}
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

    private void colocarBlanquesHoritzontals(CasellaNegra casellaNegra, TaulerEnunciat te){
        int y = casellaNegra.getCoordY();
        int x = casellaNegra.getCoordX() + 1;
        int cont = 0;
        while(te.esBlanca(x, y)){
            x++;
            cont++;
        }
        y = casellaNegra.getCoordY();
        x = casellaNegra.getCoordX() + 1;
        Set<Integer> fila = combinacions.getCombinacios(casellaNegra.getFila(), cont).stream().findFirst().get();
        for (Integer integer : fila) {
            ((CasellaBlanca) te.getCasella(x, y)).setValor(integer);
            x++;
        }
    }

    //Donat una casella negra amb una pila de valors que ja en té, retorna les combinacions que no té els números de la pila
    private Set<Integer> combinacionsSenseAlgunsNums(CasellaNegra casellaNegra, Set<Integer> set, Direccio direccio){
        if(direccio == Direccio.HORITZONTAL){
            //TODO
        }
        return null;
    }

}
