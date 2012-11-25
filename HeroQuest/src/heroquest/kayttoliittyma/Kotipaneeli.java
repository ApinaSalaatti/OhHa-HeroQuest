/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import java.util.List;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import heroquest.PeliController;
import heroquest.KotiController;
import heroquest.util.Tiedostoapuri;
import heroquest.domain.kauppa.MyyntiTavara;
/**
 *
 * @author Merioksan Mikko
 */
public class Kotipaneeli extends JPanel {
    private PeliController controller;
    private Kauppapaneeli kauppaPanel;
    private JComboBox kartat;
    
    public Kotipaneeli(PeliController pc) {
        this.controller = pc;
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        this.setLayout(new GridLayout(3, 1));
        this.add(new JLabel("Kotona ollaan!"));
        
        kauppaPanel = new Kauppapaneeli(controller);
       
        
        JPanel karttaPanel = new JPanel();
        JButton kartanvalintanappi = new JButton("Valitse luolasto");
        
        kartanvalintanappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.pelaajaPoistuuKotoa(kartat.getSelectedItem().toString());
            }
        });
        
        kartat = new JComboBox(Tiedostoapuri.kansioTauluksi("src/kartat"));
        karttaPanel.add(kartat);
        karttaPanel.add(kartanvalintanappi);
        
        this.add(kauppaPanel);
        this.add(karttaPanel);
    }
    
    public void paivita() {
        kauppaPanel.paivita();
    }
}
