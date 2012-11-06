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
    
    public Kartta(int koko) {
        kartta = new Karttapala[koko][koko];
        random = new Random();
        luoKartta(koko);
    }
    
    private void luoKartta(int koko) {
        KarttaGeneraattori generaattori = new KarttaGeneraattori();
        generaattori.luoKartta(koko);
        kartta = generaattori.getKartta();
    }
    
    public Karttapala getAloituspala() {
        return kartta[0][0];
    }
    
    public Karttapala[][] getKartta() {
        return kartta;
    }
    
    public Karttapala getPalanNaapuri(Karttapala pala, Ilmansuunta suunta) {
        return pala.getNaapuri(suunta);
    }
    
    public void tulosta() {
        for(int y = 0; y < kartta.length; y++) {
            for(int x = 0; x < kartta[0].length; x++) {
                if(kartta[y][x] == null) {
                    System.out.print(" ");
                }
                else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }
}
