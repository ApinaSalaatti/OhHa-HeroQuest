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
    private Random random;
    
    public Olento(String nimi, int voima, int energia) {
        this.random = new Random();
        this.nimi = nimi;
        this.voima = voima;
        this.energia = energia;

    }
    // jos nimeä ei anneta, haetaan random hauska nimi
    public Olento(int voima, int energia) {
        this.random = new Random();
        this.nimi = Nimilista.getHauskaNimi();
        this.voima = voima;
        this.energia = energia;
    }
    
    public String getNimi() {
        return nimi;
    }
    
    public int getVoima() {
        return voima;
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
    public void setSijainti(Karttapala pala) {
        sijainti = pala;
    }
    public void liiku(Ilmansuunta suunta) {
        sijainti = sijainti.getNaapuri(suunta);
    }
}
