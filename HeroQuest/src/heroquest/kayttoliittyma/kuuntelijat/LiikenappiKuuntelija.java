/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma.kuuntelijat;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import heroquest.Peli;
import heroquest.domain.Karttapala;
import heroquest.domain.Ilmansuunta;
import heroquest.kayttoliittyma.Pelipaneeli;
/**
 *
 * @author Merioksan Mikko
 */
// ActionListener-luokka näppäimille jotka
public class LiikenappiKuuntelija implements ActionListener {
    private Peli peli;
    private Pelipaneeli kohde;
    
    public LiikenappiKuuntelija(Peli p, Pelipaneeli kohde) {
        this.peli = p;
        this.kohde = kohde;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Karttapala vanha = peli.getPelaaja().getSijainti();
        
        if(e.getActionCommand().equals("^")) {
            peli.pelaajanLiike(Ilmansuunta.POHJOINEN);
        }
        if(e.getActionCommand().equals("V")) {
            peli.pelaajanLiike(Ilmansuunta.ETELA);
        }
        if(e.getActionCommand().equals("<")) {
            peli.pelaajanLiike(Ilmansuunta.LANSI);
        }
        if(e.getActionCommand().equals(">")) {
            peli.pelaajanLiike(Ilmansuunta.ITA);
        }
        Karttapala uusi = peli.getPelaaja().getSijainti();
        
        String viesti = "";
        if(vanha.equals(uusi)) {
            viesti = "Ei sinne voi liikkua! >:-(\n";
        }
        else {
            viesti = uusi.toString();
        }
        
        kohde.paivita(viesti);
        
        if(peli.getPelaaja().getLiikkeet() == 0) {
            peli.lopetaVuoro();
            viesti = "Pelottavat monsterit liikkuvat pimeässä...";
            kohde.paivita(viesti);
        }
    }
}
