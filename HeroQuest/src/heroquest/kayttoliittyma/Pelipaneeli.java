/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Container;

import heroquest.PeliController;
import heroquest.PeliController;
import heroquest.domain.Ilmansuunta;
/**
 *
 * @author Merioksan Mikko
 */
public class Pelipaneeli extends JPanel {
    private Karttapaneeli kartta;
    private Tietopaneeli tiedot;
    private CardLayout layout;
    private Container container;
    private PeliController controller;
    
    public Pelipaneeli(Karttapaneeli k, Tietopaneeli t, CardLayout layout, Container container, PeliController pc) {
        this.kartta = k;
        this.tiedot = t;
        this.layout = layout;
        this.container = container;
        this.controller = pc;
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        this.setLayout(new GridLayout(1, 2));
        this.add(kartta);
        this.add(tiedot);
    }
    
    public void paivita(String tapahtuma) {
        if(controller.getTila().equals("voitto") || controller.getTila().equals("kuolema")) {
            layout.show(container, "lopetus");
        }
        kartta.piirraKartta();
        tiedot.paivitaTiedot(tapahtuma);
    }
}
