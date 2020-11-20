package domini.tauler.casella;

public class CasellaBlanca extends Casella{

    // ATRIBUTS PRIVATS
    
    private Integer valor; //És un "Integer" i no un "int" perquè ha de poder suportar valor "null".
    
    // CONSTRUCTORES
    
    public CasellaBlanca(int x, int y) {
        super(x, y);
        this.valor = null;
    }
    
    public CasellaBlanca(int x, int y, Integer valor) {
        super(x, y);
        this.valor = valor;
    }
    
    // ENCAPSULACIONS
    
    public Integer getValor() {
        return valor;
    }
    
    // MÈTODES PÚBLICS

    public boolean setValor(Integer valor) {
//        if (valor < 1  && valor != null) return false;
        this.valor = valor;
        return true;
    }

    @Override
    public String to_String() {
        if (this.valor != null) {
            if (this.valor > 9) {
                return "  " + valor + " ";
            } else {
                return "  " + valor + "  ";
            }
        }
        return "     ";
    }

    public String save_String() {
        if (valor == null) return "?";
        return valor.toString();
    }
}
