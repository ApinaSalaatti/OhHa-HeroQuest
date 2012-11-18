/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest;

import java.util.Random;
import heroquest.domain.Olento;
import heroquest.domain.Monsteri;
import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
import heroquest.domain.Ilmansuunta;
import heroquest.kayttoliittyma.Kayttoliittyma;
/**
 * Luokka joka toimii välittäjänä pelilogiikan ja käyttöliittymän välillä.
 * Idea on, että käyttöliittymä voi olla millainen tahansa pelilogiikan muuttumatta. MVC-mallin C.
 * 
 * @author Merioksan Mikko
 */
public class PeliController {
    /**
     * Satunnaisuutta tarvittaessa hyödynnettävä Random-olio.
     */
    private Random random;
    /**
     * Peli-luokan olio, joka sisältää kaiken pelilogiikan. MVC-mallissa se model.
     */
    private Peli peli;
    /**
     * Käyttöliittymä-olio, voi olla toteutettu monin eri tavoin.
     */
    private Kayttoliittyma kali;
    
    public PeliController(Kayttoliittyma k) {
        this.random = new Random();
        this.kali = k;
    }
    
    /**
     * Metodi, jota käyttöliittymä kutsuu kun ollaan valmiina aloittamaan peli.
     * Käyttöliittymän pitää lähettää metodille pelaajalta pyydettyjä tietoja.
     * 
     * @param nimi pelaajan hahmon nimi
     * @param luokka pelaajan hahmon hahmoluokka
     * @param kartanNimi pelaajan valitsema kartta
     */
    public void aloitaPeli(String nimi, String luokka, String kartanNimi) {
        PeliTehdas pt = new PeliTehdas();
        this.peli = pt.luoPeli(nimi, luokka, kartanNimi);

        paivitaKali("Sijaintisi:\n" + peli.getPelaaja().getSijainti());
        paivitaKali("Tervetuloa, urhea sankari, tähän maanmainioon seikkailuun!\n");
    }
    
    /**
     * 
     * @return Peli-luokan olio, joka on parhaillaan käytössä.
     */
    public Peli getPeli() {
        return peli;
    }
    
    /**
     * Metodi, joka lähettää käyttöliittymälle viestin siitä, mitä viimeisimmän kontrolleri-kutsun jälkeen tapahtui.
     * 
     * @param tapahtuma kontrollerin muilta metodeilta saatava viesti tapahtumista
     */
    public void paivitaKali(String tapahtuma) {
        peli.getKartta().paivitaNahdyt(peli.getPelaaja().getSijainti());
        peli.tuleekoTaistelu();
        kali.paivita(tapahtuma);
    }
    
    /**
     * 
     * @return parhaillaan käytössä oleva kartta
     */
    public Kartta getKartta() {
        return peli.getKartta();
    }
    
    /**
     * Metodi, joka liikuttaa pelaajahahmoa.
     * Päivittää käyttöliittymää viestillä liikkeen onnistumisesta.
     * Lopuksi tarkistetaan onko käyttäjällä liikkeitä jäljellä ja päivitetään käyttöliittymää sen mukaisesti.
     * 
     * @param suunta suunta johon halutaan liikkua
     */
    public void pelaajanLiike(Ilmansuunta suunta) {
        Karttapala vanha = peli.getPelaaja().getSijainti();
        peli.pelaajanLiike(suunta);
        Karttapala uusi = peli.getPelaaja().getSijainti();
        
        if(vanha.equals(uusi)) {
            paivitaKali("Ei sinne voi liikkua! >:-(\n");
        }
        else {
            paivitaKali("Siirryit onnistuneest! Sijaintisi nyt:\n" + peli.getPelaaja().getSijainti() + "\n");
        }
        
        if(peli.getPelaaja().getLiikkeet() == 0) {
            peli.lopetaVuoro();
            paivitaKali("Pelottavat monsterit liikkuvat pimeässä...\n");
        }
    }
    
    /**
     * Metodi, joka simuloi nopanheittoa. Heitetään pelaajan nopeuden mukaista määrää noppia.
     * Metodia kutsutaan, kun pelaaja on käyttänyt kaikki liikkeensä.
     */
    public void liikenopanHeitto() {
        int yhteensa = 0;
        for(int i = 0; i < peli.getPelaaja().getNopeus(); i++) {
            yhteensa += random.nextInt(6) + 1;
        }
        peli.getPelaaja().setLiikkeet(yhteensa);
        paivitaKali("Mainio heitto: " + yhteensa + "\n");
    }
    
    /**
     * Metodi, joka lähettää pelaajan statuksen (nimen, voiman, energian, yms) käyttöliittymälle
     * 
     * @return pelaajan status String-oliona
     */
    public String pelaajanStatus() {
        return peli.getPelaaja().toString();
    }
    
    /*
     * Metodi, joka palauttaa tilan jossa peli on. Sen mukaan käyttöliittymä osaa näyttää oikeat komponentit.
     * 
     * @return pelin/pelaajan tilanne
     */
    public String getTila() {
        if(peli.getPelaaja().getEnergia() <= 0) {
            return "kuolema";
        }
        if(peli.taistelunAika()) {
            return "taistelu";
        }
        if(peli.getPelaaja().getSijainti().aarrePaikalla()) {
            return "voitto";
        }
        else if(peli.getPelaaja().getLiikkeet() > 0) {
            return "liike";
        }
        else {
            return "liikenoppa";
        }
    }
    
    /*
     * Taistelumetodi. Taistelussa nopeampi hahmo hyökkää ensin ja hitaampi puolustaa.
     * Mikäli hitaampi olento selviää hyökkäyksestä, on sen vuoro hyökätä.
     */
    public void taistele() {
        Olento aloittaja = null;
        Olento seuraaja = null;
        
        // jos vastustajaa ei jostain syystä ollutkaan, ei voida taistellakaan. :(
        if(peli.getVastustaja() == null) {
            paivitaKali("Ei ole ketään ketä vastaan taistella (mitäs peliä tämä on??)!");
        }
        else {
            
            // tarkastetaan kumpi on nopeampi ja asetetaan aloittaja ja seuraaja sen mukaisesti.
            if(peli.getPelaaja().getNopeus() >= peli.getVastustaja().getNopeus()) {
                aloittaja = peli.getPelaaja();
                seuraaja = peli.getVastustaja();
            }
            else {
                aloittaja = peli.getVastustaja();
                seuraaja = peli.getPelaaja();
            }
            
            StringBuilder taisteluviesti = new StringBuilder();
            taisteluviesti.append("On taistelun aika!\n");
            taisteluviesti.append("Vastassasi on pelottava monsteri " + peli.getVastustaja().toString() + "\n");
            
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
                peli.poistaKuolleetMonsterit();
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
                peli.poistaKuolleetMonsterit();
                }
            }
            
            
            paivitaKali(taisteluviesti.toString());
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
