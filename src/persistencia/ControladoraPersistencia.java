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
        
        shadow      --> usuari:hashPassword:configuració:{idPartida1,idPartida2...}
        partides    --> idPartida:idEnunciat:temps:dificultat
        repositori  --> idEnunciat:usuariPropietari:dificultat:N:usuari:temps
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
        
        if (!Dades.existeixArxiu(root + "shadow" + ext)) reescriureDocument("shadow", "nologin:x:0,0,0,0:0");
        if (!Dades.existeixArxiu(root + "partides" + ext)) reescriureDocument("partides", "0:0:0:0");
        if (!Dades.existeixArxiu(root + "repositori" + ext)) reescriureDocument("repositori", "");
        if (!Dades.existeixArxiu(root + "ranking" + ext)) reescriureDocument("ranking", "0:\n0:\n0:");
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
                if (elems.length > 3) {
                    llista.addAll(Arrays.asList(fila.split(":")[3].split(",")));
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
        
        String document = getDocument("shadow");
        String[] llistaUsuaris = getLlista(document, "\n");
        Object[] fila = getUsuari(usuari, llistaUsuaris);
        String[] elemsUsuari = getLlista((String) fila[0], ":");
        String[] config = getLlista(elemsUsuari[2],",");
        
        numeroBlanquesEstablertes = Integer.parseInt(config[0]);
        numeroBlanques  = Integer.parseInt(config[1]);
        dimX = Integer.parseInt(config[2]);
        dimY = Integer.parseInt(config[3]);
        
        Object[] ret = new Object[] {numeroBlanquesEstablertes, numeroBlanques, dimX, dimY};
        return ret;
    }
    
    public boolean setConfiguracio(String usuari, String[] config) {
        // numeroBlanquesEstablertes, numeroBlanques, dimX, dimY
        String document = getDocument("shadow");
        String[] llistaUsuaris = getLlista(document, "\n");
        Object[] fila = getUsuari(usuari, llistaUsuaris);
        String[] elemsUsuari = getLlista((String) fila[0], ":");
        
        elemsUsuari[2] = config[0] + "," + config[1] + "," + config[2] + "," + config[3];
        llistaUsuaris = reescriureElemLlista(llistaUsuaris, elemsUsuari, (int) fila[1]);
        document = prepararEscriptura(llistaUsuaris);
        reescriureDocument("shadow", document);
        
        return true;
    }
    
    private int getDificultat(String idEnunciat) {
        String documentRepositori = getDocument("repositori");
        String[] repositori = getLlista(documentRepositori, "\n");
        Object[] enunciat = getUsuari(idEnunciat, repositori);
        return Integer.parseInt(getLlista((String) enunciat[0], ":")[2]);
    }
    
    public Object[] getInfoPartida(String id_partida) {
        String partides = getDocument("partides");
        String[] files = getLlista(partides, "\n");
        
        Object[] partida = getUsuari(id_partida, files);
        String[] elemsPartida = getLlista((String) partida[0], ":");
        
        return new Object[] {elemsPartida[1], elemsPartida[0], Integer.parseInt(elemsPartida[2]), elemsPartida[3]};
    }
    
    public ArrayList<Object[]> getLlistaInfoPartides(String usuari) {
        ArrayList<Object[]> ret = new ArrayList<>();
        Vector<String> llista = llista_id_partides(usuari);
        
        for (String idPartida : llista) {
            ret.add(getInfoPartida(idPartida));
        }
        return ret;
    }
    
    public ArrayList<Object[]> getLlistaInfoEnunciats() {
        ArrayList<Object[]> llista = new ArrayList<>();
        
        String document = getDocument("repositori");
        String[] llistaEnunciats = getLlista(document, "\n");
        
        // Per si el repositori està buit:
        if (llistaEnunciats.length == 1 && llistaEnunciats[0].equals("")) return llista;
        
        for (String enunciat : llistaEnunciats) {
            Object[] info = new Object[] {"id","owner","diff","bool","user","time"};
            String[] aux = getLlista(enunciat,":");
            System.arraycopy(aux, 0, info, 0, aux.length);
            llista.add(info);
        }
        return llista;
    }
    
    public Object[] carregaPartida (String id_partida) {
        // TODO dificultat
        
        String documentPartides = getDocument("partides");
        String[] llistaPartides = getLlista(documentPartides, "\n");
        
        Object[] fila;
        fila = getUsuari(id_partida, llistaPartides);
        String[] elemsPartida = getLlista((String) fila[0], ":");

        int dificultat = getDificultat(elemsPartida[1]);
        
        String documentComencada = getDocument("comencada/" + id_partida);
        Object[] ret = new Object[] {documentComencada, Integer.parseInt(elemsPartida[2]), dificultat};
        return ret;
    }
    
    public Object[] carregaPartidaRepositori(int idEnunciat, String usuari) {
        // TODO dificultat

        int dificultat = getDificultat("" + idEnunciat);
        
        int id_partida = assignarId("partides");
        String id = "" + id_partida;
        guardarPartidaShadow(usuari, id);
        guardarNovaPartidaPartides(id, 0, idEnunciat, dificultat);
        
        String tauler = getDocument("enunciats/" + idEnunciat);
        guardarPartidaDirs(id_partida, "comencada", tauler);
        
        return new Object[] {id_partida, tauler, dificultat};
    }
    
    public String getEnunciatDePartida(String id_partida) {
        Object[] enunciat = getIdDocument(id_partida, "partides");
        return getDocument("enunciats/" + (int) enunciat[0]);
    }
    
    //////////////////// PRIVADES RANDOM *GUARDAR ////////////////////
    
    private int assignarId(String arxiu) {
        String document = getDocument(arxiu);
        String[] llista = getLlista(document, "\n");
        
        if (llista.length > 1) return Integer.parseInt(llista[llista.length - 1].split(":")[0]) + 1;
        if (llista[0].length() > 0) return 2;
        return 1;
    }
    
    private String[] guardarElemLlista(String[] llista, String id, int idEnunciat, Integer temps, int dificultat) {
        String escriure = id + ":" + idEnunciat + ":" + temps + ":" + dificultat;
        
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
    
    private Object[] getIdDocument(String id_partida, String arxiu) {
        String document = getDocument(arxiu);
        String[] llistaPartides = getLlista(document, "\n");
        Object[] partida = getUsuari(id_partida, llistaPartides);
        return new Object[] {Integer.parseInt(getLlista((String) partida[0], ":")[1]), partida[1]};
    }
    
    //////////////////// GUARDAR PARTIDA SHADOW ////////////////////
    
    private void guardarPartidaShadow(String usuari, String id) {
        String document = getDocument("shadow");
        String[] llistaUsuaris = getLlista(document, "\n");
        
        Object[] fila;
        fila = getUsuari(usuari, llistaUsuaris);
        String[] elemsUsuari = getLlista((String) fila[0], ":");
        
        if (elemsUsuari.length == 3) {
            String[] aux = new String[4];
            aux[0] = elemsUsuari[0];
            aux[1] = elemsUsuari[1];
            aux[2] = elemsUsuari[2];
            aux[3] = id;
            
            llistaUsuaris = reescriureElemLlista(llistaUsuaris, aux, (int) fila[1]);
            document = prepararEscriptura(llistaUsuaris);
            reescriureDocument("shadow", document);
        }
        else if (elemsUsuari.length > 3) {
            String[] partides = getLlista(elemsUsuari[3], ",");
            boolean existeix = false;

            for (int i = 0; i < partides.length; i++) if (id.equals(partides[i])) existeix = true;

            if (!existeix) {
                elemsUsuari[3] = elemsUsuari[3] + "," + id;

                llistaUsuaris = reescriureElemLlista(llistaUsuaris, elemsUsuari, (int) fila[1]);
                document = prepararEscriptura(llistaUsuaris);
                reescriureDocument("shadow", document);
            }
        }
    }
    
    //////////////////// GUARDAR PARTIDA PARTIDES ////////////////////
    
    private void guardarNovaPartidaPartides(String id, Integer temps, int idEnunciat, int dificultat) {
        String document = getDocument("partides");
        String[] llistaPartides = getLlista(document, "\n");
        
        llistaPartides = guardarElemLlista(llistaPartides, id, idEnunciat, temps, dificultat);
        
        document = prepararEscriptura(llistaPartides);
        reescriureDocument("partides", document);
    }
    
    private void guardarPartidaPartides(String id, Integer temps, String idEnunciat, int dificultat, int index) {
        String document = getDocument("partides");
        String[] llistaPartides = getLlista(document, "\n");
        llistaPartides[index] = id + ":" + idEnunciat + ":" + temps + ":" + dificultat;
        //llistaPartides = guardarElemLlista(llistaPartides, id, idEnunciat, temps, dificultat);
        
        document = prepararEscriptura(llistaPartides);
        reescriureDocument("partides", document);
    }
    
    //////////////////// GUARDAR PARTIDA ENUNCIATS I COMENCADES ////////////////////
    
    private void guardarPartidaDirs(int idEnunciat, String dir, String tauler) {
        reescriureDocument(dir + "/" + idEnunciat, tauler);
    }
    
    //////////////////// GUARDAR PARTIDA AL REPOSITORI ////////////////////
    
    private void guardarNovaPartidaRep(String idEnunciat, String autor, int dificultat) {
        String document = getDocument("repositori");
        String escriure = idEnunciat + ":" + autor + ":" + dificultat + ":0:";
        if (!document.equals("")) escriure = "\n" + escriure;
        reescriureDocument("repositori", document + escriure);
    }
    
    //////////////////// GUARDAR PARTIDA ////////////////////
    
    public String guardaNovaPartida (String usuari, Integer timestamp, String taulerFormatEstandard, int dificultat) {
        // TODO dificultat
        int id_partida = assignarId("partides");
        String id = "" + id_partida;
        guardarPartidaShadow(usuari, id);
        
        int idEnunciat = assignarIdDocument("enunciats");
        guardarNovaPartidaPartides(id, timestamp, idEnunciat, dificultat);
        
        guardarPartidaDirs(id_partida, "comencada", taulerFormatEstandard);
        guardarPartidaDirs(idEnunciat, "enunciats", taulerFormatEstandard);
        guardarNovaPartidaRep("" + idEnunciat, usuari, dificultat);
        
        return id;
    }
    
    public boolean guardaPartida (String id_partida, String usuari, Integer timestamp, String taulerFormatEstandard, int dificultat) {
        guardarPartidaShadow(usuari, id_partida);
        
        Object[] enunciat = getIdDocument(id_partida, "partides");
        guardarPartidaPartides(id_partida, timestamp, ""+enunciat[0], dificultat, (int) enunciat[1]);
        
        //guardarPartidaDirs(idEnunciat, "enunciats", taulerFormatEstandard);
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
        
        String[] partides = getLlista(elemsUsuari[3], ",");
        elemsUsuari[3] = borrarPartidaUsuari(partides, id_partida);
        
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
        int numElems = Integer.parseInt(elems[3]);
        if (numElems == 0) {
            int mida = elems.length;
            String[] ret = new String[mida + 1];
            System.arraycopy(elems, 0, ret, 0, mida);
            ret[3] = "1";
            ret[mida] = usuari + ":" + temps;
            return ret;
        }
        if (temps < Integer.parseInt(elems[5])) {
            elems[4] = usuari;
            elems[5] = "" + temps;
        }
        return elems;
    }
    
    private String[] actualitzarRanking(String[] elemsEntrada, String usuari, Integer temps) {
        int mida = Integer.parseInt(elemsEntrada[0]);
        if (mida == 0) {
            return new String[] {"1:" + usuari + "," + temps};
        }
        
        boolean bool = false;
        String[] ret;
        if (mida < 3) {
            mida++;
            bool = true;
        }
        ret = new String[mida + 1];
        ret[0] = "" + mida;
        
        if (bool) mida--;
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
        //ret[mida] = usuari + "," + temps;
        return ret;
    }
    
    public boolean acabarPartida(String usuari, String id_partida, Integer temps, int dificultat) {
        // TODO dificultat
        
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
        if (dificultat < 3) {
            String documentRanking = getDocument("ranking");
            String[] ranking = getLlista(documentRanking, "\n");
            String entrada = ranking[dificultat];
            String[] elemsEntrada = getLlista(entrada, ":");

            elemsEntrada = actualitzarRanking(elemsEntrada, usuari, temps);
            ranking = reescriureElemLlista(ranking, elemsEntrada, dificultat);

            documentRanking = prepararEscriptura(ranking);
            reescriureDocument("ranking", documentRanking);
        }
        
        // Borrar de shadow i la partida començada
        borrarPartidaComencada(id_partida, "comencada/");
        borrarPartidaShadow(usuari, id_partida);
        
        return true;
    }
    
    public boolean addUser(String usuari, String hash) {
        
        String shadow = "";
        try {shadow = Dades.carregaArxiu(root + "shadow.txt");}
        catch (NoSuchFileException ex) {}
        
        String line = "\n" + usuari + ":" + hash + ":0,9,5,5:";
        
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