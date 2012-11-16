/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Container;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import heroquest.PeliController;
/**
 *
 * @author Merioksan Mikko
 */
public class Ylavalikko extends JMenuBar {
    private Container container;
    private CardLayout nakyma;
    private PeliController controller;
    
    public Ylavalikko(Container container, CardLayout layout, PeliController controller) {
        this.container = container;
        this.nakyma = layout;
        this.controller = controller;
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        JMenu tiedostoValikko = new JMenu("Tiedosto");
        
        JMenuItem uusiPeli = new JMenuItem("Uusi peli");
        JMenuItem tallenna = new JMenuItem("Tallenna");
        JMenuItem lataa = new JMenuItem("Lataa tallennus");
        JMenuItem poistu = new JMenuItem("Poistu");
        
        uusiPeli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nakyma.show(container, "aloitus");
            }
        });
        
        poistu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        tiedostoValikko.add(uusiPeli);
        tiedostoValikko.add(tallenna);
        tiedostoValikko.add(lataa);
        tiedostoValikko.add(poistu);
        this.add(tiedostoValikko);
    }
}
