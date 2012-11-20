/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.util;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Scanner;
/**
 *
 * @author Merioksan Mikko
 */
public class TiedostoapuriTest {
    
    public TiedostoapuriTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void tiedostonAvaus() {
        Scanner lukija = Tiedostoapuri.tiedostoLukijaan("test/moi.txt");
        
        assertEquals(lukija.next(), "moi");
    }
}
