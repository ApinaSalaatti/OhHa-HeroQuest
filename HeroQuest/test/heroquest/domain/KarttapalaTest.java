package heroquest.domain;

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

import heroquest.domain.Karttapala;
import heroquest.domain.Ilmansuunta;

/**
 *
 * @author merioksa
 */
public class KarttapalaTest {
    Karttapala pala1;
    Karttapala pala2;
    Karttapala pala3;
    
    public KarttapalaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pala1 = new Karttapala();
        pala2 = new Karttapala();
        pala3 = new Karttapala();
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
    public void uudenPalanNaapuritOvatNull() {
        assertEquals(pala1.getNaapuri(Ilmansuunta.POHJOINEN), null);
        assertEquals(pala1.getNaapuri(Ilmansuunta.ITA), null);
        assertEquals(pala1.getNaapuri(Ilmansuunta.ETELA), null);
        assertEquals(pala1.getNaapuri(Ilmansuunta.LANSI), null);
    }
    
    @Test
    public void palanAsetusNaapuriksiOnnistuu() {
        pala1.setNaapuri(pala2, Ilmansuunta.ITA);
        
        assertEquals(pala1.getNaapuri(Ilmansuunta.ITA), pala2);
    }
    
    @Test
    public void siirtyminenPalojenValillaOnnistuu() {
        pala1.setNaapuri(pala2, Ilmansuunta.ITA);
        pala2.setNaapuri(pala3, Ilmansuunta.ITA);
        
        Karttapala pala4 = pala1.getNaapuri(Ilmansuunta.ITA);
        pala4 = pala4.getNaapuri(Ilmansuunta.ITA);
        
        assertEquals(pala4, pala3);
    }
}
