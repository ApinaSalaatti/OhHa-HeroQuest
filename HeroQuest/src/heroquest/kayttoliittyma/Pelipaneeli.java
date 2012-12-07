
package heroquest.kayttoliittyma;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Container;

import heroquest.PeliController;

/**
 * Pelin eri näkymien näyttämistä säätelevä komponentti.
 * 
 * @author Merioksan Mikko
 */
public class Pelipaneeli extends JPanel {
    /**
     * Paneeli, joka näyttää pelaajalle kaikki kodin toiminnot.
     */
    private Kotipaneeli koti;
    /**
     * Paneeli, jolle tulostetaan kulloinkin seikkailun kohteena oleva luolasto.
     */
    private Karttapaneeli kartta;
    /**
     * Paneeli, jolla on kaksi näkymää: koti ja luolasto.
     */
    private JPanel nakymaPanel;
    /**
     * CardLayout, jonka avulla kodin ja luolaston välillä vaihdellaan.
     */
    private CardLayout nakymaLayout;
    /**
     * Paneeli, jolle kaikki pelaajan tiedot ja näppäimet tulostetaan.
     */
    private Tietopaneeli tiedot;
    /**
     * Koko peliruudun kokoisia näkymiä hallinnoiva CardLayout. Esim kuollessa tämä vaihtuu kuolinruuduksi.
     */
    private CardLayout layout;
    /**
     * Koko ikkunan Container, jota tarvitaan kun koko ikkunan näkymiä vaihdetaan.
     */
    private Container container;
    /**
     * Kontrolleri, jolta haetaan ja jolle lähetetään pelitapahtumia.
     */
    private PeliController controller;
    
    public Pelipaneeli(Kotipaneeli koti, Karttapaneeli k, Tietopaneeli t, CardLayout layout, Container container, PeliController pc) {
        this.koti = koti;
        this.kartta = k;
        this.tiedot = t;
        this.layout = layout;
        this.container = container;
        this.controller = pc;
        luoKomponentit();
    }
    
    /**
     * Luodaan näkymän komponentit.
     */
    private void luoKomponentit() {
        this.setLayout(new GridLayout(1, 2));
        
        nakymaLayout = new CardLayout(1, 2);
        nakymaPanel = new JPanel(nakymaLayout);
        nakymaPanel.add(koti, "koti");
        nakymaPanel.add(kartta, "luolasto");

        
        this.add(nakymaPanel);
        this.add(tiedot);
    }
    
    /**
     * Päivitetään näkymä parametrina saadun tapahtuman mukaisesti.
     * @param tapahtuma viimeisimmät pelitapahtumat
     */
    public void paivita(String tapahtuma) {
        if(controller.getTila().equals("koti")) {
            nakymaLayout.show(nakymaPanel, "koti");
        }
        else {
            nakymaLayout.show(nakymaPanel, "luolasto");
        }
        
        if(controller.getTila().equals("voitto") || controller.getTila().equals("kuolema")) {
            layout.show(container, "lopetus");
        }
        
        koti.paivita();
        if(!controller.getTila().equals("koti")) {
            kartta.piirraKartta();
        }
        tiedot.paivitaTiedot(tapahtuma);
    }
}
