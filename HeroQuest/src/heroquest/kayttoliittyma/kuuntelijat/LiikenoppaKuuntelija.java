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
 * @author merioksa
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