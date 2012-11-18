/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import heroquest.PeliController;
import heroquest.domain.Karttapala;
import heroquest.util.Kuvienkasittely;
/**
 * JPanel luokan alaluokka, jolle kartta tulostetaan.
 * 
 * @author Merioksan Mikko
 */
public class Karttapaneeli extends JPanel {
    private PeliController controller;
    private ImageIcon[] laatat;
    
    public Karttapaneeli(PeliController pc) {
        this.controller = pc;
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        // ladataan kartan piirtämiseen vaaditut kuvat
        BufferedImage lattia = Kuvienkasittely.lataaKuva("lattia.png");
        ImageIcon lattiaIcon = new ImageIcon(lattia);
        BufferedImage seina = Kuvienkasittely.lataaKuva("seina.png");
        ImageIcon seinaIcon = new ImageIcon(seina);
        BufferedImage lattiaPelaajaPaikalla = Kuvienkasittely.lataaKuva("lattiaPelaajaPaikalla.png");
        ImageIcon lattiaPelaajaPaikallaIcon = new ImageIcon(lattiaPelaajaPaikalla);
        BufferedImage lattiaMonsteriPaikalla = Kuvienkasittely.lataaKuva("lattiaMonsteriPaikalla.png");
        ImageIcon lattiaMonsteriPaikallaIcon = new ImageIcon(lattiaMonsteriPaikalla);
        BufferedImage lattiaTaistelu = Kuvienkasittely.lataaKuva("lattiaTaistelu.png");
        ImageIcon lattiaTaisteluIcon = new ImageIcon(lattiaTaistelu);
        BufferedImage lattiaAarrePaikalla = Kuvienkasittely.lataaKuva("lattiaAarrePaikalla.png");
        ImageIcon lattiaAarrePaikallaIcon = new ImageIcon(lattiaAarrePaikalla);
        
        laatat = new ImageIcon[6];
        laatat[0] = seinaIcon;
        laatat[1] = lattiaIcon;
        laatat[2] = lattiaPelaajaPaikallaIcon;
        laatat[3] = lattiaMonsteriPaikallaIcon;
        laatat[4] = lattiaTaisteluIcon;
        laatat[5] = lattiaAarrePaikallaIcon;
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
