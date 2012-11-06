/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
/**
 *
 * @author merioksa
 */
public class KarttaTest {
    private Kartta kartta;
    
    public KarttaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kartta = new Kartta(6);
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
    public void aloitusPalanKoordinaatitOikein() {
        Karttapala aloituspala = kartta.getAloituspala();
        assertEquals(aloituspala.getX(), 0);
        assertEquals(aloituspala.getY(), 0);
    }
}
