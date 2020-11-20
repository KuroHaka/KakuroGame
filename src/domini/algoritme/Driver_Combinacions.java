/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domini.algoritme;

import java.util.Scanner;

/**
 *
 * @author zsc27
 */
public class Driver_Combinacions {
    
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("DRIVER COMBINACIONS\n");
        Combinacions combinacions = new Combinacions();
        int n;
        int b;
        int opt;
        do {
            System.out.println(" -----------------------------------");
            System.out.println(" MENU:");
            System.out.println(" 0- Sortir");
            System.out.println(" 1- Retorna totes les possibles combinacions");
            System.out.println(" 2- Donat una suma d'una casella negra i un numero de caselles blanques retorna totes les combinacions");
            System.out.println(" -----------------------------------");
            System.out.print(" Opcio: ");
            opt = sc.nextInt();
            switch (opt) {
                case 1:
                    System.out.println(" -----------------------------------");
                    System.out.println(" \tRetorna totes les possibles combinacions");
                    System.out.println(" -----------------------------------");
                    for (int suma : combinacions.getSum()) {
                        for (int blanca : combinacions.getBlanques(suma)) {
                            System.out.println("suma:" + suma + " blanques:" + blanca);
                            System.out.println(combinacions.getCombinacios(suma, blanca));
                        }
                    }
                    System.out.println("FET!\n");
                    break;
                case 2:
                    System.out.println(" -----------------------------------");
                    System.out.println(" \tDonat una suma d'una casella negra i un numero de casella blanca retorna totes les combinacions");
                    System.out.println(" -----------------------------------");
                    System.out.print("Introdueix el valor que han de sumar les caselles blanques: ");
                    n = sc.nextInt();
                    System.out.print("Introdueix el numero de caselles blanques: ");
                    b = sc.nextInt();
                    if(combinacions.getCombinacios(n,b)==null)
                        System.out.println("\nNo existeix cap combinacio per aquets valors introduits"+"\n");
                    else
                        System.out.println(combinacions.getCombinacios(n,b)+"\n");
                    break;
                default:
                    System.err.println("\tOPCIO INEXISTENT");
                    break;
            }
        } while (opt != 0);
    }
}
