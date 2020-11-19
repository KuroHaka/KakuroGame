package domini.algoritme;

import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;
import presistencia.Dades;

import java.util.Scanner;

public class Driver_Algoritme {
    public static void main(String[] args) {
        Algoritme algoritme = new Algoritme();
        Scanner sc = new Scanner(System.in);
        System.out.println("DRIVER ALGORITME");
        System.out.println(" ");
        int x;
        int y;
        int opt;
        do {
            System.out.println(" -----------------------------------");
            System.out.println(" MENU:");
            System.out.println(" 0- Sortir");
            System.out.println(" 1- Genera un kakuro");
            System.out.println(" 2- Resol un kakuro");
            System.out.println(" 3- Valida format d'un kakuro");
            System.out.println(" 4- Valida solucio d'un kakuro");
            System.out.println(" -----------------------------------");
            System.out.print(" Opcio: ");
            Scanner scan = new Scanner(System.in);
            opt = scan.nextInt();
            switch (opt) {
                case 1:
                    System.out.println(" -----------------------------------");
                    System.out.println(" \tGenera un kakuro");
                    System.out.println(" -----------------------------------");
                    System.out.print("\tnumero de files: ");
                    x = sc.nextInt();
                    System.out.print("\tnumero de columnes: ");
                    y = sc.nextInt();
                    System.out.print("\tnumero de caselles blanques (introdueix 0 si es un tauler buit): ");
                    Integer b = sc.nextInt();
                    TaulerEnunciat s = algoritme.generarKakuroSimple(x, y, b);
                    System.out.println("Format Estandard: ");
                    System.out.print(s.format_Estandard());
                    System.out.println("Format Human friendly: ");
                    s.print();
                    System.out.println("FET!\n");
                    break;
                case 2:
                    System.out.println(" -----------------------------------");
                    System.out.println(" \tResol un kakuro");
                    System.out.println(" -----------------------------------");
                    System.out.println(" Indica el path del fitxer relatiu al .jar: ");
                    String e2 = Dades.carregaArxiu(scan.next());
                    TaulerEnunciat te = new TaulerEnunciat(e2);
                    System.out.println(" El tauler llegit: ");
                    System.out.print(te.format_Estandard());
                    System.out.print(" El tauler resolt: ");
                    TaulerComencat tc = algoritme.resoldreKakuro(te);
                    System.out.print(tc.format_Estandard());
                    te.print();
                    System.out.println("Format Human friendly: ");
                    tc.print();
                    System.out.println("FET!\n");
                    break;
                case 3:
                    System.out.println(" -----------------------------------");
                    System.out.println(" \tValida format d'un kakuro");
                    System.out.println(" -----------------------------------");
                    System.out.println(" Indica el path del fitxer del tauler relatiu al .jar: ");
                    String e3 = Dades.carregaArxiu(scan.next());
                    TaulerEnunciat te3 = new TaulerEnunciat(e3);
                    if(algoritme.validaFormat(te3))
                        System.out.println("FORMAT CORRECTE!");
                    else
                        System.out.println("FORMAT INCORRECTE!");
                    break;
                case 4:
                    System.out.println(" -----------------------------------");
                    System.out.println(" \tValida solucio d'un kakuro");
                    System.out.println(" -----------------------------------");
                    System.out.println(" Indica el path del fitxer del tauler relatiu al .jar: ");
                    String e4 = Dades.carregaArxiu(scan.next());
                    TaulerComencat tc4 = new TaulerComencat(e4);
                    if(algoritme.validaSolucio(tc4))
                        System.out.println("SOLUCIO CORRECTA!");
                    else
                        System.out.println("SOLUCIO INCORRECTA!");
                    break;
                default:
                    break;
            }
        } while (opt != 0);
    }
}
