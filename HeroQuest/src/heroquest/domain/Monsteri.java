/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

/**
 *
 * @author merioksa
 */
public class Monsteri extends Olento {
    
    public Monsteri(int voima, int energia) {
        super(voima, energia);
    }
    
    public void liiku() {
        Ilmansuunta suunta = Ilmansuunta.satunnainen();
        Karttapala kohde = this.getSijainti().getNaapuri(suunta);
        
        if(!kohde.monsteriPaikalla()) {
            Karttapala vanha = this.getSijainti();
            if(this.setSijainti(kohde)) {
                vanha.monsteriPoistuu();
                kohde.monsteriSaapuu();
            }
        }
    }
}
