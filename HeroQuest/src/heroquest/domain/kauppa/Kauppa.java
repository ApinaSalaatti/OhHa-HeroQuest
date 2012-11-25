/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.domain.kauppa;

import heroquest.domain.Pelaaja;
import heroquest.domain.kauppa.Tavara;
import java.util.ArrayList;
import java.util.List;

/**
 * Pelaajan kotona sijaitsevaa kauppaa kuvaava luokka. Sisältää metodeja mm. tavaroiden ostamiseen ja myymiseen.
 * 
 * @author Merioksan Mikko
 */
public class Kauppa {
    private List<Tavara> tavarat;
    private double myyntiProsentti;
    private double ostoProsentti;
    
    public Kauppa() {
        this.tavarat = new ArrayList<Tavara>();
        tavarat.add(new Tavara("salaperainenputeli.hqt"));
        myyntiProsentti = 1.5;
        ostoProsentti = 0.5;
        
    }
    
    /**
     * Metodi, joka lisää tavaran kaupan valikoimiin.
     * 
     * @param t lisättävä Tavara
     */
    public void lisaaTavara(Tavara t) {
        if(t.getNimi().length() > 0) {
            tavarat.add(t);
        }
    }
    /**
     * Metodi, joka poistaa ja palauttaa parametrina annetun tavaran kaupan vaikoimista. Jos tavaraa ei ole, palautuu null.
     * 
     * @param t poistettava tavara
     * @return poistettu tavara
     */
    public Tavara poistaTavara(Tavara t) {
        Tavara palautus = null;
        
        if(tavarat.contains(t)) {
            palautus = t;
            tavarat.remove(t);
        }
        
        return palautus;
    }
    
    /**
     * Palautetaan pelaajalle tulostettavaksi lista kaupan tuotteista ja hinnoista.
     * Metodi palauttaa MyyntiTavara-luokan olioita, jotka sisältävät tavaran lisäksi niiden hintaan lisättävän kertoimen myytäessä
     * 
     * @return String jossa tavaran nimi ja hinta
     */
    public List<MyyntiTavara> getTavarat() {
        List<MyyntiTavara> palautus = new ArrayList<MyyntiTavara>();
        
        for(Tavara t: tavarat) {
            MyyntiTavara uusi = new MyyntiTavara(t, myyntiProsentti);
            palautus.add(uusi);
        }
        
        return palautus;
    }
    
    /**
     * Metodi, joka tarkastaa onko pelaajalla varaa haluttuun tuotteeseen.
     * Mikäli ostaminen onnistuu, lisätään tavara pelaajan inventaarioon ja poistetaan kaupan vaikoimasta.
     * 
     * @param t tavara jonka pelaaja haluaa ostaa
     * @param p ostoa yrittävä pelaaja
     * @return onnistuiko kauppa
     */
    public boolean osta(MyyntiTavara tavara, Pelaaja p) {
        Tavara t = tavara.getTavara();
        
        if(t != null && p != null) {
            int varat = p.getVarat();
            int hinta = (int)(t.getArvo() * myyntiProsentti);
            if(varat >= hinta) {
                p.setVarat(varat - hinta);
                p.lisaaTavara(t);
                tavarat.remove(t);
                return true;
            }
        }
        
        return false;
    }
}
