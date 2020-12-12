package domini.ranking;

import java.util.ArrayList;
import domini.usuari.Configuracio;

public class Ranking {
    //TODO
    private ArrayList< ArrayList <RankingEntry> > R;

    ///// Constructora /////
    public Ranking() {
        this.R = new ArrayList <ArrayList <RankingEntry> > ();
        for(int i = 0; i < 3; ++i) {
            this.R.add(new ArrayList<RankingEntry> (3));
        }
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.R.get(i).get(j).modificarEntry("BUIT", 0);
            }
        } //Omplim el ranking d'entrades buides
    }

    public void afegirEntry(String id, int temps, Configuracio.Dificultat dif) { //Afegira el entry al ranking si, i només si, es un temps més ràpid que algun dels top 3 en la dificultat esmentada
        if(dif == Configuracio.Dificultat.FACIL) {
            int pos = -1;
            for(int i = 2; i >= 0; ++i) {
                if(this.R.get(0).get(i).getTempsRecord() > temps || this.R.get(0).get(i).getTempsRecord() == 0) {
                    pos = i;
                }
            }
            if(pos != -1) {
                for (int i = 2; i != pos; --i) {
                    this.R.get(0).get(i).modificarEntry(this.R.get(0).get(i-1).getId(), this.R.get(0).get(i-1).getTempsRecord());
                }
                this.R.get(0).get(pos).modificarEntry(id, temps);
            }
        }
        else if(dif == Configuracio.Dificultat.DIFICIL) {
            int pos = -1;
            for(int i = 2; i >= 0; ++i) {
                if(this.R.get(1).get(i).getTempsRecord() > temps || this.R.get(1).get(i).getTempsRecord() == 0) {
                    pos = i;
                }
            }
            if(pos != -1) {
                for (int i = 2; i != pos; --i) {
                    this.R.get(1).get(i).modificarEntry(this.R.get(1).get(i-1).getId(), this.R.get(1).get(i-1).getTempsRecord());
                }
                this.R.get(1).get(pos).modificarEntry(id, temps);
            }
        }
        else if(dif == Configuracio.Dificultat.EXPERT) {
            int pos = -1;
            for(int i = 2; i >= 0; ++i) {
                if(this.R.get(2).get(i).getTempsRecord() > temps || this.R.get(2).get(i).getTempsRecord() == 0) {
                    pos = i;
                }
            }
            if(pos != -1) {
                for (int i = 2; i != pos; --i) {
                    this.R.get(2).get(i).modificarEntry(this.R.get(2).get(i-1).getId(), this.R.get(2).get(i-1).getTempsRecord());
                }
                this.R.get(2).get(pos).modificarEntry(id, temps);
            }
        }
    }
}