/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest;

import org.junit.*;
import static org.junit.Assert.*;

import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
import heroquest.domain.Pelaaja;
import heroquest.domain.Monsteri;
import heroquest.domain.Ilmansuunta;
/**
 *
 * @author ApinaSalaatti
 */
public class PeliTest {
    private static int[][] testikartta = {  {0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 1, 1, 1, 1, 1, 1, 0},
                                            {0, 1, 0, 0, 1, 1, 1, 0},
                                            {0, 1, 1, 1, 1, 1, 1, 0},
                                            {0, 1, 0, 1, 0, 0, 0, 0},
                                            {0, 1, 0, 1, 1, 1, 1, 0},
                                            {0, 1, 1, 1, 0, 0, 1, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0}};
    private Peli peli;
    
    public PeliTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        Pelaaja pelaaja = new Pelaaja("Aarne", 100, 100, 100, "Soturitappaja");
        Kartta kartta = new Kartta(testikartta);
        this.peli = new Peli(pelaaja);
        this.peli.setKartta(kartta);
        
        pelaaja.setSijainti(peli.getKartta().getAloituspala());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void pelaajanLiikkuminen() {
        Ilmansuunta suunta = Ilmansuunta.ITA;
        Karttapala kohde = peli.getPelaaja().getSijainti().getNaapuri(suunta);
        
        peli.pelaajanLiike(suunta);
        
        assertEquals(peli.getPelaaja().getSijainti(), kohde);
    }
    
    @Test
    public void taistelunAloitus() {
        peli.lisaaMonsteri(new Monsteri(2, 2, 2), 2, 1);
        
        peli.pelaajanLiike(Ilmansuunta.ETELA);
        
        assertTrue(peli.taistelunAika());
    }
    
    @Test
    public void vuoronLopetusLiikuttaaMonstereita() {
        Karttapala pala = peli.getKartta().getKarttapalat()[2][5];
        Monsteri m = new Monsteri(2, 2, 2);
        peli.lisaaMonsteri(m, 2, 5);
        peli.lopetaVuoro();
        
        assertNotSame(m.getSijainti(), pala);
    }
}
