package domini.drivers;

import domini.tauler.Tauler;
import domini.tauler.TaulerEnunciat;
import presistencia.Dades;

public class Driver_TaulerEnunciat {
    
    public static void main(String[] args){

        System.out.println("TAULER EXEMPLE : ");
        String ex = Dades.carregaArxiu("dades/exemple.txt");
        TaulerEnunciat exemple = new TaulerEnunciat(ex);
        exemple.print();

        System.out.println("GETS : ");
        System.out.println("getId() = " + exemple.getId());
        System.out.println("getDimX() = " + exemple.getDimX());
        System.out.println("getDimY() = " + exemple.getDimY());
        
        System.out.println("altres MÈTODES : ");
        System.out.println("esNegra(3, 0) = " + exemple.esNegra(3, 0));
        System.out.println("esBlanca(4, 6) = " + exemple.esBlanca(4, 6));

        exemple.print();
    }
}
