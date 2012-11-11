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
import heroquest.domain.Pelaaja;
/**
 *
 * @author Merioksan Mikko
 */
// JPanel-luokan alaluokka jolle tulostetaan pelaajan tiedot
public class Tietopaneeli extends JPanel {
    private Peli peli;
    private JTextArea nimiJaLuokka;
    private JTextArea pelaajanSijainti;
    private Nappipaneeli nappiPanel;
    private Pelipaneeli pelipaneeli;
    
    public Tietopaneeli() {
        this.nimiJaLuokka = new JTextArea();
        this.pelaajanSijainti = new JTextArea();
        luoKomponentit();
    }
    
    public void setPeli(Peli p) {
        this.peli = p;
        nappiPanel.setPeli(p);
    }
    
    public void setPelipaneeli(Pelipaneeli p) {
        this.pelipaneeli = p;
        nappiPanel.setPelipaneeli(p);
    }
    
    private void luoKomponentit() {
        this.setLayout(new GridLayout(3, 1));
        pelaajanSijainti.setEditable(false);
        nimiJaLuokka.setEditable(false);
        
        nappiPanel = new Nappipaneeli();
        
        this.add(nimiJaLuokka);
        this.add(nappiPanel);
        this.add(pelaajanSijainti);
    }
    
    public void paivitaTiedot() {
        nimiJaLuokka.setText(peli.getPelaaja().toString());
        pelaajanSijainti.setText(peli.getPelaaja().getSijainti().toString());
    }

}
