/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest;

import java.util.ArrayList;
import java.util.Iterator;

import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
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
     * Kaikki pelissä jäljellä olevat monsterit.
     */
    private ArrayList<Monsteri> monsterit;
    /**
     * Apumuuttuja, joka määrittelee onko pelaaja taistelussa.
     */
    private boolean taistelunAika;
    
    public Peli(Kartta k, Pelaaja p) {
        kartta = k;
        pelaaja = p;
        monsterit = new ArrayList<Monsteri>();
        taistelunAika = false;
        
        kartta.paivitaNahdyt(pelaaja.getSijainti());
    }
    
    /**
     * Lisätään kartalle yksi Monsteri annettuun Karttapalaan.
     * 
     * @param m lisättävä Monsteri
     * @param pala Karttapala, johon monsteri lisätään
     */
    public void lisaaMonsteri(Monsteri m, Karttapala pala) {
        m.setSijainti(pala);
        pala.monsteriSaapuu(m);
        monsterit.add(m);
    }
    
    /**
     * Poistaa annetun Monsterin pelistä, esim kun Pelaaja tappaa sen.
     * 
     * @param m poistettava Monsteri
     */
    public void poistaMonsteri(Monsteri m) {
        m.getSijainti().monsteriPoistuu();
        monsterit.remove(m);
    }
    /**
     * Metodi, joka poistaa pelistä kaikki kuolleet Monsterit.
     */
    public void poistaKuolleetMonsterit() {
        Iterator<Monsteri> iter = monsterit.listIterator();
        while(iter.hasNext()) {
            Monsteri m = iter.next();
            if(m.getEnergia() <= 0) {
                m.getSijainti().monsteriPoistuu();
                iter.remove();
                pelaaja.lisaaTappo();
            }
        }
    }
    
    /**
     * @return käytössä oleva Kartta
     */
    public Kartta getKartta() {
        return kartta;
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
        pelaaja.liiku(suunta);
        tuleekoTaistelu();
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
        // hirviöt liikkuvat vain jos pelaaja ei ole taistelussa
        if(!taistelunAika) {
            for(Monsteri m : monsterit) {
                m.liiku();
            }
        }
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
}
