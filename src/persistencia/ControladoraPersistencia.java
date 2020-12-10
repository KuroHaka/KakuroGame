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
    String ext = ".txt";
    
    public ControladoraPersistencia(ControladoraInterficie ci) {
        this.ctrl_interficie = ci;
    }
    
    public void inicia() {
        /*
        
        directoris de dades {comencada, enunciats}
        arxius de dades {shadow, partides, repositori, ranking}
        arxius de comencada {"idPartida".txt...}
        arxius de enunciats {"idEnunciat".txt...}
        
        shadow      --> usuari:hashPassword:{idPartida1,idPartida2...}
        partides    --> idPartida:idEnunciat:temps
        repositori  --> idEnunciat:usuariPropietari:N:usuari:temps
            * N = bit boolea "hi ha record" (0 o 1)
            * Els temps correspon al record corresponent enunciat
        ranking     --> N{:usuari,temps}
            * Hi ha 3 files, una per dificultat
            * N = [0..3] indica quants apartats hi hauran posteriorment
        
        idPartida   --> "Tauler amb caselles editades (o no), partida d'un usuari concret"
        idEnunciat  --> "Tauler amb les caselles inicials de l'enunciat"
        
        */
        
        System.out.println("(Persist) Que comenci el drama :)");
        if (!Dades.existeixDirectori("dades")) Dades.ferDirectori("dades");
        
        if (!Dades.existeixDirectori(root + "comencada")) Dades.ferDirectori(root + "comencada");
        if (!Dades.existeixDirectori(root + "enunciats")) Dades.ferDirectori(root + "enunciats");
        
        if (!Dades.existeixArxiu(root + "shadow" + ext)) reescriureDocument(root + "shadow" + ext, "admin:x:0");
        if (!Dades.existeixArxiu(root + "partides" + ext)) reescriureDocument(root + "partides" + ext, "0:0:0");
        if (!Dades.existeixArxiu(root + "repositori" + ext)) reescriureDocument(root + "repositori" + ext, "0:admin:0:");
        if (!Dades.existeixArxiu(root + "ranking" + ext)) reescriureDocument(root + "ranking" + ext, "0:admin:0:\n0:admin:0:\n0:admin:0:");
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
    
    public Vector<String> llista_id_enunciats() {
        Vector<String> llista = new Vector<String>();
        
        String document = getDocument("repositori");
        String[] llistaEnunciats = getLlista(document, "\n");
        
        for (String enunciat : llistaEnunciats) {
            llista.add(enunciat.split(":")[0]);
        }
        return llista;
    }
    
    public Vector<String> llista_id_partides(String usuari) {
        Vector<String> llista = new Vector<>();
        
        String shadow = "";
        try {shadow = Dades.carregaArxiu(root + "shadow.txt");}
        catch (NoSuchFileException ex) {}
        
        String[] files = shadow.split("\n");
        for (String fila : files) {
            String[] elems = fila.split(":");
            if (elems[0].equals(usuari)) {
                if (elems.length > 2) {
                    llista.addAll(Arrays.asList(fila.split(":")[2].split(",")));
                }
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
        String partides = getDocument("partides");
        String[] files = getLlista(partides, "\n");
        
        Object[] partida = getUsuari(id_partida, files);
        String[] elemsPartida = getLlista((String) partida[0], ":");
        
        return new Object[] {elemsPartida[1], elemsPartida[0], Integer.parseInt(elemsPartida[2])};
    }
    
    public Object[] carregaPartida (String id_partida) {
        String documentPartides = getDocument("partides");
        String[] llistaPartides = getLlista(documentPartides, "\n");
        
        Object[] fila;
        fila = getUsuari(id_partida, llistaPartides);
        String[] elemsPartida = getLlista((String) fila[0], ":");
        
        String documentComencada = getDocument("comencada/" + id_partida);
        Object[] ret = new Object[] {documentComencada, Integer.parseInt(elemsPartida[2])};
        return ret;
    }
    
    public Object[] carregaPartidaRepositori(int idEnunciat, String usuari) {
        int id_partida = assignarId("partides");
        String id = "" + id_partida;
        guardarPartidaShadow(usuari, id);
        guardarPartidaPartides(id, 0, idEnunciat);
        
        String tauler = getDocument("enunciats/" + idEnunciat);
        guardarPartidaDirs(id_partida, "comencada", tauler);
        
        return new Object[] {id_partida, tauler};
    }
    
    //////////////////// PRIVADES RANDOM *GUARDAR ////////////////////
    
    private int assignarId(String arxiu) {
        String document = getDocument(arxiu);
        String[] llista = getLlista(document, "\n");
        
        if (llista.length > 1) return Integer.parseInt(llista[llista.length - 1].split(":")[0]) + 1;
        if (llista[0].length() > 0) return 2;
        return 1;
    }
    
    private String[] guardarElemLlista(String[] llista, String id, int idEnunciat, Integer temps) {
        String escriure = id + ":" + idEnunciat + ":" + temps;
        
        String[] ret = new String[llista.length + 1];
        System.arraycopy(llista, 0, ret, 0, llista.length);
        ret[llista.length] = escriure;
        
        return ret;
    }
    
    private int assignarIdDocument(String dir) {
        int extensio = ext.length();
        String[] llistaEnunciats = Dades.llistaArxius(root + dir);
        int idPotencial = -1;
        
        for (String enunciat : llistaEnunciats) {
            int aux = Integer.parseInt(enunciat.substring(0, enunciat.length()-extensio));
            if (aux > idPotencial) idPotencial = aux;
        }
        return idPotencial+1;
    }
    
    private int getIdDocument(String id_partida, String arxiu) {
        String document = getDocument(arxiu);
        String[] llistaPartides = getLlista(document, "\n");
        Object[] partida = getUsuari(id_partida, llistaPartides);
        return Integer.parseInt(getLlista((String) partida[0], ":")[1]);
    }
    
    //////////////////// GUARDAR PARTIDA SHADOW ////////////////////
    
    private void guardarPartidaShadow(String usuari, String id) {
        String document = getDocument("shadow");
        String[] llistaUsuaris = getLlista(document, "\n");
        
        Object[] fila;
        fila = getUsuari(usuari, llistaUsuaris);
        String[] elemsUsuari = getLlista((String) fila[0], ":");
        
        if (elemsUsuari.length == 2) {
            String[] aux = new String[3];
            aux[0] = elemsUsuari[0];
            aux[1] = elemsUsuari[1];
            aux[2] = id;
            
            llistaUsuaris = reescriureElemLlista(llistaUsuaris, aux, (int) fila[1]);
            document = prepararEscriptura(llistaUsuaris);
            reescriureDocument("shadow", document);
        }
        else if (elemsUsuari.length > 2) {
            String[] partides = getLlista(elemsUsuari[2], ",");
            boolean existeix = false;

            for (int i = 0; i < partides.length; i++) if (id.equals(partides[i])) existeix = true;

            if (!existeix) {
                elemsUsuari[2] = elemsUsuari[2] + "," + id;

                llistaUsuaris = reescriureElemLlista(llistaUsuaris, elemsUsuari, (int) fila[1]);
                document = prepararEscriptura(llistaUsuaris);
                reescriureDocument("shadow", document);
            }
        }
    }
    
    //////////////////// GUARDAR PARTIDA PARTIDES ////////////////////
    
    private void guardarPartidaPartides(String id, Integer temps, int idEnunciat) {
        String document = getDocument("partides");
        String[] llistaPartides = getLlista(document, "\n");
        
        llistaPartides = guardarElemLlista(llistaPartides, id, idEnunciat, temps);
        
        document = prepararEscriptura(llistaPartides);
        reescriureDocument("partides", document);
    }
    
    //////////////////// GUARDAR PARTIDA ENUNCIATS I COMENCADES ////////////////////
    
    private void guardarPartidaDirs(int idEnunciat, String dir, String tauler) {
        reescriureDocument(dir + "/" + idEnunciat, tauler);
    }
    
    //////////////////// GUARDAR PARTIDA AL REPOSITORI ////////////////////
    
    private void guardarNovaPartidaRep(String idEnunciat, String autor) {
        String document = getDocument("repositori");
        String escriure = "\n" + idEnunciat + ":" + autor + ":0:";
        reescriureDocument("repositori", document + escriure);
    }
    
    //////////////////// GUARDAR PARTIDA ////////////////////
    
    public String guardaNovaPartida (String usuari, Integer timestamp, String taulerFormatEstandard) {
        int id_partida = assignarId("partides");
        String id = "" + id_partida;
        guardarPartidaShadow(usuari, id);
        
        int idEnunciat = assignarIdDocument("enunciats");
        guardarPartidaPartides(id, timestamp, idEnunciat);
        
        guardarPartidaDirs(idEnunciat, "comencada", taulerFormatEstandard);
        guardarPartidaDirs(id_partida, "enunciats", taulerFormatEstandard);
        guardarNovaPartidaRep("" + idEnunciat, usuari);
        
        return id;
    }
    
    public boolean guardaPartida (String id_partida, String usuari, Integer timestamp, String taulerFormatEstandard) {
        guardarPartidaShadow(usuari, id_partida);
        
        int idEnunciat = getIdDocument(id_partida, "partides");
        guardarPartidaPartides(id_partida, timestamp, idEnunciat);
        
        guardarPartidaDirs(idEnunciat, "enunciats", taulerFormatEstandard);
        guardarPartidaDirs(Integer.parseInt(id_partida), "comencada", taulerFormatEstandard);
        
        return true;
    }
    
    
    //////////////////// PRIVADES RANDOM *BORRAR ////////////////////
    
    private String getDocument(String nomArxiu) {
        String arxiu = "";
        try {arxiu = Dades.carregaArxiu(root + nomArxiu + ext);}
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
                escriure = usuari;
            }
            else escriure += "\n" + usuari;
        }
        return escriure;
    }
    
    private void reescriureDocument(String arxiu, String escriure) {
        Dades.guardarArxiu(root + arxiu + ext, escriure);
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
    
    private void borrarPartidaPartides(String id_partida) {
        String document = getDocument("partides");
        String[] llistaPartides = getLlista(document, "\n");
        
        Object[] fila = getUsuari(id_partida, llistaPartides);
        
        document = borrarElemLlista(llistaPartides, (int) fila[1]);
        reescriureDocument("partides", document);
    }
    
    //////////////////// ELIMINAR PARTIDA COMENCADA ////////////////////
    
    private void borrarPartidaComencada(String id_partida, String dir) {
        Dades.borrarArxiu(root + dir + id_partida + ext);
        System.out.println("(Persist) S'ha borrat de comencada " + id_partida);
    }
    
    //////////////////// ELIMINAR PARTIDA ////////////////////
    
    public boolean borrarPartida(String id_partida, String usuari) {
        borrarPartidaPartides(id_partida);
        borrarPartidaShadow(usuari, id_partida);
        borrarPartidaComencada(id_partida, "comencada/");
        
        return true;
    }
    
    //////////////////// ELIMINAR PARTIDA FI ////////////////////
    
    private String[] actualitzarRep(String[] elems, String usuari, Integer temps) {
        int numElems = Integer.parseInt(elems[2]);
        if (numElems == 0) {
            int mida = elems.length;
            String[] ret = new String[mida + 1];
            System.arraycopy(elems, 0, ret, 0, mida);
            ret[mida] = usuari + ":" + temps;
            return ret;
        }
        if (temps < Integer.parseInt(elems[4])) {
            elems[3] = usuari;
            elems[4] = "" + temps;
        }
        return elems;
    }
    
    private String[] actualitzarRanking(String[] elemsEntrada, String usuari, Integer temps) {
        int mida = Integer.parseInt(elemsEntrada[0]);
        if (mida == 0) {
            return new String[] {usuari + "," + temps};
        }
        
        String[] ret;
        if (mida < 3) mida++;
        ret = new String[mida + 1];
        ret[0] = "" + mida;
        
        for (int i = 1; i <= mida; i++) {
            String[] llista = getLlista(elemsEntrada[i],",");
            
            int tempsAux = Integer.parseInt(llista[1]);
            if (tempsAux > temps) {
                ret[i] = usuari + "," + temps;
                usuari = llista[0];
                temps = tempsAux;
            }
            else ret[i] = elemsEntrada[i];
        }
        return ret;
    }
    
    public boolean acabarPartida(String usuari, String id_partida, Integer temps, int dificultat) {
        // Borrar de partides i agafar el idEnunciat
        String documentPartides = getDocument("partides");
        String[] llistaPartides = getLlista(documentPartides, "\n");
        Object[] partida = getUsuari(id_partida, llistaPartides);
        String[] elemsPartida = getLlista((String) partida[0], ":");
        
        documentPartides = borrarElemLlista(llistaPartides, (int) partida[1]);
        reescriureDocument("partides", documentPartides);
        
        // Actualitzar repositori
        String documentRepositori = getDocument("repositori");
        String[] repositori = getLlista(documentRepositori, "\n");
        Object[] enunciat = getUsuari(elemsPartida[1],repositori);
        String[] elemsEnunciat = getLlista((String) enunciat[0], ":");
        
        elemsEnunciat = actualitzarRep(elemsEnunciat, usuari, temps);
        repositori = reescriureElemLlista(repositori, elemsEnunciat, (int) enunciat[1]);
        
        documentRepositori = prepararEscriptura(repositori);
        reescriureDocument("repositori", documentRepositori);
        
        // Actualitzar ranking
        String documentRanking = getDocument("ranking");
        String[] ranking = getLlista(documentRanking, "\n");
        String entrada = ranking[dificultat];
        String[] elemsEntrada = getLlista(entrada, ":");
        
        elemsEntrada = actualitzarRanking(elemsEntrada, usuari, temps);
        ranking = reescriureElemLlista(ranking, elemsEntrada, dificultat);
        
        documentRanking = prepararEscriptura(ranking);
        reescriureDocument("ranking", documentRanking);
        
        // Borrar de shadow i la partida començada
        borrarPartidaComencada(id_partida, "comencada/");
        borrarPartidaShadow(usuari, id_partida);
        
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
    
    public Object[] getRankings(int dificultat) {
        String[] nomUser = new String[]{"?","?","?"};
        String[] temps = new String[] {"?","?","?"};
        
        String documentRanking = getDocument("ranking");
        String[] ranking = getLlista(documentRanking, "\n");
        String[] entrada = getLlista(ranking[dificultat], ":");
        int mida = Integer.parseInt(entrada[0]);
        
        for (int i = 0; i < mida; i++) {
            String[] record = getLlista(entrada[i+1], ",");
            nomUser[i] = record[0];
            temps[i] = record[1];
        }

        return new Object[] {nomUser, temps};
    }
    
}