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
        boolean lopetus = false;
        while(!lopetus) {
            tulostaPelaajanTiedot();
            System.out.println("Komennot: (p)ohjoinen (i)tä (e)telä (l)änsi e(x)it");
            String komento = lukija.nextLine();
            if(komento.equals("p")) {
                pelaaja.liiku(Ilmansuunta.POHJOINEN);
            }
            else if(komento.equals("i")) {
                pelaaja.liiku(Ilmansuunta.ITA);
            }
            else if(komento.equals("e")) {
                pelaaja.liiku(Ilmansuunta.ETELA);
            }
            else if(komento.equals("l")) {
                pelaaja.liiku(Ilmansuunta.LANSI);
            }
            else if(komento.equals("x")) {
                lopetus = true;
            }
            else {
                System.out.println("Anna kelvollinen komento! >:-(");
            }
        }
    }
    
    private void tulostaPelaajanTiedot() {
        System.out.println(pelaaja.getNimi());
        System.out.println("Voima: " + pelaaja.getVoima());
        System.out.println("Energia: " + pelaaja.getVoima());
        System.out.println(pelaaja.getSijainti());
    }
}
