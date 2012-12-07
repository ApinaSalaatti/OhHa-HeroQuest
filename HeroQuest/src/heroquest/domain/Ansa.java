/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import heroquest.PeliController;
/**
 * Karttapaloissa mahdollisesti sijaitsevaa ansaa kuvaava luokka.
 * 
 * @author Merioksan Mikko
 */
public class Ansa {
    /**
     * Ansan nimi
     */
    private String nimi;
    /**
     * Ansan tekemä vahinko
     */
    private int vahinko;
    /**
     * Ansan lauetessa pelaajalle näytettävä viesti. 
     */
    private String viesti;
    /**
     * Tieto, onko pelaaja havainnut ansan vai ei.
     */
    private boolean havaittu;
    
    public Ansa(String n, int v, String viesti) {
        this.nimi = n;
        
        // vahingon täytyy olla vähintään 1
        if(v <= 0) {
            this.vahinko = 1;
        }
        else {
            this.vahinko = v;
        }
        
        this.viesti = viesti;
        havaittu = false;
    }
    
    /**
     * Tekee ansasta näkyvän kartalla.
     */
    public void paljastu() {
        havaittu = true;
    }
    /**
     * Palauttaa tiedon, onko ansa löydetty.
     * 
     * @return tieto ansan näkymisestä
     */
    public boolean havaittu() {
        return havaittu;
    }
    /**
     * Olento osuessa ansoitettuun ruutuun, ansa laukeaa ja olento ottaa vahinkoa.
     * 
     * @param o ansan laukaissut olento
     */
    public String laukea(Olento o) {
        paljastu();
        o.otaVahinkoa(vahinko);
        return viesti;
    }
}
