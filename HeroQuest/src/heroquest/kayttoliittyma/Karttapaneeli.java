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

import heroquest.domain.Karttapala;
/**
 *
 * @author Merioksan Mikko
 */
// JPanel luokan alaluokka, jolle kartta tulostetaan
public class Karttapaneeli extends JPanel {
    private Karttapala[][] kartta;
    private ImageIcon lattiaIcon;
    private ImageIcon seinaIcon;
    
    public Karttapaneeli(Karttapala[][] k) {
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        // ladataan kartan piirtämiseen vaaditut kuvat
        BufferedImage lattia = lataaKuva("lattia.png");
        lattiaIcon = new ImageIcon(lattia);
        BufferedImage seina = lataaKuva("seina.png");
        seinaIcon = new ImageIcon(seina);
    }
    
    // "piirretään" kartta käyttöliittymään
    public void piirraKartta(Karttapala[][] k) {
        this.kartta = k;
        this.setLayout(new GridLayout(kartta.length, kartta[0].length));
        for(int y = 0; y < kartta.length; y++) {
            for(int x = 0; x < kartta[0].length; x++) {
                if(kartta[y][x] != null) {
                    this.add(new JLabel(lattiaIcon));
                }
                else {
                    this.add(new JLabel(seinaIcon));
                }
            }
        }
    }
    
    private BufferedImage lataaKuva(String nimi) {
        String polku = "src/kuvat/" + nimi;
        BufferedImage kuva = null;
        try {
            kuva = ImageIO.read(new File(polku));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return kuva;
    }
}
