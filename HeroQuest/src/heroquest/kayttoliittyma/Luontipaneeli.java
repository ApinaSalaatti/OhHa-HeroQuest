/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.kayttoliittyma;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import heroquest.PeliController;
import heroquest.kayttoliittyma.kuuntelijat.AloitusnappiKuuntelija;
/**
 * Käyttöliittymä uuden hahmon luomiseen. Kysyy käyttäjältä hahmon luomiseksi vaaditut tiedot.
 * 
 * @author Merioksan Mikko
 */
public class Luontipaneeli extends JPanel {
    private static String[] luokat = { "Taikamaagi", "Miekkasoturi", "Konna", "Kekkeruusi" };
    private Container container;
    private CardLayout cards;
    private PeliController controller;
    
    public Luontipaneeli(Container c, CardLayout layout, PeliController pc) {
        this.container = c;
        this.cards = layout;
        this.controller = pc;
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        this.setLayout(new GridLayout(5, 1));
        
        JTextField nimi = new JTextField();
        nimi.setColumns(20);
        JPanel nimipaneeli = new JPanel();
        nimipaneeli.add(new JLabel("Hahmon nimi: "));
        nimipaneeli.add(nimi);
        
        JComboBox luokkalista = new JComboBox(luokat);
        JPanel luokkapaneeli = new JPanel();
        luokkapaneeli.add(new JLabel("Luokka: "));
        luokkapaneeli.add(luokkalista);
        
        Kuvavalintapaneeli kuvavalintaPanel = new Kuvavalintapaneeli();
        
        JButton aloitusnappi = new JButton("Aloita seikkailu!");
        aloitusnappi.addActionListener(new AloitusnappiKuuntelija(container, cards, nimi, luokkalista, kuvavalintaPanel, controller));
        JPanel nappipaneeli = new JPanel();
        nappipaneeli.add(aloitusnappi);
        
        JLabel tervehdys = new JLabel("Tervetuloa superhypermegajännittävään Mahtiseikkailuun!");
        tervehdys.setFont(new Font("tervehdys", 1, 30));
        this.add(tervehdys);
        this.add(nimipaneeli);
        this.add(kuvavalintaPanel);
        this.add(luokkapaneeli);
        this.add(nappipaneeli);
    }
}
