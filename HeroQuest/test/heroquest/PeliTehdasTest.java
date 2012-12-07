/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest;

import org.junit.*;
import static org.junit.Assert.*;

import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;

/**
 *
 * @author ApinaSalaatti
 */
public class PeliTehdasTest {
    private PeliTehdas pt;
    private Peli peli;
    private Kartta k;
    
    public PeliTehdasTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        this.pt = new PeliTehdas();
        peli = null;
        peli = pt.luoPeli("Aarne", "Taikamaagi", "harri.png");
        
        k = null;
        try {
            k = pt.luoLuolasto("testikartta.hqm");
        }
        catch(Exception e){
            System.out.println("Karttatiedosto ei aukea");
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void tehdasRakentaaPelin() {
        assertNotNull(peli);
    }
    
    @Test
    public void tehdasLuoPelaajan() {
        assertEquals(peli.getPelaaja().getNimi(), "Aarne");
    }
    
    @Test
    public void tehdasAsettaaLuokanAttribuutitOikein() {
        assertEquals(peli.getPelaaja().getVoima(), 2);
        assertEquals(peli.getPelaaja().getEnergia(), 2);
    }
    
    @Test
    public void tehdasLuoKartan() {
        assertNotNull(k);
    }
    
    @Test
    public void monsterienAsetus() {
        Karttapala pala = k.getKarttapalat()[2][2];
        assertTrue(pala.monsteriPaikalla());
    }
    
    @Test
    public void aarteenAsetus() {
        Karttapala pala = k.getKarttapalat()[3][3];
        assertTrue(pala.aarrePaikalla());
    }
}
