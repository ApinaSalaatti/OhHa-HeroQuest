package heroquest;

import java.util.Collection;

import heroquest.util.Tiedostoapuri;
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
    /**
     * Saavutuksia valvova luokka. Siis saavutuksia! 
     */
    private Saavutusmanageri saavutusmanageri;
    
    public PeliController(Kayttoliittyma k) {
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
     * @param kuva pelaajan kuvan tiedostonimi
     */
    public void aloitaPeli(String nimi, String luokka, String kuva) {
        tehdas = new PeliTehdas();
        this.peli = tehdas.luoPeli(nimi, luokka, kuva);
        this.koti = new KotiController(this);
        
        this.saavutusmanageri = new Saavutusmanageri(peli);
        
        paivitaKali("Sijaintisi:\n" + peli.getPelaajanSijainti());
        paivitaKali("Tervetuloa, urhea sankari, tähän maanmainioon seikkailuun!\n");
    }
    
    /**
     * Palautetaan käynnissä oleva peli.
     * 
     * @return Peli-luokan olio, joka on parhaillaan käytössä.
     */
    public Peli getPeli() {
        return peli;
    }
    
    /**
     * Palautetaan parhaillaan kodin toimintoja hallinnoiva kontrolleri.
     * 
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
     * @param kartta kartan nimi, jolle pelaaja on siirtymässä. Kartta siis luodaan tämän nimisestä tiedostosta
     */
    public void pelaajaPoistuuKotoa(String kartta) throws Exception {
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
        
        if(!tapahtuma.equals("")) {
            kali.paivita(tapahtuma);
        }
        
        // tarkastetaan saavutettiinko saavutuksia
        String saavutus = saavutusmanageri.tarkistaSaavutukset(tapahtuma);
        if(!saavutus.equals("")) {
            kali.paivita(saavutus);
        }
    }
    
    /**
     * Palautetaan parhaillaan kompattava luolasto.
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
        String viesti = peli.pelaajanLiike(suunta);
        Karttapala uusi = peli.getPelaaja().getSijainti();
        
        if(vanha.equals(uusi)) {
            paivitaKali("Ei sinne voi liikkua! >:-(\n");
        }
        else {
            paivitaKali("Liikkeitä jäljellä: " + peli.getPelaaja().getLiikkeet() + "\n");
            paivitaKali("Siirryit onnistuneesti! Sijaintisi nyt:\n" + peli.getPelaaja().getSijainti() + "\n");
        }
        
        if(!viesti.equals("") && viesti != null) {
            paivitaKali(viesti);
        }
        
        if(peli.getPelaaja().getLiikkeet() == 0) {
            peli.lopetaVuoro();
            paivitaKali("Pelottavat monsterit liikkuvat pimeässä...\n");
        }
    }
    
    /**
     * Kutsutaan nopanheittoa simuloivaa metodia.
     */
    public void liikenopanHeitto() {
        int heitto = peli.liikenopanHeitto();
        paivitaKali("Mainio heitto: " + heitto + "\n");
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
        String viesti = peli.kaytaTavaraa(t);
        
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
    
    /**
     * Palautetaan pelaajan saavuttamat ja saavuttamattoma saavutukset.
     * 
     * @return lista kaikista saavutetetuista ja saavuttamattomista saavutuksista
     */
    public Collection<Saavutus> getSaavutukset() {
        return saavutusmanageri.getSaavutukset();
    }
    
    /*
     * Kutsutaan Peli-luokan taistele-metodia.
     */
    public void taistele() {
        paivitaKali(peli.taistele());
    }
    
    /**
     * Pelin tallentava metodi. Tallennetaan pelin tiedot annetun nimiseen tallennuskansioon.
     */
    public void tallenna(String tiedostonimi) {
        Tiedostoapuri.tallennaData(peli);
        Tiedostoapuri.tallennaPeli(this, tiedostonimi);
    }
    
    /**
     * Pelin tallennuksesta lataava metodi. Rakentaa uuden pelin tiedoston tietojen perusteella.
     * 
     * @param tiedostonimi tiedosto josta peli ladataan
     */
    public void lataa(String tiedostonimi) throws Exception {
        PeliTehdas pt = new PeliTehdas();
        this.peli = pt.lataaPeli(tiedostonimi, this);
        
        paivitaKali("Sijaintisi: " + peli.getPelaaja().getSijainti());
        paivitaKali("Peli ladattu!\n");
    }
}
