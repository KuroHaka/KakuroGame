package domini.algoritme;

import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;
import java.util.Scanner;
import presistencia.Dades;

public class Driver_resoldreKakuro {
    public static Algoritme algoritme = new Algoritme();
    
    static String path = "test/algoritmes/resoldreKakuro/";
    
    private static void test(String enu, String sol) {
        String enun = Dades.carregaArxiu(path + enu);
        TaulerEnunciat enunciat = new TaulerEnunciat(enun);
        
        boolean validesa = algoritme.validaFormat(enunciat);
        if (validesa == false) {
            System.out.println(enu + ": no ha passat el test per enunciat incorrecte.");
            return;
        }
        
        String solu = Dades.carregaArxiu(path + sol);
        TaulerComencat solucio = new TaulerComencat(solu);
        
        TaulerComencat resolucioAux = algoritme.resoldreKakuro(enunciat);
        TaulerComencat resolucio = new TaulerComencat(resolucioAux.format_Estandard());
        
        assert resolucio.getId().equals(solucio.getId());
        if (resolucio.getId().equals(solucio.getId())) {
            resolucio.print();
            System.out.println(enu + ": ha passat el test, és vàlid!!!");
        }
        else System.out.println(enu + ": no ha passat el test, no és vàlid...");
        System.out.println();
    }
    
    private static String input() {
        ///// S'HA DE FER LA FUNCIÓ INPUT /////
        String ret = "resoldreKakuro001.in";
        
        ///////////////////////////////////////
        // AFEGIR CODI PER A LLEGIR INPUT!!! //
        ///////////////////////////////////////
        
        Scanner scan = new Scanner(System.in);
        ret = scan.next();
        
        return ret;
    }
    
    private static String output() {
        ///// S'HA DE FER LA FUNCIÓ INPUT /////
        ///// AQUESTA S'HAURÀ DE BORRAR I FER UN ALTRE COP INPUT /////
        String ret = "resoldreKakuro001.out";
        
        Scanner scan = new Scanner(System.in);
        ret = scan.next();
        
        return ret;
    }
    
    public static void main(String[] args) {
        
        boolean fiBucle = false;
        while (!fiBucle) {
            System.out.println("TEST RESOLDRE KAKUROS: ");
            System.out.println("Posa el nom de l'arxiu\".in\" o surt prement \"p\":");
            System.out.println("ASSUMIREM QUE EL \".OUT\" TÉ EL MATEIX NOM QUE EL \".IN\"");
            String in = input();
            if (in.equals("p")) {
                fiBucle = true;
            }
            else {
                String out = in.substring(0, in.length()-2);
                out = out + "out";
                test(in,out);
            }
        }
    }
}
