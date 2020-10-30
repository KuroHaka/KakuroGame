package domini.tauler;

public class TaulerComencat extends Tauler{
    
    private void recalculaId(){
        this.id = calculaHash(format_Estandard());
    }
}
