/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;

import heroquest.PeliController;
/**
 * Näkymä, joka esitetään pelaajalle kun tämä kuolee.
 * 
 * @author Merioksan Mikko
 */
public class Lopetuspaneeli extends JPanel {
    /**
     * Kontrolleri josta haetaan pelaajan tiedot.
     */
    private PeliController controller;
    /**
     * JLabel johon tulostetaan pelaajan nimi.
     */
    private JLabel nimi;
    /**
     * JLabel johon tulostetaan pelaajan tapot.
     */
    private JLabel tapot;
    /**
     * JLabel johon tulostetaan ystävällinen viesti.
     */
    private JLabel heihei;
    
    public Lopetuspaneeli(PeliController controller) {
        this.controller = controller;
        luoKomponentit();
    }
    
    /**
     * Luodaan näkymän komponentit.
     */
    private void luoKomponentit() {
        this.setLayout(new GridLayout(3, 1));
        nimi = new JLabel();
        nimi.setFont(new Font("Arial", 1, 50));
        
        tapot = new JLabel();
        tapot.setFont(new Font("Arial", 1, 50));
        
        heihei = new JLabel();
        heihei.setFont(new Font("Arial", 1, 50));
        
        this.add(nimi);
        this.add(tapot);
        this.add(heihei);
    }
    
    /**
     * Päivitetään näkymään pelaajan tiedot.
     */
    public void paivita() {
        nimi.setText("Nimesi oli " + controller.getPeli().getPelaaja().getNimi());
        tapot.setText("Tienasit yhteensä " + controller.getPeli().getPelaaja().getExp() + " pistettä! Wowzers!");
        heihei.setText("SE ON LOPPU NYT. HEI HEI!");
    }
}
