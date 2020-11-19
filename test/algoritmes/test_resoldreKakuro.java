package algoritmes;

import domini.algoritme.Algoritme;
import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;
import presistencia.Dades;

public class test_resoldreKakuro {
    public static Algoritme algoritme = new Algoritme();
    
    private static void test(String enu, String sol) {
        String enun = Dades.carregaArxiu("test/algoritmes/"+enu);
        TaulerEnunciat enunciat = new TaulerEnunciat(enun);
        
        boolean validesa = algoritme.validaFormat(enunciat);
        assert validesa == true;
        if (validesa == false) {
            System.out.println(enu + ": no ha passat el test per enunciat incorrecte.");
            return;
        }
        
        String solu = Dades.carregaArxiu("test/algoritmes/"+sol);
        TaulerComencat solucio = new TaulerComencat(solu);
        
        TaulerComencat resolucioAux = algoritme.resoldreKakuro(enunciat);
        TaulerComencat resolucio = new TaulerComencat(resolucioAux.format_Estandard());
        
        assert resolucio.getId().equals(solucio.getId());
        if (!resolucio.getId().equals(solucio.getId())) {
            System.out.println(enu + ": no ha passat el test.");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("TEST RESOLDRE KAKUROS: ");
        
        System.out.println("Test 001...");
        test("resoldreKakuro001.in", "resoldreKakuro001.out");
    }
}
