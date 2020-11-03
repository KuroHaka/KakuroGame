package domini.algoritme;
import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import domini.tauler.casella.CasellaNegra;


public class Combinacions {
    //sum->Squares->combianations

    private final ArrayList<ArrayList<int[][]>> combinacio = new ArrayList<>();
    private final int[] sums;

    public Set<Set<Integer>> getCombinacios(int suma, int blanques){
        Set<Set<Integer>> ret = new HashSet<>();
        if(blanques==1 && suma<10 ){
            Set<Integer> set = new HashSet<>();
            set.add(suma);
            ret.add(set);
            return ret;
        }
        try {
            for (int[] j : this.combinacio.get(suma).get(blanques)) {
                Set<Integer> set = new HashSet<>();
                for (int i : j) {
                    set.add(i);
                }
                ret.add(set);
            }
            return ret;
        }
        catch (NullPointerException e){
            return null;
        }
    }

    public boolean noEstaBuit(int suma, int blanques){
        try {
            return this.combinacio.get(suma).get(blanques).length > 0;
        }
        catch (IndexOutOfBoundsException | NullPointerException i ){
            return false;
        }
    }

    public Set<Integer> getSum(){
        Set<Integer> set = new HashSet<>();
        for (int sum : this.sums) {
            set.add(sum);
        }
        return set;
    }

    public Set<Integer> getBlanques(int sum){
        if(sum<3 || sum>45){
            return null;
        }
        boolean flag = true;
        int i = 1;
        while(flag){
            i++;
            if(this.noEstaBuit(sum, i)){
                flag = false;
            }
        }
        Set<Integer> blanques = new HashSet<>();
        while(noEstaBuit(sum, i)){
            blanques.add(i);
            i++;
        }
        return blanques;
    }

