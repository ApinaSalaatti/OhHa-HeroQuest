
package heroquest.kayttoliittyma;

import java.util.Collection;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;

import heroquest.PeliController;
import heroquest.Saavutus;
/**
 * Pelaajan saavuttamat saavutukset (Achievement) esittävä paneeli.
 * 
 * @author Merioksan Mikko
 */
public class Saavutuspaneeli extends JPanel {
    private PeliController controller;
    private JTextArea saavutukset;
    
    public Saavutuspaneeli(PeliController pc) {
        this.controller = pc;
        luoKomponentit();
    }
    
    /**
     * Luodaan näkymän komponentit.
     */
    private void luoKomponentit() {
        this.setLayout(new BorderLayout());
        saavutukset = new JTextArea();
        saavutukset.setEditable(false);
        
        EtchedBorder eb = new EtchedBorder();
        TitledBorder tb = BorderFactory.createTitledBorder(eb, "Saavutuksesi");
        this.setBorder(tb);
        
        this.add(saavutukset);
    }
    
    /**
     * Päivitetään näkymä pelaajan uusilla saavutuksilla.
     */
    public void paivita() {
        Collection<Saavutus> saavutukset = controller.getSaavutukset();
        
        StringBuilder sb = new StringBuilder();
        for(Saavutus s : saavutukset) {
            sb.append(s.toString() + "\n");
        }
        this.saavutukset.setText(sb.toString());
    }
}
