package domini.algoritme;

import domini.tauler.Tauler;
import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;
import domini.tauler.casella.Casella;
import domini.tauler.casella.CasellaBlanca;
import domini.tauler.casella.CasellaNegra;

import java.util.*;

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
        for(int y = 0; y < t.getDimY(); y++) {
            for (int x = 0; x < t.getDimX(); x++) {
                if (t.esBlanca(x, y) && ((CasellaBlanca) t.getCasella(x, y)).getValor() == null && !getPossiblesValors(t.getCasella(x, y), Direccio.HORITZONTAL).isEmpty()) {
                    ((CasellaBlanca) t.getCasella(x, y)).setValor(getPossiblesValors(t.getCasella(x, y), Direccio.HORITZONTAL).stream().findFirst().get());
                }
            }
        }
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

    private Set<Integer> getPossiblesValors(Casella casella, Direccio direccio){
        Set<Integer> ret = new HashSet<>();
        try {
            for(Set<Integer> s : Objects.requireNonNull(combinacionsRestants(casella, direccio))) {
                ret.addAll(s);
            }
        }
        catch (NullPointerException e){
            return null;
        }
        return ret;
    }

    //Retorna les combinacions possibles restants
    private Set<Set<Integer>> combinacionsRestants(Casella casella, Direccio direccio){
        Set<Set<Integer>> ret = new HashSet<>();
        if(getBlanquesUtilitzades(casella, direccio).isEmpty()){
            return combinacions.getCombinacios(getSumRestant(casella, direccio), getNumBlanquesRestants(casella, direccio));
        }
        try {
            for (Set<Integer> s : combinacions.getCombinacios(getSumRestant(casella, direccio), getNumBlanquesRestants(casella, direccio))) {
                if (Collections.disjoint(s,getBlanquesUtilitzades(casella, direccio))) {
                    ret.add(s);
                }
            }
            return ret;
        }
        catch (NullPointerException e){
            return null;
        }
    }

    private int getNumBlanquesRestants(Casella casella, Direccio direccio){
        int y = casella.getCoordY();
        int x = casella.getCoordX();
        while (t.esBlanca(x,y)){
            if (direccio == Direccio.HORITZONTAL)
                x--;
            else
                y--;
        }
        while (t.esNegra(x,y)){
            if (direccio == Direccio.HORITZONTAL)
                x++;
            else
                y++;
        }
        int cont = 0;
        switch (direccio){
            case HORITZONTAL:
                while (t.esBlanca(x,y) && x<t.getDimX() && y<t.getDimY()){
                    if(((CasellaBlanca)t.getCasella(x,y)).getValor()==null){
                        cont++;
                    }
                    x++;
                }
                break;
            case VERTICAL:
                while (t.esBlanca(x,y)){
                    if(((CasellaBlanca)t.getCasella(x,y)).getValor()==null){
                        cont++;
                    }
                    y++;
                }
                break;
        }
        return cont;
    }
    
    private int getSumRestant(Casella casella, Direccio direccio){
        int x = casella.getCoordX();
        int y = casella.getCoordY();
        int suma = 0;
        if(t.esNegra(x,y)){
            switch (direccio){
                case HORITZONTAL: return ((CasellaNegra) t.getCasella(x, y)).getFila();
                case VERTICAL: return ((CasellaNegra) t.getCasella(x, y)).getColumna();
            }
        }
        while (t.esBlanca(x,y)){
            if (direccio == Direccio.HORITZONTAL)
                x--;
            else
                y--;
        }
        int i = x;
        int j = y;
        while (t.esNegra(x,y)){
            if (direccio == Direccio.HORITZONTAL)
                x++;
            else
                y++;
        }
        while (t.esBlanca(x, y) ) {
            if(((CasellaBlanca) t.getCasella(x, y)).getValor()!=null)
                suma += ((CasellaBlanca) t.getCasella(x, y)).getValor();
            if (direccio == Direccio.HORITZONTAL)
                x++;
            else
                y++;
        }
        if (direccio == Direccio.HORITZONTAL)
            return (((CasellaNegra) t.getCasella(i, j)).getFila() - suma);
        else
            return (((CasellaNegra) t.getCasella(i, j)).getColumna() - suma);
    }
    
    private Set<Integer> getBlanquesUtilitzades(Casella casella,Direccio direccio){
        int x = casella.getCoordX();
        int y = casella.getCoordY();
        while (t.esBlanca(x,y)){
            if (direccio == Direccio.HORITZONTAL)
                x--;
            else
                y--;
        }
        while (t.esNegra(x,y)){
            if (direccio == Direccio.HORITZONTAL)
                x++;
            else
                y++;
        }
        Set<Integer> ret = new HashSet<>();
        while (t.esBlanca(x,y) && x<t.getDimX() && y<t.getDimY()){
            if(((CasellaBlanca)t.getCasella(x,y)).getValor()!=null) {
                ret.add(((CasellaBlanca) t.getCasella(x, y)).getValor());
            }
            if(direccio == Direccio.HORITZONTAL)
                x++;
            else
                y++;
        }
        return ret;
    }

    // ALGORITMES DE GENERACIO
    
    public TaulerComencat generarKakuroSimpleSolucionat(int rows, int cols){

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
                hist = getInts(tauler, r, hist, c);
            }
        }
        //Anar columna per columna i busquem valors de blanca repetits en els blocs consecutius i els posem negre
        for(int c = 1; c < cols; ++c){
            int hist[] = new int[]{-1,0,0,0,0,0,0,0,0,0,0};
            for(int r = 1; r < rows; ++r){
                if (tauler[r][c].getClass() == CasellaNegra.class){
                    hist = new int[]{-1,0,0,0,0,0,0,0,0,0,0};
                }else{
                    hist = getInts(tauler, r, hist, c);
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
        TaulerComencat t = new TaulerComencat(tauler);
        return t;
    }
    
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
                hist = getInts(tauler, r, hist, c);
            }
        }
        //Anar columna per columna i busquem valors de blanca repetits en els blocs consecutius i els posem negre
        for(int c = 1; c < cols; ++c){
            int hist[] = new int[]{-1,0,0,0,0,0,0,0,0,0,0};
            for(int r = 1; r < rows; ++r){
                if (tauler[r][c].getClass() == CasellaNegra.class){
                    hist = new int[]{-1,0,0,0,0,0,0,0,0,0,0};
                }else{
                    hist = getInts(tauler, r, hist, c);
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
        
        // Fins aquí, és quasi idèntic a generarKakuroSolucionat(int rows, int cols).
        
        // Eliminar les caselles blanques pertinents
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

    private int[] getInts(Casella[][] tauler, int r, int[] hist, int c) {
        int valor = ((CasellaBlanca) tauler[r][c]).getValor();
        hist[valor]++;
        if (hist[valor] > 1){
            tauler[r][c] = new CasellaNegra(c, r, null, null); // TODO: ara sempre són les ultimes
            hist = new int[]{-1,0,0,0,0,0,0,0,0,0,0};
        }
        return hist;
    }

    // ALGORITME DE VALIDACIÓ DE SOLUCIÓ
    
    public boolean validaSolucio(TaulerComencat tc){
        
        int rows = tc.getDimY();
        int cols = tc.getDimX();
        
        //Check negres a dalt i esquerra
        for(int r = 0; r < rows; ++r)
            if (tc.getCasella(0,r).getClass() != CasellaNegra.class) return false;
        
        for(int c = 0; c < cols; ++c)
            if (tc.getCasella(c,0).getClass() != CasellaNegra.class) return false;

        
        //Check totes blanques amb valor
        for(int c = 1; c < cols; ++c)
            for(int r = 1; r < rows; ++r)
                if (tc.getCasella(c,r).getClass() == CasellaBlanca.class)
                    if(((CasellaBlanca) tc.getCasella(c,r)).getValor() == null) return false;
        
        //Check de valors de files
        for(int r = 1; r < rows; ++r){
            int acum_fila = 0;
            for(int c = cols - 1; c >= 0; --c){
                if (tc.getCasella(c,r).getClass() == CasellaNegra.class){
                    if (acum_fila > 0){
                        Integer v = ((CasellaNegra)tc.getCasella(c,r)).getFila();
                        if (acum_fila == 0 && v!=null) return false;
                        else if (v != acum_fila) return false;
                        acum_fila = 0;
                    }
                } else {
                    acum_fila += ((CasellaBlanca) tc.getCasella(c,r)).getValor();
                }
            }
        }
        //Check de valors de columnes
        for(int c = 1; c < cols; ++c){
            int acum_col = 0;
            for(int r = rows - 1; r >= 0; --r){
                if (tc.getCasella(c,r).getClass() == CasellaNegra.class){
                    if (acum_col > 0){
                        Integer v = ((CasellaNegra)tc.getCasella(c,r)).getColumna();
                        if (acum_col == 0 && v!=null) return false;
                        else if (v != acum_col) return false;
                        acum_col = 0;
                    }
                } else {
                    acum_col += ((CasellaBlanca) tc.getCasella(c,r)).getValor();
                }
            }
        }
        return true;
    }
    
    // ALGORITME VALIDADOR DE FORMAT DE PROPOSTA KAKURO
    
    public boolean validaFormat (TaulerEnunciat te){
    
        return true;
    }
}
