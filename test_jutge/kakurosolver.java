
import java.util.ArrayList;
import java.util.Scanner;

public class kakurosolver {
    public static void main(String[] args) {
        Algoritme algoritme = new Algoritme();
        Scanner sc = new Scanner(System.in);
        StringBuilder line;
        String[] lineVector;

        line = new StringBuilder(sc.nextLine()); //read 1,2,3

        //separate all values by comma
        lineVector = line.toString().split(",");
        //parsing the values to Integer
        int x=Integer.parseInt(lineVector[0]);
        int y=Integer.parseInt(lineVector[1]);
        for (int i = 0; i < y; i++) {
            line.append("\n").append(sc.nextLine());
        }
        TaulerEnunciat te = new TaulerEnunciat(line.toString());
        ArrayList<TaulerComencat> sol = algoritme.resoldreKakuro(te);
        System.out.println(sol.size());
        for(TaulerComencat tc: sol) {
            System.out.println(tc.format_Estandard());
        }
    }
}
