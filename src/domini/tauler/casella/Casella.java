package domini.tauler.casella;

public abstract class Casella {
    private int coordX;
    private int coordY;
    //TODO
    public Casella(int x, int y){
        this.coordX = x;
        this.coordY = y;
    }

    public int getCoordX() {
        return coordX;
    }
    
    public int getCoordY() {
        return coordY;
    }
    
    public abstract String to_String();
    
    public abstract String save_String();
    
    public void print(){
        System.out.print(this.to_String());
    }
    
    
}
