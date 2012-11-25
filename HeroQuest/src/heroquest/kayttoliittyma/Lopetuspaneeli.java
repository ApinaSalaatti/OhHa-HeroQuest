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
 *
 * @author Merioksan Mikko
 */
public class Lopetuspaneeli extends JPanel {
    private PeliController controller;
    private JLabel nimi;
    private JLabel tapot;
    private JLabel heihei;
    
    public Lopetuspaneeli(PeliController controller) {
        this.controller = controller;
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        this.setLayout(new GridLayout(3, 1));
        nimi = new JLabel();
        nimi.setFont(new Font("nimi", 1, 50));
        
        tapot = new JLabel();
        tapot.setFont(new Font("tapot", 1, 50));
        
        heihei = new JLabel();
        heihei.setFont(new Font("heihei", 1, 50));
        
        this.add(nimi);
        this.add(tapot);
        this.add(heihei);
    }
    
    public void paivita() {
        nimi.setText("Nimesi oli " + controller.getPeli().getPelaaja().getNimi());
        tapot.setText("Tienasit yhteensä " + controller.getPeli().getPelaaja().getExp() + " pistettä! Wowzers!");
        heihei.setText("SE ON LOPPU NYT. HEI HEI!");
    }
}