    public Combinacions() {
        for (int i = 0; i < 46; i++) {
            this.combinacio.add(new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null, null, null)));
        }
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

        afegirCombinacio(14,2,new int[][]{{5,9,},{6,8,}});
        afegirCombinacio(14,3,new int[][]{{1,4,9,},{1,5,8,},{1,6,7,},{2,3,9,},{2,4,8,},{2,5,7,},{3,4,7,},{3,5,6,}});
        afegirCombinacio(14,4,new int[][]{{1,2,3,8,},{1,2,4,7,},{1,2,5,6,},{1,3,4,6,},{2,3,4,5,}});

        afegirCombinacio(15,2,new int[][]{{6,9,},{7,8,}});
        afegirCombinacio(15,3,new int[][]{{1,5,9,},{1,6,8,},{2,4,9,},{2,5,8,},{2,6,7,},{3,4,8,},{3,5,7,},{4,5,6,}});
        afegirCombinacio(15,4,new int[][]{{1,2,3,9,},{1,2,4,8,},{1,2,5,7,},{1,3,4,7,},{1,3,5,6,},{2,3,4,6,}});
        afegirCombinacio(15,5,new int[][]{{1,2,3,4,5,}});

        afegirCombinacio(16,2,new int[][]{{7,9,}});
        afegirCombinacio(16,3,new int[][]{{1,6,9,},{1,7,8,},{2,5,9,},{2,6,8,},{3,4,9,},{3,5,8,},{3,6,7,},{4,5,7,}});
        afegirCombinacio(16,4,new int[][]{{1,2,4,9,},{1,2,5,8,},{1,2,6,7,},{1,3,4,8,},{1,3,5,7,},{1,4,5,6,},{2,3,4,7,},{2,3,5,6,}});
        afegirCombinacio(16,5,new int[][]{{1,2,3,4,6,}});

        afegirCombinacio(17,2,new int[][]{{8,9,}});
        afegirCombinacio(17,3,new int[][]{{1,7,9,},{2,6,9,},{2,7,8,},{3,5,9,},{3,6,8,},{4,5,8,},{4,6,7,}});
        afegirCombinacio(17,4,new int[][]{{1,2,5,9,},{1,2,6,8,},{1,3,4,9,},{1,3,5,8,},{1,3,6,7,},{1,4,5,7,},{2,3,4,8,},{2,3,5,7,},{2,4,5,6,}});
        afegirCombinacio(17,5,new int[][]{{1,2,3,4,7,},{1,2,3,5,6,}});

        afegirCombinacio(18,3,new int[][]{{1,8,9,},{2,7,9,},{3,6,9,},{3,7,8,},{4,5,9,},{4,6,8,},{5,6,7,}});
        afegirCombinacio(18,4,new int[][]{{1,2,6,9,},{1,2,7,8,},{1,3,5,9,},{1,3,6,8,},{1,4,5,8,},{1,4,6,7,},{2,3,4,9,},{2,3,5,8,},{2,3,6,7,},{2,4,5,7,},{3,4,5,6,}});
        afegirCombinacio(18,5,new int[][]{{1,2,3,4,8,},{1,2,3,5,7,},{1,2,4,5,6,}});

        afegirCombinacio(19,3,new int[][]{{2,8,9,},{3,7,9,},{4,6,9,},{4,7,8,},{5,6,8,}});
        afegirCombinacio(19,4,new int[][]{{1,2,7,9,},{1,3,6,9,},{1,3,7,8,},{1,4,5,9,},{1,4,6,8,},{1,5,6,7,},{2,3,5,9,},{2,3,6,8,},{2,4,5,8,},{2,4,6,7,},{3,4,5,7,}});
        afegirCombinacio(19,5,new int[][]{{1,2,3,4,9,},{1,2,3,5,8,},{1,2,3,6,7,},{1,2,4,5,7,},{1,3,4,5,6,}});

        afegirCombinacio(20,3,new int[][]{{3,8,9,},{4,7,9,},{5,6,9,},{5,7,8,}});
        afegirCombinacio(20,4,new int[][]{{1,2,8,9,},{1,3,7,9,},{1,4,6,9,},{1,4,7,8,},{1,5,6,8,},{2,3,6,9,},{2,3,7,8,},{2,4,5,9,},{2,4,6,8,},{2,5,6,7,},{3,4,5,8,},{3,4,6,7,}});
        afegirCombinacio(20,5,new int[][]{{1,2,3,5,9,},{1,2,3,6,8,},{1,2,4,5,8,},{1,2,4,6,7,},{1,3,4,5,7,},{2,3,4,5,6,}});

        afegirCombinacio(21,3,new int[][]{{4,8,9,},{5,7,9,},{6,7,8,}});
        afegirCombinacio(21,4,new int[][]{{1,3,8,9,},{1,4,7,9,},{1,5,6,9,},{1,5,7,8,},{2,3,7,9,},{2,4,6,9,},{2,4,7,8,},{2,5,6,8,},{3,4,5,9,},{3,4,6,8,},{3,5,6,7,}});
        afegirCombinacio(21,5,new int[][]{{1,2,3,6,9,},{1,2,3,7,8,},{1,2,4,5,9,},{1,2,4,6,8,},{1,2,5,6,7,},{1,3,4,5,8,},{1,3,4,6,7,},{2,3,4,5,7,}});
        afegirCombinacio(21,6,new int[][]{{1,2,3,4,5,6,}});

        afegirCombinacio(22,3,new int[][]{{5,8,9,},{6,7,9,}});
        afegirCombinacio(22,4,new int[][]{{1,4,8,9,},{1,5,7,9,},{1,6,7,8,},{2,3,8,9,},{2,4,7,9,},{2,5,6,9,},{2,5,7,8,},{3,4,6,9,},{3,4,7,8,},{3,5,6,8,},{4,5,6,7,}});
        afegirCombinacio(22,5,new int[][]{{1,2,3,7,9,},{1,2,4,6,9,},{1,2,4,7,8,},{1,2,5,6,8,},{1,3,4,5,9,},{1,3,4,6,8,},{1,3,5,6,7,},{2,3,4,5,8,},{2,3,4,6,7,}});
        afegirCombinacio(22,6,new int[][]{{1,2,3,4,5,7,}});

        afegirCombinacio(23,3,new int[][]{{6,8,9,}});
        afegirCombinacio(23,4,new int[][]{{1,5,8,9,},{1,6,7,9,},{2,4,8,9,},{2,5,7,9,},{2,6,7,8,},{3,4,7,9,},{3,5,6,9,},{3,5,7,8,},{4,5,6,8,}});
        afegirCombinacio(23,5,new int[][]{{1,2,3,8,9,},{1,2,4,7,9,},{1,2,5,6,9,},{1,2,5,7,8,},{1,3,4,6,9,},{1,3,4,7,8,},{1,3,5,6,8,},{1,4,5,6,7,},{2,3,4,5,9,},{2,3,4,6,8,},{2,3,5,6,7,}});
        afegirCombinacio(23,6,new int[][]{{1,2,3,4,5,8,},{1,2,3,4,6,7,}});

        afegirCombinacio(24,3,new int[][]{{7,8,9,}});
        afegirCombinacio(24,4,new int[][]{{1,6,8,9,},{2,5,8,9,},{2,6,7,9,},{3,4,8,9,},{3,5,7,9,},{3,6,7,8,},{4,5,6,9,},{4,5,7,8,}});
        afegirCombinacio(24,5,new int[][]{{1,2,4,8,9,},{1,2,5,7,9,},{1,2,6,7,8,},{1,3,4,7,9,},{1,3,5,6,9,},{1,3,5,7,8,},{1,4,5,6,8,},{2,3,4,6,9,},{2,3,4,7,8,},{2,3,5,6,8,},{2,4,5,6,7,}});
        afegirCombinacio(24,6,new int[][]{{1,2,3,4,5,9,},{1,2,3,4,6,8,},{1,2,3,5,6,7,}});

        afegirCombinacio(25,4,new int[][]{{1,7,8,9,},{2,6,8,9,},{3,5,8,9,},{3,6,7,9,},{4,5,7,9,},{4,6,7,8,}});
        afegirCombinacio(25,5,new int[][]{{1,2,5,8,9,},{1,2,6,7,9,},{1,3,4,8,9,},{1,3,5,7,9,},{1,3,6,7,8,},{1,4,5,6,9,},{1,4,5,7,8,},{2,3,4,7,9,},{2,3,5,6,9,},{2,3,5,7,8,},{2,4,5,6,8,},{3,4,5,6,7,}});
        afegirCombinacio(25,6,new int[][]{{1,2,3,4,6,9,},{1,2,3,4,7,8,},{1,2,3,5,6,8,},{1,2,4,5,6,7,}});

        afegirCombinacio(26,4,new int[][]{{2,7,8,9,},{3,6,8,9,},{4,5,8,9,},{4,6,7,9,},{5,6,7,8,}});
        afegirCombinacio(26,5,new int[][]{{1,2,6,8,9,},{1,3,5,8,9,},{1,3,6,7,9,},{1,4,5,7,9,},{1,4,6,7,8,},{2,3,4,8,9,},{2,3,5,7,9,},{2,3,6,7,8,},{2,4,5,6,9,},{2,4,5,7,8,},{3,4,5,6,8,}});
        afegirCombinacio(26,6,new int[][]{{1,2,3,4,7,9,},{1,2,3,5,6,9,},{1,2,3,5,7,8,},{1,2,4,5,6,8,},{1,3,4,5,6,7,}});

        afegirCombinacio(27,4,new int[][]{{3,7,8,9,},{4,6,8,9,},{5,6,7,9,}});
        afegirCombinacio(27,5,new int[][]{{1,2,7,8,9,},{1,3,6,8,9,},{1,4,5,8,9,},{1,4,6,7,9,},{1,5,6,7,8,},{2,3,5,8,9,},{2,3,6,7,9,},{2,4,5,7,9,},{2,4,6,7,8,},{3,4,5,6,9,},{3,4,5,7,8,}});
        afegirCombinacio(27,6,new int[][]{{1,2,3,4,8,9,},{1,2,3,5,7,9,},{1,2,3,6,7,8,},{1,2,4,5,6,9,},{1,2,4,5,7,8,},{1,3,4,5,6,8,},{2,3,4,5,6,7,}});

        afegirCombinacio(28,4,new int[][]{{4,7,8,9,},{5,6,8,9,}});
        afegirCombinacio(28,5,new int[][]{{1,3,7,8,9,},{1,4,6,8,9,},{1,5,6,7,9,},{2,3,6,8,9,},{2,4,5,8,9,},{2,4,6,7,9,},{2,5,6,7,8,},{3,4,5,7,9,},{3,4,6,7,8,}});
        afegirCombinacio(28,6,new int[][]{{1,2,3,5,8,9,},{1,2,3,6,7,9,},{1,2,4,5,7,9,},{1,2,4,6,7,8,},{1,3,4,5,6,9,},{1,3,4,5,7,8,},{2,3,4,5,6,8,}});
        afegirCombinacio(28,7,new int[][]{{1,2,3,4,5,6,7,}});

        afegirCombinacio(29,4,new int[][]{{5,7,8,9,}});
        afegirCombinacio(29,5,new int[][]{{1,4,7,8,9,},{1,5,6,8,9,},{2,3,7,8,9,},{2,4,6,8,9,},{2,5,6,7,9,},{3,4,5,8,9,},{3,4,6,7,9,},{3,5,6,7,8,}});
        afegirCombinacio(29,6,new int[][]{{1,2,3,6,8,9,},{1,2,4,5,8,9,},{1,2,4,6,7,9,},{1,2,5,6,7,8,},{1,3,4,5,7,9,},{1,3,4,6,7,8,},{2,3,4,5,6,9,},{2,3,4,5,7,8,}});
        afegirCombinacio(29,7,new int[][]{{1,2,3,4,5,6,8,}});

        afegirCombinacio(30,4,new int[][]{{6,7,8,9,}});
        afegirCombinacio(30,5,new int[][]{{1,5,7,8,9,},{2,4,7,8,9,},{2,5,6,8,9,},{3,4,6,8,9,},{3,5,6,7,9,},{4,5,6,7,8,}});
        afegirCombinacio(30,6,new int[][]{{1,2,3,7,8,9,},{1,2,4,6,8,9,},{1,2,5,6,7,9,},{1,3,4,5,8,9,},{1,3,4,6,7,9,},{1,3,5,6,7,8,},{2,3,4,5,7,9,},{2,3,4,6,7,8,}});
        afegirCombinacio(30,7,new int[][]{{1,2,3,4,5,6,9,},{1,2,3,4,5,7,8,}});

        afegirCombinacio(31,5,new int[][]{{1,6,7,8,9,},{2,5,7,8,9,},{3,4,7,8,9,},{3,5,6,8,9,},{4,5,6,7,9,}});
        afegirCombinacio(31,6,new int[][]{{1,2,4,7,8,9,},{1,2,5,6,8,9,},{1,3,4,6,8,9,},{1,3,5,6,7,9,},{1,4,5,6,7,8,},{2,3,4,5,8,9,},{2,3,4,6,7,9,},{2,3,5,6,7,8,}});
        afegirCombinacio(31,7,new int[][]{{1,2,3,4,5,7,9,},{1,2,3,4,6,7,8,}});

        afegirCombinacio(32,5,new int[][]{{2,6,7,8,9,},{3,5,7,8,9,},{4,5,6,8,9,}});
        afegirCombinacio(32,6,new int[][]{{1,2,5,7,8,9,},{1,3,4,7,8,9,},{1,3,5,6,8,9,},{1,4,5,6,7,9,},{2,3,4,6,8,9,},{2,3,5,6,7,9,},{2,4,5,6,7,8,}});
        afegirCombinacio(32,7,new int[][]{{1,2,3,4,5,8,9,},{1,2,3,4,6,7,9,},{1,2,3,5,6,7,8,}});

        afegirCombinacio(33,5,new int[][]{{3,6,7,8,9,},{4,5,7,8,9,}});
        afegirCombinacio(33,6,new int[][]{{1,2,6,7,8,9,},{1,3,5,7,8,9,},{1,4,5,6,8,9,},{2,3,4,7,8,9,},{2,3,5,6,8,9,},{2,4,5,6,7,9,},{3,4,5,6,7,8,}});
        afegirCombinacio(33,7,new int[][]{{1,2,3,4,6,8,9,},{1,2,3,5,6,7,9,},{1,2,4,5,6,7,8,}});

        afegirCombinacio(34,5,new int[][]{{4,6,7,8,9,}});
        afegirCombinacio(34,6,new int[][]{{1,3,6,7,8,9,},{1,4,5,7,8,9,},{2,3,5,7,8,9,},{2,4,5,6,8,9,},{3,4,5,6,7,9,}});
        afegirCombinacio(34,7,new int[][]{{1,2,3,4,7,8,9,},{1,2,3,5,6,8,9,},{1,2,4,5,6,7,9,},{1,3,4,5,6,7,8,}});

        afegirCombinacio(35,5,new int[][]{{5,6,7,8,9,}});
        afegirCombinacio(35,6,new int[][]{{1,4,6,7,8,9,},{2,3,6,7,8,9,},{2,4,5,7,8,9,},{3,4,5,6,8,9,}});
        afegirCombinacio(35,7,new int[][]{{1,2,3,5,7,8,9,},{1,2,4,5,6,8,9,},{1,3,4,5,6,7,9,},{2,3,4,5,6,7,8,}});

        afegirCombinacio(36,6,new int[][]{{1,5,6,7,8,9,},{2,4,6,7,8,9,},{3,4,5,7,8,9,}});
        afegirCombinacio(36,7,new int[][]{{1,2,3,6,7,8,9,},{1,2,4,5,7,8,9,},{1,3,4,5,6,8,9,},{2,3,4,5,6,7,9,}});
        afegirCombinacio(36,8,new int[][]{{1,2,3,4,5,6,7,8,}});

        afegirCombinacio(37,6,new int[][]{{2,5,6,7,8,9,},{3,4,6,7,8,9,}});
        afegirCombinacio(37,7,new int[][]{{1,2,4,6,7,8,9,},{1,3,4,5,7,8,9,},{2,3,4,5,6,8,9,}});
        afegirCombinacio(37,8,new int[][]{{1,2,3,4,5,6,7,9,}});

        afegirCombinacio(38,6,new int[][]{{3,5,6,7,8,9,}});
        afegirCombinacio(38,7,new int[][]{{1,2,5,6,7,8,9,},{1,3,4,6,7,8,9,},{2,3,4,5,7,8,9,}});
        afegirCombinacio(38,8,new int[][]{{1,2,3,4,5,6,8,9,}});

        afegirCombinacio(39,6,new int[][]{{4,5,6,7,8,9,}});
        afegirCombinacio(39,7,new int[][]{{1,3,5,6,7,8,9,},{2,3,4,6,7,8,9,}});
        afegirCombinacio(39,8,new int[][]{{1,2,3,4,5,7,8,9,}});

        afegirCombinacio(40,7,new int[][]{{1,4,5,6,7,8,9,},{2,3,5,6,7,8,9,}});
        afegirCombinacio(40,8,new int[][]{{1,2,3,4,6,7,8,9,}});

        afegirCombinacio(41,7,new int[][]{{2,4,5,6,7,8,9,}});
        afegirCombinacio(41,8,new int[][]{{1,2,3,5,6,7,8,9,}});

        afegirCombinacio(42,7,new int[][]{{3,4,5,6,7,8,9,}});
        afegirCombinacio(42,8,new int[][]{{1,2,4,5,6,7,8,9,}});

        afegirCombinacio(43,8,new int[][]{{1,3,4,5,6,7,8,9,}});

        afegirCombinacio(44,8,new int[][]{{2,3,4,5,6,7,8,9,}});

        afegirCombinacio(45,9,new int[][]{{1,2,3,4,5,6,7,8,9,}});

        this.sums = new int[43];
        for (int i = 0; i < 43; ++i) {
            this.sums[i] = i+3;
        }
    }

    private void afegirCombinacio(int suma, int blanques, int[][] combinacions){
        this.combinacio.get(suma).add(blanques, combinacions);
    }

}
