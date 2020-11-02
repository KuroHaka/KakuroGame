package domini.testing;

import domini.algoritme.Algoritme;
import domini.tauler.TaulerEnunciat;
import static domini.testing.Driver_Algoritme_resoldreKakuro.algoritme;

public class Driver_Algoritme_generarKakuro {
    public static Algoritme algoritme = new Algoritme();

    public static void main(String[] args) {
        System.out.println("TESTING GENERADOR DE KAKURO: ");
        TaulerEnunciat t;
        t = algoritme.generarKakuroSimple(7,9,null);
        t.print();
        t = algoritme.generarKakuroSimple(10,10,5);
        t.print();
    }
    
}