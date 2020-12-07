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
    
    //////////////////// PRIVADES RANDOM ////////////////////
    
    private String getDocument(String nomArxiu) {
        String arxiu = "";
        try {arxiu = Dades.carregaArxiu(root + nomArxiu + ".txt");}
        catch (NoSuchFileException ex) {}
        return arxiu;
    }
    
    private String[] getLlista(String arxiu, String separador) {
        String[] ret = arxiu.split(separador);
        return ret;
    }
    
    private Object[] getUsuari(String usuari, String[] llista) {
        for (int i = 0; i < llista.length; i++) {
            if (llista[i].split(":")[0].equals(usuari)) return new Object[] {llista[i],i};
        }
        return null;
    }
    
    //////////////////// ELIMINAR PARTIDA ////////////////////
    
    private String borrarElemLlista(String[] llistaPartides, int index) {
        String escriure = "";
        boolean primer = true;
        
        for (int i = 0; i < llistaPartides.length; i++) {
            if (i != index) {
                if (primer) {
                primer = false;
                escriure += llistaPartides[i];
                }
                else escriure += "\n" + llistaPartides[i];
            }
        }
        return escriure;
    }
    
    private String borrarPartidaUsuari(String[] partides, String id_partida) {
        String ret = "";
        boolean primer = true;
        
        for (String partida : partides) {
            if (!partida.equals(id_partida)) {
                if (primer) {
                    primer = false;
                    ret += partida;
                }
                else ret += "," + partida;
            }
            else System.out.println("(Ctrl Persist) Ara es borra de shadow partida " + id_partida);
        }
        return ret;
    }
    
    private String[] reescriureElemLlista(String[] llistaUsuaris, String[] elemsUsuari, int index) {
        String escriure = "";
        boolean primer = true;
        for (String elem : elemsUsuari) {
            if (primer) {
                primer = false;
                escriure += elem;
            }
            else escriure += ":" + elem;
        }
        llistaUsuaris[index] = escriure;
        return llistaUsuaris;
    }
    
    private String prepararEscriptura(String[] llistaUsuaris) {
        String escriure = "";
        boolean primer = true;
        for (String usuari : llistaUsuaris) {
            if (primer) {
                primer = false;
                escriure += usuari;
            }
            else escriure += "\n" + usuari;
        }
        return escriure;
    }
    
    private void reescriureDocument(String arxiu, String escriure) {
        Dades.guardarArxiu(root + arxiu +".txt", escriure);
    }
    
    //////////////////// ELIMINAR PARTIDA SHADOW ////////////////////
    
    private void borrarPartidaShadow(String usuari, String id_partida) {
        String document = getDocument("shadow");
        String[] llistaUsuaris = getLlista(document, "\n");
        
        Object[] fila;
        fila = getUsuari(usuari, llistaUsuaris);
        String[] elemsUsuari = getLlista((String) fila[0], ":");
        
        String[] partides = getLlista(elemsUsuari[2], ",");
        elemsUsuari[2] = borrarPartidaUsuari(partides, id_partida);
        
        llistaUsuaris = reescriureElemLlista(llistaUsuaris, elemsUsuari, (int) fila[1]);
        document = prepararEscriptura(llistaUsuaris);
        reescriureDocument("shadow", document);
    }
    
    //////////////////// ELIMINAR PARTIDA PARTIDES ////////////////////
    
    private void borrarPartidaPartides(String usuari, String id_partida) {
        String document = getDocument("partides");
        String[] llistaPartides = getLlista(document, "\n");
        
        Object[] fila;
        fila = getUsuari(id_partida, llistaPartides);
        
        document = borrarElemLlista(llistaPartides, (int) fila[1]);
        reescriureDocument("partides", document);
    }
    
    //////////////////// ELIMINAR PARTIDA ////////////////////
    
    public boolean borrarPartida(String id_partida, String usuari) {
        borrarPartidaPartides(usuari, id_partida);
        borrarPartidaShadow(usuari, id_partida);
        
        return true;
    }
    
    
    
    /*private void borrarPartidaSha(String fila, String id_partida) {
        String[] elems = getLlista(fila,":");
        String[] partides = getLlista(elems[2], ",");
        for (String partida : partides) {
            if (partida.equals(id_partida)) 
        }
    }
    
    public boolean borrarPartidaSh(String usuari, String id_partida) {
        String document = getDocument("shadow");
        String[] llista = getLlista(document, "\n");
        String fila = getUsuari(usuari, llista);
        if (fila != null) {
            borrarPartidaSha(fila, id_partida);
            
        }
        return true;
    }
    
    //////////////////// ELIMINAR PARTIDA ////////////////////
    
    private boolean borrarPartidaShadow(String usuari, String id_partida) {
        String shadow = "";
        try {shadow = Dades.carregaArxiu(root + "shadow.txt");}
        catch (NoSuchFileException ex) {}
        
        String[] files = shadow.split("\n");
        String escriure = "";
        boolean primer = true;
        System.out.println("(Ctrl Persist) Ara es borra de shadow partida " + id_partida);
        
        for (String fila : files) {
            if (fila.split(":")[0].equals(usuari)) {
                String[] partides = fila.split(":")[2].split(",");
                String llistaPartides = "";
                boolean primerAux = true;
                
                for (String partida : partides) {
                    if (!partida.equals(id_partida)) {
                        if (primerAux) {
                            llistaPartides += fila;
                            primerAux = false;
                        }
                        else llistaPartides += "," + fila;
                    }
                    else System.out.println("(Ctrl Persist) S'ha borrat de shadow partida " + id_partida);
                }
                if (primer) {
                    String escriurePartides = fila.split(":")[0] + ":" + fila.split(":")[1] + ":" + llistaPartides;
                    escriure += escriurePartides;
                    primer = false;
                }
                else {
                    String escriurePartides = fila.split(":")[0] + ":" + fila.split(":")[1] + ":" + llistaPartides;
                    escriure += "\n" + escriurePartides;
                }
            }
            else if (primer) {
                    escriure += fila;
                    primer = false;
                }
            else escriure += "\n" + fila;
        }
        
        Dades.guardarArxiu(root + "shadow.txt", escriure);
        return true;
    }
    
    private boolean borrarPartidaPartides(String id_partida) {
        String partides = "";
        try {partides = Dades.carregaArxiu(root + "partides.txt");}
        catch (NoSuchFileException ex) {}
        
        String[] files = partides.split("\n");
        String escriure = "";
        boolean primer = true;
        System.out.println("(Ctrl Persist) Ara es borra de partides partida " + id_partida);
        
        for (int i = 0; i < files.length; i++) {
            if (!files[i].split(":")[0].equals(id_partida)) {
                if (primer) {
                    escriure += files[i];
                    primer = false;
                }
                else escriure += "\n" + files[i];
            }
            else System.out.println("(Ctrl Persist) S'ha borrat de partides partida " + id_partida);
        }
        Dades.guardarArxiu(root + "partides.txt", escriure);
        return true;
    }
    
    public boolean borrarPartida(String id_partida, String usuari) {
        boolean ok = borrarPartidaPartides(id_partida);
        if (ok) {
            boolean ok2 = borrarPartidaShadow(usuari, id_partida);
        }
        return true;
    }*/
    
    //////////////////// ELIMINAR PARTIDA FI ////////////////////
    
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