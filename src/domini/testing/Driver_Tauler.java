package domini.testing;

import domini.tauler.Tauler;
import domini.tauler.TaulerEnunciat;
import domini.tauler.casella.Casella;
import domini.tauler.casella.CasellaBlanca;
import domini.tauler.casella.CasellaNegra;
import presistencia.Dades;

public class Driver_Tauler {
    
    public static void main(String[] args){

        System.out.println("TAULER STANDARD INPUT: ");
        Tauler t = new TaulerEnunciat(); // LLegeix el tauler per std in
        
        System.out.println("TAULER HUMAN FRIENDLY: ");
        t.print();
        
        System.out.println("TAULER FORMAT STANDARD: ");
        String save = t.format_Estandard();
        System.out.print(save);
        
        System.out.println("GUARAR TAULER A ARXIU ");
        Dades.guardarArxiu("dades/test101.txt", save);
        
        System.out.println("ARXIU GUARDAT: ");
        String load = Dades.carregaArxiu("dades/test101.txt");
        System.out.print(load);
                
        System.out.println("LLEGIR TAULER FORMAT STANDARD: ");
        Tauler t_new = new TaulerEnunciat(load);
        t_new.print();
        System.out.println("DONE");
        
        System.out.println("TAULER TEST 101 : ");
        String e1 = Dades.carregaArxiu("dades/test101.txt");
        Tauler t_exemple = new TaulerEnunciat(e1);
        t_exemple.print(); //Human friendly format

        System.out.println("TAULER EXEMPLE : ");
        String e2 = Dades.carregaArxiu("dades/exemple.txt");
        //String exemple = Dades.carregaArxiu("dades/test101.txt");
        Tauler t_exemple2 = new TaulerEnunciat(e2);
        t_exemple2.print(); //Human friendly format

        // FALTA CANVIS AL TAULER (Set caselles)
    }
}
