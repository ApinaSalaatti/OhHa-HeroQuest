/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.domain.kauppa;

import java.util.ArrayList;
import java.util.List;

import heroquest.domain.Pelaaja;
import heroquest.domain.kauppa.Tavara;
import heroquest.domain.Inventaario;

/**
 * Pelaajan kotona sijaitsevaa kauppaa kuvaava luokka. Sisältää metodeja mm. tavaroiden ostamiseen ja myymiseen.
 * 
 * @author Merioksan Mikko
 */
public class Kauppa {
    /**
     * Kaupan inventaario, joka sisältää siis kaupassa myynnissä olevat tavarat.
     */
    private Inventaario tavarat;
    /**
     * Prosentti, jonka kauppa lisää myymiensä tavaroiden arvoon.
     */
    private double myyntiProsentti;
    /**
     * Prosentti, jonka kauppa poistaa ostamiensa tavaroiden arvosta.
     */
    private double ostoProsentti;
    
    public Kauppa() {
        this.tavarat = new Inventaario();
        tavarat.lisaaTavara(new Tavara("kotiportaali.hqt"));
        tavarat.lisaaTavara(new Tavara("salaperainenputeli.hqt"));
        tavarat.lisaaTavara(new Tavara("voimaputeli.hqt"));
        tavarat.lisaaTavara(new Tavara("energiaputeli.hqt"));
        tavarat.lisaaTavara(new Tavara("ansojenpaljastaja.hqt"));
        myyntiProsentti = 1.5;
        ostoProsentti = 0.5;   
    }
    /**
     * Konstruktori, jonka avulla luodaan kauppa jossa on myytävänä tietyt tavarat.
     * 
     * @param tavarat 
     */
    public Kauppa(List<Tavara> tavarat) {
        this.tavarat = new Inventaario();
        for(Tavara t: tavarat) {
            this.tavarat.lisaaTavara(t);
        }
        myyntiProsentti = 1.5;
        ostoProsentti = 0.5;  
    }
    
    /**
     * Metodi, joka lisää tavaran kaupan valikoimiin.
     * 
     * @param t lisättävä Tavara
     */
    public void lisaaTavara(Tavara t) {
        tavarat.lisaaTavara(t);
    }
    /**
     * Metodi, joka poistaa ja palauttaa parametrina annetun tavaran kaupan vaikoimista. Jos tavaraa ei ole, palautuu null.
     * 
     * @param t poistettava tavara
     * @return poistettu tavara
     */
    public Tavara poistaTavara(Tavara t) {
        Tavara palautus = null;
        if(tavarat.poistaTavara(t)) {
            palautus = t;
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
        
        for(Tavara t: tavarat.getTavarat()) {
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
    public boolean myy(MyyntiTavara tavara, Pelaaja p) {
        Tavara t = tavara.getTavara();
        
        if(t != null && p != null) {
            int varat = p.getVarat();
            int hinta = (int)(t.getArvo() * myyntiProsentti);
            if(varat >= hinta) {
                p.setVarat(varat - hinta);
                p.lisaaTavara(t);
                tavarat.poistaTavara(t);
                return true;
            }
        }
        
        return false;
    }
    /**
     * Metodi, joka myy tavaran pelaajalta kaupalle. Kaupalla on aina kaikkeen varaa.
     * 
     * @param t myytävä tavara
     * @param p pelaaja jolle myydään
     * @return onnistuiko myynti
     */
    public boolean osta(Tavara t, Pelaaja p) {
        int arvo = (int)(t.getArvo() * ostoProsentti);
        if(p.poistaTavara(t)) {
            p.setVarat(p.getVarat() + arvo);
            tavarat.lisaaTavara(t);
            return true;
        }
        
        return false;
    }
    
    /**
     * Peliä tallennettaessa, tallennetaan kaupassa sillä hetkellä myynnissä olevat tavarat.
     * 
     * @return kaupan inventaarion tavarat
     */
    public String tallenna() {
        return tavarat.tallenna();
    }
}
