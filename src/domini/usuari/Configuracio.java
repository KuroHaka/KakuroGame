package domini.usuari;

import domini.tauler.casella.*;

public class Configuracio {
    private int numeroBlanquesEstablertes;
    private int numeroBlanques;
    private int dimX;
    private int dimY;
    private int dif; //si dif == 1 --> Principiant, == 2 --> Intermig, == 3 --> Expert, == 0 --> No asignada
    
    //Constructoras
    public Configuracio() {
        this.numeroBlanquesEstablertes = 10;
        this.numeroBlanques = 20;
        this.dimX = 8;
        this.dimY = 8;
        this.dif = 1;
    }
    
    public Configuracio(Casella[][] c) {
        this.dimX = c.length;
        this.dimY = c[0].length;
        int cont = 0;
        int est = 0;
        for(int i = 0; i < this.dimX; ++i) {
            for(int j = 0; j < this.dimY; ++j) {
                if(c[i][j].getClass() == (CasellaBlanca.class)) {
                    ++cont;
                    if(((CasellaBlanca)c[i][j]).getValor() != null) {
                        ++est;
                    }
                }
            }
        }
        this.numeroBlanquesEstablertes = est;
        this.numeroBlanques = cont;
        float f = (this.numeroBlanques - this.numeroBlanquesEstablertes)/((this.dimX - 1)* (this.dimY - 1));
        if(f > 0.8) {this.dif = 3;}
        else if(f > 0.5) {this.dif = 2;}
        else {this.dif = 1;}  //Establertes les normes de dificultat
    }
    
    public Configuracio(int dx, int dy, int nb, int nbe) {
        this.dimX = dx;
        this.dimY = dy;
        this.numeroBlanques = nb;
        this.numeroBlanquesEstablertes = nbe;
        float f = (this.numeroBlanques - this.numeroBlanquesEstablertes)/((this.dimX - 1)* (this.dimY - 1));
        if(f > 0.8) {this.dif = 3;}
        else if(f > 0.5) {this.dif = 2;}
        else {this.dif = 1;}
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
        System.out.println("Numero Blanques Establertes: " + this.numeroBlanquesEstablertes);
        System.out.println("Dimensio X: " + this.dimX + " , Dimensio Y : " + this.dimY);
        if(this.dif == 1) {System.out.println("Dificultat: Principiant");}
        if(this.dif == 2) {System.out.println("Dificultat: Avançada");}
        if(this.dif == 3) {System.out.println("Dificultat: Expert");}
    }
    
    
}
