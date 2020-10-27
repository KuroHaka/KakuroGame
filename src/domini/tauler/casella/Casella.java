package domini.tauler.casella;

public abstract class Casella {
    
    // ATRIBUTS PRIVATS
    
    private int coordX;
    private int coordY;
    
    // CONSTRUCTORA
    
    public Casella(int x, int y){
        this.coordX = x;
        this.coordY = y;
    }
    
    // ENCAPSULACIONS

    public int getCoordX() {
        return coordX;
    }
    
    public int getCoordY() {
        return coordY;
    }
    
    // MÈTODES ABSTRACTES
    
    public abstract String to_String();
    
    public abstract String save_String();
    
    // MÈTODES PÚBLICS
    
    public void print(){
        System.out.print(this.to_String());
    }
    
    
}
