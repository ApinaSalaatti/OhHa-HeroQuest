/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest;

import heroquest.domain.*;
import java.util.List;
import java.util.Random;

import heroquest.domain.kauppa.Tavara;
import heroquest.util.Tiedostoapuri;
/**
 * Pelilogiikan sisältävä luokka. MVC-mallin M.
 * 
 * @author Merioksan Mikko
 */
public class Peli {
    /**
     * Satunnaisuutta.
     */
    private Random random;
    /**
     * Käytössä oleva kartta.
     */
    private Kartta kartta;
    /**
     * Pelaajan hahmo.
     */
    private Pelaaja pelaaja;
    /**
     * Apumuuttuja, joka määrittelee onko pelaaja taistelussa.
     */
    private boolean taistelunAika;
    /**
     * Apumuuttuja, joka kertoo onko pelaaja kotona vai luolastossa.
     */
    private boolean pelaajaKotona;
    /**
     * Pelaajan kotia simuloiva Karttapala-luokan aliluokka.
     */
    private Kotikarttapala kotipala;
    
    public Peli(Pelaaja p) {
        random = new Random();
        pelaaja = p;
        taistelunAika = false;
        kotipala = new Kotikarttapala();
        pelaajaSaapuuKotiin();
    }
    
    /**
     * Lisätään kartalle yksi Monsteri annettuun Karttapalaan.
     * 
     * @param m lisättävä Monsteri
     * @param y kartan y-koordinaatti, jolle Monsteri lisätään
     * @param x kartan x-koordinaatti, jolle Monsteri lisätään
     */
    public void lisaaMonsteri(Monsteri m, int y, int x) {
        kartta.lisaaMonsteri(m, y, x);
    }
    
    /**
     * Poistaa annetun Monsterin pelistä, esim kun Pelaaja tappaa sen.
     * 
     * @param m poistettava Monsteri
     */
    public void poistaMonsteri(Monsteri m) {
        kartta.poistaMonsteri(m);
    }
    /**
     * Metodi, joka poistaa pelistä kaikki kuolleet Monsterit.
     */
    public void poistaKuolleetMonsterit() {
        List<Monsteri> kuolleet = kartta.poistaKuolleetMonsterit();
        int tapot = 0;
        int exp = 0;
        for(Monsteri m : kuolleet) {
            tapot++;
            exp += m.getExpArvo();
        }
        
        pelaaja.lisaaTapot(tapot);
        pelaaja.lisaaExp(exp);
    }
    
    /**
     * Palautetaan kullakin hetkellä seikkailun kohteena oleva luolasto.
     * 
     * @return käytössä oleva Kartta
     */
    public Kartta getKartta() {
        return kartta;
    }
    /**
     * Asetetaan luolasto, johon ollaan syöksymässä
     * 
     * @param k luolasto, johon ollaan syöksymässä
     */
    public void setKartta(Kartta k) {
        kartta = k;
    }
    
    /**
     * Palautetaan pelaajaa tällä hetkellä kuvaava pelaaja-olio.
     * 
     * @return pelaajan hahmo
     */
    public Pelaaja getPelaaja() {
        return pelaaja;
    }
    
    /**
     * Liikuttaa pelaajaa haluttuun suuntaan kutsumalla Pelaaja-luokan liiku-metodia.
     * 
     * @param suunta haluttu suunta
     */
    public String pelaajanLiike(Ilmansuunta suunta) {
        String viesti = "";
        if(pelaaja.getLiikkeet() > 0) {
            if(kartta.ulosKartalta(pelaaja.getSijainti(), suunta)) {
                pelaajaSaapuuKotiin();
            }
            else {
                pelaaja.liiku(suunta);
            }

            if(pelaaja.getSijainti().ansaPaikalla()) {
                viesti = pelaaja.getSijainti().laukaiseAnsa(pelaaja);
            }
            tuleekoTaistelu();
        }
        else {
            viesti = "Liikkeesi näyttävät olevan lopussa. Heitä noppaa!";
        }
        
        return viesti;
    }
    
