package domini.testing;

import domini.tauler.Tauler;
import domini.tauler.TaulerEnunciat;
import presistencia.Dades;

public class Driver_Tauler {
    
    public static void main(String[] args){
        
        Tauler t = new TaulerEnunciat(); // LLegeix el tauler per std in
        
        System.out.println("TAULER HUMAN FRIENDLY: ");
        t.print();
        
        System.out.println("TAULER FORMAT STANDARD: ");
        String save = t.format_Estandard();
        System.out.print(save);
        
        System.out.println("GUARDANT TAULER A ARXIU... ");
        Dades.guardarArxiu("dades/test101.txt", save);
        
        System.out.println("ARXIU GUARDAT: ");
        String load = Dades.carregaArxiu("dades/test101.txt");
        System.out.print(load);
                
        System.out.println("LLEGINT TAULER FORMAT STANDARD: ");
        Tauler t_new = new TaulerEnunciat(load);
        t_new.print();
        System.out.println("DONE");
    }
}
