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
        
        // pohjoiseen
        int kohta = y;
        while(kohta >= 0 && kartta[kohta][x] != null) {
            nahty[kohta][x] = kartta[kohta][x];
            kohta--;
        }
        // itään
        kohta = x;
        while(kohta < kartta[0].length && kartta[y][kohta] != null) {
            nahty[y][kohta] = kartta[y][kohta];
            kohta++;
        }
        // etelään
        kohta = y;
        while(kohta < kartta.length && kartta[kohta][x] != null) {
            nahty[kohta][x] = kartta[kohta][x];
            kohta++;
        }
        // länteen
        kohta = x;
        while(kohta >= 0 && kartta[y][kohta] != null) {
            nahty[y][kohta] = kartta[y][kohta];
            kohta--;
        }
    }
}