    /**
     * Metodi, joka simuloi nopanheittoa. Heitetään pelaajan nopeuden mukaista määrää noppia.
     * Metodia kutsutaan, kun pelaaja on käyttänyt kaikki liikkeensä.
     */
    public int liikenopanHeitto() {
        int yhteensa = 0;
        for(int i = 0; i < pelaaja.getNopeus(); i++) {
            yhteensa += random.nextInt(6) + 1;
        }
        pelaaja.setLiikkeet(yhteensa);
        return yhteensa;
    }
    
    public Karttapala getPelaajanSijainti() {
        return pelaaja.getSijainti();
    }
    
    /**
     * Poimitaan kaikki pelaajan sijaintiruudussa olevat tavarat.
     * 
     * @return tieto tavaroiden poiston onnistumisesta
     */
    public boolean tavaroidenPoiminta() {
        List<Tavara> tavarat = pelaaja.getSijainti().poimiTavarat();
        if(tavarat.size() <= 0) {
            return false;
        }
        
        for(Tavara t : tavarat) {
            if(t.equals(new Tavara("arvokasaarre.hqt"))) {
                pelaaja.lisaaExp(50);
            }
            pelaaja.lisaaTavara(t);
        }
        return true;
    }
    
    /**
     * Metodi pelaajan inventaariossa olevien tavaroiden käyttämiseen.
     * Jokainen tavara lähettää käytettäessä viestin, joka eteenpäin lähetetään käyttöliittymälle.
     * 
     * @param t tavara, jota pelaaja haluaa käyttää
     */
    public String kaytaTavaraa(Tavara t) {
        String viesti = "";
        if(t != null) {
            viesti = t.kayta(this) + "\n";
            if(t.kkayttoinen()) {
                pelaaja.getInventaario().poistaTavara(t);
            }
        }
        else {
            viesti = "Valitse käytettävä tavara!\n";
        }
        
        return viesti;
    }
    
    /**
     * Metodi, joka liikuttaa kaikkia pelissä olevia Monstereita.
     * Tämä tehdään yleensä kun pelaaja on käyttäny kaikki liikkeensä.
     */
    private void monsterienLiike() {
        kartta.monsterienLiike(taistelunAika, pelaaja);
    }
    
    /**
     * Palauttaa pelaajan kanssa samassa ruudussa olevan Monsterin. Jos sellaista ei ole, palautetaan null.
     * 
     * @return monsteri joka on samassa ruudussa pelaajan kanssa.
     */
    public Monsteri getVastustaja() {
        Karttapala pelaajaSijainti = pelaaja.getSijainti();
        return pelaajaSijainti.getMonsteri();
    }
    
    /**
     * Metodi, joka pelaajan liikkeiden loppuessa "lopettaa vuoron", eli liikuttaa Monstereita.
     */
    public void lopetaVuoro() {
        monsterienLiike();
        tuleekoTaistelu();
    }
    
    /**
     * Metodi, jossa tarkastetaan joutuuko pelaaja taisteluun (ts. onko samassa ruudussa monsteri).
     */
    public void tuleekoTaistelu() {
        Karttapala pelaajanSijainti = pelaaja.getSijainti();
        taistelunAika = false;
        if(pelaajanSijainti.monsteriPaikalla()) {
            taistelunAika = true;
        }
    }
    
    /**
     * Palautetaan tieto siitä, ollaanko taistelussa.
     * 
     * @return tieto siitä, ollaanko taistelussa
     */
    public boolean taistelunAika() {
        return taistelunAika;
    }
    
