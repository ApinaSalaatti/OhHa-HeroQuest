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
    private JTextArea tapahtumat;
    private Nappipaneeli nappiPanel;
    private Pelipaneeli pelipaneeli;
    
    public Tietopaneeli() {
        this.nimiJaLuokka = new JTextArea();
        this.tapahtumat = new JTextArea();
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
        tapahtumat.setEditable(false);
        nimiJaLuokka.setEditable(false);
        
        nappiPanel = new Nappipaneeli();
        
        this.add(nimiJaLuokka);
        this.add(nappiPanel);
        this.add(tapahtumat);
    }
    
    // päivittää kaikki käyttöliittymän näyttämät tiedot (pelaajan statuksen, tapahtumat-feedin ja nappulat)
    public void paivitaTiedot(String tapahtuma) {
        // päivitetään pelaajan status
        nimiJaLuokka.setText(peli.getPelaaja().toString());
        
        // päivitetään tapahtumat-feed
        paivitaTapahtumat(tapahtuma);
        
        // vaihdetaan käyttöliittymän moodi (a.k.a. enabloidaan oikeat napit)
        if(peli.taistelunAika()) {
            nappiPanel.vaihdaMoodi("taistelu");
        }
        else if(peli.getPelaaja().getLiikkeet() > 0) {
            nappiPanel.vaihdaMoodi("liike");
        }
        else {
            nappiPanel.vaihdaMoodi("liikenoppa");
        }
    }
    
    // päivitetään tapahtumat-feediä. Uusin tapahtuma tulee alkuun
    public void paivitaTapahtumat(String tapahtuma) {
        String vanha = tapahtumat.getText();
        tapahtumat.setText(tapahtuma);
        tapahtumat.append("\n" + vanha);
        
    }

}
