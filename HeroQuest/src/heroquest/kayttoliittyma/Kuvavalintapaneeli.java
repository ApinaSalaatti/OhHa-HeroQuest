/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import heroquest.util.Tiedostoapuri;
/**
 * Simppeli käyttöliittymäkomponentti, joka yksinkertaisesti näyttää kaikki "naamat" kansiossa sijaitsevat kuvatiedostot, ja antaa valita niistä yhden.
 * 
 * @author Merioksan Mikko
 */
public class Kuvavalintapaneeli extends JPanel {
    /**
     * Kuvatiedostojen nimet taulukossa
     */
    String[] naamat;
    /**
     * Valitun kuvan indeksi naamat-taulussa.
     */
    int valittu;
    
    public Kuvavalintapaneeli() {
        luoKomponentit();
    }
    
    /**
     * Luodaan näkymän komponentit.
     */
    private void luoKomponentit() {
        naamat = Tiedostoapuri.kansioTauluksi("kuvat/naamat");
        valittu = 0;
        
        final CardLayout naamaLayout = new CardLayout(1, naamat.length);
        final JPanel naamaPanel = new JPanel(naamaLayout);
        
        // luodaan jokaiselle nassulle oma JPanel, jotka sijaitsevat CardLayoutin eri korteilla.
        for(String naama : naamat) {
            naamaPanel.add(new JLabel(new ImageIcon("kuvat/naamat/" + naama)), naama);
        }
        
        JButton vaihtonappi = luoKuvanvaihtonappi(naamaLayout, naamaPanel);
        
        this.add(new JLabel("Valitse naama:"));
        this.add(naamaPanel);
        this.add(vaihtonappi);
    }
    
    /**
     * Luodaan nappi jolla vaihdetaan valittua pelaajahahmon pärstäkuvaa, eli naamat-taulukon indeksiä.
     * 
     * @param naamaLayout kaikki kuvat sijaitsevat tällä CardLayoutilla
     * @param naamaPanel JPanel jolla naamat esitetään
     * @return 
     */
    public JButton luoKuvanvaihtonappi(final CardLayout naamaLayout, final JPanel naamaPanel) {
        JButton vaihtonappi = new JButton("Seuraava kuva");
        vaihtonappi.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               naamaLayout.next(naamaPanel);
               valittu++;
               if(valittu == naamat.length) {
                   valittu = 0;
               }
           }
        });
        
        return vaihtonappi;
    }
    
    /**
     * Palautetaan valittu kuvatiedosto merkkijonona.
     * 
     * @return kuvatiedoston nimi merkkijonona
     */
    public String getValittu() {
        return naamat[valittu];
    }
}
