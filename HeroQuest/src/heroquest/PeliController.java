package heroquest;

import java.util.Random;

import heroquest.util.Tiedostoapuri;
import heroquest.domain.Olento;
import heroquest.domain.Monsteri;
import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
import heroquest.domain.Ilmansuunta;
import heroquest.domain.kauppa.Tavara;
import heroquest.kayttoliittyma.Kayttoliittyma;
/**
 * Luokka joka toimii välittäjänä pelilogiikan ja käyttöliittymän välillä.
 * Idea on, että käyttöliittymä voi olla millainen tahansa pelilogiikan muuttumatta. MVC-mallin C.
 * 
 * @author Merioksan Mikko
 */
public class PeliController {
    /**
     * Satunnaisuutta tarvittaessa.
     */
    private Random random;
    /**
     * Pelin ja karttojen luomiseen käytettävän apuluokan olio.
     */
    private PeliTehdas tehdas;
    /**
     * Peli-luokan olio, joka sisältää kaiken pelilogiikan. MVC-mallissa se model.
     */
    private Peli peli;
    /**
     * Käyttöliittymä-olio, voi olla toteutettu monin eri tavoin.
     */
    private Kayttoliittyma kali;
    /**
     * Ali-kontrolleri, joka sisältää kaikki pelaajan kotona hoidettavat toimenpiteet
     */
    private KotiController koti;
    
    public PeliController(Kayttoliittyma k) {
        this.random = new Random();
        this.kali = k;
        this.koti = new KotiController(this);
        this.tehdas = new PeliTehdas();
    }
    
    /**
     * Metodi, jota käyttöliittymä kutsuu kun ollaan valmiina aloittamaan peli.
     * Käyttöliittymän pitää lähettää metodille pelaajalta pyydettyjä tietoja.
     * 
     * @param nimi pelaajan hahmon nimi
     * @param luokka pelaajan hahmon hahmoluokka
     * @param kartanNimi pelaajan valitsema kartta
     */
    public void aloitaPeli(String nimi, String luokka) {
        tehdas = new PeliTehdas();
        this.peli = tehdas.luoPeli(nimi, luokka);
        this.koti = new KotiController(this);
        
        paivitaKali("Sijaintisi:\n" + peli.getPelaajanSijainti());
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
     * @return KotiController joka on parhaillaan käytössä
     */
    public KotiController getKoti() {
        return koti;
    }
    /**
     * Pelaajan kotona olevaksi asettava metodi
     */
    public void pelaajaSaapuuKotiin() {
        peli.pelaajaSaapuuKotiin();
        paivitaKali("Tervetuloa kotiin, urhea sankari!");
    }
    /**
     * Pelaajan luolastoa komppaavaksi asettava metodi
     * 
     * @param pala kohteena olevan luolaston aloituspala
     */
    public void pelaajaPoistuuKotoa(String kartta) {
        Kartta k = tehdas.luoLuolasto(kartta);
        peli.setKartta(k);
        peli.pelaajaPoistuuKotoa(k.getAloituspala());
        paivitaKali(peli.getPelaaja().getSijainti().toString());
        paivitaKali("Jännittävä seikkailu alkaa!");
    }
    
    /**
     * Metodi, joka lähettää käyttöliittymälle viestin siitä, mitä viimeisimmän kontrolleri-kutsun jälkeen tapahtui.
     * 
     * @param tapahtuma kontrollerin muilta metodeilta saatava viesti tapahtumista
     */
    public void paivitaKali(String tapahtuma) {
        if(!peli.pelaajaKotona()) {
            peli.getKartta().paivitaNahdyt(peli.getPelaaja().getSijainti());
            peli.tuleekoTaistelu();
        }
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
            paivitaKali("Siirryit onnistuneesti! Sijaintisi nyt:\n" + peli.getPelaaja().getSijainti() + "\n");
        }
        
        if(uusi.ansaPaikalla()) {
            uusi.laukaiseAnsa(peli.getPelaaja(), this);
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
     * Metodi tavaroiden poimimiseen. Kutsuu peli-luokan poimimis-metodia ja päivittää käyttöliittymää poiminnan onnistumisesta riippuen.
     */
    public void tavaroidenPoiminta() {
        if(peli.tavaroidenPoiminta()) {
            paivitaKali("Tavarat poimittu!\n");
        }
        else {
            paivitaKali("Ei mitään poimittavaa. :(\n");
        } 
    }
    
    /**
     * Metodi pelaajan inventaariossa olevien tavaroiden käyttämiseen.
     * Jokainen tavara lähettää käytettäessä viestin, joka eteenpäin lähetetään käyttöliittymälle.
     * 
     * @param t tavara, jota pelaaja haluaa käyttää
     */
    public void kaytaTavaraa(Tavara t) {
        String viesti = null;
        if(t != null) {
            viesti = t.kayta(this) + "\n";
            paivitaKali(viesti);
            if(t.kkayttoinen()) {
                peli.getPelaaja().getInventaario().poistaTavara(t);
            }
        }
        else {
            viesti = "Valitse käytettävä tavara!\n";
        }
        
        paivitaKali(viesti);
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
        if(peli.pelaajaKotona()) {
            return "koti";
        }
        if(peli.taistelunAika()) {
            return "taistelu";
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
    
    /**
     * Pelin tallentava metodi. Yksinkertaisesti kirjoitetaan pelaajan tiedot tekstitiedostoon.
     */
    public void tallenna(String tiedostonimi) {
        Tiedostoapuri.tallennaPeli(peli.tallenna(), tiedostonimi);
    }
    
    /**
     * Pelin tallennuksesta lataava metodi. Rakentaa uuden pelin tiedoston tietojen perusteella.
     * 
     * @param tiedostonimi tiedosto josta peli ladataan
     */
    public void lataa(String tiedostonimi) {
        PeliTehdas pt = new PeliTehdas();
        this.peli = pt.lataaPeli(tiedostonimi);
        
        paivitaKali("Sijaintisi: " + peli.getPelaaja().getSijainti());
        paivitaKali("Peli ladattu!\n");
    }
}
