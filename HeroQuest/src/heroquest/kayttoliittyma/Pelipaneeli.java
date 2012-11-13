/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import javax.swing.JPanel;
import java.awt.GridLayout;

import heroquest.PeliController;
import heroquest.PeliController;
/**
 *
 * @author Merioksan Mikko
 */
public class Pelipaneeli extends JPanel {
    private Karttapaneeli kartta;
    private Tietopaneeli tiedot;
    private PeliController controller;
    
    public Pelipaneeli(Karttapaneeli k, Tietopaneeli t, PeliController pc) {
        this.kartta = k;
        this.tiedot = t;
        this.controller = pc;
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        this.setLayout(new GridLayout(1, 2));
        this.add(kartta);
        this.add(tiedot);
    }
    
    public void paivita(String tapahtuma) {
        kartta.piirraKartta();
        tiedot.paivitaTiedot(tapahtuma);
    }
}
