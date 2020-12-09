package domini.repositori;

import domini.tauler.*;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;

public class Repositori {
    private Map<String, ArrayList<TaulerEnunciat> > rep;

    //Constructoras

    public Repositori() {
        this.rep = new HashMap<String, ArrayList<TaulerEnunciat> > ();
    }

    //Funciones

    public TaulerEnunciat getTaulerEnunciatName(String name, String idT) {
        for(int i = 0; i < rep.get(name).size(); ++i) {
            if(rep.get(name).get(i).getId().equals(idT)) {return rep.get(name).get(i);}
        }
        return null;
    }
    
    public TaulerEnunciat getTaulerEnunciat(String idT) {
        Iterator<Map.Entry<String, ArrayList<TaulerEnunciat> > > it = rep.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ArrayList<TaulerEnunciat> > pair = it.next();
            for(int i = 0; i < pair.getValue().size(); ++i) {
                if(pair.getValue().get(i).getId().equals(idT)) {return pair.getValue().get(i);}
            }
        }
        return null;
    }

    public void saveTaulerEnunciat(String name, TaulerEnunciat t) {
        rep.get(name).add(t);
    }

    public void eliminaTaulerEnunciat(String name, TaulerEnunciat t) {
        rep.get(name).remove(t);
    }

    public boolean existeixEntrada(String name, TaulerEnunciat t) {
        return rep.get(name).contains(t);
    }
    
    public void nouUsuariRepositori(String name) {
        rep.put(name, new ArrayList <TaulerEnunciat> ());
    }
    
    public void eliminaUsuariRepositori(String name) {
        rep.remove(name);
    }
    
    public ArrayList<TaulerEnunciat> getTotsKakurosUsuari(String name) {
        return rep.get(name);
    }
    
}
