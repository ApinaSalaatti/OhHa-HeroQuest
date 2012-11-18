/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import java.util.ArrayList;
import java.util.Random;

import heroquest.util.KarttaGeneraattori;
/**
 * Luokka, johon on kapseloitu yksittäiset karttapalat sisältävä kaksiulotteinen taulukko.
 * Sisältää myös kartan käsittelyyn käytettäviä metodeja.
 * 
 * @author Merioksan Mikko
 */
public class Kartta {
    /**
     * Kaikki karttaan liittyvät karttapalat taulukossa.
     */
    private Karttapala[][] kartta;
    /**
     * Pelaajan löytämät karttapalat. Nämä määritellään yksinkertaisen line of sightin perusteella.
     */
    private Karttapala[][] nahty;
    
    public Kartta(int[][] lahde) {
        kartta = new Karttapala[lahde.length][lahde[0].length];
        
        KarttaGeneraattori generaattori = new KarttaGeneraattori();
        generaattori.luoKartta(lahde);
        kartta = generaattori.getKartta();
        nahty = new Karttapala[kartta.length][kartta[0].length];
    }
    
    /**
     * @return pala josta peli aloitetaan. Aina vasen ylänurkka.
     */
    public Karttapala getAloituspala() {
        return kartta[1][1];
    }
    
    /**
     * @return kartta taulukkona
     */
    public Karttapala[][] getKarttapalat() {
        return kartta;
    }
    
    /**
     * @return pelaajan löytämät palat taulukkona
     */
    public Karttapala[][] getNahdytPalat() {
        return nahty;
    }
    
    /**
     * Päivitetään pelaajan näkökenttä annetusta palasta lähtien.
     * Line of sight on hyvin yksinkertainen, pelaaja vain näkee kaikki ruudut suorassa linjassa seuraavaan seinään tai kartan reunaan asti.
     * 
     * @param pala pala, josta line of sight lasketaan, yleensä pelaajan sijainti
     */
    public void paivitaNahdyt(Karttapala pala) {
        int y = pala.getY();
        int x = pala.getX();
        
        asetaNakyvaksi(x, y, 0, -1);
        asetaNakyvaksi(x, y, 0, 1);
        asetaNakyvaksi(x, y, -1, 0);
        asetaNakyvaksi(x, y, 1, 0);
    }
   /**
    * Asetetaan annetuissa koordinaateissa oleva pala näkyväksi ja siirrytään rekursiivisesti seuraavaan.
    * Palataan kun törmätään seinään tai syöksytään ulos kartan reunalta.
    * 
    * @param x näkyväksi asetettavan palan x-koordinaatti
    * @param y näkyväksi asetettavan palan y-koordinaatti
    * @param seurX suunta, johon x-akselilla liikutaan annetuista koordinaateista
    * @param seurY suunta, johon y-akselilla liikutaan annetuista koordinaateista
    */
    public void asetaNakyvaksi(int x, int y, int seurX, int seurY) {
        if(x < 0 || x >= kartta[0].length || y < 0 || y >= kartta.length || kartta[y][x] == null) {
            return;
        }
        nahty[y][x] = kartta[y][x];
        asetaNakyvaksi(x+seurX, y+seurY, seurX, seurY);
    }
}
