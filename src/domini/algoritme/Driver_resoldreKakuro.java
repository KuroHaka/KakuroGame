package domini.algoritme;

import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;
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
    }
    
    private static String input() {
        ///// S'HA DE FER LA FUNCIÓ INPUT /////
        String ret = "resoldreKakuro001.in";
        
        ///////////////////////////////////////
        // AFEGIR CODI PER A LLEGIR INPUT!!! //
        ///////////////////////////////////////
        
        return ret;
    }
    
    private static String output() {
        ///// S'HA DE FER LA FUNCIÓ INPUT /////
        ///// AQUESTA S'HAURÀ DE BORRAR I FER UN ALTRE COP INPUT /////
        String ret = "resoldreKakuro001.out";
        return ret;
    }
    
    public static void main(String[] args) {
        System.out.println("TEST RESOLDRE KAKUROS: ");
        
        String in = input();
        String out = output();
        test(in, out);
    }
}
