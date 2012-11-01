/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author merioksa
 */
public class Kartta {
    private ArrayList<Karttapala> palat;
    private Random random;
    
    public Kartta(int koko) {
        random = new Random();
        palat = new ArrayList<>();
        luoPalat(koko);
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
            int suunta = random.nextInt(4);
            
        }
    }
    
    public Karttapala getPalanNaapuri(Karttapala pala, Ilmansuunta suunta) {
        return pala.getNaapuri(suunta);
    }
}
