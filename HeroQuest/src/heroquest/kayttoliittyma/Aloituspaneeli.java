/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.kayttoliittyma;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import heroquest.PeliController;
import heroquest.domain.Kartta;
import heroquest.domain.Pelaaja;
import heroquest.util.Tiedostoapuri;
import heroquest.kayttoliittyma.kuuntelijat.AloitusnappiKuuntelija;
/**
 *
 * @author merioksa
 */
public class Aloituspaneeli extends JPanel {
    private static String[] luokat = { "Taikamaagi", "Miekkasoturi", "Konna", "Kekkeruusi" };
    private Container container;
    private CardLayout nakyma;
    private PeliController controller;
    
    public Aloituspaneeli(JFrame frame, CardLayout layout, PeliController pc) {
        this.container = frame.getContentPane();
        this.nakyma = layout;
        this.controller = pc;
        luoKomponentit(frame);
    }
    
    private void luoKomponentit(JFrame frame) {
        this.setLayout(new GridLayout(3, 1));
        
        JButton uusiPeli = luoUusiPeliNappi();
        JButton lataaPeli = luoLataaPeliNappi(frame);
        JButton poistu = luoPoistuNappi();
        
        this.add(uusiPeli);
        this.add(lataaPeli);
        this.add(poistu);
    }
    
    public JButton luoUusiPeliNappi() {
        JButton uusiPeli = new JButton("Uusi peli");
        
        uusiPeli.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               nakyma.show(container, "luonti");
           }
        });
        
        return uusiPeli;
    }
    
    public JButton luoLataaPeliNappi(final JFrame frame) {
        JButton lataaPeli = new JButton("Lataa peli");
        
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
                    controller.lataa(tiedostonimi);
                    nakyma.show(container, "peli");
                }
            }
        });
        
        return lataaPeli;
    }
    
    public JButton luoPoistuNappi() {
        JButton poistu = new JButton("Poistu");
        
        poistu.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.exit(0);
           }
        });
        
        return poistu;
    }
}
