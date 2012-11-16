/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import java.util.Random;

import heroquest.util.Nimilista;
/**
 *
 * @author merioksa
 */
public abstract class Olento {
    private String nimi;
    private int voima;
    private int energia;
    private int nopeus;
    private Karttapala sijainti;
    protected Random random;
    
    public Olento(String nimi, int voima, int energia, int nopeus) {
        this.random = new Random();
        
        setNimi(nimi);
        setVoima(voima);
        setEnergia(energia);
        setNopeus(nopeus);
    }
    // jos nimeä ei anneta, haetaan random hauska nimi
    public Olento(int voima, int energia, int nopeus) {
        this.random = new Random();
        
        setNimi("");
        setVoima(voima);
        setEnergia(energia);
        setNopeus(nopeus);
    }
    
    // luokan (ja aliluokkien) sisäinen asetusfunktio, nimi ei voi olla tyhjä tai pelkkää white spacea
    protected void setNimi(String nimi) {
        nimi = nimi.trim();
        if(nimi.length() > 0) {
            this.nimi = nimi;
        }
        else {
            this.nimi = Nimilista.getHauskaNimi();
        }
    }
    public String getNimi() {
        return nimi;
    }
    
    // konstruktorille tarkoitettu asetusfunktio, voima ei voi olla alle 1
    private void setVoima(int voima) {
        if(voima <= 0) {
            this.voima = 1;
        }
        else {
            this.voima = voima;
        }
    }
    public int getVoima() {
        return voima;
    }
    
    // konstruktorille tarkoitettu asetusfunktio, energia ei voi olla alle 1
    private void setEnergia(int energia) {
        if(energia <= 0) {
            this.energia = 1;
        }
        else {
            this.energia = energia;
        }
    }
    public int getEnergia() {
        return energia;
    }
    public void muutaEnergia(int muutos) {
        this.energia += muutos;
    }
    
    public void setNopeus(int nopeus) {
        if(nopeus < 0) {
            this.nopeus = 0;
        }
        else {
            this.nopeus = nopeus;
        }
    }
    public int getNopeus() {
        return nopeus;
    }
    
    public Karttapala getSijainti() {
        return sijainti;
    }
    public boolean setSijainti(Karttapala pala) {
        if(pala != null) {
            sijainti = pala;
            return true;
        }
        return false;
    }
    
    // kaikki olennot voivat hyökätä ja puolustaa, ja ottaa vahinkoa mutta hieman eri tavoilla
    public abstract int hyokkaa();
    public abstract int puolustaudu();
    public abstract String otaVahinkoa(int vahinko);
}
