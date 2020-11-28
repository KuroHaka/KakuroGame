package domini.tauler;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;
import persistencia.Dades;

public class Driver_tauler {
    
    public static void main(String[] args) throws IOException {
        System.out.println("DRIVER TAULER");
        System.out.println(" ");
        int opt;
        do {
            Scanner scan = new Scanner(System.in);
            System.out.println(" MENU:");
            System.out.println(" -----------------------------------");
            System.out.println(" 1- Entrada tauler per stdin");
            System.out.println(" 2- Entrada tauler per directori");
            System.out.println(" -----------------------------------");
            System.out.print(" Opcio: ");
            opt = scan.nextInt();
            switch (opt) {
                case 1:
                    System.out.println(" -----------------------------------");
                    System.out.println(" \tEntrada tauler per stdin");
                    System.out.println(" -----------------------------------");
                    System.out.println("(introdueix \"exit\" al acabar): ");

                    StringBuilder a = new StringBuilder();
                    a.append(scan.next());
                    while (scan.hasNextLine()) {
                        String nou = scan.nextLine();
                        if (nou.equals("exit"))
                            break;
                        a.append(nou);
                        a.append("\n");
                    }
                    TaulerEnunciat te = new TaulerEnunciat(a.toString());
                    System.out.print(" El tauler llegit en format estandard: ");
                    System.out.println(te.format_Estandard());
                    System.out.print(" El tauler llegit en format Human Friendly: ");
                    te.print();
                    System.out.println("FET!");
                    te.print();
                    break;
                case 2:
                    System.out.println(" -----------------------------------");
                    System.out.println(" \tEntrada tauler per directori");
                    System.out.println(" -----------------------------------");
                    System.out.println(" Indica el path relatiu al .jar: ");
                    String e3 = null;
                    while (true) {
                        String aux = scan.next();
                        try {
                            e3 = Dades.carregaArxiu(aux);
                            break;
                        } catch (NoSuchFileException e) {
                            System.out.println("Arxiu o path no existeix, torna-ho a introduir: ");
                        }
                    }
                    TaulerEnunciat te3 = new TaulerEnunciat(e3);
                    System.out.print(" El tauler llegit en format estandard: ");
                    System.out.println(te3.format_Estandard());
                    System.out.print(" El tauler llegit en format Human Friendly: ");
                    te3.print();
                    System.out.println(" Done.");
                    break;
                default:
                    break;
            }
        }while(opt!=0);
    }
}
