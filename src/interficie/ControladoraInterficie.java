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
    public PlayingFrame playing = new PlayingFrame(this);
    public RepositoriFrame repo = new RepositoriFrame(this);
    
    // Funció per INICIAR una instància del Controlador.
    
    public void inicia() {
        // Inicia altres controladores
        ctrl_persist.inicia();
        ctrl_domini.inicia();
        
        // Inicia i Visibilitza el Frame de login
        login.inicia();
        login.setVisible(true);
    }
    
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
