/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.kayttoliittyma.kuuntelijat;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Container;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import heroquest.PeliController;

/**
 *
 * @author merioksa
 */
public class AloitusnappiKuuntelija implements ActionListener {
    private Container container;
    private CardLayout layout;
    private JTextField nimi;
    private JComboBox luokka;
    private JComboBox kartta;
    private PeliController controller;
    
    public AloitusnappiKuuntelija(Container c, CardLayout lo, JTextField n, JComboBox l, JComboBox k, PeliController pc) {
        this.container = c;
        this.layout = lo;
        this.nimi = n;
        this.luokka = l;
        this.kartta = k;
        this.controller = pc;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String nimiStr = nimi.getText();
        String luokkaStr = luokka.getSelectedItem().toString();
        String karttaStr = kartta.getSelectedItem().toString();
        
        controller.aloitaPeli(nimiStr, luokkaStr, karttaStr);
        
        layout.show(container, "peli");

    }
}
