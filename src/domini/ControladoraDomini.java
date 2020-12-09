package domini;

import domini.algoritme.Algoritme;
import domini.hashing.Hash;
import domini.partida.Partida;
import domini.ranking.Ranking;
import domini.repositori.Repositori;
import domini.tauler.Tauler;
import domini.tauler.TaulerComencat;
import domini.tauler.TaulerEnunciat;
import domini.usuari.Configuracio;
import domini.usuari.Usuari;

import interficie.ControladoraInterficie;

import persistencia.ControladoraPersistencia;

import java.util.ArrayList;
import java.util.Vector;

public class ControladoraDomini {
    
    // ATRIBUTS
    
    // Algoritme
    private Algoritme algoritme = new Algoritme();
    
    // Usuaris
    private ArrayList<String> ids_usuaris;
    private String nom_usuari_actual;
    private Usuari usuari_actual;
    
    //Partides usuari
    private Vector<String> partides;
    private String id_partida_actual;
    private Partida partida_actual;
    
    // Repositori
    private Vector<String> id_enunciats_repo;
    private Repositori repositori;
    
    // Ranking TODO
    //private ArrayList<String> id_entrades_ranking; // ?????
    private Ranking ranking;
    
    // Controladores
    public ControladoraInterficie ctrl_interficie;
    public ControladoraPersistencia ctrl_persist;
    
    // CONSTRUCTORES
    
    public ControladoraDomini (ControladoraInterficie ci, ControladoraPersistencia persist) {
        this.ctrl_interficie = ci;
        this.ctrl_persist = persist;
    }
    
    // INICIALITZACIÓ
    
    public void inicia () {
        this.ids_usuaris = ctrl_persist.llista_usuaris();
        this.id_enunciats_repo = ctrl_persist.llista_id_enunciats();
    }
    
    public boolean seleccionaUsuari (String nom_usuari){
        if( ! ids_usuaris.contains(nom_usuari)) return false;
        if( nom_usuari.equals(this.nom_usuari_actual)) return true;
        
        // TODO
        String hashPwd = ctrl_persist.getHashPassword(nom_usuari);
        partides = ctrl_persist.llista_id_partides(nom_usuari);
        
        Object[] ret = ctrl_persist.getConfiguracio(nom_usuari);
        Configuracio c = new Configuracio((int)ret[0], (int)ret[1], (int)ret[2], (int)ret[3]);
        
        Usuari u = new Usuari(nom_usuari, hashPwd, c, partides);
        this.nom_usuari_actual = nom_usuari;
        this.usuari_actual = u;
        return true;
    }
    
    
/////////////////// MÈTODES dins la capa de DOMINI
    
    public Vector<String> llistaPartidesUsuari() {
        return usuari_actual.getLlistaIdPartides();
    }
    
    public Vector<String> llista_id_enunciats() {
        return ctrl_persist.llista_id_enunciats();
        //return new Vector<String>();
    }

    
/////////////////// MÈTODES cap a la capa de PERSISTÈNCIA 
    
    ////// Usuaris
    
    public boolean registrarUsuari (String nom_u, String password) {
        if (ids_usuaris.contains(nom_u)){
            System.out.println("(CtrlDomini) Usuari ja existeix");
            return false;
        }
        String hash = Hash.calculaHash(password);
        boolean correcte = ctrl_persist.addUser(nom_u, hash);
        if (correcte){
            ids_usuaris.add(nom_u);
        }
        return correcte;
    }
        
    public boolean validarCredencials (String id_usuari, String password){
        String hash = Hash.calculaHash(password);
        String real = ctrl_persist.getHashPassword(id_usuari);
        return real.equals(hash);
    }
    
    public Object[] getConfigPreferida (String usuari) {
        return ctrl_persist.getConfiguracio(usuari);
    }
    
    /* TODO */  public boolean setConfigPreferida (String usuari, Object[] conf) {
        //return ctrl_persist.setConfiguracio(usuari, conf);
        return true;
    }
    
    
    ////// Partides
    
    public Object[] getInfoPartida (String id_partida) {
        return ctrl_persist.getInfoPartida(id_partida);
    }
    
    public boolean borrarPartida (String id_partida, String usuari) {
        return ctrl_persist.borrarPartida(id_partida, usuari);
    }
    
    public boolean guardaPartida(int temps, String tauler_fStd) {
        return ctrl_persist.guardaPartida(id_partida_actual, nom_usuari_actual, temps, tauler_fStd);
    }
    
    ////// Començar a jugar
    
    public Object[] iniciaPartida(String id_partida) {
        System.out.println("(CtrlDomini) iniciar partida id=" + id_partida);
        
        // OBTENIR DADES
        Object[] ret = ctrl_persist.carregaPartida(id_partida);
        
        // CONSTRUIR Partida
        int timestamp = (int) ret[1];
        String formatStd = (String) ret[0];
        TaulerEnunciat te = new TaulerEnunciat(formatStd);
        TaulerComencat tc = new TaulerComencat(te);
        Partida partida = new Partida(usuari_actual, te, tc, 0, false);
        
        // INICIAR
        this.partida_actual = partida;
        this.id_partida_actual = id_partida;
        
        String[][] tauler = tauler_a_MatriuStrings(tc);
        return new Object[] {tauler, timestamp};
    }
    
    public Object[] generaIniciaNovaPartida(int files, int cols, int valor, Integer blanques) {
        System.out.println("(CtrlDomini) generar nova partida");
        
        // GENERAR nova Partida
        TaulerEnunciat te = algoritme.generarKakuro(files, cols, blanques, valor);
        TaulerComencat tc = new TaulerComencat(te);
        String formatStd = tc.format_Estandard();
        String nou_id = ctrl_persist.guardaNovaPartida(nom_usuari_actual, 0, formatStd);
        partides.add(nou_id); // retorna un bool..
        
        // INICIAR
        this.partida_actual = new Partida(usuari_actual, te, tc, 0, false);
        this.id_partida_actual = nou_id;
        
        String[][] tauler = tauler_a_MatriuStrings(tc);
        return new Object[] {tauler, 0}; // '0' ja que és una nova partida.
    }
    
    public Object[] iniciaNovaPartidaDesdeRepositori(String id_enunciat) {
        System.out.println("(CtrlDomini) Iniciar partida desde Repositori");
        
        // OBTENIR DADES
        Object[] ret = ctrl_persist.carregaPartidaRepositori(Integer.parseInt(id_enunciat), this.nom_usuari_actual);
        
        // CONSTRUIR Partida
        String nou_id_partida = (String) ret[0];
        String formatStd = (String) ret[1];
        TaulerEnunciat te = new TaulerEnunciat(formatStd);
        TaulerComencat tc = new TaulerComencat(te);
        Partida partida = new Partida(usuari_actual, te, tc, 0, false);
        
        // GENERAR nova Partida
        partides.add(nou_id_partida); // retorna un bool..
        
        // INICIAR
        this.partida_actual = new Partida(usuari_actual, te, tc, 0, false);
        this.id_partida_actual = nou_id_partida;
        
        String[][] tauler = tauler_a_MatriuStrings(tc);
        return new Object[] {tauler, 0}; // '0' ja que és una nova partida.
    }
        
        
/////////////////// CANVIS DE FORMAT
    
    private String[][] tauler_a_MatriuStrings(Tauler tauler) {
        return tauler.toFormatInterficie();
    }
}