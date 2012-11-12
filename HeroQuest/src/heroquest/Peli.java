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
        luoMonsterit();
    }
    
    private void luoMonsterit() {
        Monsteri m = new Monsteri(5, 5);
        monsterit.add(m);
        Karttapala[][] palat = kartta.getKarttapalat();
        m.setSijainti(palat[3][1]);
        palat[3][1].monsteriSaapuu(m);
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
    
    private void monsterienLiike() {
        for(Monsteri m : monsterit) {
            m.liiku();
        }
    }
    
    public void lopetaVuoro() {
        monsterienLiike();
        tuleekoTaistelu();
    }
    
    public void tuleekoTaistelu() {
        Karttapala pelaajanSijainti = pelaaja.getSijainti();
        for(Monsteri m : monsterit) {
            if(m.getSijainti().equals(pelaajanSijainti)) {
                taistelunAika = true;
                return;
            }
        }
        taistelunAika = false;
    }
    
    public boolean taistelunAika() {
        return taistelunAika;
    }
}
