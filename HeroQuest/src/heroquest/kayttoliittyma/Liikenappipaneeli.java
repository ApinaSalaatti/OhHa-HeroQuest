package heroquest.kayttoliittyma;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;

import heroquest.PeliController;
import heroquest.kayttoliittyma.kuuntelijat.LiikenappiKuuntelija;
import heroquest.kayttoliittyma.kuuntelijat.LiikenoppaKuuntelija;
import heroquest.kayttoliittyma.kuuntelijat.TaistelunoppaKuuntelija;

/**
 * Liikenapit ja nopat liikkeeseen ja taisteluun sisältävä käyttöliittymäkomponentti.
 * 
 * @author Merioksan Mikko
 */
public class Liikenappipaneeli extends JPanel {
    /**
     * Kontrolleri jonka liikkumis- ja muita metodeja kutsutaan
     */
    private PeliController controller;
    /**
     * Nappi joka liikuttaa pelaajaa ylös.
     */
    private JButton ylos;
    /**
     * Nappi joka liikuttaa pelaajaa alas.
     */
    private JButton alas;
    /**
     * Nappi joka liikuttaa pelaajaa vasemmalle.
     */
    private JButton vasen;
    /**
     * Nappi joka tekee arvatkaapa mitä.
     */
    private JButton oikea;
    /**
     * Nappi josta "heitetään" liikenoppaa.
     */
    private JButton liikenoppa;
    /**
     * Nappi josta "heitetään" taistelunoppaa
     */
    private JButton taistelunoppa;
    
    public Liikenappipaneeli(PeliController pc) {
        this.controller = pc;
        luoKomponentit();
    }
    
    /**
     * Luodaan käyttöliittymän komponentit.
     */
    private void luoKomponentit() {
        this.setLayout(new GridLayout(2, 1));
        JPanel liikkumisnapit = luoLiikkumisnappipaneeli();
        JPanel nopat = new JPanel();

        liikenoppa = new JButton("Liiku!");
        taistelunoppa = new JButton("Taistele!");
        
        liikenoppa.addActionListener(new LiikenoppaKuuntelija(controller));
        taistelunoppa.addActionListener(new TaistelunoppaKuuntelija(controller));

        nopat.add(new JLabel("Heitä noppaa:"));
        nopat.add(liikenoppa);
        nopat.add(taistelunoppa);
        
        this.add(liikkumisnapit);
        this.add(nopat);
    }
    
    /**
     * Luodaan paneeli jossa liikkumiseen tarvittavat napit sijaitsevat.
     * 
     * @return valmis paneeli
     */
    public JPanel luoLiikkumisnappipaneeli() {
        JPanel liikkumisnapit = new JPanel();
        ylos = new JButton("^");
        alas = new JButton("V");
        vasen = new JButton("<");
        oikea = new JButton(">");
        
        ylos.addActionListener(new LiikenappiKuuntelija(controller));
        alas.addActionListener(new LiikenappiKuuntelija(controller));
        vasen.addActionListener(new LiikenappiKuuntelija(controller));
        oikea.addActionListener(new LiikenappiKuuntelija(controller));
        
        liikkumisnapit.add(ylos);
        liikkumisnapit.add(alas);
        liikkumisnapit.add(vasen);
        liikkumisnapit.add(oikea);
        
        return liikkumisnapit;
    }
    
    /**
     * Päivitetään näkymä, eli enabloidaan/disabloidaan tietyt napit
     * 
     * @param tila missä tilassa peli on
     */
    public void paivita(String tila) {
        if(tila.equals("taistelu")) {
            liikeNapit(false);
            liikenoppa.setEnabled(false);
            taistelunoppa.setEnabled(true);
        }
        else if(tila.equals("liikenoppa")) {
            liikeNapit(false);
            liikenoppa.setEnabled(true);
            taistelunoppa.setEnabled(false);
        }
        else if(tila.equals("liike")) {
            liikeNapit(true);
            liikenoppa.setEnabled(false);
            taistelunoppa.setEnabled(false);
        }
        else if(tila.equals("koti")) {
            liikeNapit(false);
            liikenoppa.setEnabled(false);
            taistelunoppa.setEnabled(false);
        }
    }
    
    /**
     * Asetetaan liikenappien tila.
     * 
     * @param b enabloidaanko vai disabloidaanko
     */
    private void liikeNapit(boolean b) {
        if(b) {
            ylos.setEnabled(true);
            alas.setEnabled(true);
            vasen.setEnabled(true);
            oikea.setEnabled(true);
        }
        else {
            ylos.setEnabled(false);
            alas.setEnabled(false);
            vasen.setEnabled(false);
            oikea.setEnabled(false);
        }
    }
}
