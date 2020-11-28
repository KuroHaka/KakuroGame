package interficie;

import domini.ControladoraDomini;
import persistencia.ControladoraPersistencia;

public class ControladoraInterficie {
    
    ControladoraDomini domini = new ControladoraDomini();
    ControladoraPersistencia persist = new ControladoraPersistencia();
    InitFrame init = new InitFrame();
    
    public void inicia() {
        domini.inicia();
        persist.inicia();
        init.setVisible(true);
    }
}
