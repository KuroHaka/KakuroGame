package domini.tauler;

import domini.tauler.casella.Casella;
import domini.tauler.casella.CasellaBlanca;

public class TaulerComencat extends Tauler{
    
    // CONSTRUCTORES
    
    public TaulerComencat(){
        super();
    }
    
    public TaulerComencat(Casella[][] t){
        super(t);
    }
    
    public TaulerComencat(String t){
        super(t);
    }
    
    // MÈTODES PÚBLICS
    
    public boolean setValor(Integer x, Integer y, Integer valor){
        // En un tauler començat, només es pot canviar el valor d'una casella blanca.
        if(tauler[y][x].getClass() != CasellaBlanca.class) return false;
        return ((CasellaBlanca)tauler[y][x]).setValor(valor);
    }
    
    // MÈTODES PRIVATS
    
    private void recalculaId(){
        this.id = calculaHash(format_Estandard());
    }
}
