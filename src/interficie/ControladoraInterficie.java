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
    
    public boolean valor_canviat(int fila, int col, Integer valor){
    
        return true;
    }
    
    public void iniciaPartida(String id_partida) {
        System.out.println("(CtrlInt) iniciar partida id=" + id_partida);
        Object[] ret = ctrl_domini.iniciaPartida(id_partida);
        playing = new PlayingFrame(this, ret);
    }

    public void generaAndIniciaNovaPartida(int files, int cols, int valor, Integer blanques) {
        System.out.println("(CtrlInt) generar i iniciar nova partida");
        Object[] ret = ctrl_domini.generaIniciaNovaPartida(files, cols, valor, blanques);
        playing = new PlayingFrame(this, ret);
    }
    
    public void iniciaNovaPartidaDesdeRepositori(String id_enunciat) {
        System.out.println("(CtrlInt) Iniciar partida desde Repositori");
        Object[] ret = ctrl_domini.iniciaNovaPartidaDesdeRepositori(id_enunciat);
        playing = new PlayingFrame(this, ret);
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
