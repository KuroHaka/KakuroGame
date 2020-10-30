package domini.testing;

import domini.tauler.casella.CasellaNegra;

public class Driver_CasellaNegra {

    public static void main(String[] args){
        
        CasellaNegra cn_buida = new CasellaNegra(4,5);
        // Opcionalment: CasellaNegra cn_buida = new CasellaNegra(4,5,null,null);
        System.out.println("Casella negra a X=" + cn_buida.getCoordX() + " Y=" + cn_buida.getCoordY() + ", sense valors C/F establerts (*)");

        CasellaNegra cn_mig = new CasellaNegra(4,5,11,null);
        System.out.println("Casella negra a X=" + cn_mig.getCoordX() + " Y=" + cn_mig.getCoordY() + ", valor F =" + cn_mig.getFila());

        CasellaNegra cn_mig2 = new CasellaNegra(0,12,null,1);
        System.out.println("Casella negra a X=" + cn_mig2.getCoordX() + " Y=" + cn_mig2.getCoordY() + ", valor C =" + cn_mig2.getColumna());
        
        CasellaNegra cn_complet = new CasellaNegra(5,12,11,7);
        System.out.println("Casella negra a X=" + cn_complet.getCoordX() + " Y=" + cn_complet.getCoordY() + ", valor C =" + cn_complet.getColumna() + ", valor F =" + cn_mig.getFila());
    }
}
