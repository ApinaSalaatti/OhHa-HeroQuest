/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import heroquest.Peli;
import heroquest.domain.Karttapala;
import heroquest.util.Kuvienkasittely;
/**
 *
 * @author Merioksan Mikko
 */
// JPanel luokan alaluokka, jolle kartta tulostetaan
public class Karttapaneeli extends JPanel {
    private Peli peli;
    private ImageIcon lattiaIcon;
    private ImageIcon seinaIcon;
    private ImageIcon lattiaPelaajaPaikallaIcon;
    
    public Karttapaneeli() {
        luoKomponentit();
    }
    
    public void setPeli(Peli peli) {
        this.peli = peli;
    }
    
    private void luoKomponentit() {
        // ladataan kartan piirtämiseen vaaditut kuvat
        BufferedImage lattia = Kuvienkasittely.lataaKuva("lattia.png");
        lattiaIcon = new ImageIcon(lattia);
        BufferedImage seina = Kuvienkasittely.lataaKuva("seina.png");
        seinaIcon = new ImageIcon(seina);
        BufferedImage lattiaPelaajaPaikalla = Kuvienkasittely.lataaKuva("lattiaPelaajaPaikalla.png");
        lattiaPelaajaPaikallaIcon = new ImageIcon(lattiaPelaajaPaikalla);
    }
    
    // "piirretään" kartta käyttöliittymään
    public void piirraKartta() {
        Karttapala[][] kartta = peli.getKartta().getKarttapalat();
        this.removeAll();
        this.setLayout(new GridLayout(kartta.length, kartta[0].length));
        for(int y = 0; y < kartta.length; y++) {
            for(int x = 0; x < kartta[0].length; x++) {
                if(kartta[y][x] != null) {
                    if(!kartta[y][x].pelaajaPaikalla()) {
                        this.add(new JLabel(lattiaIcon));
                    }
                    else {
                        this.add(new JLabel(lattiaPelaajaPaikallaIcon));
                    }
                }
                else {
                    this.add(new JLabel(seinaIcon));
                }
            }
        }
    }
}
