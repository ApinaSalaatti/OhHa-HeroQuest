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
import javax.swing.JOptionPane;
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
    /**
     * PeliController, jota tarvitaan esim saavutusten ja kaupan tavaroiden päivittämiseen.
     */
    private PeliController controller;
    /**
     * Kotona sijaitsevan kaupan käyttöliittymä.
     */
    private Kauppapaneeli kauppaPanel;
    /**
     * Lista josta valitaan luolasto.
     */
    private JComboBox kartat;
    /**
     * Paneeli jolla näytetään pelaajan kuva.
     */
    private JPanel pelaajanKuva;
    /**
     * Paneeli joka näyttää pelaajan saavutukset.
     */
    private Saavutuspaneeli saavutukset;
    
    public Kotipaneeli(PeliController pc) {
        this.controller = pc;
        luoKomponentit();
    }
    
    /**
     * Luodaan näkymän komponentit.
     */
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
        JButton kartanvalintanappi = luoKartanvalintanappi();
        
        kartat = new JComboBox(Tiedostoapuri.kansioTauluksi("kartat"));
        karttaPanel.add(new JLabel("Valitse luolasto:"));
        karttaPanel.add(kartat);
        karttaPanel.add(kartanvalintanappi);
        
        this.add(kauppaPanel);
        this.add(karttaPanel);
    }
    
    /**
     * Luodaan nappi kartan valintaa varten.
     * 
     * @return valmis nappi
     */
    public JButton luoKartanvalintanappi() {
        JButton kartanvalintanappi = new JButton("Syöksy seikkailuun!");
        
        kartanvalintanappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.pelaajaPoistuuKotoa(kartat.getSelectedItem().toString());
                }
                catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Karttatiedoston lukeminen epäonnistui! Oletkohan sorkkinut sitä?");
                }
            }
        });
        
        return kartanvalintanappi;
    }
    
    /**
     * Päivitetään näkymä, eli tarkastetaan tuleeko pelaajan kuvaa muuttaa ja päivitetään saavutukset ja kauppa.
     */
    public void paivita() {
        pelaajanKuva.removeAll();
        pelaajanKuva.add(new JLabel(new ImageIcon("kuvat/naamat/" + controller.getPeli().getPelaaja().getKuva())));
        kauppaPanel.paivita();
        saavutukset.paivita();
    }
}
