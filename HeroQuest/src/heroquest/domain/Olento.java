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
public class Olento {
    private String nimi;
    private int voima;
    private int energia;
    private Karttapala sijainti;
    protected Random random;
    
    public Olento(String nimi, int voima, int energia) {
        this.random = new Random();
        
        setNimi(nimi);
        setVoima(voima);
        setEnergia(energia);
    }
    // jos nimeä ei anneta, haetaan random hauska nimi
    public Olento(int voima, int energia) {
        this.random = new Random();
        
        setNimi("");
        setVoima(voima);
        setEnergia(energia);
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
    public void otaVahinkoa(int vahinko) {
        energia -= vahinko;
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
}
