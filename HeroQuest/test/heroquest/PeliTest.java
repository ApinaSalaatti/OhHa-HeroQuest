/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;

import heroquest.domain.Ansa;
import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
import heroquest.domain.Pelaaja;
import heroquest.domain.Monsteri;
import heroquest.domain.kauppa.Tavara;
import heroquest.domain.Ilmansuunta;
/**
 *
 * @author ApinaSalaatti
 */
public class PeliTest {
    private static int[][] testikartta = {  {0, 1, 0, 0, 0, 0, 0, 0},
                                            {0, 1, 1, 1, 1, 1, 1, 0},
                                            {0, 1, 0, 0, 1, 1, 1, 0},
                                            {0, 1, 1, 1, 1, 1, 1, 0},
                                            {0, 1, 0, 1, 0, 0, 0, 0},
                                            {0, 1, 0, 1, 1, 1, 1, 0},
                                            {0, 1, 1, 1, 0, 0, 1, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0}};
    private Peli peli;
    
    public PeliTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        Pelaaja pelaaja = new Pelaaja("Aarne", 100, 100, 100, "Soturitappaja");
        Kartta kartta = new Kartta(testikartta, "testikartta.hqm");
        this.peli = new Peli(pelaaja);
        this.peli.setKartta(kartta);
        
        peli.pelaajaPoistuuKotoa(peli.getKartta().getAloituspala());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void liikenopanHeittoTuottaaOikeanLuvun() {
        int min = peli.getPelaaja().getNopeus();
        int max = peli.getPelaaja().getNopeus() * 6;
        
        int heitto = peli.liikenopanHeitto();
        
        assertTrue(heitto >= min && heitto <= max);
    }
    
    @Test
    public void pelaajanLiikkuminen() {
        Ilmansuunta suunta = Ilmansuunta.ITA;
        Karttapala kohde = peli.getPelaaja().getSijainti().getNaapuri(suunta);
        
        peli.liikenopanHeitto();
        peli.pelaajanLiike(suunta);
        
        assertEquals(peli.getPelaaja().getSijainti(), kohde);
    }
    
    @Test
    public void ilmanLiikkeitaEiVoiLiikkua() {
        Ilmansuunta suunta = Ilmansuunta.ITA;
        Karttapala pala = peli.getPelaaja().getSijainti();
        
        peli.pelaajanLiike(suunta);
        
        assertEquals(peli.getPelaaja().getSijainti(), pala);
    }
    
    @Test
    public void taistelunAloitus() {
        peli.lisaaMonsteri(new Monsteri(2, 2, 2, 2), 2, 1);
        
        peli.liikenopanHeitto();
        peli.pelaajanLiike(Ilmansuunta.ETELA);
        
        assertTrue(peli.taistelunAika());
    }
    
    @Test
    public void kuolleidenPoistaminen() {
        Monsteri monsu = new Monsteri(2, 2, 2, 2);
        peli.lisaaMonsteri(monsu, 2, 1);
        monsu.otaVahinkoa(5);
        peli.poistaKuolleetMonsterit();
        List<Monsteri> monsterit = peli.getKartta().getMonsterit();
        assertTrue(monsterit.isEmpty());
    }
    
    @Test
    public void vuoronLopetusLiikuttaaMonstereita() {
        Karttapala pala = peli.getKartta().getKarttapalat()[2][5];
        Monsteri m = new Monsteri(2, 2, 2, 2);
        peli.lisaaMonsteri(m, 2, 5);
        peli.lopetaVuoro();
        
        assertNotSame(m.getSijainti(), pala);
    }
    
    @Test
    public void kartaltaUlosLiikkuminenViePelaajanKotiin() {
        peli.liikenopanHeitto();
        peli.pelaajanLiike(Ilmansuunta.POHJOINEN);
        peli.pelaajanLiike(Ilmansuunta.POHJOINEN);
        assertTrue(peli.pelaajaKotona());
    }
    
    @Test
    public void tavaroidenPoiminta() {
        peli.getKartta().getAloituspala().addTavara(new Tavara("arvokasaarre.hqt"));
        peli.tavaroidenPoiminta();
        assertTrue(peli.getPelaaja().getInventaario().getTavarat().size() > 0);
    }
    
    @Test
    public void tavaranKayttaminen() {
        Tavara puteli = new Tavara("salaperainenputeli.hqt");
        peli.getPelaaja().lisaaTavara(puteli);
        peli.kaytaTavaraa(puteli);
        
        assertEquals(peli.getPelaaja().getNimi(), "Kakkapylly");
    }
    
    @Test
    public void tavaranKayttaminenPoistaaKertakayttoisen() {
        Tavara puteli = new Tavara("salaperainenputeli.hqt");
        peli.getPelaaja().lisaaTavara(puteli);
        peli.kaytaTavaraa(puteli);
        
        assertTrue(peli.getPelaaja().getInventaario().getTavarat().isEmpty());
    }
    
    @Test
    public void tavaranKayttaminenEiPoistaMonikayttoista() {
        Tavara puteli = new Tavara("arvokasaarre.hqt");
        peli.getPelaaja().lisaaTavara(puteli);
        peli.kaytaTavaraa(puteli);
        
        assertFalse(peli.getPelaaja().getInventaario().getTavarat().isEmpty());
    }
    
    @Test
    public void aarteenLoytaminenLisaaExpaa() {
        peli.getKartta().getAloituspala().addTavara(new Tavara("arvokasaarre.hqt"));
        peli.tavaroidenPoiminta();
        int xp = peli.getPelaaja().getExp();
        assertTrue(xp == 50);
    }
    
    @Test
    public void ansaanAstuminenVahentaaEnergiaa() {
        peli.getKartta().getKarttapalat()[2][1].viritaAnsa(new Ansa("Miina", 10, "AUTS!"));
        peli.liikenopanHeitto();
        peli.pelaajanLiike(Ilmansuunta.ETELA);
        assertTrue(peli.getPelaaja().getEnergia() < 100);
    }
    
    @Test
    public void taisteluTappaaHirviot() {
        peli.liikenopanHeitto();
        
        peli.lisaaMonsteri(new Monsteri(2, 2, 2, 2), 2, 1);
        peli.lisaaMonsteri(new Monsteri(2, 2, 2, 2), 3, 1);
        
        peli.pelaajanLiike(Ilmansuunta.ETELA);
        peli.taistele();
        
        peli.pelaajanLiike(Ilmansuunta.ETELA);
        peli.taistele();
        
        assertTrue(peli.getKartta().getMonsterit().isEmpty());
    }
    
    @Test
    public void hirvionTappaminenLisaaExpaa() {
        peli.lisaaMonsteri(new Monsteri(2, 2, 2, 2), 1, 1);
        
        peli.taistele();
        
        assertTrue(peli.getPelaaja().getExp() > 0);
    }
}
