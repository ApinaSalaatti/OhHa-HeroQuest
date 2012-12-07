
package heroquest.kayttoliittyma;

import java.util.List;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import heroquest.PeliController;
import heroquest.domain.kauppa.MyyntiTavara;
import heroquest.KotiController;

/**
 * Käyttöliittymäkomponentti, joka näyttää pelaajan kodin kaupan tarjonnan.
 * 
 * @author Merioksan Mikko
 */
public class Kauppapaneeli extends JPanel {
    /**
     * Kontrolleri, jolta haetaan kaupan tavarat.
     */
    private PeliController controller;
    /**
     * JList jossa kaupan tavarat näytetään.
     */
    private JList kaupanTavarat;
    
    public Kauppapaneeli(PeliController pc) {
        this.controller = pc;
        luoKomponentit();
    }
    
    /**
     * Luodaan näkymän komponentit.
     */
    private void luoKomponentit() {
        this.setLayout(new BorderLayout());
        
        EtchedBorder eb = new EtchedBorder();
        TitledBorder tb = BorderFactory.createTitledBorder(eb, "Kauppa");
        this.setBorder(tb);
        
        kaupanTavarat = new JList();
        kaupanTavarat.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JButton ostamisnappi = new JButton("Osta");
        ostamisnappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getKoti().osta((MyyntiTavara)kaupanTavarat.getSelectedValue());
            }
        });
        
        this.add(kaupanTavarat);
        this.add(ostamisnappi, BorderLayout.SOUTH);
    }
    
    /**
     * Päivitetään kaupan tavarat.
     */
    public void paivita() {
        KotiController koti = controller.getKoti();
        List<MyyntiTavara> tavarat = koti.getKaupanTavarat();
        kaupanTavarat.setListData(tavarat.toArray());
    }
}
