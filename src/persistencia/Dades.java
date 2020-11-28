package persistencia;

import java.nio.file.NoSuchFileException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
        
public class Dades {

    public static void guardarArxiu(String arxiu, String bufferedData){
        Path pa = Path.of(arxiu);
        try {
            Files.writeString(pa, bufferedData);
        } catch (IOException ex) {
            Logger.getLogger(Dades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String carregaArxiu(String arxiu) throws NoSuchFileException {
        String ret = "";
        Path pa = Path.of(arxiu);
        try {
            ret = Files.readString(pa);
        } catch (NoSuchFileException ex) {
            throw new NoSuchFileException("","","");
        } catch (IOException ex) {
            Logger.getLogger(Dades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
}
