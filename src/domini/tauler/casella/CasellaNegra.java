package domini.tauler.casella;

public class CasellaNegra extends Casella{
    
    // ATRIBUTS PRIVATS
    
    private Integer fila;
    private Integer columna;
    
    // CONSTRUCTORES
    
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
    
    // ENCAPSULACIONS
    
    public Integer getFila() {
        return fila;
    }

    public Integer getColumna() {
        return columna;
    }
    
    // MÈTODES PÚBLICS
    
    public String to_String() {
        String str;
        if (this.columna != null)
            str = "" + this.columna + "\\";
        else str = "_\\";
        if (this.fila != null)
            str += this.fila;
        else str += "_";
        return str;
    }

    public String save_String() {
        String ret = "";
        if(columna!=null) ret = ret + "C" + columna.toString();
        if(fila!=null) ret = ret + "F" + fila.toString();
        if(fila==null && columna==null) ret = "*";
        return ret;
    }
}
