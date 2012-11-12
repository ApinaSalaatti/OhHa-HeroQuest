/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.tekstikayttoliittyma;

import java.util.Scanner;

import heroquest.domain.Kartta;
import heroquest.domain.Olento;
import heroquest.domain.Ilmansuunta;
/**
 *
 * @author Merioksan Mikko
 */
public class Tekstikayttoliittyma {
    private Scanner lukija;
    Kartta kartta;
    Olento pelaaja;
    
    public Tekstikayttoliittyma(Scanner l) {
        this.lukija = l;
        kartta = new Kartta(10);
        pelaaja = new Olento(5, 5);
        pelaaja.setSijainti(kartta.getAloituspala());
    }
    
    public void kaynnista() {
        
    }
    
    private void tulostaPelaajanTiedot() {
        System.out.println(pelaaja.getNimi());
        System.out.println("Voima: " + pelaaja.getVoima());
        System.out.println("Energia: " + pelaaja.getVoima());
        System.out.println(pelaaja.getSijainti());
    }
}
