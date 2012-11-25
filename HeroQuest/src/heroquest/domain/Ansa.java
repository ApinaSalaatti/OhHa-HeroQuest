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
     * Ansan nimi/tyyppi
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
        this.vahinko = v;
        this.viesti = viesti;
        havaittu = false;
    }
    
    public void paljastu() {
        havaittu = true;
    }
    public boolean havaittu() {
        return havaittu;
    }
    /**
     * Olento osuessa ansoitettuun ruutuun, ansa laukeaa ja olento ottaa vahinkoa.
     * 
     * @param o ansan laukaissut olento
     * @param pc käytössä oleva controlleri
     */
    public void laukea(Olento o, PeliController pc) {
        paljastu();
        o.otaVahinkoa(vahinko);
        pc.paivitaKali(viesti);
    }
}
