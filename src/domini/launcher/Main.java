package domini.launcher;

import domini.algoritme.Combinacions;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //Joc joc = new Joc();

        Combinacions combinacions = new Combinacions();
        printTotsElsCombinacions(combinacions);


    }

    //test perque veieu com funciona la clase Combinacions
    public static void printTotsElsCombinacions(Combinacions combinacions) {
        for (int suma : combinacions.getSum()) {
            for (int blanca : combinacions.getBlanques(suma)) {
                System.out.println("suma:" + suma + " blanques:" + blanca);
                System.out.println(Arrays.deepToString(combinacions.getCombinacios(suma, blanca)));
            }
        }
    }
}
