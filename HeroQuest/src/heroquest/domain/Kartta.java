/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import java.util.ArrayList;

/**
 *
 * @author merioksa
 */
public class Kartta {
    private ArrayList<Karttapala> palat;
    
    public Kartta(int koko) {
        palat = new ArrayList<>();
        luoPalat(koko);
    }
    
    public void luoPalat(int koko) {
        for(int i = 0; i < koko; i++) {
            palat.add(new Karttapala());
        }
    }
    
    public Karttapala getPalanNaapuri(Karttapala pala, Ilmansuunta suunta) {
        return pala.getNaapuri(suunta);
    }
}
