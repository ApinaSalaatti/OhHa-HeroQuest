/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import heroquest.domain.Pelaaja;
/**
 *
 * @author Merioksan Mikko
 */
// JPanel-luokan alaluokka jolle tulostetaan pelaajan tiedot
public class Tietopaneeli extends JPanel {
    private Pelaaja pelaaja;
    private JLabel nimiJaLuokka;
    private JTextArea pelaajanSijainti;
    
    public Tietopaneeli(Pelaaja p) {
        this.pelaaja = p;
        this.nimiJaLuokka = new JLabel();
        this.pelaajanSijainti = new JTextArea();
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        this.setLayout(new GridLayout(2, 1));

        this.add(nimiJaLuokka);
        this.add(pelaajanSijainti);
    }
    
    public void paivitaTiedot() {
        nimiJaLuokka.setText(pelaaja.getNimi() + " (" + pelaaja.getLuokka() + ")");
        pelaajanSijainti.setText(pelaaja.getSijainti().toString());
    }

}
