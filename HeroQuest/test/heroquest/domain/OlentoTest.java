package heroquest.domain;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import static org.junit.Assert.*;

import heroquest.domain.Olento;
import heroquest.domain.Karttapala;
import heroquest.domain.Ilmansuunta;
/**
 *
 * @author ApinaSalaatti
 */
public class OlentoTest {
    private Olento olento;
    private Karttapala pala1;
    private Karttapala pala2;
    
    public OlentoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        olento = new Olento(5, 5);
        pala1 = new Karttapala();
        pala1.setSijainti(0, 0);
        pala2 = new Karttapala();
        pala2.setSijainti(0, -1);
        
        pala1.setNaapuri(pala2, Ilmansuunta.POHJOINEN);
        pala2.setNaapuri(pala1, Ilmansuunta.ETELA);
        
        olento.setSijainti(pala1);
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
    public void vahingonOttaminenToimii() {
        olento.otaVahinkoa(2);
        assertEquals(olento.getEnergia(), 3);
    }
}
