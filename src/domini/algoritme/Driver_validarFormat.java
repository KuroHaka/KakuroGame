package domini.algoritme;

import domini.tauler.TaulerEnunciat;
import presistencia.Dades;

public class Driver_validarFormat {
    public static Algoritme algoritme = new Algoritme();
    
    private static void test(String nom) {
        String arxiu = Dades.carregaArxiu("test/algoritmes/validarFormat/"+nom);
        TaulerEnunciat enunciat = new TaulerEnunciat(arxiu);
        enunciat.print();
        
        boolean validesa = algoritme.validaFormat(enunciat);
        if (validesa) System.out.println(nom + ": ha passat el test, és vàlid...");
        else System.out.println(nom + ": no ha passat el test, no és vàlid...");
    }
    
    private static String input() {
        ///// S'HA DE FER LA FUNCIÓ INPUT /////
        String ret = "validaFormat001.in";
        
        ///////////////////////////////////////
        // AFEGIR CODI PER A LLEGIR INPUT!!! //
        ///////////////////////////////////////
        
        return ret;
    }
    
    public static void main(String[] args) {
        System.out.println("TEST VALIDADOR DE FORMATS D'ENUNCIATS: ");
        System.out.println("Afegeix el nom d'un arxiu per a comprovar:");
        String in = input();
        test(in);
    }
}
