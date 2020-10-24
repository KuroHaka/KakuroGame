package domini.tauler;

import domini.tauler.casella.Casella;
import domini.tauler.casella.CasellaBlanca;
import domini.testing.Llibreria;


public abstract class Tauler {
    private int id;
    private int dimX;
    private int dimY;
    private Casella[][] tauler;
    // private Dificultat dificultat; // TODO: Dificultat

    public Tauler(){
        this.tauler = llegirTauler();
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
            System.out.printf("|");
        }
    }
    
    private Casella[][] llegirTauler(){
        System.out.printf("Numero de columnes (x): ");
        int x = Llibreria.llegirEnter();
        System.out.printf("Numero de files (y): ");
        int y = Llibreria.llegirEnter();
        
        Casella[][] t  = new Casella[x][y];
        for (int i = 0; i < x; ++i)
            for (int j = 0; j < y; ++j){
                CasellaBlanca c;
                System.out.printf("Valor fila=" + x + "], col=" + y +" :");
                char ch = Llibreria.llegirCaracter();
                if (ch == '?'){
                    c = new CasellaBlanca(x,y);
                }
                else{
                    int v = Character.getNumericValue(ch);
                    c = new CasellaBlanca(x,y,v);
                }
                t[i][j] = c;
            }
        return t;
    }
}