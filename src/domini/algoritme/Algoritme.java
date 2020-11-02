package domini.algoritme;

import domini.tauler.Tauler;
import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;

import domini.tauler.casella.CasellaBlanca;
import domini.tauler.casella.CasellaNegra;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

enum Direccio{
    HORITZONTAL, VERTICAL
}
public class Algoritme {
    Combinacions combinacions = new Combinacions();
    TaulerEnunciat t;

    public Algoritme(TaulerEnunciat taulerEnunciat) {
        this.t = taulerEnunciat;
    }

    public TaulerEnunciat generarKakuro(int x, int y, int numeroBlanques){
        
        return null;
    }

    public TaulerComencat resoldreKakuro(){
        for(int j = 0; j<t.getDimY(); j++) {
            for (int i = 0; i < t.getDimX(); i++) {
                if(t.esNegra(i,j)){
                    CasellaNegra casellaNegra= (CasellaNegra) t.getCasella(i,j);
                    if(casellaNegra.getFila()!=null){
                        colocarBlanquesHoritzontals(casellaNegra);
                    }
                }
            }
        }
        System.out.println(combinacionsRestants(((CasellaBlanca)t.getCasella(3,5)),Direccio.VERTICAL));
        return null;
    }

    private void colocarBlanquesHoritzontals(CasellaNegra casellaNegra){
        int y = casellaNegra.getCoordY();
        int x = casellaNegra.getCoordX() + 1;
        int cont = 0;
        while(t.esBlanca(x, y)){
            x++;
            cont++;
        }
        y = casellaNegra.getCoordY();
        x = casellaNegra.getCoordX() + 1;
        Set<Integer> fila = combinacions.getCombinacios(casellaNegra.getFila(), cont).stream().findFirst().get();
        for (Integer integer : fila) {
            ((CasellaBlanca) t.getCasella(x, y)).setValor(integer);
            x++;
        }
    }



    //Retorna les combinacions possibles restants
    private Set<Set<Integer>> combinacionsRestants(CasellaBlanca casellaBlanca, Direccio direccio){
        Set<Set<Integer>> ret = new HashSet<>();
        try {
            for (Set<Integer> s : combinacions.getCombinacios(getSumConsecuent(casellaBlanca, direccio), getNumBlanquesConsecuents(casellaBlanca, direccio))) {
                if (!getBlanquesUtilitzades(casellaBlanca, direccio).containsAll(s)) {
                    ret.add(s);
                }
            }
            return ret;
        }
        catch (NullPointerException e){
            return null;
        }
    }

    private int getNumBlanquesConsecuents(CasellaBlanca casellaBlanca, Direccio direccio){
        int y = casellaBlanca.getCoordY();
        int x = casellaBlanca.getCoordX();
        int cont = 0;
        switch (direccio){
            case HORITZONTAL:
                x++;
                while (t.esBlanca(x,y)){
                    cont++;
                    x++;

                }
                break;
            case VERTICAL:
                y++;
                while (t.esBlanca(x,y)){
                    cont++;
                    y++;
                }
                break;
        }
        return cont;
    }
    private int getSumConsecuent(CasellaBlanca casellaBlanca, Direccio direccio){
        int x = casellaBlanca.getCoordX();
        int y = casellaBlanca.getCoordY();
        int suma = 0;
        switch (direccio){
            case HORITZONTAL:
                while(t.esBlanca(x,y)) {
                    suma += ((CasellaBlanca) t.getCasella(x, y)).getValor();
                    x--;
                }
                return ((CasellaNegra)t.getCasella(x,y)).getFila()-suma;
            case VERTICAL:
                while(t.esBlanca(x,y)) {
                    suma += ((CasellaBlanca) t.getCasella(x, y)).getValor();
                    y--;
                }
                return ((CasellaNegra)t.getCasella(x,y)).getColumna()-suma;
        }
        return 0;
    }
    private Set<Integer> getBlanquesUtilitzades(CasellaBlanca casellaBlanca,Direccio direccio){
        int x = casellaBlanca.getCoordX();
        int y = casellaBlanca.getCoordY();
        Set<Integer> ret = new HashSet<>();
        switch (direccio){
            case HORITZONTAL:
                while (t.esBlanca(x,y)){
                    ret.add(((CasellaBlanca)t.getCasella(x,y)).getValor());
                    x--;
                }
                break;
            case VERTICAL:
                while (t.esBlanca(x,y)) {
                    ret.add(((CasellaBlanca) t.getCasella(x, y)).getValor());
                    y--;
                }
                break;
        }
        return ret;
    }
}
