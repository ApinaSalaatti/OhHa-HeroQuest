/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest;

import java.util.ArrayList;

import heroquest.domain.Kartta;
import heroquest.domain.Ilmansuunta;
import heroquest.domain.Pelaaja;
import heroquest.domain.Monsteri;
/**
 *
 * @author merioksa
 */
public class Peli {
    private Kartta kartta;
    private Pelaaja pelaaja;
    private ArrayList<Monsteri> monsterit;
    
    public Peli(Kartta k, Pelaaja p) {
        kartta = k;
        pelaaja = p;
    }
    
    public Kartta getKartta() {
        return kartta;
    }
    
    public Pelaaja getPelaaja() {
        return pelaaja;
    }
    
    public void pelaajanLiike(Ilmansuunta suunta) {
        pelaaja.liiku(suunta);
    }
    
    private void monsterienLiike() {
        for(Monsteri m : monsterit) {
            m.liiku();
        }
    }
    
    public void lopetaVuoro() {
        monsterienLiike();
    }
}
