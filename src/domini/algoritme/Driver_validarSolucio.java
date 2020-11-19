package domini.algoritme;

import domini.tauler.TaulerComencat;
import java.util.Scanner;
import presistencia.Dades;

public class Driver_validarSolucio {
    public static Algoritme algoritme = new Algoritme();
    
    static String path = "test/algoritmes/validarSolucio/";
    
    public static void test(String nom) {
        String arxiu = Dades.carregaArxiu(path + nom);
        TaulerComencat tauler = new TaulerComencat(arxiu);
        tauler.print();
        
        boolean validesa = algoritme.validaSolucio(tauler);
        if (validesa) System.out.println(nom + ": ha passat el test, és vàlid...");
        else System.out.println(nom + ": no ha passat el test, no és vàlid...");
    }
    
    private static String input() {
        ///// S'HA DE FER LA FUNCIÓ INPUT /////
        String ret = "validaSolucio001.in";
        
        ///////////////////////////////////////
        // AFEGIR CODI PER A LLEGIR INPUT!!! //
        ///////////////////////////////////////
        
        Scanner scan = new Scanner(System.in);
        //System.out.println(" 1- Entrada tauler per directori");
        //System.out.println(" Indica el path relatiu al .jar: ");
        ret = scan.next();
        //String e2 = Dades.carregaArxiu(path);
        
        return ret;
    }
    
    public static void main(String[] args) {
        System.out.println("TEST VALIDADOR DE SOLUCIONS DE KAKUROS: ");
        System.out.println("Afegeix el nom d'un arxiu per a comprovar:");
        String in = input();
        test(in);
    }
}
