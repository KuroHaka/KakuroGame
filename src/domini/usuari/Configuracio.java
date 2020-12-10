package domini.usuari;

import domini.tauler.casella.*;

public class Configuracio {
    private int numeroBlanquesEstablertes;
    private int numeroBlanques;
    private int dimX;
    private int dimY;
    public enum Dificultat {
        FACIL,
        DIFICIL,
        EXPERT,
        PERSONALITZAT
    }
    private Dificultat dif;
    
    //Constructoras
    public Configuracio() {
        this.dimX = 8;
        this.dimY = 8;
        this.numeroBlanquesEstablertes = 0;
        this.numeroBlanques = 27;
        this.dif = Dificultat.DIFICIL;
    }

    public Configuracio(Dificultat dif) {
        if(dif == Dificultat.FACIL) {
            this.dimX = 5;
            this.dimY = 5;
            this.numeroBlanquesEstablertes = 4;
            this.numeroBlanques = (int)((dimX - 1) * (dimY - 1) * 0.55);
        }
        else if(dif == Dificultat.DIFICIL) {
            this.dimX = 8;
            this.dimY = 8;
            this.numeroBlanquesEstablertes = 0;
            this.numeroBlanques = (int)((dimX - 1) * (dimY - 1) * 0.55);
        }
        if(dif == Dificultat.EXPERT) {
            this.dimX = 12;
            this.dimY = 12;
            this.numeroBlanquesEstablertes = 0;
            this.numeroBlanques = (int)((dimX - 1) * (dimY - 1) * 0.55);
        }
    }
    
    public Configuracio(int dx, int dy, int nb, int nbe) {
        this.dimX = dx;
        this.dimY = dy;
        this.numeroBlanques = nb;
        this.numeroBlanquesEstablertes = nbe;
        this.dif = Dificultat.PERSONALITZAT;
    }
    
    //Funciones
    
    public int getNumeroBlanques() {
        return numeroBlanques;
    }

    public int getNumeroBlanquesEstablertes() {
        return numeroBlanquesEstablertes;
    }
    
    public int getDimX() {
        return dimX;
    }
    
    public int getDimY() {
        return dimY;
    }

    public void setNumeroBlanques(int nb) {
        this.numeroBlanques = nb;
    }

    public void setNumeroBlanquesEstablertes(int nbe) {
        this.numeroBlanquesEstablertes = nbe;
    }

    public void setDimX(int dimX) {
        this.dimX = dimX;
    }

    public void setDimY(int dimY) {
        this.dimY = dimY;
    }

    public void print() {
        System.out.println("Numero Blanques: " + this.numeroBlanques);
        System.out.println("Numero Blanques Establertes: " + this.numeroBlanquesEstablertes);
        System.out.println("Dimensio X: " + this.dimX + " , Dimensio Y : " + this.dimY);
    }
    
    
}
