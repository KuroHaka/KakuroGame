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
        
        System.out.println("Test 001...");
        test("validaFormat001.in", true);
        System.out.println("Test 002...");
        test("validaFormat002.in", false);
        System.out.println("Test 003...");
        test("validaFormat003.in", false);
        System.out.println("Test 004...");
        test("validaFormat004.in", false);
        System.out.println("Test 005...");
        test("validaFormat005.in", false);
        System.out.println("Test 006...");
        test("validaFormat006.in", false);
        System.out.println("Test 007...");
        test("validaFormat007.in", false);
        System.out.println("Test 008...");
        test("validaFormat008.in", false);
        System.out.println("Test 009...");
        test("validaFormat009.in", false);
        System.out.println("Test 010...");
        test("validaFormat010.in", false);
        System.out.println("Test 011...");
        test("validaFormat011.in", false);
        System.out.println("Test 012...");
        test("validaFormat012.in", false);
        System.out.println("Test 013...");
        test("validaFormat013.in", false);
        System.out.println("Test 014...");
        test("validaFormat014.in", true);
        System.out.println("Test 015...");
        test("validaFormat015.in", true);
        System.out.println("Test 016...");
        test("validaFormat016.in", true);
    }
}
