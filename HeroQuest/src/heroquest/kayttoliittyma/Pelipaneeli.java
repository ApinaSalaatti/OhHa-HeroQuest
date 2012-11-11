/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import javax.swing.JPanel;
import java.awt.GridLayout;

import heroquest.Peli;
/**
 *
 * @author Merioksan Mikko
 */
public class Pelipaneeli extends JPanel {
    private Peli peli;
    private Karttapaneeli kartta;
    private Tietopaneeli tiedot;
    
    public Pelipaneeli(Karttapaneeli k, Tietopaneeli t) {
        this.kartta = k;
        this.tiedot = t;
        luoKomponentit();
    }
    
    public void setPeli(Peli p) {
        this.peli = p;
        kartta.setPeli(peli);
        tiedot.setPeli(peli);
    }
    
    private void luoKomponentit() {
        this.tiedot.setPelipaneeli(this);
        this.setLayout(new GridLayout(1, 2));
        this.add(kartta);
        this.add(tiedot);
    }
    
    public void paivita() {
        kartta.piirraKartta();
        tiedot.paivitaTiedot();
    }
}
