
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
        return this.fila;
    }

    public Integer getColumna() {
        return this.columna;
    }
    
    // MÈTODES PÚBLICS
    
    @Override
    public String to_String() {
        String str;
        if (this.columna == null && this.fila == null)
            str = "*****";
        else {
            if (this.columna != null) {
                if (this.columna > 9)
                    str = "" + this.columna + "\\";
                else
                    str = " " + this.columna + "\\";
            } else str = "**\\";
            if (this.fila != null) {
                if (this.fila > 9)
                    str += this.fila;
                else
                    str += this.fila + " ";
            } else str += "**";
        }
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
