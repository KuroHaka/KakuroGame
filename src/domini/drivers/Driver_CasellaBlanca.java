package domini.drivers;

import domini.tauler.casella.CasellaBlanca;

public class Driver_CasellaBlanca {

    public static void main(String[] args){
        
        CasellaBlanca cb_buida = new CasellaBlanca(3,7);
        System.out.println("Casella blanca a X=" + cb_buida.getCoordX() + " Y=" + cb_buida.getCoordY() + ", sense valor establert (?)");
        
        CasellaBlanca cb_valor = new CasellaBlanca(5,0,4);
        System.out.println("Casella blanca a X=" + cb_valor.getCoordX() + " Y=" + cb_valor.getCoordY() + ", valor=" + cb_valor.getValor());
    }
    
}
