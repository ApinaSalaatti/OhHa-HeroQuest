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

import heroquest.Peli;
import heroquest.domain.Ilmansuunta;
import heroquest.kayttoliittyma.kuuntelijat.LiikenappiKuuntelija;
import heroquest.kayttoliittyma.kuuntelijat.LiikenoppaKuuntelija;
/**
 *
 * @author Merioksan Mikko
 */
public class Nappipaneeli extends JPanel {
    Pelipaneeli tiedot;
    JButton ylos;
    JButton alas;
    JButton vasen;
    JButton oikea;
    JButton liikenoppa;
    JButton taistelunoppa;
    
    public Nappipaneeli() {
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
    
    public void setPelipaneeli(Pelipaneeli p) {
        this.tiedot = p;
    }
    
    public void setPeli(Peli p) {
        ylos.addActionListener(new LiikenappiKuuntelija(p, tiedot));
        alas.addActionListener(new LiikenappiKuuntelija(p, tiedot));
        vasen.addActionListener(new LiikenappiKuuntelija(p, tiedot));
        oikea.addActionListener(new LiikenappiKuuntelija(p, tiedot));
        liikenoppa.addActionListener(new LiikenoppaKuuntelija(p, tiedot));
    }
    
    public void vaihdaMoodi(String moodi) {
        if(moodi.equals("taistelu")) {
            liikeNapit(false);
            liikenoppa.setEnabled(false);
            taistelunoppa.setEnabled(true);
        }
        else if(moodi.equals("liikenoppa")) {
            liikeNapit(false);
            liikenoppa.setEnabled(true);
            taistelunoppa.setEnabled(false);
        }
        else if(moodi.equals("liike")) {
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
