package persistencia;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
        
public class Dades {

    public static String[] llistaArxius(String dir) {
        String[] pathnames;
        File f = new File(dir);
        pathnames = f.list();
        System.out.println("(Dades) Arxius a " + dir + ": " + pathnames.length); 
        return pathnames;
    }
    
    public static void guardarArxiu(String arxiu, String bufferedData){
        Path pa = Path.of(arxiu);
        try {
            Files.writeString(pa, bufferedData);
        } catch (IOException ex) {
            Logger.getLogger(Dades.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("(Dades) Arxiu " + arxiu + " guardat");
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
        // TODO: Comprovar això funciona
        ret = ret.replace("\r\n", "\n"); //Windows format to Unix format
        ret = ret.replace('\r', '\n'); //Remove Carriage Returns from old MacOS (also System)
        System.out.println("(Dades) Arxiu " + arxiu + " carregat");
        return ret;
    }
    
    public static boolean borrarArxiu(String arxiu) {
        File f = new File(arxiu); 
        return f.delete();
    }
    
    public static boolean ferDirectori(String dir) {
        File f = new File(dir);
        return f.mkdir();
    }
    
    public static boolean existeixDirectori(String dir) {
        File f = new File(dir);
        return f.exists();
    }
    
}
