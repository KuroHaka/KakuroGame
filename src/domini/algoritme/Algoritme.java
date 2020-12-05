package domini.algoritme;

import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;
import domini.tauler.casella.Casella;
import domini.tauler.casella.CasellaBlanca;
import domini.tauler.casella.CasellaNegra;
import static java.lang.Math.random;

import java.util.*;

enum Direccio{
    HORITZONTAL, VERTICAL
}
public class Algoritme {
    Combinacions combinacions = new Combinacions();

    public Algoritme() {
    }

    // ALGORITME SOLVER

    public TaulerComencat resoldreKakuro(TaulerEnunciat tauler){
        TaulerComencat t = tauler.comencarTauler();
        CasellaBlanca casellaBlanca = seguentCasellaBlanca(t.getCasella(0,1),t);
        if(casellaBlanca==null){
            return null;
        }
        return resoldreKakuro(t, compteCasellesBlanques(t)-1, casellaBlanca);
    }

    private TaulerComencat resoldreKakuro(TaulerComencat t, int blanquesRestant, CasellaBlanca casellarecent){
        Set<Integer> horit = getPossiblesValors(casellarecent, Direccio.HORITZONTAL, t);
        if(horit!=null){
            for(Integer i: horit){
                CasellaBlanca casella = ((CasellaBlanca)t.getCasella(casellarecent.getCoordX(), casellarecent.getCoordY()));
                casella.setValor(i);
                if(blanquesRestant<=0 && validaSolucio(t)){
                    return t;
                }
                CasellaBlanca seguent = seguentCasellaBlanca(casellarecent, t);
                if(seguent== null){
                    casellarecent.setValor(null);
                    return null;
                }
                TaulerComencat ret = resoldreKakuro(t, blanquesRestant-1, seguent);
                if(ret != null){
                    return ret;
                }
            }
        }
        casellarecent.setValor(null);
        return  null;
    }

    private int compteCasellesBlanques(TaulerComencat t){
        int count = 0;
        for(int y = 0; y < t.getDimY(); y++) {
            for (int x = 0; x < t.getDimX(); x++) {
                if (t.esBlanca(x, y)) {
                    count++;
                }
            }
        }
        return count;
    }

    private CasellaBlanca seguentCasellaBlanca(Casella casella, TaulerComencat t){
        int y = casella.getCoordY();
        int x = casella.getCoordX();
        do{
            x++;
            if(x>=t.getDimX()){
                y++;
                x=0;
                if(y>=t.getDimY()){
                    return null;
                }
            }
        }while((t.esNegra(x,y)));
        return ((CasellaBlanca)t.getCasella(x,y));
    }

    private Set<Integer> getPossiblesValors(Casella casella, Direccio direccio, TaulerComencat t){
//        System.out.println(casella.getCoordX()+" "+casella.getCoordY());
        Set<Integer> hori = new HashSet<>();
        Set<Integer> verti = new HashSet<>();
        try {
            for(Set<Integer> x : combinacionsRestants(casella, direccio, t)) {
                hori.addAll(x);
            }
            for(Set<Integer> y : combinacionsRestants(casella, Direccio.VERTICAL, t)){
                verti.addAll(y);
            }
        }
        catch (NullPointerException e){
            return null;
        }
//        System.out.println(hori);
//        System.out.println(verti);
        hori.retainAll(verti);
//        System.out.println(hori);
//        System.out.println("___________");
        return hori;
    }

    //Retorna les combinacions possibles restants
    private Set<Set<Integer>> combinacionsRestants(Casella casella, Direccio direccio, TaulerComencat t){
        Set<Set<Integer>> ret = new HashSet<>();
        if(getBlanquesUtilitzades(casella, direccio, t).isEmpty()){
            return combinacions.getCombinacios(getSumRestant(casella, direccio, t), getNumBlanquesRestants(casella, direccio, t));
        }
        try {
            for (Set<Integer> s : combinacions.getCombinacios(getSumRestant(casella, direccio, t), getNumBlanquesRestants(casella, direccio, t))) {
                if (Collections.disjoint(s,getBlanquesUtilitzades(casella, direccio, t))) {
                    ret.add(s);
                }
            }
            return ret;
        }
        catch (NullPointerException e){
            return null;
        }
    }

