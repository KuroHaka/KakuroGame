package domini.algoritme;

import domini.tauler.Tauler;
import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;
import domini.tauler.casella.Casella;
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

    public Algoritme() {
        this.t = null;
    }
    
    public Algoritme(TaulerEnunciat taulerEnunciat) {
        this.t = taulerEnunciat;
    }

    // ALGORITME SOLVER
    
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

    //Donat una casella blanca amb una pila de valors que ja en té, retorna les combinacions que no té els números de la pila
    private Set<Set<Integer>> combinacionsSenseAlgunsNums(CasellaBlanca casellaBlanca, Direccio direccio){
        Set<Set<Integer>> ret = new HashSet<>();
        switch (direccio){
            case HORITZONTAL:
                combinacions.getCombinacios(getSumConsecuent(casellaBlanca,direccio),getNumBlanquesConsecuents(casellaBlanca,direccio));
        }
        return null;
    }

    // ALGORITME DE GENERACIO
    
    public TaulerEnunciat generarKakuroSimple(int rows, int cols, Integer numeroBlanques){
        // x = nºCols ; y = nºFiles
        Casella[][] tauler = new Casella[rows][cols];
        //Negres a dalt i esquerra
        for(int r = 0; r < rows; ++r){
            CasellaNegra ca = new CasellaNegra(0, r, null, null);
            tauler[r][0] = ca;
        }
        for(int c = 0; c < cols; ++c){
            CasellaNegra ca = new CasellaNegra(c, 0, null, null);
            tauler[0][c] = ca;
        }
        //Emplenar amb caselles blanques de valor random
        for(int r = 1; r < rows; ++r){
            for(int c = 1; c < cols; ++c){
                int ran = (int) ((Math.random() * 9) + 1);
                CasellaBlanca ca = new CasellaBlanca(c, r, ran);
                tauler[r][c] = ca;
            }
        }
        //Anar fila per fila i busquem valors de blanca repetits en els blocs consecutius i els posem negre 
        for(int r = 1; r < rows; ++r){
            int hist[] = new int[]{-1,0,0,0,0,0,0,0,0,0,0};
            for(int c = 1; c < cols; ++c){
                int valor = ((CasellaBlanca) tauler[r][c]).getValor();
                hist[valor]++;
                if (hist[valor] > 1){
                    tauler[r][c] = new CasellaNegra(c, r, null, null); // TODO: ara sempre són les ultimes
                    hist = new int[]{-1,0,0,0,0,0,0,0,0,0,0};
                }
            }
        }
        //Anar columna per columna i busquem valors de blanca repetits en els blocs consecutius i els posem negre
        for(int c = 1; c < cols; ++c){
            int hist[] = new int[]{-1,0,0,0,0,0,0,0,0,0,0};
            for(int r = 1; r < rows; ++r){
                if (tauler[r][c].getClass() == CasellaNegra.class){
                    hist = new int[]{-1,0,0,0,0,0,0,0,0,0,0};
                }else{
                    int valor = ((CasellaBlanca) tauler[r][c]).getValor();
                    hist[valor]++;
                    if (hist[valor] > 1){ 
                        tauler[r][c] = new CasellaNegra(c, r, null, null);
                        hist = new int[]{-1,0,0,0,0,0,0,0,0,0,0};
                    }
                }
            }
        }
        //Finalment, anar a cada casella negra i posar els valors corresponents de suma de columna\fila
        // De pas comptem el numer ode caselles blanques.
        int blanques = 0;
        //Check de files
        for(int r = 1; r < rows; ++r){
            int acum_fila = 0;
            for(int c = cols - 1; c >= 0; --c){
                if (tauler[r][c].getClass() == CasellaNegra.class){
                    if (acum_fila > 0){
                        tauler[r][c] = new CasellaNegra(c, r, acum_fila, null);
                        acum_fila = 0;
                    }
                } else {
                    acum_fila += ((CasellaBlanca) tauler[r][c]).getValor();
                    blanques ++;
                }
            }
        }
        //Check de columnes
        for(int c = 1; c < cols; ++c){
            int acum_col = 0;
            for(int r = rows - 1; r >= 0; --r){
                if (tauler[r][c].getClass() == CasellaNegra.class){
                    if (acum_col > 0){
                        Integer valor_f = ((CasellaNegra)tauler[r][c]).getFila();
                        tauler[r][c] = new CasellaNegra(c, r, valor_f, acum_col);
                        acum_col = 0;
                    }
                } else {
                    acum_col += ((CasellaBlanca) tauler[r][c]).getValor();
                }
            }
        }
        if(numeroBlanques != null && numeroBlanques < (rows-1)*(cols-1)){
            if(numeroBlanques < blanques){ // Deixar almenys una casella blanca sense completar... 
                // Anem buidant random, fins que blanques == numeroBlanques
                int consecutive = 0;
                int old = blanques;
                while(blanques > numeroBlanques){
                    int ranCol = (int) ((Math.random() * (cols-1)) + 1); // Colmna random entre 1..cols-1
                    int ranFila = (int) ((Math.random() * (rows-1)) + 1);
                    if (tauler[ranFila][ranCol].getClass() == CasellaBlanca.class && ((CasellaBlanca)tauler[ranFila][ranCol]).getValor()!=null){ // Nice lazy evaluation here
                        tauler[ranFila][ranCol] = new CasellaBlanca(ranCol, ranFila, null);
                        blanques --;
                    }
                    //Control per no fer bucle forever (li costa massa eliminar fent random): eliminació pseudo-manual (assistida..)
                    consecutive = blanques == old ? consecutive + 1 : 0 ; // Nice optimització
                    old = blanques;
                    //System.out.println("Blanques = " + blanques + " Consec = " + consecutive);
                    if (consecutive > 10){
                        //System.out.println("Blanques = " + blanques + ", consecutive > 10 ...");
                        // Possible algortime substitut de criba de blanques:
                        // Va fila a fila matant la primera que troba.
                        while(blanques > numeroBlanques){
                            int r = (int) ((Math.random() * (rows-1)) + 1); //random row // WTF: perquè rows-1 ? no hauria de ser rows-2? RandomNumber(int min, int max) := ((Math.random() * (max - min)) + min);
                            r = r % rows; // Wtf.. no entenc res. Han passat 15 minuts i ja no sé com funciona això. Però crec que no hauria de funcionar. who knows perquè funciona bé.
                            //System.out.println("Selected rand Row = " + r + " Blanques = " + blanques);
                            int index = (int) ((Math.random() * (cols-1)) + 1);
                            for(int c = index; c < cols + index && blanques > numeroBlanques; ++c){
                                if (tauler[r][c % cols].getClass() == CasellaBlanca.class && ((CasellaBlanca)tauler[r][c % cols]).getValor()!=null){
                                    tauler[r][c % cols] = new CasellaBlanca(c, r, null);
                                    blanques --;
                                    break;
                                }
                            }
                        }
                    }
                }
            } else return generarKakuroSimple(rows, cols, numeroBlanques); // Potser s'ha de fer un numeroBlanques-- ? Per assegurar que podrá generar...
        }else{
        //Si numeroBlanques == null, o és impossible de cumplir... eliminem totes les caselles blanques.
            for(int r = 1; r < rows; ++r){
                for(int c = 1; c < cols; ++c){
                    if (tauler[r][c].getClass() == CasellaBlanca.class)
                        tauler[r][c] = new CasellaBlanca(c, r, null);
                }
            }
        }
        TaulerEnunciat t = new TaulerEnunciat(tauler);
        return t;
    }
    
}
