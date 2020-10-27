package domini.tauler;

import domini.tauler.casella.Casella;
import domini.tauler.casella.CasellaBlanca;
import domini.tauler.casella.CasellaNegra;
import interficie.testing.Mock_Presentacio_stdio;


public abstract class Tauler {
    
    // ATRIBUTS PRIVATS
    
    private int id;
    private int dimX;
    private int dimY;
    private Casella[][] tauler;
    // private Dificultat dificultat;

    // CONSTRUCTORES
    
    public Tauler(){
        this.tauler = llegirTauler_interface(); //llegirTauler();
        this.dimX = tauler[0].length;
        this.dimY = tauler.length;
        this.id = 0; // TODO
    }
    
    public Tauler(Casella[][] t){
        this.tauler = t;
        this.dimX = tauler[0].length;
        this.dimY = tauler.length;
        this.id = 0; // TODO
    }
    
    public Tauler(String t){
        this(llegirTauler_String(t)); // Mètode Static que retorna en format matriu
    }
    
    // ENCAPSULACIONS
    
    public int getId() {
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
                System.out.printf("|");
                tauler[i][j].print();
            }
            System.out.printf("|\n");
        }
    }
    
    // MÈTODES PÚBLICS
    
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
    
    private Casella[][] llegirTauler_interface(){
        System.out.printf("Numero de columnes (x): ");
        int x = Mock_Presentacio_stdio.llegirEnter();
        System.out.printf("Numero de files (y): ");
        int y = Mock_Presentacio_stdio.llegirEnter();
        
        Casella[][] t  = new Casella[y][x];
        for (int i = 0; i < y; ++i)
            for (int j = 0; j < x; ++j){
                Casella c;
                System.out.printf("Valor fila= " + i + ", col= " + j +" : ");
                String comanda = Mock_Presentacio_stdio.llegirString();
                t[i][j] = generaCasellaXYSegonsComanda(comanda, x, y);
            }
        return t;
    }
    
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
    
}