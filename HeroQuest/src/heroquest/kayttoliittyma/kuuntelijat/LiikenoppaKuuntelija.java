
package heroquest.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import heroquest.PeliController;
/**
 * ActionListenerin toteuttava luokka liikkumisnopan heiton simulointia varten
 * 
 * 
 * @author Merioksan Mikko
 */
public class LiikenoppaKuuntelija implements ActionListener {
    private PeliController controller;
    
    public LiikenoppaKuuntelija(PeliController pc) {
        this.controller = pc;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.liikenopanHeitto();
    }
}
