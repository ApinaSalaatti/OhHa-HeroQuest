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
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
import heroquest.domain.Pelaaja;
import heroquest.Peli;
/**
 *
 * @author Merioksan Mikko
 */
public class Kayttoliittyma implements Runnable {
    // valmis kartta testitarkoituksiin
    private static int[][] testiKartta = {  {0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 1, 1, 1, 1, 1, 1, 0},
                                            {0, 1, 0, 0, 0, 0, 0, 0},
                                            {0, 1, 1, 1, 0, 0, 0, 0},
                                            {0, 1, 0, 1, 0, 0, 0, 0},
                                            {0, 1, 0, 1, 1, 1, 1, 0},
                                            {0, 1, 1, 1, 0, 0, 1, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0}};
    private Kartta kartta;
    private Pelaaja pelaaja;
    private JFrame frame;
    private Peli peli;
    
    public Kayttoliittyma() {
    }
    
    @Override
    public void run() {
        frame = new JFrame("HeroQuest by Merioksan Mikko!");
        frame.setPreferredSize(new Dimension(800, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        // luodaan pelaaja kokeeksi. Tämä toteutetaan myöhemmin kysymällä pelaajalta ensin tietoja
        this.kartta = new Kartta(testiKartta);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container container) {
        int koko = kartta.getKartta().length;
        CardLayout layout = new CardLayout(1, 2);
        container.setLayout(layout);
        
        Aloituspaneeli aloitusPanel = new Aloituspaneeli();
        JPanel peliPanel = new JPanel(new GridLayout(1, 2));
        
        Karttapaneeli karttaPanel = new Karttapaneeli(kartta.getKartta());
        Tietopaneeli tietoPanel = new Tietopaneeli(pelaaja);
        peliPanel.add(karttaPanel);
        peliPanel.add(tietoPanel);
        
        container.add(aloitusPanel);
        container.add(peliPanel);
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
