package domini.testing;

import domini.algoritme.Algoritme;
import domini.tauler.TaulerEnunciat;
import presistencia.Dades;

public class Driver_Algoritme_resoldreKakuro {
    public static Algoritme algoritme = new Algoritme();

    public static void main(String[] args) {

        System.out.println("TAULER EXEMPLE: ");
        String e2 = Dades.carregaArxiu("dades/exemple.txt");
        TaulerEnunciat t = new TaulerEnunciat(e2);
        t.print();

        System.out.println("\n\nTAULER RESOLT: ");
        algoritme.resoldreKakuro(t);
        t.print();

    }
}