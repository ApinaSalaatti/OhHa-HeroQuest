/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.kayttoliittyma.kuuntelijat;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import heroquest.Peli;
import heroquest.domain.Pelaaja;
import heroquest.domain.Kartta;
/**
 *
 * @author merioksa
 */
public class AloitusnappiKuuntelija implements ActionListener {
    private JTextField nimi;
    private JComboBox luokka;
    private JTextField koko;
    private Peli peli;
    
    public AloitusnappiKuuntelija(JTextField n, JComboBox l, JTextField k, Peli p) {
        this.nimi = n;
        this.luokka = l;
        this.koko = k;
        this.peli = p;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int kartanKoko = Integer.parseInt(koko.getText());
        Kartta k = new Kartta(kartanKoko);
        
        Pelaaja p = new Pelaaja(nimi.getText(), 5, 5, luokka.getSelectedItem().toString());
        
        this.peli = new Peli(k, p);
    }
}
