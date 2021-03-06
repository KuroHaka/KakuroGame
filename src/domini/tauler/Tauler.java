package domini.tauler;

import domini.hashing.Hash;
import domini.tauler.casella.Casella;
import domini.tauler.casella.CasellaBlanca;
import domini.tauler.casella.CasellaNegra;
//import interficie.testing.Mock_Presentacio_stdio;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class Tauler {
    
    // ATRIBUTS PRIVATS
    
    protected String id; // Protected perquè TaulerComençat ha de poder "actualitzar-lo"
    protected static int dimX;
    protected static int dimY;
    protected Casella[][] tauler;
    // private Dificultat dificultat;

    // CONSTRUCTORES
    
    /*public Tauler(){
        this.tauler = null;// new Casella[0][0]; //legirTauler_interface(); //llegirTauler();
        //this.dimX = 0;//tauler[0].length;
        //this.dimY = 0;//tauler.length;
        this.id = null;//Hash.calculaHash(format_Estandard());
    }*/
    
    public Tauler(Casella[][] t){
        this.tauler = t;
        this.dimX = tauler[0].length;
        this.dimY = tauler.length;
        this.id = Hash.calculaHash(format_Estandard());
    }

    public Tauler(String t){
        this(llegirTauler_String(t)); // Mètode Static que retorna en format matriu
    }
    
    // ENCAPSULACIONS

    public Casella getCasella(Integer x, Integer y){
        return this.tauler[y][x];
    }
    
    public Integer getValor(int x, int y) {
        if (!esBlanca(x,y)) return -1;
        return ((CasellaBlanca)this.tauler[y][x]).getValor();
    }

    public String getId() {
        return id;
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }
       
    public void print() {
        System.out.println("Tauler id=" + this.id + ":");
        for (int i = 0; i < dimY; ++i){
            for (int j = 0; j < dimX; ++j){
                System.out.print("|");
                tauler[i][j].print();
            }
            System.out.print("|\n");
        }
    }
    
    // MÈTODES PÚBLICS

    public boolean esNegra(int x, int y){
        if(x<this.tauler[0].length && y<this.tauler.length){
            return this.tauler[y][x].getClass().equals(CasellaNegra.class);
        }
        else
            return false;
    }

    public boolean esBlanca(int x, int y){
        if(x<this.tauler[0].length && y<this.tauler.length){
            return this.tauler[y][x].getClass().equals(CasellaBlanca.class);
        }
        else
            return false;
    }
    
    public String format_Estandard(){
        String ret = "";
        ret = ret + dimY + "," + dimX + "\n";
        for (int i = 0; i < dimY; ++i){
            for (int j = 0; j < dimX; ++j){
                ret += tauler[i][j].save_String();
                if( j != dimX - 1 ) ret += ","; 
            }
            ret += "\n";
        }
        return ret;
    }
    
    
    // MÈTODES PRIVATS
    
    private static Casella generaCasellaXYSegonsComanda(String comanda, int x, int y){
        Casella c;
        char ch = comanda.charAt(0);
        if (ch == '?'){ //Casella Blanca Buida
            c = new CasellaBlanca(x, y);
        }
        else if (ch == '*'){ //Casella Negra Buida
            c = new CasellaNegra(x, y, null, null);
        }
        else if (ch == 'C'){
            if(comanda.contains("F")){ // CxxFyy
                int k = comanda.indexOf('F');
                int Vcol = Integer.parseInt(comanda.substring(1, k));
                int Vfila = Integer.parseInt(comanda.substring(k + 1));
                c = new CasellaNegra(x, y, Vfila, Vcol);
            } else { // Cxx
                int Vcol = Integer.parseInt(comanda.substring(1));
                c = new CasellaNegra(x, y, null, Vcol);
            }
        }
        else if(ch == 'F'){ // Fyy
            int Vfila = Integer.parseInt(comanda.substring(1));
            c = new CasellaNegra(x, y, Vfila, null);
        }
        else{ //Valor Casella Blanca
            int v = Character.getNumericValue(ch); // entre 0..9
            c = new CasellaBlanca(x, y, v);
        }
        return c;
    }
    /*
    private Casella[][] llegirTauler_interface(){
        System.out.print("Numero de columnes (x): ");
        int x = Mock_Presentacio_stdio.llegirEnter();
        System.out.print("Numero de files (y): ");
        int y = Mock_Presentacio_stdio.llegirEnter();
        
        Casella[][] t  = new Casella[y][x];
        for (int i = 0; i < y; ++i)
            for (int j = 0; j < x; ++j){
                Casella c;
                System.out.print("Valor fila= " + i + ", col= " + j +" : ");
                String comanda = Mock_Presentacio_stdio.llegirString();
                t[i][j] = generaCasellaXYSegonsComanda(comanda, x, y);
            }
        return t;
    }
    */
    private static Casella[][] llegirTauler_String(String t){

        //if(t.contains("\r")) System.out.println("Carriage Return");
        //if(t.contains("\n")) System.out.println("Line Feed");
        //if(t.contains("\r\n")) System.out.println("End Of Line");
        
        t = t.replace("\r\n", "\n"); //Windows format to Unix format
        t = t.replace('\r', '\n'); //Remove Carriage Returns from old MacOS (also System)
      
        //READ SIZE
        String sY = t.substring(0, t.indexOf(','));
        //System.out.println("\"" + sY + "\"");
        int dimY = Integer.parseInt(sY); 
        //System.out.println(" # Files:" + dimY);
        
        String sX = t.substring(t.indexOf(',') + 1, t.indexOf('\n') /*-1*/); // -1 should not be -> \r\n de windows... fak... \n java...
        //System.out.println("\"" + sX + "\"");
        int dimX = Integer.parseInt(sX);
        //System.out.println(" # Colms:" + dimX);
        
        Casella[][] ret = new Casella[dimY][dimX];
        
        //READ TABLE
        int pointer = t.indexOf('\n', 0) + 1;
        String table = t.substring(pointer);
        String[] lines = table.split("\n");
        for (int y = 0; y < dimY; ++y){
            //System.out.println("Line " + y + ":" + lines[y]);
            String[] reads = lines[y].split(",");
            for(int x = 0; x < dimX; ++x){
                String comanda = reads[x];
                ret[y][x] = generaCasellaXYSegonsComanda(comanda, x, y);
            }
        }
        return ret;
    }
    
    // Mètodes per Controladora de Domini (Canvis de Format per Interficie)
    
    public String[][] toFormatInterficie() {
        
        /* 'tauler' Està en format : 
        
            - blanca valor  4
            - blanca buida  ?
            - negra coses   4\3  (columna\fila)
            - negra buida   *
        
        */
        String[][] ret = new String[dimY][dimX]; // x: Nfila; y: Ncolumna.
        for (int i = 0; i < dimY; ++i)
            for (int j = 0; j < dimX; ++j) {
                if(esBlanca(j, i)){
                    Integer v = getValor(j,i);
                    ret[i][j] = "" + (v == null ? "?" : v);
                } else {
                    CasellaNegra casella_negra = (CasellaNegra)tauler[i][j];
                    Integer col = casella_negra.getColumna();
                    Integer fila = casella_negra.getFila();
                    String c = "" + (col == null ? " " : col);
                    String f = "" + (fila == null ? " " : fila);
                    ret[i][j] = c + "\\" + f;
                }
                
            }
        
        return ret;
    }
    
}