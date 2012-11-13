/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author ApinaSalaatti
 */
public class PeliTehdasTest {
    private PeliTehdas pt;
    private Peli peli;
    
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
        peli = pt.luoPeli("Aarne", "Taikamaagi", "kartta.hqm");
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
    public void tehdasRakentaaPelin() {
        assertNotNull(peli);
    }
    
    @Test
    public void tehdasLuoPelaajanOikein() {
        assertEquals(peli.getPelaaja().getNimi(), "Aarne");
    }
    
    @Test
    public void tehdasLuoKartan() {
        assertNotNull(peli.getKartta());
    }
}
