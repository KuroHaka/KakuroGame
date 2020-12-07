package persistencia;

import interficie.ControladoraInterficie;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    
    ControladoraInterficie ctrl_interficie;
    String root = "dades/";
    
    public ControladoraPersistencia(ControladoraInterficie ci) {
        this.ctrl_interficie = ci;
    }
    
    public void inicia() {
        
    }
       
    public ArrayList<String> llista_usuaris() {
        ArrayList<String> llista = new ArrayList<>();
        
        String shadow = "";
        try {shadow = Dades.carregaArxiu(root + "shadow.txt");}
        catch (NoSuchFileException ex) {}
        
        String[] files = shadow.split("\n");
        for (String fila : files) {
            llista.add(fila.split(":")[0]);
        }
        System.out.println("(Ctrl Persist) S'ha extret llista usuaris");
        return llista;
    }
    
    public ArrayList<String> llista_id_enunciats() {
        // TODO
        
        
        
        return null;
    }
    
    public Vector<String> llista_id_partides(String usuari) {
        Vector<String> llista = new Vector<>();
        
        String shadow = "";
        try {shadow = Dades.carregaArxiu(root + "shadow.txt");}
        catch (NoSuchFileException ex) {}
        
        String[] files = shadow.split("\n");
        for (String fila : files) {
            if (fila.split(":")[0].equals(usuari)) {
                llista.addAll(Arrays.asList(fila.split(":")[2].split(",")));
                System.out.println("(Ctrl Persist) S'han extret partides de " + usuari);
                return llista;
            }
        }
        System.out.println("(Ctrl Persist) No s'ha trobat usuari " + usuari);
        return null;
    }
    
    public String getHashPassword(String usuari) {
        String shadow = "";
        try {shadow = Dades.carregaArxiu(root + "shadow.txt");}
        catch (NoSuchFileException ex) {}
        
        String[] files = shadow.split("\n");
        for (String fila : files) {
            if (fila.split(":")[0].equals(usuari)) {
                System.out.println("(Ctrl Persist) S'ha extret hash de " + usuari);
                return fila.split(":")[1];
            }
        }
        System.out.println("(Ctrl Persist) No s'ha trobat usuari " + usuari);
        return "no_existeix_usuari";
    }
    
    public Object[] getUsuari(String usuari) {
        // TODO
        return null;
    }
    
    public Object[] getConfiguracio (String usuari) {
        // TODO
        int numeroBlanquesEstablertes = 10;
        int numeroBlanques = 20;
        int dimX = 8;
        int dimY = 8;
        
        Object[] ret = new Object[] {numeroBlanquesEstablertes, numeroBlanques, dimX, dimY};
        return ret;
    }
    
    public Object[] getInfoPartida(String id_partida) {
        String nomEnunciat = "";
        String nomComencada = "";
        int tempsActual = 0; 
        
        String partides = "";
        try {partides = Dades.carregaArxiu(root + "partides.txt");}
        catch (NoSuchFileException ex) {}
        
        String[] files = partides.split("\n");
        for (String fila : files) {
            if (fila.split(":")[0].equals(id_partida)) {
                nomEnunciat = (fila.split(":")[1]);
                nomComencada = (fila.split(":")[2]);
                tempsActual = Integer.parseInt(fila.split(":")[3]);
                
                System.out.println("(Ctrl Persist) S'han extret info partida de " + id_partida);
                return new Object[] {nomEnunciat, nomComencada, tempsActual};
            }
        }
        System.out.println("(Ctrl Persist) No s'ha trobat id_partida " + id_partida);
        return null;
    }
    
    public Object[] carregaPartida (String id_partida) {
        // TODO
        
        
        
        return null;
    }
    
    public boolean guardaPartida (String usuari, Integer timestamp, String taulerFormatEstandard) {
        // TODO
        return true;
    }
    
    public boolean borrarPartida(String id_partida) {
        String partides = "";
        try {partides = Dades.carregaArxiu(root + "partides.txt");}
        catch (NoSuchFileException ex) {}
        
        String[] files = partides.split("\n");
        String escriure = "";
        boolean primer = true;
        System.out.println("(Ctrl Persist) Ara es borra partida " + id_partida);
        
        for (int i = 0; i < files.length; i++) {
            if (!files[i].split(":")[0].equals(id_partida)) {
                if (primer) {
                    escriure += files[i];
                }
                else escriure += "\n" + files[i];
            }
            else System.out.println("(Ctrl Persist) S'ha borrat partida " + id_partida);
        }
        Dades.guardarArxiu(root + "partides.txt", escriure);
        return true;
    }
    
    public boolean addUser(String usuari, String hash) {
        
        String shadow = "";
        try {shadow = Dades.carregaArxiu(root + "shadow.txt");}
        catch (NoSuchFileException ex) {}
        
        String line = "\n" + usuari + ":" + hash + ":";
        
        String new_shadow = shadow + line;
        Dades.guardarArxiu(root + "shadow.txt", new_shadow);
        
        System.out.println("(Ctrl Persist) S'ha afegit usuari " + usuari);
        return true;
    }
    
}