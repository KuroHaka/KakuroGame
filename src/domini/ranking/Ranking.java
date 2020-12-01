package domini.ranking;

import domini.tauler.TaulerEnunciat;
import domini.usuari.Usuari;

import java.util.ArrayList;
import java.util.Map;

public class Ranking {
    //TODO
    private ArrayList<RankingEntry> R;
    private int size;
    
    ///// Constructora /////
    public Ranking() {
        this.R = new ArrayList<RankingEntry>();
    }
    
    public void afegirEntry(int id, int temps) {
        boolean exist = false;
        for (int i = 0; i < size; i++) {
            if (R.get(i).getId() == id) {
                if (R.get(i).getTempsRecord() > temps) {
                    R.get(i).modificarTemps(temps);
                }
                exist = true;
                break;
            }
        }
        if (!exist) {
            R.add(new RankingEntry(id, temps));
            size++;
        }
    }
    
    ///// Consultores /////
    
    public void print() {
        System.out.println("Rànkings: ");
        for (int i = 0; i < size; i++) {
            R.get(i).printEntry();
        }
    }
}
