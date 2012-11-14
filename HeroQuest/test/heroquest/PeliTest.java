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
                                            {0, 1, 0, 0, 0, 0, 0, 0},
                                            {0, 1, 1, 1, 0, 0, 0, 0},
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
        Pelaaja pelaaja = new Pelaaja("Aarne", 50, 50, "Soturitappaja");
        Kartta kartta = new Kartta(testikartta);
        pelaaja.setSijainti(kartta.getAloituspala());
        
        this.peli = new Peli(kartta, pelaaja);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void pelaajanLiikkuminen() {
        Ilmansuunta suunta = Ilmansuunta.ITA;
        Karttapala kohde = peli.getPelaaja().getSijainti().getNaapuri(suunta);
        
        peli.pelaajanLiike(suunta);
        
        assertEquals(peli.getPelaaja().getSijainti(), kohde);
    }
    
    @Test
    public void taistelunAloitus() {
        Karttapala pala = peli.getKartta().getKarttapalat()[2][1];
        peli.lisaaMonsteri(new Monsteri(2, 2), pala);
        
        peli.pelaajanLiike(Ilmansuunta.ETELA);
        
        assertTrue(peli.taistelunAika());
    }
}
