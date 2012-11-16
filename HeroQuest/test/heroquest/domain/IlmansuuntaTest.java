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
public class IlmansuuntaTest {
    
    public IlmansuuntaTest() {
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void vastakohtaOikein() {
        Ilmansuunta suunta = Ilmansuunta.ITA;
        assertEquals(suunta.vastakohta(), Ilmansuunta.LANSI);
    }
    
    @Test
    public void koordinaattienMuutoksetOikein() {
        Ilmansuunta suunta = Ilmansuunta.ITA;
        assertEquals(suunta.xMuutos(), 1);
        assertEquals(suunta.yMuutos(), 0);
    }
}
