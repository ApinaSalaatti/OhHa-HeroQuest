/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.kayttoliittyma;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import heroquest.domain.Kartta;
import heroquest.domain.Pelaaja;
/**
 *
 * @author merioksa
 */
public class Aloituspaneeli extends JPanel {
    private static String[] luokat = { "Taikamaagi", "Miekkasoturi", "Konna", "Kekkeruusi" };
    
    public Aloituspaneeli() {
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
        
        JTextField koko = new JTextField();
        koko.setColumns(3);
        JPanel kokopaneeli = new JPanel();
        kokopaneeli.add(new JLabel("Kartan koko"));
        kokopaneeli.add(koko);
        
        JButton aloitusnappi = new JButton("Aloita seikkailu!");
        JPanel nappipaneeli = new JPanel();
        nappipaneeli.add(aloitusnappi);
        
        this.add(new JLabel("Tervetuloa superhypermegajännittävään Mahtiseikkailuun!"));
        this.add(nimipaneeli);
        this.add(luokkapaneeli);
        this.add(kokopaneeli); 
        this.add(nappipaneeli);
    }
}
