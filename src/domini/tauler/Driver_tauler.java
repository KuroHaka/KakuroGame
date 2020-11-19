package domini.tauler;

import java.util.Scanner;
import presistencia.Dades;

public class Driver_tauler {
    
    public static void main(String[] args){
        System.out.println("DRIVER TAULER");
        System.out.println(" ");
        System.out.println(" MENU:");
        System.out.println(" ");
        System.out.println(" 0- Entrada tauler per stdin");
        System.out.println(" 1- Entrada tauler per directori");
        System.out.println(" ");
        System.out.print  (" Opcio: ");
        Scanner scan = new Scanner(System.in);
        int opt = scan.nextInt();
        
        switch(opt){
                case 0: 
                    System.out.println(" 0- Entrada tauler per stdin");
                    System.out.println(" Indica les dimensions del tauler:");
                    System.out.print(" Numero de Columnes (x):");
                    int x = scan.nextInt();
                    System.out.print(" Numero de Files (y):");
                    int y = scan.nextInt();
                    System.out.println(" Llegir en format estàndard:");
                    String t = x + "," + y;
                    for(int i = 0; i < y; ++i){
                        t += scan.nextLine();
                        System.out.println(" Llegir en format estàndard:");
                    }
                    //TaulerEnunciat te = new TaulerEnunciat(t);
                    System.out.println(" Done.");
                    System.out.print(t);
                    break;
                case 1:
                    System.out.println(" 1- Entrada tauler per directori");
                    System.out.println(" Indica el path relatiu al .jar: ");
                    String path = scan.next();
                    String e2 = Dades.carregaArxiu(path);
                    //String txt = Dades.carregaArxiu(path);
                    TaulerEnunciat te = new TaulerEnunciat(e2);
                    System.out.print(" El tauler llegit: ");
                    te.print();
                    System.out.println(" Done.");
                    break;
                default:
                    break;
        }
        
        /*System.out.println("GETS : ");
        System.out.println("getId() = " + t.getId());
        System.out.println("getDimX() = " + t.getDimX());
        System.out.println("getDimY() = " + t.getDimY());
        
        System.out.println("altres MÈTODES : ");
        System.out.println("esNegra(2, 3) = " + t.esNegra(2, 3));
        System.out.println("esBlanca(3, 0) = " + t.esBlanca(3, 0));
        
        System.out.println("FER CANVIS AL TAULER EXEMPLE : ");
        System.out.println("setValor(2, 1, 8) = " + t.setValor(2, 1, 8));*/
        
        
    }
}
