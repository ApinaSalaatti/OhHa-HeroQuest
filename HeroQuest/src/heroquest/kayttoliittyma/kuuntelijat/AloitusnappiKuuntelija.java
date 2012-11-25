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
 * ActionListenerin toteuttava luokka, jonka avulla lähetetään pelin alussa pelaajan valitsemat tiedot Peli-luokalle
 * 
 * @author Merioksan Mikko
 */
public class AloitusnappiKuuntelija implements ActionListener {
    private Container container;
    private CardLayout layout;
    private JTextField nimi;
    private JComboBox luokka;
    private PeliController controller;
    
    public AloitusnappiKuuntelija(Container c, CardLayout lo, JTextField n, JComboBox l, PeliController pc) {
        this.container = c;
        this.layout = lo;
        this.nimi = n;
        this.luokka = l;
        this.controller = pc;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String nimiStr = nimi.getText();
        String luokkaStr = luokka.getSelectedItem().toString();
        
        controller.aloitaPeli(nimiStr, luokkaStr);
        
        layout.show(container, "peli");

    }
}
