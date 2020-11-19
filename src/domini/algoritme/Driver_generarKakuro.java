package domini.algoritme;

import domini.tauler.TaulerEnunciat;

public class Driver_generarKakuro {
    public static Algoritme algoritme = new Algoritme();
    
    private static void test(int x, int y) {
        TaulerEnunciat enunciat = algoritme.generarKakuroSimple(x,y,null);
        
        boolean validesa = algoritme.validaFormat(enunciat);
        if (validesa) {
            enunciat.print();
            System.out.println(x+","+y+ ": ha passat el test, és vàlid...");
        }
        else System.out.println(x+","+y+ ": no ha passat el test, no és vàlid...");
    }
    
    private static int input() {
        ///// S'HA DE FER LA FUNCIÓ INPUT /////
        int ret = 4;
        
        ///////////////////////////////////////
        // AFEGIR CODI PER A LLEGIR INPUT!!! //
        ///////////////////////////////////////
        
        return ret;
    }
    
    public static void main(String[] args) {
        System.out.println("TEST GENERADOR DE KAKUROS: ");
        System.out.println("Posa dimensions:");
        
        int x = input();
        int y = input();
        test(x, y);
    }
}
