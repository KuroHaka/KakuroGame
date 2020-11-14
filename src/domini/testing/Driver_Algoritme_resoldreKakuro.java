package domini.testing;

import domini.algoritme.Algoritme;
import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;
import presistencia.Dades;

public class Driver_Algoritme_resoldreKakuro {
    public static Algoritme algoritme;

    public static void main(String[] args) {
        algoritme  = new Algoritme();
        for (int i = 0; i < 10; ++i){
            TaulerEnunciat s = algoritme.generarKakuroSimple(6,6,null);
            TaulerComencat solucio = algoritme.resoldreKakuro(s);
            boolean valid = algoritme.validaSolucio(solucio);
            solucio.print();
            System.out.println("Aquesta solució de kakuro és " + (valid ? "vàlida" : "invàlida"));
        }
/*
        System.out.println("TAULER EXEMPLE: ");
        String e2 = Dades.carregaArxiu("dades/exemple.txt");
        TaulerEnunciat t = new TaulerEnunciat(e2);
        t.print();
        System.out.println("\n\nTAULER RESOLT: ");
        TaulerComencat solucio = algoritme.resoldreKakuro(t);
        solucio.print();
*/



    }
}
