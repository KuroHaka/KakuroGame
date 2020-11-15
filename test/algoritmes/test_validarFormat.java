package algoritmes;

import domini.algoritme.Algoritme;
import domini.tauler.TaulerEnunciat;
import presistencia.Dades;

public class test_validarFormat {
    public static Algoritme algoritme = new Algoritme();
    
    private static void test(String nom, boolean validesaReal) {
        String arxiu = Dades.carregaArxiu("test/algoritmes/"+nom);
        TaulerEnunciat enunciat = new TaulerEnunciat(arxiu);
        boolean validesa = algoritme.validaFormat(enunciat);
        assert validesa == validesaReal;
        
        if (validesa != validesaReal) System.out.println(nom + ": no ha passat el test.");
    }
    
    public static void main(String[] args) {
        System.out.println("TEST VALIDADOR DE FORMATS DE ENUNCIATS: ");
        
        test("validaFormat001.in", false);
        ///// AFEGIR TESTOS /////
        
    }
}
