/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest;

import java.util.Scanner;

import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
import heroquest.domain.Pelaaja;
import heroquest.domain.Monsteri;
import heroquest.util.KarttaGeneraattori;
import heroquest.util.Tiedostoapuri;
/**
 *
 * @author Merioksan Mikko
 * 
 * Tehdasluokka joka luo pelin, pelaajan ja kartan annettujen parametrien pohjalta
 * 
 */
public class PeliTehdas {
    public PeliTehdas() {
    }
    
    /*
     * luodaan peli annetuilla parametreilla
     * pelaajanNimi ja pelaajanLuokka -parametrien avulla luodaan pelaajahahmo
     * kartanNimi on .hqm-tiedosto josta karttadata luetaan
     * .hqm-tiedoston formaatti on selitetty dokumentaatiossa, ks. kartanluontiohje.txt
     * 
     */
    public Peli luoPeli(String pelaajanNimi, String pelaajanLuokka, String kartanNimi) {
        Pelaaja pelaaja = luoPelaaja(pelaajanNimi, pelaajanLuokka);
        
        Scanner lukija = Tiedostoapuri.tiedostoLukijaan("src/kartat/" + kartanNimi);
        Kartta kartta = luoKartta(lukija);
        
        // poistetaan karttadatan tyhjä rivi (se on siellä ihan selkeyden vuoksi!)
        System.out.println(lukija.nextLine());
        
        // pelaaja kartan alkuun
        pelaaja.setSijainti(kartta.getAloituspala());
        kartta.getAloituspala().pelaajaSaapuu();
        
        Peli peli = new Peli(kartta, pelaaja);
        
        luoMonsterit(lukija, peli, kartta);
        
        return peli;
    }
    
    private Kartta luoKartta(Scanner lukija) {
        
        int x = Integer.parseInt(lukija.nextLine());
        int y = Integer.parseInt(lukija.nextLine());
        int[][] kartta = new int[y][x];
        
        for(int rivi = 0; rivi < y; rivi++) {
            String riviStr = lukija.nextLine();
            for(int sarake = 0; sarake < x; sarake++) {
                if(riviStr.charAt(sarake) == '1') {
                    kartta[rivi][sarake] = 1;
                }
                else {
                    kartta[rivi][sarake] = 0;
                }
            }
        }
        
        return new Kartta(kartta);
    }
    
    private Pelaaja luoPelaaja(String nimi, String luokka) {
        int voima = 0;
        int energia = 0;
        
        if(luokka.equals("Taikamaagi")) {
            voima = 2;
            energia = 2;
        }
        else if(luokka.equals("Konna")) {
            voima = 8;
            energia = 2;
        }
        else if(luokka.equals("Kekkeruusi")) {
            voima = 100;
            energia = 100;
        }
        else {
            voima = 5;
            energia = 5;
        }
        
        return new Pelaaja(nimi, voima, energia, luokka);
    }
    
    private void luoMonsterit(Scanner lukija, Peli peli, Kartta kartta) {
        Karttapala[][] palat = kartta.getKarttapalat();
        for(int y = 0; y < palat.length; y++) {
            String riviStr = lukija.nextLine();
            for(int x = 0; x < palat[0].length; x++) {
                if(riviStr.charAt(x) == '1') {
                    Monsteri m = new Monsteri(5, 1);
                    Karttapala pala = palat[y][x];
                    peli.lisaaMonsteri(m, pala);
                }
            }
        }
    }
}
