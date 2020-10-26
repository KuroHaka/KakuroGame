package domini.testing;

import domini.tauler.Tauler;
import domini.tauler.TaulerEnunciat;

public class Driver_Tauler {
    
    public static void main(String[] args){
        Tauler t = new TaulerEnunciat();
        t.print();
        System.out.println("PRINT FILE TO SAVE: ");
        System.out.print(t.guardarTauler_String());
    }
}
