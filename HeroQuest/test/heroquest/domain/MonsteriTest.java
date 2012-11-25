/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author ApinaSalaatti
 */
public class MonsteriTest {
    private static int[][] testikartta = {  {0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 1, 1, 1, 1, 1, 1, 0},
                                            {0, 1, 0, 0, 0, 0, 0, 0},
                                            {0, 1, 1, 1, 0, 0, 0, 0},
                                            {0, 1, 0, 1, 0, 0, 0, 0},
                                            {0, 1, 0, 1, 1, 1, 1, 0},
                                            {0, 1, 1, 1, 0, 0, 1, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0}};
    private Monsteri monsteri1;
    private Monsteri monsteri2;
    private Kartta kartta;
    private Karttapala[][] palat;
    
    public MonsteriTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        monsteri1 = new Monsteri(5, 1, 5, 100);
        monsteri2 = new Monsteri(5, 1, 5, 100);
        kartta = new Kartta(testikartta);
        palat = kartta.getKarttapalat();
        
        monsteri1.setSijainti(palat[1][1]);
        palat[1][1].monsteriSaapuu(monsteri1);
        monsteri2.setSijainti(palat[2][1]);
        palat[2][1].monsteriSaapuu(monsteri2);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void monsteriSaaNimen() {
        assertTrue(monsteri1.getNimi().length() > 0);
    }
    
    @Test
    public void liikkuminenTyhjaanRuutuunToimii() {
        monsteri1.liiku(Ilmansuunta.ITA);
        assertEquals(monsteri1.getSijainti(), palat[1][2]);
    }
    
    @Test
    public void seinaanLiikkuminenEiLiikutaMonsteria() {
        Karttapala vanha = monsteri1.getSijainti();
        monsteri1.liiku(Ilmansuunta.LANSI);
        
        assertEquals(vanha, monsteri1.getSijainti());
    }
    
    @Test
    public void monsteriEiVoiLiikkuaRuutuunJossaOnJoMonsteri() {
        Karttapala vanha = monsteri1.getSijainti();
        monsteri1.liiku(Ilmansuunta.ETELA);
        
        assertEquals(vanha, monsteri1.getSijainti());
    }
    
    @Test
    public void vahingonOttaminenToimii() {
        monsteri1.otaVahinkoa(1);
        
        assertEquals(monsteri1.getEnergia(), 0);
    }
}
