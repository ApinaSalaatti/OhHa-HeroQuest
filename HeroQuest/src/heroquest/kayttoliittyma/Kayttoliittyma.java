/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
import heroquest.domain.Pelaaja;
/**
 *
 * @author Merioksan Mikko
 */
public class Kayttoliittyma implements Runnable {
    private Kartta kartta;
    private Pelaaja pelaaja;
    private JFrame frame;
    
    public Kayttoliittyma() {
        this.kartta = new Kartta(6);
    }
    
    @Override
    public void run() {
        frame = new JFrame("HeroQuest by Merioksan Mikko!");
        frame.setPreferredSize(new Dimension(800, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        pelaaja = new Pelaaja(5, 5, "Taikamaagivelho");
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container container) {
        int koko = kartta.getKartta().length;
        GridLayout karttaLayout = new GridLayout(koko, koko);
        GridLayout layout = new GridLayout(1, 2);
        
        container.setLayout(layout);
        
        // siirrä omaan karttapaneeli-luokkaan
        JPanel karttaPanel = new JPanel(karttaLayout);
        
        BufferedImage lattia = lataaKuva("lattia.png");
        ImageIcon lattiaIcon = new ImageIcon(lattia);
        BufferedImage seina = lataaKuva("seina.png");
        ImageIcon seinaIcon = new ImageIcon(seina);
        
        for(int y = 0; y < koko; y++) {
            for(int x = 0; x < koko; x++) {
                if(kartta.getKartta()[y][x] != null) {
                    karttaPanel.add(new JLabel(lattiaIcon));
                }
                else {
                    karttaPanel.add(new JLabel(seinaIcon));
                }
            }
        }
        // --------------------------------------
        
        // siirrä omaan pelaajadatapaneeli-luokkaan
        JPanel pelaajaPanel = new JPanel();
        
        pelaajaPanel.add(new JLabel(pelaaja.getNimi()));
        // --------------------------------------
        
        container.add(karttaPanel);
        container.add(pelaajaPanel);
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
