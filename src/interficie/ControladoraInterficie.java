package interficie;

import domini.ControladoraDomini;
import java.util.concurrent.TimeUnit;
import persistencia.ControladoraPersistencia;

public class ControladoraInterficie {
    
    // CONTROLADORES de Domini i Persistencia
    
    public ControladoraDomini ctrl_domini;
    public ControladoraPersistencia ctrl_persist;
    
    // CONSTRUCTORA
    
    public ControladoraInterficie() {
        ctrl_persist = new ControladoraPersistencia(this);
        ctrl_domini = new ControladoraDomini(this, ctrl_persist);
    };
    
    // Tots els FRAMES
    
    public LoginFrame login = new LoginFrame(this);
    public IniciFrame inici = new IniciFrame(this);
    public RegistreFrame registre = new RegistreFrame(this);
    public PlayingFrame playing; // S'ha d'inicialitzar quan es comença la partida cada cop. // = new PlayingFrame(this);
    public RepositoriFrame repo = new RepositoriFrame(this);
    public RankingFrame ranking = new RankingFrame(this);
    
    // Inicialització
    
    public void inicia() {
        // Inicia altres controladores
        ctrl_persist.inicia();
        ctrl_domini.inicia();
        
        // Inicia i Visibilitza el Frame de login
        login.inicia();
        login.setVisible(true);
    }
    
    
    
/////////////////// PARTIDES
    public String[][] getSolucio(){
        return ctrl_domini.getSolucio();
    }
    
    public boolean esSolucio(String[][] tauler){
        return ctrl_domini.esSolucio(tauler);
    }
    public boolean valor_canviat(int fila, int col, Integer valor){
    
        return true;
    }
          
    private void iniciarFramePlaying (Object[] ret) {
        playing = new PlayingFrame(this, ret);
        inici.setVisible(false);
        playing.setVisible(true);
    }
    
    public void iniciaPartida(String id_partida) {
        System.out.println("(CtrlInt) iniciar partida id=" + id_partida);
        Object[] ret = ctrl_domini.iniciaPartida(id_partida);
        iniciarFramePlaying (ret);
    }

    public void generaAndIniciaNovaPartida(int files, int cols, int valor, Integer blanques, int dificultat) {
        System.out.println("(CtrlInt) generar i iniciar nova partida");
        Object[] ret = ctrl_domini.generaIniciaNovaPartida(files, cols, valor, blanques, dificultat);
        iniciarFramePlaying (ret);
    }
    
    public void iniciaNovaPartidaDesdeRepositori(String id_enunciat) {
        System.out.println("(CtrlInt) Iniciar partida desde Repositori");
        Object[] ret = ctrl_domini.iniciaNovaPartidaDesdeRepositori(id_enunciat);
        iniciarFramePlaying (ret);
    }
    
    public void iniciaNovaPartidaDesdeArxiu(String path) {
        System.out.println("(CtrlInt) Iniciar partida desde Arxiu");
        Object[] ret = ctrl_domini.iniciaNovaPartidaDesdeArxiu(path);
        iniciarFramePlaying (ret);
    }
    
    public boolean guardaPartida(int temps, String[][] tauler_format_interficie) {
        return ctrl_domini.guardaPartida(temps, tauler_format_interficie);
    }
        
    public void acabaPartida (boolean opt, int temps) {
        ctrl_domini.acabaPartida(opt,temps);
    }
    
/////////////////// CONFIGURACIÓ USUARI
    
    public boolean setConfigPreferida (int f,int c,int bv,int b) {
        String[] config = new String[] {""+f, ""+c, ""+bv, ""+b};
        return ctrl_domini.setConfigPreferida(config);
    }
    
/////////////////// RANKING
    
    public Object[] getRankings (int dificultat) {
        return ctrl_persist.getRankings(dificultat);
    }
    
/////////////////// CANVIS DE FORMAT
    
    public static String deTimestampAVerbose(long seconds) {
            int day = (int)TimeUnit.SECONDS.toDays(seconds);        
            long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
            long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
            long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);
            String tot = "";
            boolean first = true;
            if (day != 0) {tot += (day + "d"); first=false;}
            if (hours != 0) { if(!first) tot += " "; tot += (hours + "h"); first=false;}
            if (minute != 0) { if(!first) tot += " "; tot += (minute + "m"); first=false;}
            if (second != 0) { if(!first) tot += " "; tot += (second + "s"); first=false;}
            if (first) return "no començada";
            return tot;
    }
    
    
}
