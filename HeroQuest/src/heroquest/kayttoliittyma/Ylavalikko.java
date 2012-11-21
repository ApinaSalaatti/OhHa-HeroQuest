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
    
    private void luoKomponentit(final JFrame frame) {
        JMenu tiedostoValikko = new JMenu("Tiedosto");
        
        JMenuItem uusiPeli = new JMenuItem("Uusi peli");
        JMenuItem tallenna = new JMenuItem("Tallenna");
        JMenuItem lataa = new JMenuItem("Lataa tallennus");
        JMenuItem poistu = new JMenuItem("Poistu");
        
        uusiPeli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nakyma.show(container, "aloitus");
            }
        });
        
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
                if(controller.getPeli() != null && tiedostonimi != null && tiedostonimi.length() > 0) {
                    controller.tallenna(tiedostonimi);
                }
            }
        });
        
        lataa.addActionListener(new ActionListener() {
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
                    "tallennus"
                );
                if(tiedostonimi != null && tiedostonimi.length() > 0) {
                    controller.lataa(tiedostonimi);
                }
            }
        });
        
        poistu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        tiedostoValikko.add(uusiPeli);
        tiedostoValikko.add(tallenna);
        tiedostoValikko.add(lataa);
        tiedostoValikko.add(poistu);
        this.add(tiedostoValikko);
    }
}
