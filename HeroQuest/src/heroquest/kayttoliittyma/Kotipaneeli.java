/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import java.util.List;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import heroquest.PeliController;
import heroquest.util.Tiedostoapuri;
/**
 * Pelaajan kodin käyttöliittymä. Sisältään esimerkiksi kaupan josta pelaaja voi ostaa tarvikkeita.
 * 
 * @author Merioksan Mikko
 */
public class Kotipaneeli extends JPanel {
    private PeliController controller;
    private Kauppapaneeli kauppaPanel;
    private JComboBox kartat;
    private JPanel pelaajanKuva;
    private Saavutuspaneeli saavutukset;
    
    public Kotipaneeli(PeliController pc) {
        this.controller = pc;
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        this.setLayout(new GridLayout(3, 1));
        JPanel ylapuoli = new JPanel(new GridLayout(1, 2));
        pelaajanKuva = new JPanel();
        saavutukset = new Saavutuspaneeli(controller);
        
        ylapuoli.add(pelaajanKuva);
        ylapuoli.add(saavutukset);
        this.add(ylapuoli);
        
        kauppaPanel = new Kauppapaneeli(controller);
       
        
        JPanel karttaPanel = new JPanel();
        JButton kartanvalintanappi = new JButton("Syöksy seikkailuun!");
        
        kartanvalintanappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.pelaajaPoistuuKotoa(kartat.getSelectedItem().toString());
            }
        });
        
        kartat = new JComboBox(Tiedostoapuri.kansioTauluksi("src/kartat"));
        karttaPanel.add(new JLabel("Valitse luolasto:"));
        karttaPanel.add(kartat);
        karttaPanel.add(kartanvalintanappi);
        
        this.add(kauppaPanel);
        this.add(karttaPanel);
    }
    
    public void paivita() {
        pelaajanKuva.removeAll();
        pelaajanKuva.add(new JLabel(new ImageIcon("src/kuvat/naamat/" + controller.getPeli().getPelaaja().getKuva())));
        kauppaPanel.paivita();
        saavutukset.paivita();
    }
}
