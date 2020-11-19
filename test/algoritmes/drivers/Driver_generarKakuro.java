package domini.algoritme;

import domini.tauler.TaulerEnunciat;
import java.util.Scanner;

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
        System.out.println();
    }
    
    private static int input() {
        ///// S'HA DE FER LA FUNCIÓ INPUT /////
        int ret = 4;
        
        ///////////////////////////////////////
        // AFEGIR CODI PER A LLEGIR INPUT!!! //
        ///////////////////////////////////////
        
        Scanner scan = new Scanner(System.in);
        ret = scan.nextInt();
        
        return ret;
    }
    
    public static void main(String[] args) {
        
        boolean fiBucle = false;
        while (!fiBucle) {
            System.out.println("TEST GENERADOR DE KAKUROS: ");
            
            System.out.print("Posa el nombre de files o surt posant \"-1\":");
            int x = input();
            
            if (x < 0) fiBucle = true;
            else {
                System.out.print("Posa el nombre de columnes o surt posant \"-1\":");
                int y = input();
                
                if (y < 0) fiBucle = true;
                else test(x, y);
            }
        }
    }
}
