/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.kayttoliittyma.kuuntelijat;

import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import heroquest.Peli;
import heroquest.kayttoliittyma.Pelipaneeli;
/**
 *
 * @author merioksa
 */
public class LiikenoppaKuuntelija implements ActionListener {
    private Random random;
    private Pelipaneeli kohde;
    private Peli peli;
    
    public LiikenoppaKuuntelija(Peli p, Pelipaneeli k) {
        this.random = new Random();
        this.peli = p;
        this.kohde = k;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int heitto = random.nextInt(6) + 1;
        peli.getPelaaja().setLiikkeet(heitto);
        
        kohde.paivita("Mainio heitto: " + heitto + "\n");
    }
}
