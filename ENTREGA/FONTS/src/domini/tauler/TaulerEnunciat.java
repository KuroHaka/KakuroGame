package domini.tauler;

import domini.tauler.casella.Casella;

public class TaulerEnunciat extends Tauler{
    //TODO
    public TaulerEnunciat(){
        super();
    }
    
    public TaulerEnunciat(Casella[][] t){
        super(t);
    }
    
    public TaulerEnunciat(String t){
        super(t);
        //super(llegirTauler_String(t)); // Mètode Static que retorna en format matriu
    }
    public TaulerComencat comencarTauler(){
        return new TaulerComencat(this.format_Estandard());
    }
}
