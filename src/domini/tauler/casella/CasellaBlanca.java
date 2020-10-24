package domini.tauler.casella;

public class CasellaBlanca extends Casella{

    private Integer valor; //És un "Integer" i no un "int" perquè ha de poder suportar valor "null".

    public CasellaBlanca(int x, int y) {
        super(x, y);
        this.valor = null;
    }
    
    public CasellaBlanca(int x, int y, Integer valor) {
        super(x, y);
        this.valor = valor;
    }
    
    public String to_String() {
        if (this.valor != null)
            return " " + valor + " ";
        return " ? ";
    }

    public Integer getValor() {
        return valor;
    }
}
