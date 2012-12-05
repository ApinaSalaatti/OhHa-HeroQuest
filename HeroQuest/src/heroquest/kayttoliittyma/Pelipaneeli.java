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
 * Pelin eri näkymien näyttämistä säätelevä komponentti.
 * 
 * @author Merioksan Mikko
 */
public class Pelipaneeli extends JPanel {
    private Kotipaneeli koti;
    private Karttapaneeli kartta;
    private JPanel nakymaPanel;
    private CardLayout nakymaLayout;
    private Tietopaneeli tiedot;
    private CardLayout layout;
    private Container container;
    private PeliController controller;
    
    public Pelipaneeli(Kotipaneeli koti, Karttapaneeli k, Tietopaneeli t, CardLayout layout, Container container, PeliController pc) {
        this.koti = koti;
        this.kartta = k;
        this.tiedot = t;
        this.layout = layout;
        this.container = container;
        this.controller = pc;
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        this.setLayout(new GridLayout(1, 2));
        
        nakymaLayout = new CardLayout(1, 2);
        nakymaPanel = new JPanel(nakymaLayout);
        nakymaPanel.add(koti, "koti");
        nakymaPanel.add(kartta, "luolasto");

        
        this.add(nakymaPanel);
        this.add(tiedot);
    }
    
    public void paivita(String tapahtuma) {
        if(controller.getTila().equals("koti")) {
            nakymaLayout.show(nakymaPanel, "koti");
        }
        else {
            nakymaLayout.show(nakymaPanel, "luolasto");
        }
        
        if(controller.getTila().equals("voitto") || controller.getTila().equals("kuolema")) {
            layout.show(container, "lopetus");
        }
        
        koti.paivita();
        if(!controller.getTila().equals("koti")) {
            kartta.piirraKartta();
        }
        tiedot.paivitaTiedot(tapahtuma);
    }
}
