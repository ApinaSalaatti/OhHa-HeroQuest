/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridLayout;

import heroquest.PeliController;
import heroquest.domain.Karttapala;
/**
 * JPanel luokan alaluokka, jolle kartta tulostetaan.
 * 
 * @author Merioksan Mikko
 */
public class Karttapaneeli extends JPanel {
    /**
     * PeliController-olio, jolta haetaan piirrettävä kartta.
     */
    private PeliController controller;
    /**
     * Kaikki erilaiset kartan karttapalaikonit taulukkona.
     */
    private ImageIcon[] laatat;
    
    public Karttapaneeli(PeliController pc) {
        this.controller = pc;
        luoKomponentit();
    }
    
    /**
     * Luodan näkymän komponentit.
     */
    private void luoKomponentit() {
        // ladataan kartan piirtämiseen vaaditut kuvat
        ImageIcon lattiaIcon = new ImageIcon("kuvat/lattia.png");
        ImageIcon seinaIcon = new ImageIcon("kuvat/seina.png");
        ImageIcon lattiaPelaajaPaikallaIcon = new ImageIcon("kuvat/lattiaPelaajaPaikalla.png");
        ImageIcon lattiaMonsteriPaikallaIcon = new ImageIcon("kuvat/lattiaMonsteriPaikalla.png");
        ImageIcon lattiaTaisteluIcon = new ImageIcon("kuvat/lattiaTaistelu.png");
        ImageIcon lattiaAarrePaikallaIcon = new ImageIcon("kuvat/lattiaAarrePaikalla.png");
        ImageIcon lattiaAnsaPaikallaIcon = new ImageIcon("kuvat/lattiaAnsaPaikalla.png");
        ImageIcon lattiaTavaraaPaikallaIcon = new ImageIcon("kuvat/lattiaTavaraaPaikalla.png");
        
        laatat = new ImageIcon[8];
        laatat[0] = seinaIcon;
        laatat[1] = lattiaIcon;
        laatat[2] = lattiaPelaajaPaikallaIcon;
        laatat[3] = lattiaMonsteriPaikallaIcon;
        laatat[4] = lattiaTaisteluIcon;
        laatat[5] = lattiaAarrePaikallaIcon;
        laatat[6] = lattiaAnsaPaikallaIcon;
        laatat[7] = lattiaTavaraaPaikallaIcon;
    }
    
    /**
     * "Piirretään" kartta käyttöliittymään.
     */
    public void piirraKartta() {
        Karttapala[][] kartta = controller.getKartta().getNahdytPalat();
        this.removeAll();
        this.setLayout(new GridLayout(kartta.length, kartta[0].length));
        for(int y = 0; y < kartta.length; y++) {
            for(int x = 0; x < kartta[0].length; x++) {
                Karttapala nykyinen = kartta[y][x];
                if(kartta[y][x] != null) {
                    this.add(new JLabel(laatat[nykyinen.status()]));
                }
                else {
                    this.add(new JLabel(laatat[0]));
                }
            }
        }
    }
}
