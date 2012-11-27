package heroquest.domain;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import static org.junit.Assert.*;

import heroquest.domain.Pelaaja;
import heroquest.domain.Kartta;
import heroquest.domain.Ilmansuunta;
/**
 *
 * @author ApinaSalaatti
 */
public class PelaajaTest {
    private static int[][] testiKartta = {  {0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 1, 1, 1, 1, 1, 1, 0},
                                            {0, 1, 0, 0, 0, 0, 0, 0},
                                            {0, 1, 1, 1, 0, 0, 0, 0},
                                            {0, 1, 0, 1, 0, 0, 0, 0},
                                            {0, 1, 0, 1, 1, 1, 1, 0},
                                            {0, 1, 1, 1, 0, 0, 1, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0}};
    private Pelaaja pelaaja;
    private Kartta kartta;
    
    public PelaajaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        pelaaja = new Pelaaja(5, 5, 5, "Maagikko");
        kartta = new Kartta(testiKartta, "testikartta.hqm");
        pelaaja.setSijainti(kartta.getAloituspala());
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
    public void pelaajanLiikkuminenEtelaanToimii() {
        pelaaja.liiku(Ilmansuunta.ETELA);
        assertEquals(pelaaja.getSijainti().getY(), 2);
    }
    
    @Test
    public void seinaaPainLiikkuminenEiLiikutaPelaajaa() {
        pelaaja.liiku(Ilmansuunta.POHJOINEN);
        assertEquals(pelaaja.getSijainti().getY(), 1);
    }
    
    @Test
    public void vahingonOttaminenToimii() {
        pelaaja.otaVahinkoa(2);
        assertEquals(pelaaja.getEnergia(), 3);
    }
    
     @Test
     public void nimenTrimmausOnnistuu() {
         pelaaja = new Pelaaja("     Masa       ", 5, 5, 5, "Superhahmo");
         assertEquals(pelaaja.getNimi(), "Masa");
     }
     
     @Test
     public void tyhjaStringEiKelpaa() {
         pelaaja = new Pelaaja("", 5, 5, 5, "Superhahmo");
         assertTrue(pelaaja.getNimi().length() > 0);
     }
     
     @Test
     public void negatiivinenVoimaEiKelpaa() {
         pelaaja = new Pelaaja("Jouko", -5, -5, -5, "Suurenmoinen sankarimies");
         
         assertTrue(pelaaja.getVoima() > 0);
     }
     
}
