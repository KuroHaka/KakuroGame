package domini.tauler;

import domini.tauler.casella.Casella;
import domini.tauler.casella.CasellaBlanca;
import domini.tauler.casella.CasellaNegra;
import domini.testing.Mock_Presentacio_stdio;


public abstract class Tauler {
    private int id;
    private int dimX;
    private int dimY;
    private Casella[][] tauler;
    // private Dificultat dificultat; // TODO: Dificultat

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
                    int v = Character.getNumericValue(ch);
                    c = new CasellaBlanca(x, y, v);
                }
                t[i][j] = c;
            }
        return t;
    }
    
    private Casella[][] llegirTauler_String(String t){
        //TODO
        return null;
    }
    
    public /*private*/ String guardarTauler_String(){
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
}