    private int getNumBlanquesRestants(Casella casella, Direccio direccio, TaulerComencat t){
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
    
    private int getSumRestant(Casella casella, Direccio direccio, TaulerComencat t){
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
    
    private Set<Integer> getBlanquesUtilitzades(Casella casella,Direccio direccio, TaulerComencat t){
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
        int rows = te.getDimY();
        int cols = te.getDimX();
        
        //Check de la primera casella negra, la (0,0)
        if (te.getCasella(0,0).getClass() != CasellaNegra.class){
            System.out.println("Incorrecte: La primera casella (0,0) no es negra.");
                return false;
        }
        if (((CasellaNegra)te.getCasella(0,0)).getColumna() != null || ((CasellaNegra)te.getCasella(0,0)).getFila() != null){
            System.out.println("Incorrecte: La primera casella (0,0) no pot tenir valor de columna ni fila.");
                return false;
        }
        
        //Check negres a dalt i esquerra // No poden tenir valor de columna
        for(int r = 0; r < rows; ++r){
            if (te.getCasella(0,r).getClass() != CasellaNegra.class){
                System.out.println("Incorrecte: Columna inicial (col=0) no es tota negra.");
                return false;
            }
            if (((CasellaNegra)te.getCasella(0,r)).getColumna() != null){
            System.out.println("Incorrecte: La casella (0,"+r+") no pot tenir valor de columna: Esta a la primera columna!");
                return false;
            }
        }
        
        for(int c = 0; c < cols; ++c){ // No poden tenir valor de fila
            if (te.getCasella(c,0).getClass() != CasellaNegra.class){
                System.out.println("Incorrecte: Fila inicial (row=0) no es tota negra.");
                return false;
            }
            if (((CasellaNegra)te.getCasella(c,0)).getFila() != null){
            System.out.println("Incorrecte: La casella ("+c+",0) no pot tenir valor de fila: Esta a la primera fila!");
                return false;
            }
        }
        //Check de valors de files són possibles, i.e. (N*N + N)/2 <= valor_fila <= 9*N - (N*N - N)/2
        for(int r = 1; r < rows; ++r){
            int acum_fila = 0; // És l'acumulació de valors de caselles blanques fins aquella casella negra.
            int ja_plenes = 0; // És el número de caselles blanques que ja tenen valor establert fins aquella casella negra.
            int N = 0; // N és el número de caselles blanques fins a aquella casella negra.
            for(int c = cols - 1; c >= 0; --c){
                if (te.getCasella(c,r).getClass() == CasellaNegra.class){
                    //if (acum_fila > 0){
                        Integer valor_fila = ((CasellaNegra)te.getCasella(c,r)).getFila();
                        if (N == 0 && valor_fila!=null){ 
                            System.out.println("Incorrecte: Casella negra (row="+r+",col="+c+") hi ha valor de fila="+valor_fila+" pero no te caselles blanques a la dreta.");
                            return false;
                        }
                        if (N != 0 && valor_fila==null){
                            System.out.println("Incorrecte: Casella negra (row="+r+",col="+c+") no hi ha valor de fila pero te "+N+" caselles blanques a la dreta.");
                            return false;
                        }
                        N = N - ja_plenes;
                        if (N != 0 && (N*N + N)/2 + acum_fila > valor_fila){
                            int valor_fila_min = (N*N + N)/2 + acum_fila;
                            System.out.println("Incorrecte: Casella negra (row="+r+",col="+c+") el valor de fila minim es "+valor_fila_min+".");
                            return false;
                        }
                        if(N != 0 && valor_fila > 9*N - (N*N - N)/2 + acum_fila){
                            int valor_fila_max = 9*N - (N*N - N)/2 + acum_fila;
                            System.out.println("Incorrecte: Casella negra (row="+r+",col="+c+") el valor de fila maxim es "+valor_fila_max+".");
                            return false;
                        }
                        //if (acum_fila == 0 && v!=null) return false;
                        //else if (v != acum_fila) return false;
                        acum_fila = 0;
                        ja_plenes = 0;
                        N = 0;
                    //}
                } else {
                    N += 1;
                    if (((CasellaBlanca) te.getCasella(c,r)).getValor() != null){
                        acum_fila += ((CasellaBlanca) te.getCasella(c,r)).getValor();
                        ja_plenes += 1;
                    }
                }
            }
        }
        //Check de valors de columnes
        for(int c = 1; c < cols; ++c){
            int acum_col = 0; // És l'acumulació de valors de caselles blanques fins aquella casella negra.
            int ja_plenes = 0; // És el número de caselles blanques que ja tenen valor establert fins aquella casella negra.
            int N = 0; // N és el número de caselles blanques fins a aquella casella negra.
            for(int r = rows - 1; r >= 0; --r){
                if (te.getCasella(c,r).getClass() == CasellaNegra.class){
                    //if (acum_col > 0){
                        Integer valor_col = ((CasellaNegra)te.getCasella(c,r)).getColumna();
                        if (N == 0 && valor_col!=null){ 
                            System.out.println("Incorrecte: Casella negra (row="+r+",col="+c+") hi ha valor de columna="+valor_col+" pero no te caselles blanques a sota.");
                            return false;
                        }
                        if (N != 0 && valor_col==null){
                            System.out.println("Incorrecte: Casella negra (row="+r+",col="+c+") no hi ha valor de columna pero te "+N+" caselles blanques a sota.");
                            return false;
                        }
                        N = N - ja_plenes;
                        if (N != 0 && (N*N + N)/2 + acum_col > valor_col){
                            int valor_col_min = (N*N + N)/2 + acum_col;
                            System.out.println("Incorrecte: Casella negra (row="+r+",col="+c+") el valor de columna minim es "+valor_col_min+".");
                            return false;
                        }
                        if(N != 0 && valor_col > 9*N - (N*N - N)/2 + acum_col){
                            int valor_col_max = 9*N - (N*N - N)/2 + acum_col;
                            System.out.println("Incorrecte: Casella negra (row="+r+",col="+c+") el valor de columna maxim es "+valor_col_max+".");
                            return false;
                        }
                        acum_col = 0;
                        ja_plenes = 0;
                        N = 0;
                    //}
                } else {
                    N += 1;
                    if (((CasellaBlanca) te.getCasella(c,r)).getValor() != null){
                        acum_col += ((CasellaBlanca) te.getCasella(c,r)).getValor();
                        ja_plenes += 1;
                    }
                }
            }
        }
        return true;
    }

    
    //Algoritme de generació de solució única

    /*private CasellaBlanca seguentCasellaBlanca(Casella casella, TaulerComencat t){
        int y = casella.getCoordY();
        int x = casella.getCoordX();
        do{
            x++;
            if(x>=t.getDimX()){
                y++;
                x=0;
                if(y>=t.getDimY()){
                    return null;
                }
            }
        }while((t.esNegra(x,y)));
        return ((CasellaBlanca)t.getCasella(x,y));
    }*/

    private Set<Integer> getUnicaCombinacio(TaulerComencat tc, CasellaBlanca casellarecent) {
        int sumaFila;
        int x;
        try {
            Set <Integer> used = new HashSet<>();
            for(x = casellarecent.getCoordX() - 1; tc.getCasella(x, casellarecent.getCoordY()).getClass() == CasellaBlanca.class; --x) {
                used.add(((CasellaBlanca)tc.getCasella(x, casellarecent.getCoordY())).getValor());
            }
            sumaFila = ((CasellaNegra)tc.getCasella(x, casellarecent.getCoordY())).getFila();
            int blanquesSeguides = 0;
            for(x = x+1; x < tc.getDimX() && tc.getCasella(x, casellarecent.getCoordY()).getClass() == CasellaBlanca.class; ++x) {
                ++blanquesSeguides;
            }
            Set<Set<Integer> > comb = combinacions.getCombinacios(sumaFila, blanquesSeguides);
            Set <Integer> combunica = new HashSet<>();
            for(Set<Integer> s : comb) {
                combunica.addAll(s);
            }
            combunica.removeAll(used);
            return combunica;
        }
        catch (StackOverflowError e){
            return null;
        }
    }

    private Set<Integer> getValorsColumna(TaulerComencat tc, CasellaBlanca casellarecent) {
        try {
            Set <Integer> used = new HashSet<>();
            for(int y = casellarecent.getCoordY() - 1; tc.getCasella(casellarecent.getCoordX(), y).getClass() == CasellaBlanca.class; --y) {
                used.add(((CasellaBlanca)tc.getCasella(casellarecent.getCoordX(), y)).getValor());
            }
            return used;
        }
        catch (StackOverflowError e) {
            return null;
        }
    }

    private TaulerComencat solucionaKakuro(int nb, TaulerComencat tc) {
        CasellaBlanca casellaBlanca = seguentCasellaBlanca(tc.getCasella(0,1), tc);
        if(casellaBlanca == null) {
            return null;
        }
        return solucionaKakuro(tc, casellaBlanca, nb);
    }

    private TaulerComencat solucionaKakuro(TaulerComencat tc, CasellaBlanca casellarecent, int numeroBlanques) {
        Set<Integer> comb = getUnicaCombinacio(tc, casellarecent);
        Set<Integer> used = getValorsColumna(tc, casellarecent);
        if(used == null || comb == null) {return null;}
        comb.removeAll(used);
        if(comb.isEmpty()) {return null;}
        else {
            for(Integer i: comb) {
                ((CasellaBlanca)tc.getCasella(casellarecent.getCoordX(), casellarecent.getCoordY())).setValor(i);
                if(numeroBlanques<=0){
                    return tc;
                }
                CasellaBlanca seguent = seguentCasellaBlanca(casellarecent, tc);
                if(seguent== null){
                    ((CasellaBlanca)tc.getCasella(casellarecent.getCoordX(), casellarecent.getCoordY())).setValor(null);
                    return null;
                }
                TaulerComencat tc2 = solucionaKakuro(tc, seguent, numeroBlanques-1);
                if(tc2 != null){
                    return tc2;
                }
                else {return null;}
            }
        }
        ((CasellaBlanca)tc.getCasella((casellarecent.getCoordX()), casellarecent.getCoordY())).setValor(null);
        return null;
    }

    private ArrayList<ArrayList<Integer> > crea1comb() {
        ArrayList<ArrayList<Integer> > unaComb = new ArrayList<ArrayList<Integer> >(9);
        ArrayList <Integer> a1 = new ArrayList<Integer> ();
        a1.add(1); a1.add(2); a1.add(3); a1.add(4); a1.add(5); a1.add(6); a1.add(7); a1.add(8); a1.add(9); unaComb.add(a1);//Combinacions de 1 casella
        a1 = new ArrayList<Integer> (); a1.add(3); a1.add(4); a1.add(16); a1.add(17); unaComb.add(a1);
        a1 = new ArrayList<Integer> (); a1.add(6); a1.add(7); a1.add(23); a1.add(24); unaComb.add(a1);
        a1 = new ArrayList<Integer> (); a1.add(10); a1.add(11); a1.add(29); a1.add(30); unaComb.add(a1);
        a1 = new ArrayList<Integer> (); a1.add(15); a1.add(16); a1.add(34); a1.add(35); unaComb.add(a1);
        a1 = new ArrayList<Integer> (); a1.add(21); a1.add(22); a1.add(38); a1.add(39); unaComb.add(a1);
        a1 = new ArrayList<Integer> (); a1.add(28); a1.add(29); a1.add(41); a1.add(42); unaComb.add(a1);
        a1 = new ArrayList<Integer> (); a1.add(36); a1.add(37); a1.add(38); a1.add(39); a1.add(40); a1.add(41); a1.add(42); a1.add(43); a1.add(44); unaComb.add(a1);
        a1 = new ArrayList<Integer> (); a1.add(45); unaComb.add(a1);
        return unaComb;
    }

    private int getNumFElems(Casella[][] tauler, int i, int j) {
        int cont = 0;
        for(int j2 = j + 1; j2 < tauler[0].length; ++j2) {
            if(tauler[i][j2].getClass() == CasellaBlanca.class) {
                ++cont; //sumem 1 per cada casella blanca a l'esquerra
            }
            else return cont;
        }
        return cont;
    }

    public TaulerEnunciat generarKakuro(int rows, int cols, Integer numeroBlanques, int numeroBlanquesEstablertes) {
        if(numeroBlanques==null){
            numeroBlanques=(int)Math.round(0.55*(rows-1)*(cols-1));
        }
        Casella[][] tauler = new Casella[rows][cols];
        //Negres a dalt i esquerra
        for (int r = 0; r < rows; ++r) {
            CasellaNegra ca = new CasellaNegra(0, r, null, null);
            tauler[r][0] = ca;
        }
        for (int c = 0; c < cols; ++c) {
            CasellaNegra ca = new CasellaNegra(c, 0, null, null);
            tauler[0][c] = ca;
        }
        //Emplenar amb caselles blanques buides
        for (int r = 1; r < rows; ++r) {
            for (int c = 1; c < cols; ++c) {
                CasellaBlanca ca = new CasellaBlanca(c, r, null);
                tauler[r][c] = ca;
            }
        }
        //Posar una negra si n'hi han 9 blanques seguides en una fila o columna(acaba amb la simetria si el kakuro es de més de 10x10
        int blanquesSeguides = 0;
        int negresColocades = 0;
        int rand;
        for (int r = 1; r < rows; ++r) {
            for(int c = 1; c < cols; ++c) {
                if (blanquesSeguides == 9) {
                    rand = (int) (Math.random() * 3) + 1;
                    ++negresColocades;
                    CasellaNegra ca = new CasellaNegra(c - rand, r, null, null);
                    tauler[r][c - rand] = ca;
                    blanquesSeguides = rand;
                }
                else {
                    ++blanquesSeguides;
                }
            }
            blanquesSeguides = 0;
        }
        blanquesSeguides = 0;
        for (int c = 1; c < cols; ++c) {
            for(int r = 1; r < rows; ++r) {
                if (blanquesSeguides == 9) {
                    rand = (int) (Math.random() * 3) + 1;
                    ++negresColocades;
                    CasellaNegra ca = new CasellaNegra(c, r - rand, null, null);
                    tauler[r - rand][c] = ca;
                    blanquesSeguides = rand;
                }
                else {
                    ++blanquesSeguides;
                }
            }
            blanquesSeguides = 0;
        }
        int negExtres = (rows-1)*(cols-1) - (numeroBlanques) - negresColocades;
        for(int p = 0; p < negExtres; ++p) {
            int r = (int) ((Math.random() * (rows - 1)) + 1);
            int c = (int) ((Math.random() * (cols - 1)) + 1);
            --p;
            if(tauler[r][c].getClass() == CasellaBlanca.class) {
                ++p;
                CasellaNegra ca = new CasellaNegra(c, r, null, null);
                tauler[r][c] = ca;
            }
        }
        int cont = 0;
        for(int r = 0; r < rows; ++r) {
            for(int c = 0; c < cols; ++c) {
                if(tauler[r][c].getClass() == CasellaBlanca.class) {
                    ++cont;
                }
            }
        }
        while((cont - numeroBlanques) > 0) {
            int r = (int) ((Math.random() * (rows - 1)) + 1);
            int c = (int) ((Math.random() * (cols - 1)) + 1);
            if(tauler[r][c].getClass() == CasellaBlanca.class) {
                --cont;
                CasellaNegra ca = new CasellaNegra(c, r, null, null);
                tauler[r][c] = ca;
            }
        }
        ArrayList<ArrayList<Integer> > unaComb = new ArrayList<ArrayList<Integer> >(9);
        unaComb = crea1comb();        // x = nºCols ; y = nºFiles
        //Posem totes les caselles negres amb fila amb valors random de 1 sola combinacio possible per aconseguir una solucio única
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols - 1; ++j) {
                if(tauler[i][j].getClass() == CasellaNegra.class) {
                    if(tauler[i][j + 1].getClass() == CasellaBlanca.class) {
                        int numbElems = getNumFElems(tauler, i, j);
                        //j+= numbElems; ????
                        int q = unaComb.get(numbElems - 1).size() - 1;
                        int c = (int)(Math.random()*q);
                        int v = unaComb.get(numbElems - 1).get(c);
                        tauler[i][j] = new CasellaNegra(i, j, v, null);
                    }
                }
            }
        }

        //SOLVER!!!!!!!!!!!!!!!! tiene q comprobar si tiene solucion
        try {
            TaulerComencat tc = new TaulerComencat(tauler);
            solucionaKakuro(numeroBlanques - 1, tc);
            boolean algunNull = false;
            for(int r = 0; r < rows; ++r) {
                for(int c = 0; c < cols; ++c) {
                    if(tc.getCasella(c,r).getClass() == CasellaBlanca.class) {
                        if(((CasellaBlanca)tc.getCasella(c,r)).getValor() == null) {algunNull = true;}
                    }
                }
            }
            if(algunNull) {
                return generarKakuro(rows, cols, numeroBlanques, numeroBlanquesEstablertes);
            }
            else {
                //rellenar las cols
                for (int c = 1; c < cols; ++c) {
                    int acum_col = 0;
                    for (int r = rows - 1; r >= 0; --r) {
                        if (tc.getCasella(c,r).getClass() == CasellaNegra.class) {
                            if (acum_col > 0) {
                                Integer valor_f = ((CasellaNegra)tc.getCasella(c,r)).getFila();
                                tauler[r][c] = new CasellaNegra(c, r, valor_f, acum_col);
                                acum_col = 0;
                            }
                        } else {
                            acum_col += ((CasellaBlanca)tc.getCasella(c, r)).getValor();
                        }
                    }
                }
                TaulerComencat tcBuit = tc;
                int buidades = 0;
                while(buidades < (numeroBlanques - numeroBlanquesEstablertes)) {
                    int r = (int) ((Math.random() * (rows - 1)) + 1);
                    int c = (int) ((Math.random() * (cols - 1)) + 1);
                    if(tcBuit.getCasella(c,r).getClass() == CasellaBlanca.class) {
                        if(((CasellaBlanca)tcBuit.getCasella(c,r)).getValor() != null) {
                            ((CasellaBlanca)tcBuit.getCasella(c,r)).setValor(null);
                            tauler[r][c] = new CasellaBlanca(c, r, null);
                            ++buidades;
                        }
                    }
                }
                TaulerEnunciat t = new TaulerEnunciat(tauler);
                return t;
            }
        }
        catch (StackOverflowError e) {return generarKakuro(rows, cols, numeroBlanques, numeroBlanquesEstablertes);}
    }

    // ALGORITME D'AJUDA
    
    public Object[] getAjuda(TaulerComencat comencat, TaulerComencat solucio) {
        
        ArrayList<Object[]> vec = new ArrayList<Object[]>();
        int mida = 0;
        
        for (int i = 0; i < comencat.getDimX(); i++) {
            for (int j = 0; j < comencat.getDimY(); j++) {
                Integer valor = comencat.getValor(i, j);
                
                if (valor != null && valor != -1) {
                    Integer valorSol = solucio.getValor(i, j);
                    if (valor != valorSol) {
                        return new Object[] {i, j, valorSol};
                    }
                }
                else {
                    vec.add(new Object[] {i,j});
                    mida++;
                }
            }
        }
        
        int aux = (int) random() % mida;
        
        int i = (int) vec.get(aux)[0];
        int j = (int) vec.get(aux)[1];
        return new Object[] {i, j, solucio.getValor(i, j)};
    }

}
