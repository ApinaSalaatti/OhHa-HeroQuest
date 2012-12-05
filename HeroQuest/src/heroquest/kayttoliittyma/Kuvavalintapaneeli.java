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
    
    private void luoKomponentit() {
        naamat = Tiedostoapuri.kansioTauluksi("src/kuvat/naamat");
        valittu = 0;
        
        final CardLayout naamaLayout = new CardLayout(1, naamat.length);
        final JPanel naamaPanel = new JPanel(naamaLayout);
        
        for(String naama : naamat) {
            naamaPanel.add(new JLabel(new ImageIcon("src/kuvat/naamat/" + naama)), naama);
        }
        
        // nappi, joka vaihtaa esitetyn kuvan sekä valitun kuvan indeksin.
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
        
        this.add(new JLabel("Valitse naama:"));
        this.add(naamaPanel);
        this.add(vaihtonappi);
    }
    
    public String getValittu() {
        return naamat[valittu];
    }
}
