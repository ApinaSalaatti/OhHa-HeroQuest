/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import heroquest.PeliController;
import heroquest.domain.Ilmansuunta;
import heroquest.kayttoliittyma.kuuntelijat.LiikenappiKuuntelija;
import heroquest.kayttoliittyma.kuuntelijat.LiikenoppaKuuntelija;
import heroquest.kayttoliittyma.kuuntelijat.TaistelunoppaKuuntelija;
/**
 *
 * @author Merioksan Mikko
 */
public class Nappipaneeli extends JPanel {
    PeliController controller;
    JButton ylos;
    JButton alas;
    JButton vasen;
    JButton oikea;
    JButton liikenoppa;
    JButton taistelunoppa;
    
    public Nappipaneeli(PeliController pc) {
        this.controller = pc;
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        this.setLayout(new GridLayout(1, 2));
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
        
        liikkumisnapit.add(ylos, BorderLayout.NORTH);
        liikkumisnapit.add(alas);
        liikkumisnapit.add(vasen, BorderLayout.WEST);
        liikkumisnapit.add(oikea, BorderLayout.EAST);
        
        nopat.add(new JLabel("Heitä noppaa:"), BorderLayout.NORTH);
        nopat.add(liikenoppa);
        nopat.add(taistelunoppa, BorderLayout.SOUTH);
        this.add(liikkumisnapit);
        this.add(nopat);
    }
    
    // enabloidaan/disabloidaan oikean nappulat ja namiskat käyttöliittymästä sen mukaan, missä tilassa peli on
    public void vaihdaTila(String tila) {
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
