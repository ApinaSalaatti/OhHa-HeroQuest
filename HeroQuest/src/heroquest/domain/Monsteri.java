/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import heroquest.util.Nimilista;
/**
 *
 * @author merioksa
 */
public class Monsteri extends Olento {
    
    public Monsteri(int voima, int energia) {
        super(voima, energia);
        String nimi = Nimilista.getPelottavaNimi();
        this.setNimi(nimi);
    }
    
    public void liiku() {
        Ilmansuunta suunta = Ilmansuunta.satunnainen();
        Karttapala kohde = this.getSijainti().getNaapuri(suunta);
        
        if(!kohde.monsteriPaikalla()) {
            Karttapala vanha = this.getSijainti();
            if(this.setSijainti(kohde)) {
                vanha.monsteriPoistuu();
                kohde.monsteriSaapuu(this);
            }
        }
    }
    
    public int hyokkaa() {
        int iskut = 0;
        for(int i = 0; i < this.getVoima(); i++) {
            int heitto = random.nextInt(6) + 1;
            if(heitto > 4) {
                iskut++;
            }
        }
        return iskut;
    }
    
    public int puolustaudu() {
        int torjunnat = 0;
        for(int i = 0; i < this.getVoima(); i++) {
            int heitto = random.nextInt(6) + 1;
            if(heitto > 5) {
                torjunnat++;
            }
        }
        return torjunnat;
    }
    
    @Override
    public String toString() {
        return this.getNimi();
    }
}