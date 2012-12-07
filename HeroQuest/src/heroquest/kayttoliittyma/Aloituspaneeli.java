/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.kayttoliittyma;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import heroquest.PeliController;
import heroquest.util.Tiedostoapuri;
/**
 * Pelin alkuvalikko.
 * 
 * @author Merioksan Mikko
 */
public class Aloituspaneeli extends JPanel {
    /**
     * Container jossa paneeli sijaitsee. CardLayoutin näkymän vaihtamista varten.
     */
    private Container container;
    /**
     * CardLayout, joka sisältää pelin eri näkymät.
     */
    private CardLayout nakyma;
    /**
     * PeliController-olio, jota kutsutaan peliä aloitettaessa.
     */
    private PeliController controller;
    
    public Aloituspaneeli(JFrame frame, CardLayout layout, PeliController pc) {
        this.container = frame.getContentPane();
        this.nakyma = layout;
        this.controller = pc;
        luoKomponentit(frame);
    }
    
    /**
     * Luodaan näkymän komponentit.
     * 
     * @param frame peli-ikkunan frame-olio
     */
    private void luoKomponentit(JFrame frame) {
        this.setLayout(new GridLayout(4, 1));
        this.setBackground(Color.WHITE);
        
        JLabel logo = new JLabel(new ImageIcon("kuvat/logo.png"));
        
        JButton uusiPeli = luoUusiPeliNappi(frame);
        JPanel uusiPeliPanel = new JPanel();
        uusiPeliPanel.setBackground(Color.WHITE);
        uusiPeliPanel.add(uusiPeli);
        
        JButton lataaPeli = luoLataaPeliNappi(frame);
        JPanel lataaPeliPanel = new JPanel();
        lataaPeliPanel.setBackground(Color.WHITE);
        lataaPeliPanel.add(lataaPeli);
        
        JButton poistu = luoPoistuNappi();
        JPanel poistuPanel = new JPanel();
        poistuPanel.setBackground(Color.WHITE);
        poistuPanel.add(poistu);
        
        this.add(logo);
        this.add(uusiPeliPanel);
        this.add(lataaPeliPanel);
        this.add(poistuPanel);
    }
    
    /**
     * Luodaan näppäin uuden pelin aloitamiseen.
     * 
     * @param frame pelin ikkuna
     * @return valmis "Uusi peli"-näppäin
     */
    public JButton luoUusiPeliNappi(final JFrame frame) {
        JButton uusiPeli = new JButton(new ImageIcon("kuvat/uusiPeli.png"));
        
        uusiPeli.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String viesti = "Tervetuloa Luolastokuningas II:n maailmaan, oi jalo sankari!\n";
               viesti += "On kulunut 15 vuotta siitä, kun isäsi voitti ensimmäisen Luolastokuningas-turnauksen. Tänä vuonna on vihdoin sinun aikasi loistaa!\n";
               viesti += "Tehtävänäsi on löytää kaikki Wolframivuoren kätköihin piilotetut aarteet ja tulla isäsi tavoin kruunatuksi...\n\n";
               viesti += "LUOLASTOKUNINKAAKSI!";
               JOptionPane.showMessageDialog(frame, viesti);
               nakyma.show(container, "luonti");
           }
        });
        
        uusiPeli.setContentAreaFilled(false);
        uusiPeli.setBorderPainted(false);
        uusiPeli.setFocusPainted(false);
        
        return uusiPeli;
    }
    
    /**
     * Luodaan uusi pelin lataamisen mahdollistava nappi.
     * 
     * @param frame peli-ikkuna
     * @return valmis nappi
     */
    public JButton luoLataaPeliNappi(final JFrame frame) {
        JButton lataaPeli = new JButton(new ImageIcon("kuvat/lataaPeli.png"));
        
        lataaPeli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] tallennukset = Tiedostoapuri.kansioTauluksi("tallennukset/");
                String tiedostonimi = (String)JOptionPane.showInputDialog(
                    frame,
                    "Valitse tallennus",
                    "Lataa peli",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    tallennukset,
                    null
                );
                if(tiedostonimi != null && tiedostonimi.length() > 0) {
                    try {
                        controller.lataa(tiedostonimi);
                        nakyma.show(container, "peli");
                    }
                    catch(Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Tallennuksen lataaminen epäonnistui. Yritä uudelleen!");
                    }
                }
            }
        });
        
        lataaPeli.setContentAreaFilled(false);
        lataaPeli.setBorderPainted(false);
        lataaPeli.setFocusPainted(false);
        return lataaPeli;
    }
    
    /**
     * Luodaan uusi pelistä poistumis -nappi.
     * @return valmis nappi
     */
    public JButton luoPoistuNappi() {
        JButton poistu = new JButton(new ImageIcon("kuvat/poistu.png"));
        
        poistu.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.exit(0);
           }
        });
        
        poistu.setContentAreaFilled(false);
        poistu.setBorderPainted(false);
        poistu.setFocusPainted(false);
        
        return poistu;
    }
}
