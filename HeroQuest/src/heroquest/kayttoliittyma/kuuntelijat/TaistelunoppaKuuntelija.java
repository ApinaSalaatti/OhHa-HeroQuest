/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import heroquest.PeliController;
/**
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
