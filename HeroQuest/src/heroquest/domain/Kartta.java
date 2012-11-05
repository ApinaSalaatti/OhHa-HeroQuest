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
    private ArrayList<Karttapala> palat;
    private Random random;
    
    public Kartta(int koko) {
        random = new Random();
        palat = new ArrayList<Karttapala>();
        KarttaGeneraattori generaattori = new KarttaGeneraattori();
        palat = generaattori.luoKartta(koko);
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
    
    public Karttapala getAloituspala() {
        return palat.get(0);
    }
    
    public Karttapala getPalanNaapuri(Karttapala pala, Ilmansuunta suunta) {
        return pala.getNaapuri(suunta);
    }
    
    public void tulosta() {
        for(int y = -palat.size()+1; y < palat.size(); y++) {
            boolean piirretty = false;
            for(int x = -palat.size()+1; x < palat.size(); x++) {
                for(Karttapala k : palat) {
                    if(k.getX() == x && k.getY() == y) {
                        System.out.print("#");
                        piirretty = true;
                    }
                }
                if(!piirretty) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}