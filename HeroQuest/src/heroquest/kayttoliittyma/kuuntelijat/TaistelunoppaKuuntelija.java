
package heroquest.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import heroquest.PeliController;
/**
 * ActionListener, joka yksinkertaisesti kutsuu controllerin taistelu-metodia.
 * 
 * @author Merioksan Mikko
 */
public class TaistelunoppaKuuntelija implements ActionListener {
    private PeliController controller;
    
    public TaistelunoppaKuuntelija(PeliController pc) {
        this.controller = pc;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.taistele();
    }
}
