package interficie;

import domini.ControladoraDomini;
import persistencia.ControladoraPersistencia;

public class ControladoraInterficie {
    
    private String dades_default_root = "dades";
    private String dades_root = dades_default_root;
    
    public ControladoraDomini domini = new ControladoraDomini();
    public ControladoraPersistencia persist = new ControladoraPersistencia(dades_root, this);
    
    public LoginFrame login = new LoginFrame(this);
    public IniciFrame inici = new IniciFrame(this);
    public RegistreFrame registre = new RegistreFrame(this);
    
    public void inicia() {
        domini.inicia();
        persist.inicia();
        login.inicia();
        login.setVisible(true);
    }
}