    /**
     * Pelaajan kotona olevaksi asettava metodi
     */
    public void pelaajaSaapuuKotiin() {
        Tiedostoapuri.tallennaData(this);
        if(pelaaja.getSijainti() != null) {
            pelaaja.getSijainti().pelaajaPoistuu();
        }
        pelaaja.setSijainti(kotipala);
        kotipala.pelaajaSaapuu();
        pelaajaKotona = true;
    }
    /**
     * Pelaajan luolastoa komppaavaksi asettava metodi
     * 
     * @param pala kohteena olevan luolaston aloituspala
     */
    public void pelaajaPoistuuKotoa(Karttapala pala) {
        kotipala.pelaajaPoistuu();
        pelaaja.setSijainti(pala);
        pelaaja.setLiikkeet(0);
        pala.pelaajaSaapuu();
        pelaajaKotona = false;
    }
    /**
     * Palautetaan tieto siitä, onko pelaaja kotona vai luolastossa.
     * 
     * @return tieto siitä, onko pelaaja kotona vai luolastossa
     */
    public boolean pelaajaKotona() {
        return pelaajaKotona;
    }
    
    /**
     * Taistelumetodi. Ensin katsotaan kumpi on nopeampi, pelaaja vai Monsteri. Tämän jälkeen nopein lyö ensin ja hidas ottaa vahinkoa.
     * Mikäli hitaampi Olento jäi henkiin, on sen vuoro iskeä.
     * 
     * @return tiedot taistelun kulusta
     */
    public String taistele() {
        Olento aloittaja = null;
        Olento seuraaja = null;
        
        // jos vastustajaa ei jostain syystä ollutkaan, ei voida taistellakaan. :(
        if(getVastustaja() == null) {
            return "Ei ole ketään ketä vastaan taistella (mitäs peliä tämä on??)!\n";
        }
        else {
            
            // tarkastetaan kumpi on nopeampi ja asetetaan aloittaja ja seuraaja sen mukaisesti.
            if(pelaaja.getNopeus() >= getVastustaja().getNopeus()) {
                aloittaja = pelaaja;
                seuraaja = getVastustaja();
            }
            else {
                aloittaja = getVastustaja();
                seuraaja = pelaaja;
            }
            
            StringBuilder taisteluviesti = new StringBuilder();
            taisteluviesti.append("On taistelun aika!\n");
            taisteluviesti.append("Vastassasi on pelottava monsteri " + getVastustaja().toString() + "\n");
            
            // aloittajan hyökkäys
            int vahinko = hyokkays(aloittaja, seuraaja);
            if(vahinko > 0) {
                taisteluviesti.append(seuraaja.otaVahinkoa(vahinko));
            }
            else {
                taisteluviesti.append(aloittaja.getNimi() + " löi ohi!\n");
            }
            
            if(seuraaja.getEnergia() <= 0) {
                // tarkistetaan josko kuollut Olento oli monsteri
                poistaKuolleetMonsterit();
            }
            else {
                // seuraajan hyökkäys
                vahinko = hyokkays(seuraaja, aloittaja);
                if(vahinko > 0) {
                    taisteluviesti.append(aloittaja.otaVahinkoa(vahinko));
                }
                else {
                    taisteluviesti.append(seuraaja.getNimi() + " löi ohi!\n");
                }
                if(aloittaja.getEnergia() <= 0) {
                    // tarkistetaan josko kuollut Olento oli monsteri
                    poistaKuolleetMonsterit();
                }
            }
            
            
            return taisteluviesti.toString();
        }
    }
    
    /**
     * Metodi, joka laskee yksittäisessä hyökkäyksessä tehdyn vahingon.
     * <= 0 tarkoittaa ohilyöntiä.
     * 
     * @param hyokkaaja hyökkäävä Olento
     * @param puolustaja puolustava Olento
     * @return hyökkäyksen ja puolustuksen erotus
     */
    public int hyokkays(Olento hyokkaaja, Olento puolustaja) {
        int hyokkays = hyokkaaja.hyokkaa();
        int puolustus = puolustaja.puolustaudu();
        
        return hyokkays - puolustus;
    }
}
