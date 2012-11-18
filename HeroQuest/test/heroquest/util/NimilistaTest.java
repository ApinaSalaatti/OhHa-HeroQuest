/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.util;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author ApinaSalaatti
 */
public class NimilistaTest {
    
    public NimilistaTest() {
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
    public void HauskanNimenPalautus() {
        assertTrue(Nimilista.getHauskaNimi().length() > 0);
    }
    
    @Test
    public void PelottavanNimenPalautus() {
        assertTrue(Nimilista.getPelottavaNimi().length() > 0);
    }
}
