/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.kayttoliittyma;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

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
        this.setLayout(new GridLayout(4, 1));
        this.setBackground(Color.WHITE);
        
        JLabel logo = new JLabel(new ImageIcon("src/kuvat/logo.png"));
        
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
    
    public JButton luoUusiPeliNappi(final JFrame frame) {
        JButton uusiPeli = new JButton(new ImageIcon("src/kuvat/uusiPeli.png"));
        
        uusiPeli.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String viesti = "Tervetuloa Luolakuningas II:n maailmaan, oi jalo sankari!\n";
               viesti += "On kulunut 15 vuotta siitä, kun isäsi voitti ensimmäisen Luolakuningas-turnauksen. Tänä vuonna on vihdoin sinun aikasi loistaa!\n";
               viesti += "Tehtävänäsi on löytää kaikki Wolframivuoren kätköihin piilotetut aarteet ja tulla isäsi tavoin kruunatuksi...\n\n";
               viesti += "LUOLAKUNINKAAKSI!";
               JOptionPane.showMessageDialog(frame, viesti);
               nakyma.show(container, "luonti");
           }
        });
        
        uusiPeli.setContentAreaFilled(false);
        uusiPeli.setBorderPainted(false);
        uusiPeli.setFocusPainted(false);
        
        return uusiPeli;
    }
    
    public JButton luoLataaPeliNappi(final JFrame frame) {
        JButton lataaPeli = new JButton(new ImageIcon("src/kuvat/lataaPeli.png"));
        
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
        
        lataaPeli.setContentAreaFilled(false);
        lataaPeli.setBorderPainted(false);
        lataaPeli.setFocusPainted(false);
        return lataaPeli;
    }
    
    public JButton luoPoistuNappi() {
        JButton poistu = new JButton(new ImageIcon("src/kuvat/poistu.png"));
        
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
