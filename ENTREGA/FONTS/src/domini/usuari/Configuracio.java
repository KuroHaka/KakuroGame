package domini.usuari;

import domini.tauler.casella.Casella;

public class Configuracio {
    private int numeroBlanques;
    private int dimX;
    private int dimY;
    private int dif; //si dif == 1 --> Principiant, == 2 --> Intermig, == 3 --> Expert, == 0 --> No asignada
    
    //Constructoras
    public Configuracio() {
        this.numeroBlanques = 0;
        this.dimX = 0;
        this.dimY = 0;
        this.dif = 0;
    }
    
    public Configuracio(Casella[][] c) {
        this.dimX = c.length;
        this.dimY = c[0].length;
        this.dif = 0; //COM LA DECIDIM????
        int cont = 0;
        /*for(int i = 0; i < this.dimX; ++i) {
            for(int j = 0; j < this.dimY; ++j) {
                if(CASELLA[i][j] == ES BLANCA) {++cont;}
            }
        }*/
        this.numeroBlanques = cont;
    }
    
    public Configuracio(int dx, int dy, int nb) {
        this.dimX = dx;
        this.dimY = dy;
        this.dif = 0; //COM LA DECIDIM????
        this.numeroBlanques = nb;
    }
    
    //Funciones
    
    public int getNumeroBlanques() {
        return numeroBlanques;
    }
    
    public int getDimX() {
        return dimX;
    }
    
    public int getDimY() {
        return dimY;
    }
    
    public int getDificultat() {
        return dif;
    }
    
    public void print() {
        System.out.println("Numero Blanques: " + this.numeroBlanques);
        System.out.println("Dimensio X: " + this.dimX + " , Dimensio Y : " + this.dimY);
        System.out.println("Dificultat: " + this.dif);
    }
    
    
}
