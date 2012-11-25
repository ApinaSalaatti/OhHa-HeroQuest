/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import heroquest.domain.kauppa.Tavara;
/**
 *
 * @author merioksa
 */
public class InventaarioTest {
    private Inventaario inv;
    
    public InventaarioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.inv = new Inventaario();
        this.inv.lisaaTavara(new Tavara("arvokasaarre.hqt"));
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void lisaysLisaaTavaran() {
        inv.lisaaTavara(new Tavara("arvokasaarre.hqt"));
        assertTrue(inv.getTavarat().size() > 1);
    }
    
    @Test
    public void poistaminenVahentaaTavaroita() {
        inv.poistaTavara(new Tavara("arvokasaarre.hqt"));
        assertTrue(inv.getTavarat().isEmpty());
    }
}
