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
public class AnsaTest {
    private Ansa ansa;
    private Pelaaja pelaaja;
    
    public AnsaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        this.ansa = new Ansa("Miina", 100, "Haha astuit miinaan!");
        this.pelaaja = new Pelaaja(50, 101, 100, "Killeri");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void paljastuminen() {
        ansa.paljastu();
        assertTrue(ansa.havaittu());
    }
    
    @Test
    public void vahinkoOikein() {
        ansa.laukea(pelaaja);
        assertEquals(pelaaja.getEnergia(), 1);
    }
    
    @Test
    public void negatiivinenVahinkoEiKelpaa() {
        this.ansa = new Ansa("Huono", 0, "Ei tää toimi!");
        ansa.laukea(pelaaja);
        assertEquals(pelaaja.getEnergia(), 100);
    }
    
    @Test
    public void viestiOikein() {
        String viesti = ansa.laukea(pelaaja);
        assertEquals(viesti, "Haha astuit miinaan!");
    }
}
