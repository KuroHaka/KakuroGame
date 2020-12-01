/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interficie;

import java.awt.Color;

/**
 *
 * @author zsc27
 */
public class ColorsPalet {
    private int fons_Clar = 0xfcdada;
    private int text_Clar = 0xfcdada;
    private int enfasi_Clar = 0xffa5a5;
    private int fons_Fosc = 0x3d7ea6;
    private int text_Fosc = 0x3d7ea6;
    private int enfasi_Fosc = 0x5c969e;

    public ColorsPalet() {
    }
    
    public Color getFons_Clar() {
        return new Color(fons_Clar);
    }

    public Color getText_Clar() {
        return new Color(text_Clar);
    }

    public Color getEnfasi_Clar() {
        return new Color(enfasi_Clar);
    }

    public Color getFons_Fosc() {
        return new Color(fons_Fosc);
    }

    public Color getText_Fosc() {
        return new Color(text_Fosc);
    }

    public Color getEnfasi_Fosc() {
        return new Color(enfasi_Fosc);
    }
    
    
    
}
