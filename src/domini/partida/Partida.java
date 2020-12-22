package domini.partida;

import domini.algoritme.Algoritme;
import domini.tauler.Tauler;
import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;
import domini.usuari.Usuari;

public class Partida {
    // TODO
    private String id = "";
    private boolean contrarellotge = false;
    private int tempsAcumulat = 0;
    
    private Usuari usuari;
    
    private TaulerEnunciat enunciat;
    private TaulerComencat comencat;
    private TaulerComencat solucio;
    
    private Algoritme algoritme = new Algoritme();
    
    // Constructora:
    
    public Partida(Usuari usuari, TaulerEnunciat enunciat) {
        this.usuari = usuari;
        this.enunciat = enunciat;
        // TODO : REVISAR, HAURIA DE SER AIXÍ:
        // this.comencat = (TaulerComencat) enunciat;
        this.comencat = new TaulerComencat(enunciat);
        this.solucio = algoritme.resoldreKakuro(enunciat).get(0);
    }
    
    public Partida(Usuari usuari, TaulerEnunciat enunciat, TaulerComencat comencat, int tempsAcumulat, boolean contrarellotge) {
        this.usuari = usuari;
        this.enunciat = enunciat;
        this.comencat = comencat;
        this.solucio = algoritme.resoldreKakuro(enunciat).get(0);
        this.tempsAcumulat = tempsAcumulat;
        this.contrarellotge = contrarellotge;
    }
    
    public boolean setValor(int x, int y, Integer valor) {
        return comencat.setValor(x, y, valor);
    }
    
    public Object[] getAjuda() {
        Object[] ret = algoritme.getAjuda(comencat, solucio);
        return ret;
    }
    
    public Tauler getSolucio(){
        return this.solucio;
    } 
}
