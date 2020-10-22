package domini.algoritme;
import java.util.ArrayList;


public class Combinacions {
    //sum->Squares->combianations
    private ArrayList<ArrayList<int[][]>> combinacio = new ArrayList<>();

    public Combinacions() {
        afegirCombinacio(3,2,new int[][]{{1,2}});

        afegirCombinacio(4,2,new int[][]{{1,3}});

        afegirCombinacio(5,2,new int[][]{{1,4},{2,3}});

        afegirCombinacio(6,2,new int[][]{{1,5},{2,4}});
        afegirCombinacio(6,3,new int[][]{{1,2,3}});

        afegirCombinacio(7,2,new int[][]{{1,6},{2,5},{3,4}});
        afegirCombinacio(7,3,new int[][]{{1,2,4}});

        afegirCombinacio(8,2,new int[][]{{1,7},{2,6},{3,5}});
        afegirCombinacio(8,3,new int[][]{{1,2,5},{1,3,4}});

        afegirCombinacio(9,2,new int[][]{{1,8},{2,7},{3,6}});
        afegirCombinacio(9,3,new int[][]{{1,2,6},{1,3,5},{2,3,4}});

        afegirCombinacio(10,2,new int[][]{{1,9},{2,8},{3,7},{4,6}});
        afegirCombinacio(10,3,new int[][]{{1,2,7},{1,3,6},{1,4,5},{2,3,5}});
        afegirCombinacio(10,4,new int[][]{{1,2,3,4}});

        afegirCombinacio(11,2,new int[][]{{2,9},{3,8},{4,7},{5,6}});
        afegirCombinacio(11,3,new int[][]{{1,2,8},{1,3,7},{1,4,6},{2,3,6},{2,4,5}});
        afegirCombinacio(11,4,new int[][]{{1,2,3,5}});

        afegirCombinacio(12,2,new int[][]{{3,9},{4,8},{5,7}});
        afegirCombinacio(12,3,new int[][]{{1,2,9,},{1,3,8,},{1,4,7,},{1,5,6,},{2,3,7,},{2,4,6,},{3,4,5,}});
        afegirCombinacio(12,4,new int[][]{{1,2,3,6,},{1,2,4,5,}});

        afegirCombinacio(13,2,new int[][]{{4,9,},{5,8,},{6,7,}});
        afegirCombinacio(13,3,new int[][]{{1,3,9,},{1,4,8,},{1,5,7,},{2,3,8,},{2,4,7,},{2,5,6,},{3,4,6,}});
        afegirCombinacio(13,4,new int[][]{{1,2,3,7,},{1,2,4,6,},{1,3,4,5,}});

        afegirCombinacio(14,2,new int[][]{});
        afegirCombinacio(14,3,new int[][]{});
        afegirCombinacio(14,4,new int[][]{});

        afegirCombinacio(15,2,new int[][]{});
        afegirCombinacio(15,3,new int[][]{});
        afegirCombinacio(15,4,new int[][]{});
        afegirCombinacio(15,5,new int[][]{});

        afegirCombinacio(16,2,new int[][]{});
        afegirCombinacio(16,3,new int[][]{});
        afegirCombinacio(16,4,new int[][]{});
        afegirCombinacio(16,5,new int[][]{});

        afegirCombinacio(10,5,new int[][]{});
        afegirCombinacio(10,2,new int[][]{});
        afegirCombinacio(10,3,new int[][]{});
        afegirCombinacio(10,4,new int[][]{});
        afegirCombinacio(10,5,new int[][]{{0,0,0,0,0}});
        afegirCombinacio(10,5,new int[][]{{0,0,0,0,0}});
        afegirCombinacio(10,2,new int[][]{{0,0},{0,0},{0,0}});
        afegirCombinacio(10,3,new int[][]{{0,0,0},{0,0,0},{0,0,0}});
        afegirCombinacio(10,4,new int[][]{{0,0,0,0}});
        afegirCombinacio(10,5,new int[][]{{0,0,0,0,0}});

    }
    private void afegirCombinacio(int suma, int blanques, int[][] combinacions){
        ArrayList<int[][]> afegir = new ArrayList<>();
        afegir.add(blanques, combinacions);
        this.combinacio.add(suma, afegir);
    }
}
