package domini.testing;

import domini.algoritme.Algoritme;
import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;
import static domini.testing.Driver_Algoritme_resoldreKakuro.algoritme;

public class Driver_Algoritme_validaSolucioKakuro {
    public static Algoritme algoritme = new Algoritme();

    public static void main(String[] args) {
        
        System.out.println("TESTING VALIDADOR DE SOLUCIONS DE KAKURO: ");
        
        TaulerComencat s;
        s = algoritme.generarKakuroSimpleSolucionat(7,9);
        s.print();
        boolean valid = algoritme.validaSolucio(s);
        System.out.println("Aquesta solució de kakuro és " + (valid ? "vàlida" : "invàlida"));
        
        //Canviem el valor de la primera fixta (Sempre és blanca amb l'algoritme de generació simple)
        s.setValor(1,1,9);
        s.print();
        valid = algoritme.validaSolucio(s);
        System.out.println("Aquesta solució de kakuro és " + (valid ? "vàlida" : "invàlida"));
    }
    
}