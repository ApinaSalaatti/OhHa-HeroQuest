/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest;

import java.util.ArrayList;
import java.util.Iterator;

import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
import heroquest.domain.Kotikarttapala;
import heroquest.domain.Ilmansuunta;
import heroquest.domain.Pelaaja;
import heroquest.domain.Monsteri;
/**
 * Pelilogiikan sisältävä luokka. MVC-mallin M.
 * 
 * @author Merioksan Mikko
 */
public class Peli {
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
        pelaaja = p;
        taistelunAika = false;
        kotipala = new Kotikarttapala();
        pelaajaSaapuuKotiin();
    }
    
    /**
     * Lisätään kartalle yksi Monsteri annettuun Karttapalaan.
     * 
     * @param m lisättävä Monsteri
     * @param pala Karttapala, johon monsteri lisätään
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
        int tapot = kartta.poistaKuolleetMonsterit();
        pelaaja.lisaaTapot(tapot);
    }
    
    /**
     * @return käytössä oleva Kartta
     */
    public Kartta getKartta() {
        return kartta;
    }
    /**
     * @param k luolasto, johon ollaan syöksymässä
     */
    public void setKartta(Kartta k) {
        kartta = k;
    }
    
    /**
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
    public void pelaajanLiike(Ilmansuunta suunta) {
        if(kartta.ulosKartalta(pelaaja.getSijainti(), suunta)) {
            pelaajaSaapuuKotiin();
        }
        else {
            pelaaja.liiku(suunta);
        }
        tuleekoTaistelu();
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
        if(pelaaja.getSijainti().getTavarat().size() <= 0) {
            return false;
        }
        
        pelaaja.lisaaTavarat(pelaaja.getSijainti().poimiTavarat());
        
        return true;
    }
    
    /**
     * Metodi, joka liikuttaa kaikkia pelissä olevia Monstereita.
     * Tämä tehdään yleensä kun pelaaja on käyttäny kaikki liikkeensä.
     */
    private void monsterienLiike() {
        kartta.monsterienLiike(taistelunAika);
    }
    
    /**
     * @return monsteri joka on samassa ruudussa pelaajan kanssa. Jos yksikään ei ole, return null
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
     * @return tieto siitä, ollaanko taistelussa
     */
    public boolean taistelunAika() {
        return taistelunAika;
    }
    
    /**
     * Pelaajan kotona olevaksi asettava metodi
     */
    public void pelaajaSaapuuKotiin() {
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
     * @return tieto siitä, onko pelaaja kotona vai luolastossa
     */
    public boolean pelaajaKotona() {
        return pelaajaKotona;
    }
    
    /**
     * Palautetaan pelaajan tiedot tallennettavaksi tiedostoon
     * 
     * @return pelaajan tiedot String-muuttujassa
     */
    public String tallenna() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(pelaaja.tallenna());
        
        return sb.toString();
    }
}
