package domini.hashing;

import domini.tauler.Tauler;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hash {
    
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    public static String calculaHash(String t){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Tauler.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] input = t.getBytes();
        byte[] result = md.digest(input);
        return bytesToHex(result);
    }
    
}
