/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domini.tauler.casella;

import java.util.Scanner;


/**
 *
 * @author zsc27
 */
public class Driver_Casella {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("DRIVER COMBINACIONS\n");
        int x;
        int y;
        int opt;
        int vint = 0;
        String aux;
        CasellaBlanca cb;
        do {
            System.out.println(" -----------------------------------");
            System.out.println(" MENU:");
            System.out.println(" 0- Sortir");
            System.out.println(" 1- Crea una casella blanca");
            System.out.println(" 2- Crea una casella negra");
            System.out.println(" -----------------------------------");
            System.out.print(" Opcio: ");
            opt = sc.nextInt();
            switch (opt) {
                case 1:
                    System.out.println(" -----------------------------------");
                    System.out.println(" \tCrea una casella blanca");
                    System.out.println(" -----------------------------------");
                    System.out.print("Introdueix numero de fila: ");
                    x=sc.nextInt();
                    System.out.print("Introdueix numero de columna: ");
                    y=sc.nextInt();
                    System.out.print("Introdueix el seu valor [1,9] O null: ");
                    aux = sc.next();
                    Integer val = (aux).equals("null")? null:Integer.parseInt(aux);
                    if((val!=null)&&(val<1 || val>9) || x<0 || y<0){
                        System.err.println("\nNo es por generar casella amb aquests valors introduits");
                        break;
                    }
                    cb= new CasellaBlanca(x, y, val);
                    System.out.println("Casella en format estandard");
                    System.out.println(cb.save_String());
                    System.out.println("Casella en format Human friendly");
                    System.out.println(cb.to_String());
                    System.out.println("FET!\n");
                    break;
                case 2:
                    System.out.println(" -----------------------------------");
                    System.out.println(" \tCrea una casella negra");
                    System.out.println(" -----------------------------------");
                    System.out.print("Introdueix numero de fila: ");
                    x=sc.nextInt();
                    System.out.print("Introdueix numero de columna: ");
                    y=sc.nextInt();
                    System.out.print("Introdueix numero de suma Columna (pot ser null): ");
                    aux = sc.next();
                    Integer col = (aux).equals("null")? null:Integer.parseInt(aux);
                    System.out.print("Introdueix numero de suma Fila (pot ser null): ");
                    aux = sc.next();
                    Integer fil = (aux).equals("null")? null:Integer.parseInt(aux);
                    if(x<0 || y<0){
                        System.err.println("\nNo es por generar casella amb aquests valors introduits");
                        break;
                    }
                    CasellaNegra cn = new CasellaNegra(x,y,col,fil);
                    System.out.println("Casella en format estandard");
                    System.out.println(cn.save_String());
                    System.out.println("Casella en format Human friendly");
                    System.out.println(cn.to_String());
                    System.out.println("FET!\n");
                    break;
                default:
                    System.err.println("\tOPCIO INEXISTENT");
                    break;
            }
        } while (opt != 0);
    }
}
