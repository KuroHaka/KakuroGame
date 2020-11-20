import domini.tauler.TaulerComencat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;



public class JUnitTest_Tauler {
    
    public JUnitTest_Tauler() {
        System.out.println("hola");
    }
    
    @Before
    public void abans() {
        
    }
    
    @Test
    public void prova() {
        //System.out.println("Això és un test:");
        assertEquals("efectivament", 1, 1);
        
        String enunciat =   "4,4\n" +
                            "*,*,C6,C7\n" +
                            "*,C8F5,?,?\n" +
                            "F10,?,?,?\n" +
                            "F6,?,?,?";
        TaulerComencat t = new TaulerComencat(enunciat);
        assertEquals("Testejant si dim x = 4...", t.getDimX(), 4);
        assertEquals("Testejant si dim y = 4...", t.getDimY(), 4);
        //assertEquals("Testejant format: ", t.format_Estandard(), enunciat);

        assertEquals("pos = (0,0) es negra. Testejant que pos es Negra...", t.esNegra(0,0), true);
        assertEquals("pos = (1,1) es negra. Testejant que pos no es Blanca...", t.esBlanca(1,1), false);
        assertEquals("pos = (2,2) es blanc. Testejant que pos no es Negra...", t.esNegra(2,2), false);
        assertEquals("pos = (3,3) es blanc. Testejant que pos es Blanca...", t.esBlanca(3,3), true);

        assertEquals("setValue a (0,0). Testejant que no hi pot posar un valor...", t.setValor(0,0,4), false);
        assertEquals("setValue a (2,2). Testejant que hi pot posar un valor...", t.setValor(2,2,4), true);
        
        //assertEquals("Test correcte", func(), tauler);
    }
    
    @After
    public void acabat() {
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
