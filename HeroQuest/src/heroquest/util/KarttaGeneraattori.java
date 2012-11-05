/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.util;

import java.util.ArrayList;
import java.util.Random;

import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
import heroquest.domain.Ilmansuunta;
/**
 *
 * @author Merioksan Mikko
 */
public class KarttaGeneraattori {
    private ArrayList<Karttapala> palat;
    private Random random;
    
    public KarttaGeneraattori() {
        palat = new ArrayList<Karttapala>();
        random = new Random();
    }
    
    public ArrayList<Karttapala> luoKartta(int koko) {
        luoPalat(koko);
        return palat;
    }
    
    public void luoPalat(int koko) {
        for(int i = 0; i < koko; i++) {
            Karttapala uusi = new Karttapala();
            lisaaKarttaan(uusi);
            palat.add(uusi);
        }
    }
    
    public void lisaaKarttaan(Karttapala uusi) {
        if(!palat.isEmpty()) {
            Karttapala nykyinen = palat.get(0);
            Karttapala edellinen = null;
            Ilmansuunta suunta = null;
            
            while(nykyinen != null) {
                edellinen = nykyinen;
                suunta = Ilmansuunta.satunnainen();
                nykyinen = nykyinen.getNaapuri(suunta);
            }
            
            edellinen.setNaapuri(uusi, suunta);
            uusi.setNaapuri(uusi, suunta.vastakohta());
            int uusiX = edellinen.getX() + suunta.xMuutos();
            int uusiY = edellinen.getY() + suunta.yMuutos();
            uusi.setSijainti(uusiX, uusiY);
        }
        else {
            uusi.setSijainti(0, 0);
        }
    }
}
