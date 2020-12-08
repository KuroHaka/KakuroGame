package domini.tauler;

import domini.hashing.Hash;
import domini.tauler.casella.Casella;
import domini.tauler.casella.CasellaBlanca;

public class TaulerComencat extends Tauler{
    
    // CONSTRUCTORES
    
    /*public TaulerComencat(){
        //super();
    }*/
    
    public TaulerComencat(Casella[][] t){
        super(t);
    }
    
    public TaulerComencat(String t){
        super(t);
    }

    public TaulerComencat(TaulerEnunciat enunciat) {
        this.tauler = enunciat.tauler; //llegirTauler();
        this.dimX = enunciat.dimX;
        this.dimY = enunciat.dimY;
        /// TODO : IMPORTANT!!!
        this.id = Hash.calculaHash(format_Estandard());
    }
    
    // MÈTODES PÚBLICS
    
    public boolean setValor(Integer x, Integer y, Integer valor){
        // En un tauler començat, només es pot canviar el valor d'una casella blanca.
        if(tauler[y][x].getClass() != CasellaBlanca.class) return false;
        boolean ok = ((CasellaBlanca)tauler[y][x]).setValor(valor);
        if (ok) recalculaId();
        return ok;
    }
    
    // MÈTODES PRIVATS
    
    private void recalculaId(){
        this.id = Hash.calculaHash(format_Estandard());
    }
}
