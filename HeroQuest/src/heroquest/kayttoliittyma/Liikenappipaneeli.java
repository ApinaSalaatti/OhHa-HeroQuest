/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
    private PeliController controller;
    private JButton ylos;
    private JButton alas;
    private JButton vasen;
    private JButton oikea;
    private JButton liikenoppa;
    private JButton taistelunoppa;
    
    public Liikenappipaneeli(PeliController pc) {
        this.controller = pc;
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        this.setLayout(new GridLayout(2, 1));
        JPanel liikkumisnapit = new JPanel();
        JPanel nopat = new JPanel();
        ylos = new JButton("^");
        alas = new JButton("V");
        vasen = new JButton("<");
        oikea = new JButton(">");
        liikenoppa = new JButton("Liiku!");
        taistelunoppa = new JButton("Taistele!");
        
        ylos.addActionListener(new LiikenappiKuuntelija(controller));
        alas.addActionListener(new LiikenappiKuuntelija(controller));
        vasen.addActionListener(new LiikenappiKuuntelija(controller));
        oikea.addActionListener(new LiikenappiKuuntelija(controller));
        liikenoppa.addActionListener(new LiikenoppaKuuntelija(controller));
        taistelunoppa.addActionListener(new TaistelunoppaKuuntelija(controller));
        
        liikkumisnapit.add(ylos);
        liikkumisnapit.add(alas);
        liikkumisnapit.add(vasen);
        liikkumisnapit.add(oikea);
        nopat.add(new JLabel("Heitä noppaa:"));
        nopat.add(liikenoppa);
        nopat.add(taistelunoppa);
        
        this.add(liikkumisnapit);
        this.add(nopat);
    }
    
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
