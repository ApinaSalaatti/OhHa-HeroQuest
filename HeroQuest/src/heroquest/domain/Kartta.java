/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import java.util.ArrayList;
import java.util.Random;

import heroquest.util.KarttaGeneraattori;
/**
 *
 * @author merioksa
 */
public class Kartta {
    private Karttapala[][] kartta;
    private Karttapala[][] nahty;
    private Random random;
    
    // luodaan kartta valmiina annetun taulukon pohjalta
    public Kartta(int[][] lahde) {
        kartta = new Karttapala[lahde.length][lahde[0].length];
        random = new Random();
        
        KarttaGeneraattori generaattori = new KarttaGeneraattori();
        generaattori.luoKartta(lahde);
        kartta = generaattori.getKartta();
        nahty = new Karttapala[kartta.length][kartta[0].length];
    }
    
    public Karttapala getAloituspala() {
        return kartta[1][1];
    }
    
    public Karttapala[][] getKarttapalat() {
        return kartta;
    }
    
    public Karttapala[][] getNahdytPalat() {
        return nahty;
    }
    
    // päivitetään näkökenttä argumenttina annetusta palasta lähtien
    public void paivitaNahdyt(Karttapala pala) {
        int y = pala.getY();
        int x = pala.getX();
        
        asetaNakyvaksi(x, y, 0, -1);
        asetaNakyvaksi(x, y, 0, 1);
        asetaNakyvaksi(x, y, -1, 0);
        asetaNakyvaksi(x, y, 1, 0);
    }
    
    // asetetaan annetuissa koordinaateissa oleva pala näkyväksi ja siirrytään rekursiivisesti seuraavaan
    // kunnes törmätään seinään tai syöksytään ulos kartan reunalta
    public void asetaNakyvaksi(int x, int y, int seurX, int seurY) {
        if(x < 0 || x >= kartta[0].length || y < 0 || y >= kartta.length || kartta[y][x] == null) {
            return;
        }
        nahty[y][x] = kartta[y][x];
        asetaNakyvaksi(x+seurX, y+seurY, seurX, seurY);
    }
}
