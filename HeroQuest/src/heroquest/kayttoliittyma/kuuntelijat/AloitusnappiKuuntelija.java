/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.kayttoliittyma.kuuntelijat;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Container;
import java.awt.Component;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import heroquest.Peli;
import heroquest.domain.Pelaaja;
import heroquest.domain.Kartta;
import heroquest.kayttoliittyma.Pelipaneeli;
/**
 *
 * @author merioksa
 */
public class AloitusnappiKuuntelija implements ActionListener {
    private Container container;
    private CardLayout layout;
    private JTextField nimi;
    private JComboBox luokka;
    private JTextField koko;
    private Peli peli;
    
    public AloitusnappiKuuntelija(Container c, CardLayout lo, JTextField n, JComboBox l, JTextField k, Peli p) {
        this.container = c;
        this.layout = lo;
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
        p.setSijainti(k.getAloituspala());
        k.getAloituspala().pelaajaSaapuu();
        this.peli = new Peli(k, p);
        
        Pelipaneeli peliPanel = (Pelipaneeli)container.getComponent(1);
        peliPanel.setPeli(peli);
        
        String aloitusviesti = peli.getPelaaja().getSijainti().toString();
        aloitusviesti += "Suuri seikkailu alkaa! Löydä aarre ja valloita maan neitojen sydämet!\n";
        peliPanel.paivita(aloitusviesti);
        
        layout.show(container, "peli");

    }
}
