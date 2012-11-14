/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest;

import java.util.ArrayList;

import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
import heroquest.domain.Ilmansuunta;
import heroquest.domain.Pelaaja;
import heroquest.domain.Monsteri;
/**
 *
 * @author merioksa
 */
public class Peli {
    private Kartta kartta;
    private Pelaaja pelaaja;
    private ArrayList<Monsteri> monsterit;
    private boolean taistelunAika;
    
    public Peli(Kartta k, Pelaaja p) {
        kartta = k;
        pelaaja = p;
        monsterit = new ArrayList<Monsteri>();
        taistelunAika = false;
        
        kartta.paivitaNahdyt(pelaaja.getSijainti());
    }
    
    public void lisaaMonsteri(Monsteri m, Karttapala pala) {
        m.setSijainti(pala);
        pala.monsteriSaapuu(m);
        monsterit.add(m);
    }
    
    public void poistaMonsteri(Monsteri m) {
        m.getSijainti().monsteriPoistuu();
        monsterit.remove(m);
    }
    
    public Kartta getKartta() {
        return kartta;
    }
    
    public Pelaaja getPelaaja() {
        return pelaaja;
    }
    
    public void pelaajanLiike(Ilmansuunta suunta) {
        pelaaja.liiku(suunta);
        tuleekoTaistelu();
    }
    
    // liikutetaan kaikki kartan hirviöitä kerralla
    private void monsterienLiike() {
        // hirviöt liikkuvat vain jos pelaaja ei ole taistelussa
        if(!taistelunAika) {
            for(Monsteri m : monsterit) {
                m.liiku();
            }
        }
    }
    
    // palautetaan monsteri joka on samassa ruudussa pelaajan kanssa. Jos yksikään ei ole, return null
    public Monsteri getVastustaja() {
        Karttapala pelaajaSijainti = pelaaja.getSijainti();
        for(Monsteri m : monsterit) {
            if(m.getSijainti().equals(pelaajaSijainti)) {
                return m;
            }
        }
        
        return null;
    }
    
    // kun pelaaja on käyttänyt kaikki liikkeensä, on vuoro ohi ja monsterit liikkuvat
    public void lopetaVuoro() {
        monsterienLiike();
        tuleekoTaistelu();
    }
    
    // tarkastetaan joutuuko pelaaja taisteluun (ts. onko samassa ruudussa monsteri)
    public void tuleekoTaistelu() {
        Karttapala pelaajanSijainti = pelaaja.getSijainti();
        taistelunAika = false;
        
        for(Monsteri m : monsterit) {
            if(m.getSijainti().equals(pelaajanSijainti)) {
                taistelunAika = true;
                return;
            }
        }
    }
    
    public boolean taistelunAika() {
        return taistelunAika;
    }
}
