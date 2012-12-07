/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;

import heroquest.PeliController;
/**
 * Graafisen käyttöliittymän pääkomponentti. Luo ikkunan, jonka sisään kaikki pelin komponentit luodaan.
 * 
 * @author Merioksan Mikko
 */
public class Kayttoliittyma implements Runnable {
    /**
     * Pääikkuna
     */
    private JFrame frame;
    /**
     * PeliController, jolta käyttöliittymäne monet näkymät tarvitsevat tietoja.
     */
    private PeliController controller;
    /**
     * Paneeli joka näyttää varsinaiset pelin tapahtumat.
     */
    private Pelipaneeli peliPanel;
    /**
     * Paneeli joka näytetään kun pelaaja kuolee.
     */
    private Lopetuspaneeli lopetusPanel;
    
    public Kayttoliittyma() {
    }
    
    /**
     * Asetetaan käyttöliittymälle kontrolleri-olio.
     * 
     * @param p käytettävä PeliController
     */
    public void setController(PeliController p) {
        this.controller = p;
    }
    
    @Override
    public void run() {
        frame = new JFrame("Luolastokuningas II");
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Luodaan näkymän komponentit.
     * 
     * @param container ikkunan Container, johon näkymät luodaan
     */
    private void luoKomponentit(Container container) {
        CardLayout layout = new CardLayout(1, 5);
        container.setLayout(layout);
        
        Ylavalikko ylavalikko = new Ylavalikko(frame, layout, controller);
        frame.setJMenuBar(ylavalikko);
        
        Aloituspaneeli aloitusPanel = new Aloituspaneeli(frame, layout, controller);
        Luontipaneeli luontiPanel = new Luontipaneeli(container, layout, controller);
        lopetusPanel = new Lopetuspaneeli(controller);
        Kotipaneeli kotiPanel = new Kotipaneeli(controller);
        Karttapaneeli karttaPanel = new Karttapaneeli(controller);
        Tietopaneeli tietoPanel = new Tietopaneeli(controller);
        peliPanel = new Pelipaneeli(kotiPanel, karttaPanel, tietoPanel, layout, container, controller);

        container.add(aloitusPanel, "aloitus");
        container.add(luontiPanel, "luonti");
        container.add(peliPanel, "peli");
        container.add(lopetusPanel, "lopetus");
    }
    
    /**
     * Päivitetään käyttöliittymä, eli kaikki sen näkymät.
     * 
     * @param tapahtuma viimeisimpiä tapahtumia kuvaava merkkijono
     */
    public void paivita(String tapahtuma) {
        lopetusPanel.paivita();
        peliPanel.paivita(tapahtuma);
    }
}
