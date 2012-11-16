/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.GridLayout;

import heroquest.PeliController;
/**
 *
 * @author Merioksan Mikko
 */
public class Lopetuspaneeli extends JPanel {
    private PeliController controller;
    private JTextArea nimi;
    
    public Lopetuspaneeli(PeliController controller) {
        this.controller = controller;
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        this.setLayout(new GridLayout(2, 1));
        nimi = new JTextArea();
        nimi.setEditable(false);
        this.add(nimi);
        this.add(new JLabel("SE ON LOPPU NYT HEI!"));
    }
    
    public void paivita() {
        nimi.setText(controller.pelaajanStatus());
    }
}
