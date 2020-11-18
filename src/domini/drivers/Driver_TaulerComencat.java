package domini.drivers;

import domini.tauler.Tauler;
import domini.tauler.TaulerComencat;
import presistencia.Dades;

public class Driver_TaulerComencat {
    
    public static void main(String[] args){

        System.out.println("TAULER EXEMPLE : ");
        String ex = Dades.carregaArxiu("dades/exemple.txt");
        TaulerComencat exemple = new TaulerComencat(ex);
        exemple.print();

        System.out.println("GETS : ");
        System.out.println("getId() = " + exemple.getId());
        System.out.println("getDimX() = " + exemple.getDimX());
        System.out.println("getDimY() = " + exemple.getDimY());
        
        System.out.println("altres MÈTODES : ");
        System.out.println("esNegra(2, 3) = " + exemple.esNegra(2, 3));
        System.out.println("esBlanca(3, 0) = " + exemple.esBlanca(3, 0));
        
        System.out.println("FER CANVIS AL TAULER EXEMPLE : ");
        System.out.println("setValor(2, 1, 8) = " + exemple.setValor(2, 1, 8));
        exemple.print();
        
        /*System.out.println("TAULER STANDARD INPUT: ");
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
        */
    }
}
