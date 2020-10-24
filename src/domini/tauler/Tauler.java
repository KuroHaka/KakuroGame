package domini.tauler;

import domini.tauler.casella.Casella;
import domini.tauler.casella.CasellaBlanca;
import domini.tauler.casella.CasellaNegra;
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
            System.out.printf("|\n");
        }
    }
    
    private Casella[][] llegirTauler(){
        System.out.printf("Numero de columnes (x): ");
        int x = Llibreria.llegirEnter();
        System.out.printf("Numero de files (y): ");
        int y = Llibreria.llegirEnter();
        
        Casella[][] t  = new Casella[y][x];
        for (int i = 0; i < y; ++i)
            for (int j = 0; j < x; ++j){
                Casella c;
                System.out.printf("Valor fila= " + i + ", col= " + j +" : ");
                char ch = Llibreria.llegirCaracter();
                if (ch == '?'){ //Casella Blanca Buida
                    c = new CasellaBlanca(x, y);
                }
                else if (ch == '*'){ //Casella Negra Buida
                    c = new CasellaNegra(x, y, null, null);
                }
                else if (ch == '.'){ //Trigger per llegir valors de Fila i Columna
                    throw new UnsupportedOperationException("Keep calm");
                }
                else{ //Valor Casella Blanca
                    int v = Character.getNumericValue(ch);
                    c = new CasellaBlanca(x, y, v);
                }
                t[i][j] = c;
            }
        return t;
    }
}