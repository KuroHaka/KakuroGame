package domini.drivers;

import domini.algoritme.Algoritme;
import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;
import presistencia.Dades;

public class Driver_Algoritme_resoldreKakuro {
    public static Algoritme algoritme;

    public static void main(String[] args) {
        algoritme  = new Algoritme();
        int test = 0; // 0: test random de generador 1: test de un fitxer seleccionat
        switch(test) {
            case 0:
                for (int i = 0; i < 10; ++i){
                    TaulerEnunciat s = algoritme.generarKakuroSimple(10,10,null);
                    TaulerComencat solucio = algoritme.resoldreKakuro(s);
                    boolean valid = algoritme.validaSolucio(solucio);
                    solucio.print();
                    System.out.println("Aquesta solució de kakuro és " + (valid ? "vàlida" : "invàlida"));
                }
                break;

            case 1:
                System.out.println("TAULER EXEMPLE: ");
                String e2 = Dades.carregaArxiu("dades/exemple.txt");
                TaulerEnunciat t = new TaulerEnunciat(e2);
                t.print();
                System.out.println("\n\nTAULER RESOLT: ");
                TaulerComencat solucio = algoritme.resoldreKakuro(t);
                solucio.print();
                break;
            default:
                break;
        }

    }
}
