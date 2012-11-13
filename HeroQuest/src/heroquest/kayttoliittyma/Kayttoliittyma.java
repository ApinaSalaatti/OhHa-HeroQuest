/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
import heroquest.domain.Pelaaja;
import heroquest.PeliController;
/**
 *
 * @author Merioksan Mikko
 */
public class Kayttoliittyma implements Runnable {
    // valmis kartta testitarkoituksiin
    private static int[][] testiKartta = {  {0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 1, 1, 1, 1, 1, 1, 0},
                                            {0, 1, 0, 0, 0, 0, 0, 0},
                                            {0, 1, 1, 1, 0, 0, 0, 0},
                                            {0, 1, 0, 1, 0, 0, 0, 0},
                                            {0, 1, 0, 1, 1, 1, 1, 0},
                                            {0, 1, 1, 1, 0, 0, 1, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0}};
    private Kartta kartta;
    private Pelaaja pelaaja;
    private JFrame frame;
    private PeliController controller;
    private Pelipaneeli peliPanel;
    
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
        CardLayout layout = new CardLayout(1, 2);
        container.setLayout(layout);
        
        Aloituspaneeli aloitusPanel = new Aloituspaneeli(container, layout, controller);
        Karttapaneeli karttaPanel = new Karttapaneeli(controller);
        Tietopaneeli tietoPanel = new Tietopaneeli(controller);
        peliPanel = new Pelipaneeli(karttaPanel, tietoPanel, controller);

        container.add(aloitusPanel, "aloitus");
        container.add(peliPanel, "peli");
    }
    
    public void paivita(String tapahtuma) {
        peliPanel.paivita(tapahtuma);
    }
}
