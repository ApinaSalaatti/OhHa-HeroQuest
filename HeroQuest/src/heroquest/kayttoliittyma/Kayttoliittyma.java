/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;

import heroquest.PeliController;
/**
 *
 * @author Merioksan Mikko
 */
public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private PeliController controller;
    private Pelipaneeli peliPanel;
    private Lopetuspaneeli lopetusPanel;
    
    public Kayttoliittyma() {
    }
    
    public void setController(PeliController p) {
        this.controller = p;
    }
    
    @Override
    public void run() {
        frame = new JFrame("HeroQuest by Merioksan Mikko!");
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container container) {
        CardLayout layout = new CardLayout(1, 3);
        container.setLayout(layout);
        
        Ylavalikko ylavalikko = new Ylavalikko(container, layout, controller);
        frame.setJMenuBar(ylavalikko);
        
        Aloituspaneeli aloitusPanel = new Aloituspaneeli(container, layout, controller);
        lopetusPanel = new Lopetuspaneeli(controller);
        Karttapaneeli karttaPanel = new Karttapaneeli(controller);
        Tietopaneeli tietoPanel = new Tietopaneeli(controller);
        peliPanel = new Pelipaneeli(karttaPanel, tietoPanel, layout, container, controller);

        container.add(aloitusPanel, "aloitus");
        container.add(peliPanel, "peli");
        container.add(lopetusPanel, "lopetus");
    }
    
    public void paivita(String tapahtuma) {
        lopetusPanel.paivita();
        peliPanel.paivita(tapahtuma);
    }
}
