/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain.kauppa;

import org.junit.*;
import static org.junit.Assert.*;

import heroquest.domain.Pelaaja;

/**
 *
 * @author ApinaSalaatti
 */
public class KauppaTest {
    private Pelaaja pelaaja;
    private Kauppa kauppa;
    private Tavara tavara;
    
    public KauppaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        pelaaja = new Pelaaja(5, 5, 5, "Rosmo");
        kauppa = new Kauppa();
        tavara = new Tavara("salaperainenputeli.hqt");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void ostaminen() {
        kauppa.lisaaTavara(tavara);
        assertTrue(kauppa.myy(kauppa.getTavarat().get(0), pelaaja));
    }
    
    @Test
    public void liianKalliinOstaminen() {
        kauppa.lisaaTavara(tavara);
        pelaaja.setVarat(0);
        assertFalse(kauppa.myy(kauppa.getTavarat().get(0), pelaaja));
    }
    
    @Test
    public void myyminen() {
        pelaaja.lisaaTavara(tavara);
        assertTrue(kauppa.osta(tavara, pelaaja));
    }

}
