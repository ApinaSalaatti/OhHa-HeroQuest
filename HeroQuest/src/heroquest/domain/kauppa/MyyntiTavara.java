/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.domain.kauppa;

/**
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
    
    public Tavara getTavara() {
        return tavara;
    }
    
    @Override
    public String toString() {
        return tavara.getNimi() + " (" + tavara.getArvo() * hintakerroin + " kultaduploonia)";
    }
}
