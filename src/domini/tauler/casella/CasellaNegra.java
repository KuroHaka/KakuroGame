package domini.tauler.casella;

public class CasellaNegra extends Casella{
    
    private Integer fila;
    private Integer columna;
    
    public CasellaNegra(int x, int y) {
        super(x, y);
        this.fila = null;
        this.columna = null;
    }
    
    public CasellaNegra(int x, int y, Integer f, Integer c) {
        super(x, y);
        this.fila = f;
        this.columna = c;
    }
    
    public String to_String() {
        String str;
        if (this.fila != null)
            str = "" + this.fila + "\\";
        else str = "_\\";
        if (this.columna != null)
            str += this.columna;
        else str += "_";
        return str;
    }

    public Integer getFila() {
        return fila;
    }

    public Integer getColumna() {
        return columna;
    }
}
