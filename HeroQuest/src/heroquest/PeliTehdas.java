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
 * Tehdasluokka joka luo pelin, pelaajan ja kartan annettujen parametrien pohjalta.
 * 
 * @author Merioksan Mikko
 * 
 */
public class PeliTehdas {
    public PeliTehdas() {
    }
    
    /**
     * Luodaan peli annetuilla parametreilla.
     * kartanNimi on .hqm-tiedosto josta karttadata luetaan
     * .hqm-tiedoston formaatti on selitetty dokumentaatiossa, ks. kartanluontiohje.txt
     * 
     * @param pelaajanNimi pelaajan hahmon nimi
     * @param pelaajanLuokka pelaajan hahmon hahmoluokka
     * @param kartanNimi valitun kartan nimi
     * @return rakennettu Peli-luokan olio
     */
    public Peli luoPeli(String pelaajanNimi, String pelaajanLuokka, String kartanNimi) {
        Pelaaja pelaaja = luoPelaaja(pelaajanNimi, pelaajanLuokka);
        
        Scanner lukija = Tiedostoapuri.tiedostoLukijaan("src/kartat/" + kartanNimi);
        Kartta kartta = luoKartta(lukija);
        
        // poistetaan karttadatan tyhjä rivi (se on siellä ihan selkeyden vuoksi!)
        lukija.nextLine();
        
        // pelaaja kartan alkuun
        pelaaja.setSijainti(kartta.getAloituspala());
        kartta.getAloituspala().pelaajaSaapuu();
        
        Peli peli = new Peli(kartta, pelaaja);
        
        luoMonsterit(lukija, peli, kartta);
        
        // poistetaan karttadatan tyhjä rivi (se on siellä ihan selkeyden vuoksi!)
        lukija.nextLine();
        
        sijoitaAarteet(lukija, peli, kartta);
        
        return peli;
    }
    
    /**
     * Metodi, jossa luetaan kartta Scannerista int[][] taulukkoon ja lähetetään se sitten Kartta-oliolle uuden kartan pohjaksi
     * 
     * @param lukija lukija joka sisältää karttadatan
     * @return rakennettu Kartta-luokan olio
     */
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
    
    /**
     * Metodi, joka luo Pelaaja-luokan olion annettujen parametrien perusteella. 
     * 
     * @param nimi hahmon nimi
     * @param luokka hahmon hahmoluokka
     * @return rakennettu Pelaaja-luokan olio
     */
    private Pelaaja luoPelaaja(String nimi, String luokka) {
        int voima = 0;
        int energia = 0;
        int nopeus = 0;
        
        if(luokka.equals("Taikamaagi")) {
            voima = 2;
            energia = 2;
            nopeus = 2;
        }
        else if(luokka.equals("Konna")) {
            voima = 8;
            energia = 2;
            nopeus = 4;
        }
        else if(luokka.equals("Kekkeruusi")) {
            voima = 100;
            energia = 100;
            nopeus = 100;
        }
        else {
            voima = 5;
            energia = 5;
            nopeus = 1;
        }
        
        return new Pelaaja(nimi, voima, energia, nopeus, luokka);
    }
    
    /**
     * Metodi, joka lisää monsterit kartalle annetun Scanner-olion sisältämän karttadatan perusteella.
     * 
     * @param lukija karttadatan sisältävä Scanner-olio
     * @param peli Peli-olio johon monsterit lisätään
     * @param kartta Kartta-olio johon monsterit lisätään.
     */
    private void luoMonsterit(Scanner lukija, Peli peli, Kartta kartta) {
        Karttapala[][] palat = kartta.getKarttapalat();
        for(int y = 0; y < palat.length; y++) {
            String riviStr = lukija.nextLine();
            for(int x = 0; x < palat[0].length; x++) {
                if(riviStr.charAt(x) == '1') {
                    Monsteri m = new Monsteri(5, 1, 1);
                    Karttapala pala = palat[y][x];
                    peli.lisaaMonsteri(m, pala);
                }
            }
        }
    }
    
    /**
     * Sijoitetaan arvokkaat aarteet kartalle annetun Scanner-olion sisältämän karttadatan perusteella.
     * 
     * @param lukija Scanner-olio jossa karttadata
     * @param peli Peli johon aarteet lisätään
     * @param kartta Kartta johon aarteet lisätään
     */
    private void sijoitaAarteet(Scanner lukija, Peli peli, Kartta kartta) {
        Karttapala[][] palat = kartta.getKarttapalat();
        for(int y = 0; y < palat.length; y++) {
            String riviStr = lukija.nextLine();
            for(int x = 0; x < palat[0].length; x++) {
                if(riviStr.charAt(x) == '1') {
                    palat[y][x].asetaAarre();
                }
            }
        }
    }
}
