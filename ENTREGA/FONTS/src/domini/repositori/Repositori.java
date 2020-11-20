package domini.repositori;

import domini.tauler.*;

import java.util.HashMap;
import java.util.Map;

public class Repositori {
    private Map<Integer, TaulerEnunciat> rep;

    //Constructoras

    public Repositori() {
        this.rep = new HashMap<Integer, TaulerEnunciat>();
    }

    //Funciones

    public TaulerEnunciat getTaulerEnunciat(int id) {
        return rep.get(id);
    }

    public void saveTaulerEnunciat(int id, TaulerEnunciat t) {
        rep.put(id, t);
    }

    public void eliminaTaulerEnunciat(int id) {
        rep.remove(id);
    }

    public boolean existeixEntrada(int id) {
        return rep.containsKey(id); //Detecta el tauler enunciat repetit pel hash?
    }
}
