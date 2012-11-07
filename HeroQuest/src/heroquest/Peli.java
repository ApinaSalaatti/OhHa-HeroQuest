/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest;

import java.util.ArrayList;

import heroquest.domain.Kartta;
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
}
