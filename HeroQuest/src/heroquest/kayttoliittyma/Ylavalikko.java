/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Container;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import heroquest.PeliController;
import heroquest.util.Tiedostoapuri;
/**
 * Ikkunan yläreunaa koristava valikko, josta pääsee nopeasti käsiksi pelin lataus- ja tallennus-ominaisuuksiin.
 * 
 * @author Merioksan Mikko
 */
public class Ylavalikko extends JMenuBar {
    private Container container;
    private CardLayout nakyma;
    private PeliController controller;
    
    public Ylavalikko(JFrame frame, CardLayout layout, PeliController controller) {
        this.container = frame.getContentPane();
        this.nakyma = layout;
        this.controller = controller;
        luoKomponentit(frame);
    }
    
    /**
     * Luodaan näkymän komponentit.
     * 
     * @param frame JFrame, jonka yläreunaan valikko laitetaan
     */
    private void luoKomponentit(final JFrame frame) {
        JMenu tiedostoValikko = new JMenu("Tiedosto");
        
        JMenuItem uusiPeli = luoUusiPeliValinta(frame);
        JMenuItem tallenna = luoTallennaValinta(frame);
        JMenuItem lataa = luoLataaValinta(frame);
        JMenuItem poistu = luoPoistuValinta(frame);
        
        tiedostoValikko.add(uusiPeli);
        tiedostoValikko.add(tallenna);
        tiedostoValikko.add(lataa);
        tiedostoValikko.addSeparator();
        tiedostoValikko.add(poistu);
        this.add(tiedostoValikko);
    }
    
    public JMenuItem luoPoistuValinta(final JFrame frame) {
        JMenuItem poistu = new JMenuItem("Poistu");
        poistu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        return poistu;
    }
    
    public JMenuItem luoUusiPeliValinta(final JFrame frame) {
        JMenuItem uusiPeli = new JMenuItem("Uusi peli");
        uusiPeli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nakyma.show(container, "luonti");
            }
        });
        
        return uusiPeli;
    }
    
    public JMenuItem luoLataaValinta(final JFrame frame) {
        JMenuItem lataa = new JMenuItem("Lataa peli");
        lataa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] tallennukset = Tiedostoapuri.kansioTauluksi("../tallennukset/");
                String tiedostonimi = (String)JOptionPane.showInputDialog(
                    frame,
                    "Valitse tallennus",
                    "Lataa peli",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    tallennukset,
                    "tallennus"
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
        
        return lataa;
    }
    
    public JMenuItem luoTallennaValinta(final JFrame frame) {
        JMenuItem tallenna = new JMenuItem("Tallenna peli");
        tallenna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tiedostonimi = (String)JOptionPane.showInputDialog(
                    frame,
                    "Anna tallennuksen nimi",
                    "Tallenna peli",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "tallennus"
                );
                if(controller.getPeli() == null) {
                    JOptionPane.showMessageDialog(frame, "Et ole edes aloittanut peliä vielä!");
                }
                else if(tiedostonimi == null || tiedostonimi.length() <= 0) {
                    JOptionPane.showMessageDialog(frame, "Et antanut nimeä tallennukselle!");
                }
                else {
                    controller.tallenna(tiedostonimi);
                }
            }
        });
        
        return tallenna;
    }
}
