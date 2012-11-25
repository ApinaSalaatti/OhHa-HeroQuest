/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.domain;

/**
 * Karttapalaluokan aliluokka, joka asetetaan pelaajan sijainniksi jos tämä on kotona eikä luolastossa.
 * 
 * @author Merioksan Mikko
 */
public class Kotikarttapala extends Karttapala {
    
    public Kotikarttapala() {
        super();
    }
    
    @Override
    public String toString() {
        return "Oma koti kullan kallis\n";
    }
}
