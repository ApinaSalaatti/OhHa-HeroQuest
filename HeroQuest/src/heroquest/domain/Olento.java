package heroquest.domain;

import heroquest.domain.kauppa.Tavara;
import java.util.Random;
import java.util.List;

import heroquest.util.Nimilista;
/**
 * Abstrakti yläluokka, josta peritään kaikki pelin hahmot kuten pelaajahahmo ja monsterit.
 * 
 * @author Merioksan Mikko
 */
public abstract class Olento {
    /**
     * Kuvunkin kokemustasoon tarvittava expan määrä. Taulukon indeksi on siis kyseinen kokemustaso.
     */
    private static int[] tarvittavaExp = { 0, 300, 1000, 2000, 5000, 10000 };
    /**
     * Hahmon nimi
     */
    private String nimi;
    /**
     * Hahmon voima, ts. kuinka montaa noppaa heitetään taistelussa.
     */
    private int voima;
    /**
     * Energia, eli hahmon elämänvoima, "health", voimapisteet, miten vaan. Energian pudotessa nollaan hahmo kuolee.
     */
    private int energia;
    /**
     * Hahmon nopeus, ts. kuinka montaa noppaa heitetään liikkuessa. Lisäksi nopeuden perusteella valitaan taistelun aloittaja.
     */
    private int nopeus;
    /**
     * Olennon kokemustaso.
     */
    private int taso;
    /**
     * Olennon tienaamat kokemuspisteet.
     */
    private int exp;
    /**
     * Hahmon sijainti kartalla
     */
    
    private Karttapala sijainti;
    /**
     * Hahmon inventaario, johon varastoidaan sen kantamat tavarat ja varusteet
     */
    protected Inventaario inventaario;
    /**
     * Esim. nopanheittojen simuloinnissa käytetty Random-olio. Protected jotta aliluokat pääsevät käsiksi.
     */
    protected Random random;
    
    /**
     * Konstruktori jossa hahmolle anntetaan valmiina nimi
     * 
     * @param nimi hahmon nimi
     * @param voima hahmon voima
     * @param energia hahmon energia
     * @param nopeus hahmon nopeus
     */
    public Olento(String nimi, int voima, int energia, int nopeus) {
        random = new Random();
        inventaario = new Inventaario();
        
        setNimi(nimi);
        setVoima(voima);
        setEnergia(energia);
        setNopeus(nopeus);
        
        taso = 1;
        exp = 0;
    }
    /**
     * Konstruktori jossa hahmolle ei ole annettu valmiina nimeä. Tällöin setNimi-funktio arpoo hahmolle hauskan nimen.
     * 
     * @param voima hahmon voima
     * @param energia hahmon energia
     * @param nopeus hahmon nopeus
     */
    public Olento(int voima, int energia, int nopeus) {
        random = new Random();
        inventaario = new Inventaario();
        
        setNimi("");
        setVoima(voima);
        setEnergia(energia);
        setNopeus(nopeus);
        
        taso = 1;
        exp = 0;
    }
    
    /**
     * Metodi joka asettaa hahmolle nimen.
     * Nimi ei voi olla tyhjä tai pelkkää white spacea, eikä saa sen puoleen sisältää tyhmiä ylimääräisiä spaceja alussa eikä lopussa.
     * Mikäli nimi on epäkelpo, arvotaan hahmolle hauska nimi apuluokan Nimilista avulla.
     * 
     * @see heroquest.util.Nimilista#getHauskaNimi()
     * 
     * @param nimi Käyttäjän antama (tai pelilogiikan muokkaama) syöte
     */
    public void setNimi(String nimi) {
        nimi = nimi.trim();
        if(nimi.length() > 0) {
            this.nimi = nimi;
        }
        else {
            this.nimi = Nimilista.getHauskaNimi();
        }
    }
    /**
     * Palautetaan hahmon nimi.
     * 
     * @return hahmon nimi 
     */
    public String getNimi() {
        return nimi;
    }
    
    /**
     * Asetetaan hahmon voima. Voima ei voi olla alle 1.
     * 
     * @param voima käyttäjältä syötteenä saatu voima.
     */
    public void setVoima(int voima) {
        if(voima <= 0) {
            this.voima = 1;
        }
        else {
            this.voima = voima;
        }
    }
    /**
     * Palautetaan hahmon voima.
     * 
     * @return hahmon voima
     */
    public int getVoima() {
        return voima;
    }
    
    /**
     * Asetetaan hahmon energia
     * 
     * @param energia käyttäjältä syötteenä saatu energia.
     */
    public void setEnergia(int energia) {
        if(energia <= 0) {
            this.energia = 1;
        }
        else {
            this.energia = energia;
        }
    }
    /**
     * Palautetaan hahmon energia.
     * 
     * @return hahmon energia
     */
    public int getEnergia() {
        return energia;
    }
    /**
     * Metodi hahmon energian muuttamiseen, esimerkiksi jos otetaan vahinkoa.
     * 
     * @param muutos pelilogiikan laskema muutos energiaan. Esim. vahinkoa otettaessa muutos on negatiivinen.
     */
    public void muutaEnergia(int muutos) {
        this.energia += muutos;
    }
    
