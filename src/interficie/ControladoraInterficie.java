package interficie;

import domini.ControladoraDomini;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import persistencia.ControladoraPersistencia;

public class ControladoraInterficie {
    
    private String dades_default_root = "dades";
    private String dades_root = dades_default_root;
    
    // CONTROLADORES de Domini i Persistencia
    
    public ControladoraDomini domini;
    public ControladoraPersistencia persist;
    
    // CONSTRUCTORA
    
    public ControladoraInterficie() {
        domini = new ControladoraDomini(this, persist);
        persist = new ControladoraPersistencia(dades_root, this);
    };
    
    // Tots els FRAMES
    
    public LoginFrame login = new LoginFrame(this);
    public IniciFrame inici = new IniciFrame(this);
    public RegistreFrame registre = new RegistreFrame(this);
    public PlayingFrame playing = new PlayingFrame(this);
    
    // Funció per INICIAR una instància del Controlador.
    
    public void inicia() {
        // Inicia altres controladores
        persist.inicia();
        ArrayList<String> llista_id_usuaris = null; // I need this Eugeni
        ArrayList<String> llista_id_enunciats = null; // I need this Eugeni: son enunciats del repositori
        domini.inicia(llista_id_usuaris, llista_id_enunciats);
        
        // Inicia i Visibilitza el Frame de login
        login.inicia();
        login.setVisible(true);
    }
    
    // METODES: Funcions de comunicacio amb altres Controladores
    
    //public Partida deFilenameAPartida(String filename){
    //
    //}
    
    // METODES: Funcions per donar Format a diferents tipus
    
    public Object[] deFilenameAPartidaTimestampHash(String f){
        String[] parts = f.split("@");
        String nom = parts[1];
        String timestamp = deTimestampAVerbose(Long.parseLong(parts[2]));
        String hash = parts[3];
        return new Object[] {nom, timestamp, hash};
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
