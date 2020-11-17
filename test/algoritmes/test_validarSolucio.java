package algoritmes;

import domini.algoritme.Algoritme;
import domini.tauler.TaulerComencat;
import presistencia.Dades;

public class test_validarSolucio {
    // Donat una solució, s'ha de veure si és correcte
    public static Algoritme algoritme = new Algoritme();
    
    public static void test(String nom, boolean validesaReal) {
        String arxiu = Dades.carregaArxiu("test/algoritmes/"+nom);
        TaulerComencat tauler = new TaulerComencat(arxiu);
        
        boolean validesa = algoritme.validaSolucio(tauler);
        
        assert validesa == validesaReal;
        if (validesa != validesaReal) System.out.println(nom + ": no ha passat el test.");
    }
    
    public static void main(String[] args) {
        System.out.println("TEST VALIDADOR DE SOLUCIONS DE KAKUROS: ");
        
        System.out.println("Test 001...");
        test("validaSolucio001.in", true);
        System.out.println("Test 002...");
        test("validaSolucio002.in", false);
        System.out.println("Test 003...");
        test("validaSolucio003.in", false);
        System.out.println("Test 004...");
        ///// AFEGIR TESTOS /////
    }
}
