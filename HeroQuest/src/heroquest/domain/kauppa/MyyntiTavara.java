/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.domain.kauppa;

/**
 * Myytäviä tavaroita kuvaava luokka. Luokka säilyttää perus-tavaran ja kertoimen, jolla tavaran hintaa tulee nostaa.
 * 
 * @author Merioksan Mikko
 */
public class MyyntiTavara {
    private Tavara tavara;
    private double hintakerroin;
    
    public MyyntiTavara(Tavara t, double h) {
        this.tavara = t;
        this.hintakerroin = h;
    }
    
    /**
     * Palautetaan olioon liittyvä tavara.
     * 
     * @return myytävä tavara
     */
    public Tavara getTavara() {
        return tavara;
    }
    
    @Override
    public String toString() {
        return tavara.getNimi() + " (" + tavara.getArvo() * hintakerroin + " kultaduploonia)";
    }
}
