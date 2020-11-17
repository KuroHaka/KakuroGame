package domini.drivers;

import domini.algoritme.Algoritme;
import domini.tauler.TaulerEnunciat;
import presistencia.Dades;

public class Driver_Algortime_validaFormatEnunciat {
    public static Algoritme algoritme = new Algoritme();
    
    public static void main(String[] args) {
        
        System.out.println("DRIVER VALIDADOR DE FORMATS DE ENUNCIATS: ");
        
        TaulerEnunciat s;
        for (int i = 0; i < 30; ++i){
            s = algoritme.generarKakuroSimple(7,9,null);
            boolean valid = algoritme.validaFormat(s);
            System.out.println("Aquest enunciat de kakuro té format " + (valid ? "vàlid" : "invàlid"));
        }
        
        //Cal fer load d'un enunciat i comprovar totes les propietats.
        s = new TaulerEnunciat(Dades.carregaArxiu("dades/format_incorrecte.txt"));
        s.print();
        boolean valid = algoritme.validaFormat(s);
        System.out.println("Aquest enunciat de kakuro té format " + (valid ? "vàlid" : "invàlid"));
    }
}
