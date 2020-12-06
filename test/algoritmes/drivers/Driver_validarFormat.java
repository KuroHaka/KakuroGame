package domini.algoritme;

import domini.tauler.TaulerEnunciat;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Dades;

public class Driver_validarFormat {
    public static Algoritme algoritme = new Algoritme();
    
    static String path = "test/algoritmes/validarFormat/";
    
    private static void test(String nom) {
        String arxiu = "";
        try {
            arxiu = Dades.carregaArxiu(path + nom);
        } catch (NoSuchFileException ex) {
            Logger.getLogger(Driver_validarFormat.class.getName()).log(Level.SEVERE, null, ex);
        }
        TaulerEnunciat enunciat = new TaulerEnunciat(arxiu);
        enunciat.print();
        
        boolean validesa = algoritme.validaFormat(enunciat);
        if (validesa) System.out.println(nom + ": ha passat el test, és vàlid...");
        else System.out.println(nom + ": no ha passat el test, no és vàlid...");
        System.out.println();
    }
    
    private static String input() {
        ///// S'HA DE FER LA FUNCIÓ INPUT /////
        String ret = "validaFormat001.in";
        
        ///////////////////////////////////////
        // AFEGIR CODI PER A LLEGIR INPUT!!! //
        ///////////////////////////////////////
        
        Scanner scan = new Scanner(System.in);
        ret = scan.next();
        
        return ret;
    }
    
    public static void main(String[] args) {
        boolean fiBucle = false;
        while (!fiBucle) {
            System.out.println("TEST VALIDADOR DE FORMATS D'ENUNCIATS: ");
            System.out.println("Posa el nom de l'arxiu o surt prement \"p\":");
            String in = input();
            if (in.equals("p")) {
                fiBucle = true;
            }
            else test(in);
        }
    }
}
