/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

import heroquest.Peli;
import heroquest.domain.Ilmansuunta;
import heroquest.kayttoliittyma.kuuntelijat.LiikenappiKuuntelija;
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
    
    public Nappipaneeli() {
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        ylos = new JButton("^");
        alas = new JButton("V");
        vasen = new JButton("<");
        oikea = new JButton(">");
        
        this.add(ylos, BorderLayout.NORTH);
        this.add(alas);
        this.add(vasen, BorderLayout.WEST);
        this.add(oikea, BorderLayout.EAST);
    }
    
    public void setPelipaneeli(Pelipaneeli p) {
        this.tiedot = p;
    }
    
    public void setPeli(Peli p) {
        ylos.addActionListener(new LiikenappiKuuntelija(p, tiedot));
        alas.addActionListener(new LiikenappiKuuntelija(p, tiedot));
        vasen.addActionListener(new LiikenappiKuuntelija(p, tiedot));
        oikea.addActionListener(new LiikenappiKuuntelija(p, tiedot));
    }
}