    /**
     * Asetetaan hahmolle nopeus. Nopeus ei voi olla alle 0.
     * 
     * @param nopeus logiikan laskema parametri, esim. pelaajan valitseman luokan perusteella.
     */
    public void setNopeus(int nopeus) {
        if(nopeus < 0) {
            this.nopeus = 0;
        }
        else {
            this.nopeus = nopeus;
        }
    }
    /**
     * Palautetaan hahmon nopeus.
     * 
     * @return hahmon nopeus
     */
    public int getNopeus() {
        return nopeus;
    }
    
    /**
     * Palautetaan hahmon kokemustaso.
     * 
     * @return hahmon kokemustaso
     */
    public int getTaso() {
        return taso;
    }
    /**
     * Nostaa olennon kokemustasoa yhdellä. Tasonnousu on yksinkertaista: voima, energia ja nopeus lisääntyvät yhdellä.
     */
    public void nouseTaso() {
        taso++;
        voima++;
        energia++;
        nopeus++;
    }
    
    /**
     * Palautetaan hahmon keräämät kokemuspisteet.
     * 
     * @return hahmon kokemuspisteet
     */
    public int getExp() {
        return exp;
    }
    /**
     * Lisätään olennolle kokemuspisteitä.
     * 
     * @param xp lisättävät pisteet
     */
    public void lisaaExp(int xp) {
        exp += xp;
        
        // noustaan tasoja tarvittaessa
        boolean valmis = false;
        while(!valmis) {
            if(exp >= tarvittavaExp[taso]) {
                nouseTaso();
            }
            else {
                valmis = true;
            }
        }
    }
    
    /**
     * Metodi joka asettaa pelaajan sijainnin tiettyyn karttapalaan.
     * 
     * @param pala logiikan tai käyttäjän päättämä karttapala, esim. kun pelaaja tai monsteri liikkuu
     * @return onnistuiko siirtyminen
     */
    public boolean setSijainti(Karttapala pala) {
        if(pala != null) {
            sijainti = pala;
            return true;
        }
        return false;
    }
    /**
     * Palautetaan hahmon sijainti.
     * 
     * @return hahmon sijainti
     */
    public Karttapala getSijainti() {
        return sijainti;
    }
    
    /**
     * Palautetaan hahmon inventaario-olio.
     * 
     * @return hahmon inventaario
     */
    public Inventaario getInventaario() {
        return inventaario;
    }
    /**
     * Lisätään tavaraa hahmon inventaarioon.
     * 
     * @param tavarat lista lisättävistä tavaroista
     */
    public void lisaaTavarat(List<Tavara> tavarat) {
        for(Tavara t : tavarat) {
            inventaario.lisaaTavara(t);
        }
    }
    /**
     * Lisätään yksi tavara hahmon inventaarioon
     * 
     * @param t lisättävä tavara
     */
    public void lisaaTavara(Tavara t) {
        inventaario.lisaaTavara(t);
    }
    /**
     * Poistetaan parametrina annettu tavara Olennon inventaariosta
     * @param t poistettava tavara
     * @return true, jos poistaminen onnistui
     */
    public boolean poistaTavara(Tavara t) {
        return inventaario.poistaTavara(t);
    }
    
    /**
     * Palauttaa Olennon tiedot String-muodossa. Tätä ei ole tarkoitus kutsua pelkästään, vaan jonkun perivän luokan kautta.
     * 
     * @return Olennon tiedot String-muodossa
     */
    protected String tallenna() {
        String inv = inventaario.tallenna();
        String hahmo = getNimi() + ";" + getVoima() + ";" + getEnergia() + ";" + getNopeus() + ";" + getTaso() + ";" + getExp();
        return inv + hahmo;
    }
    
    /**
     * Abstrakti metodi johon perivä luokka toteuttaa olennon hyökkäyslogiikan.
     * 
     * @return hahmon tekemä vahinko
     */
    public abstract int hyokkaa();
    /**
     * Abstrakti metodi johon perivä luokka toteuttaa olennon puolustuslogiikan
     * 
     * @return hahmon tekemät torjunnat
     */
    public abstract int puolustaudu();
    /**
     * Abstrakti metodi, jonka toteutuksen tulee muuttaa hahmon energiaa otetun vahingon mukaisesti
     * 
     * @param vahinko pelilogiikan laskema vahinko
     * @return viesti käyttöliittymälle, joka vaihtelee sen mukaan onko vahinkoa ottava Olento pelaajahahmo vai monsteri.
     */
    public abstract String otaVahinkoa(int vahinko);
}
