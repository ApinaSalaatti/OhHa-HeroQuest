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
    private Random random;
    
    // luodaan satunnainen "koko" kokoinen kartta
    public Kartta(int koko) {
        kartta = new Karttapala[koko][koko];
        random = new Random();
        
        KarttaGeneraattori generaattori = new KarttaGeneraattori();
        generaattori.luoKartta(koko);
        kartta = generaattori.getKartta();
    }
    // luodaan kartta valmiina annetun taulukon pohjalta
    public Kartta(int[][] lahde) {
        kartta = new Karttapala[lahde.length][lahde[0].length];
        random = new Random();
        KarttaGeneraattori generaattori = new KarttaGeneraattori();
        generaattori.luoKartta(lahde);
        kartta = generaattori.getKartta();
    }
    
    public Karttapala getAloituspala() {
        return kartta[1][1];
    }
    
    public Karttapala[][] getKartta() {
        return kartta;
    }
    
    public Karttapala getPalanNaapuri(Karttapala pala, Ilmansuunta suunta) {
        return pala.getNaapuri(suunta);
    }
}
