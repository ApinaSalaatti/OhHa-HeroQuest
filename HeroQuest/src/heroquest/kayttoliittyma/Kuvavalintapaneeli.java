/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Container;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import heroquest.util.Tiedostoapuri;
/**
 *
 * @author Merioksan Mikko
 */
public class Kuvavalintapaneeli extends JPanel {
    String[] naamat;
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
