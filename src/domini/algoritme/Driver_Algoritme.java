package domini.algoritme;

import domini.tauler.Tauler;
import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;
import persistencia.Dades;

import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
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

                    System.out.print(algoritme.generarKakuroSimple(x,y,0).format_Estandard());
                    System.out.print("\tnumero de caselles blanques totals(introdueix null si vols que sigui aleatori): ");
                    String b = sc.next();
                    Integer bint = b.equals("null")? null:Integer.parseInt(b);
                    System.out.print("\tnumero de caselles blanques amb valors(introdueix 0 si es un tauler buit): ");
                    int bq = sc.nextInt();
                    TaulerEnunciat s = algoritme.generarKakuro(x, y, bint, bq);
                    System.out.println("Format Estandard: ");
                    System.out.println(s.format_Estandard());
                    System.out.println("Format Human friendly: ");
                    s.print();
                    System.out.println("\nIndica el nom del fitxer del kakuro generat a guardar(introdueix null si no vols que es guardi):");
                    String ret1 = scan.next();
                    if(!ret1.equals("null")) {
                        Dades.guardarArxiu(ret1, s.format_Estandard());
                    }
                    System.out.println("FET!\n");
                    break;
                case 2:
                    System.out.println(" -----------------------------------");
                    System.out.println(" \tResol un kakuro");
                    System.out.println(" -----------------------------------");
                    System.out.println("1- resol kakuro i genera.out");
                    System.out.println("2- resol kakuro i comprova amb un altre fitxer solucio");
                    int opt2 = scan.nextInt();
                    switch(opt2)
                    {
                        case 1:
                            System.out.println("Indica el path del fitxer relatiu al .jar: ");
                            String e2 = null;
                            while(true){
                                String aux = scan.next();
                                try {
                                    e2 = Dades.carregaArxiu(aux);
                                    break;
                                }
                                catch(NoSuchFileException e){
                                    System.out.println("Arxiu o path no existeix, torna-ho a introduir: ");
                                }
                            }
                            TaulerEnunciat te = new TaulerEnunciat(e2);
                            System.out.println(" El tauler llegit: ");
                            System.out.println(te.format_Estandard());
                            System.out.print(" El tauler resolt: ");
                            ArrayList<TaulerComencat> sols = algoritme.resoldreKakuro(te);
                            System.out.println("Número de solucions: "+sols.size());
                            for(TaulerComencat tc: sols){
                                System.out.print(tc.format_Estandard());
                                te.print();
                                System.out.println("Format Human friendly: ");
                                tc.print();
                                System.out.println("\nIndica el nom del arxiu solucio a guardar(introdueix null si no vols que es guardi):");
                                String ret = scan.next();
                                if(!ret.equals("null")) {
                                    Dades.guardarArxiu(ret, tc.format_Estandard());
                                }
                                System.out.println("FET!\n");
                            }
                            break;
                        case 2 :
                            System.out.println(" Indica el path del fitxer relatiu al .jar: ");
                            String e22 = null;
                            while(true){
                                String aux = scan.next();
                                try {
                                    e22 = Dades.carregaArxiu(aux);
                                    break;
                                }
                                catch(NoSuchFileException e){
                                    System.out.println("Arxiu o path no existeix, torna-ho a introduir: ");
                                }
                            }
                            System.out.println(" Indica el path del fitxer relatiu al .jar a comparar: ");
                            String s2 = null;
                            while(true){
                                String aux = scan.next();
                                try {
                                    s2 = Dades.carregaArxiu(aux);
                                    break;
                                }
                                catch(NoSuchFileException e){
                                    System.out.println("Arxiu o path no existeix, torna-ho a introduir: ");
                                }
                            }
                            TaulerEnunciat te2 = new TaulerEnunciat(e22);
                            TaulerComencat tes2 = new TaulerComencat(s2);
                            System.out.println(" El tauler llegit: ");
                            System.out.println(te2.format_Estandard());
                            System.out.println(" El tauler resolt: ");
                            ArrayList<TaulerComencat> sols2 = algoritme.resoldreKakuro(te2);
                            for (TaulerComencat tc2:  sols2) {
                                System.out.println(tc2.format_Estandard());
                                System.out.println(" El tauler a comparar: ");
                                System.out.println(tes2.format_Estandard());
                                if (tc2.format_Estandard().equals(tes2.format_Estandard()))
                                    System.out.println(" SON IGUALS!");
                                else
                                    System.out.println(" SON DIFERENTS");
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    System.out.println(" -----------------------------------");
                    System.out.println(" \tValida format d'un kakuro");
                    System.out.println(" -----------------------------------");
                    System.out.println(" Indica el path del fitxer relatiu al .jar: ");
                    String e3 = null;
                    while(true){
                        String aux = scan.next();
                        try {
                            e3 = Dades.carregaArxiu(aux);
                            break;
                        }
                        catch(NoSuchFileException e){
                            System.out.println("Arxiu o path no existeix, torna-ho a introduir: ");
                        }
                    }
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
                    System.out.println(" Indica el path del fitxer relatiu al .jar: ");
                    String e4=null;
                    while(true){
                        String aux = scan.next();
                        try {
                            e4 = Dades.carregaArxiu(aux);
                            break;
                        }
                        catch(NoSuchFileException e){
                            System.out.println("Arxiu o path no existeix, torna-ho a introduir: ");
                        }
                    }
                    TaulerComencat tc4 = new TaulerComencat(e4);
                    if(algoritme.validaSolucio(tc4))
                        System.out.println("SOLUCIO CORRECTA!");
                    else
                        System.out.println("SOLUCIO INCORRECTA!");
                    break;
                default:
                    System.err.println("\tOPCIO INEXISTENT");
                    break;
            }
        } while (opt != 0);
    }
}
