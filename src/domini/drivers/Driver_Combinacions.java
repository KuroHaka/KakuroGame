package domini.drivers;

import domini.algoritme.Combinacions;

public class Driver_Combinacions {
    public static void main(String[] args) {

        Combinacions combinacions = new Combinacions();

        for (int suma : combinacions.getSum()) {
            for (int blanca : combinacions.getBlanques(suma)) {
                System.out.println("suma:" + suma + " blanques:" + blanca);
                System.out.println(combinacions.getCombinacios(suma, blanca));
            }
        }
    }
}
