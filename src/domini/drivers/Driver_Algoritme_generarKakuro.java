package domini.drivers;

import domini.algoritme.Algoritme;
import domini.tauler.TaulerEnunciat;

public class Driver_Algoritme_generarKakuro {
    public static Algoritme algoritme = new Algoritme();

    public static void main(String[] args) {
        System.out.println("TESTING GENERADOR DE KAKURO: ");
        TaulerEnunciat t;
        t = algoritme.generarKakuro(7,9,30,10);
        t.print();
        t = algoritme.generarKakuro(10,10,50,10);
        t.print();
    }
    
}