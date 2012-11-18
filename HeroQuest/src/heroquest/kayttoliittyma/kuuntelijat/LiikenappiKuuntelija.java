/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma.kuuntelijat;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import heroquest.PeliController;
import heroquest.domain.Karttapala;
import heroquest.domain.Ilmansuunta;
import heroquest.kayttoliittyma.Pelipaneeli;
/**
 * ActionListenerin toteuttava luokka hahmon liikuttamista varten
 * 
 * @author Merioksan Mikko
 */
public class LiikenappiKuuntelija implements ActionListener {
    private PeliController controller;
    
    public LiikenappiKuuntelija(PeliController pc) {
        this.controller = pc;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("^")) {
            controller.pelaajanLiike(Ilmansuunta.POHJOINEN);
        }
        if(e.getActionCommand().equals("V")) {
            controller.pelaajanLiike(Ilmansuunta.ETELA);
        }
        if(e.getActionCommand().equals("<")) {
            controller.pelaajanLiike(Ilmansuunta.LANSI);
        }
        if(e.getActionCommand().equals(">")) {
            controller.pelaajanLiike(Ilmansuunta.ITA);
        }
    }
}
