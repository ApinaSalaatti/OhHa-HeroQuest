/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import heroquest.Peli;
import heroquest.PeliController;
import heroquest.domain.Pelaaja;
import javax.swing.JOptionPane;
/**
 * Komponentti, jolla pelaajan tiedot ja kontrollinapit sijaitsevat.
 * 
 * @author Merioksan Mikko
 */
public class Tietopaneeli extends JPanel {
    private PeliController controller;
    private JTextArea nimiJaLuokka;
    private JTextArea tapahtumat;
    private Nappipaneeli nappiPanel;
    private Pelipaneeli pelipaneeli;
    
    public Tietopaneeli(PeliController pc) {
        this.controller = pc;
        this.nimiJaLuokka = new JTextArea();
        this.tapahtumat = new JTextArea();
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        this.setLayout(new GridLayout(2, 1));
        tapahtumat.setEditable(false);
        nimiJaLuokka.setEditable(false);
        
        nappiPanel = new Nappipaneeli(controller);
        
        JPanel ylaPanel = new JPanel(new GridLayout(2, 1));
        ylaPanel.add(nimiJaLuokka);
        ylaPanel.add(nappiPanel);

        this.add(ylaPanel);
        this.add(tapahtumat);
    }
    
    // päivittää kaikki käyttöliittymän näyttämät tiedot (pelaajan statuksen, tapahtumat-feedin ja nappulat)
    public void paivitaTiedot(String tapahtuma) {
        // päivitetään pelaajan status
        nimiJaLuokka.setText(controller.pelaajanStatus());
        
        // päivitetään tapahtumat-feed
        paivitaTapahtumat(tapahtuma);
        
        // päivitetään käyttöliittymän napit ja näpykät
        nappiPanel.paivita(controller.getTila());
    }
    
    // päivitetään tapahtumat-feediä. Uusin tapahtuma tulee alkuun
    public void paivitaTapahtumat(String tapahtuma) {
        String vanha = tapahtumat.getText();
        tapahtumat.setText(tapahtuma);
        tapahtumat.append("\n" + vanha);
        
    }

}
