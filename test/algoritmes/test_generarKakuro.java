package algoritmes;

import domini.algoritme.Algoritme;
import domini.tauler.TaulerEnunciat;

public class test_generarKakuro {
    public static Algoritme algoritme = new Algoritme();
    
    private static void test(int x, int y, boolean validesaReal) {
        TaulerEnunciat enunciat = algoritme.generarKakuroSimple(x,y,null);
        //enunciat.print();
        boolean validesa = algoritme.validaFormat(enunciat);
        
        assert validesa == validesaReal;
        if (validesa != validesaReal) {
            System.out.println(x+","+y+ ": no ha passat el test.");
            enunciat.print();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("TEST GENERADOR DE KAKUROS: ");
        
        for (int k = 0; k < 10; k++) {
            for (int i = 1; i <= 20; i++) {
                for (int j = 1; j <= 20; j++) {
                    System.out.println("Test " + i + "," + j + ":");
                    test(i, j, true);
                }
            }
        }
    }
}
