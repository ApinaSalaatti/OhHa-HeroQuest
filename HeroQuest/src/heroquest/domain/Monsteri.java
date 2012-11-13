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
    
    // monsteria satunnaiseen suuntaan liikuttava metodi
    public void liiku() {
        Ilmansuunta suunta = Ilmansuunta.satunnainen();
        Karttapala kohde = this.getSijainti().getNaapuri(suunta);
        
        if(kohde != null && !kohde.monsteriPaikalla()) {
            Karttapala vanha = this.getSijainti();
            if(this.setSijainti(kohde)) {
                vanha.monsteriPoistuu();
                kohde.monsteriSaapuu(this);
            }
        }
    }
    
    // monsteria haluttuun suuntaan liikuttava metodi
    public void liiku(Ilmansuunta suunta) {
        Karttapala vanha = this.getSijainti();
        Karttapala kohde = this.getSijainti().getNaapuri(suunta);
        
        if(kohde != null && !kohde.monsteriPaikalla()) {
            if(this.setSijainti(vanha.getNaapuri(suunta))) {
                vanha.monsteriPoistuu();
                this.getSijainti().monsteriSaapuu(this);
            }
        }
    }
    
    /*
     * Taistelumetodit:
     *  - hyökätessä monsteri heittää voimansa verran noppia ja osuu jos noppa on kuusi
     *  - puolustaessa monsteri heittää voimansa verran noppia ja puolustus onnistuu jos noppa on kuusi
     */
    @Override
    public int hyokkaa() {
        int iskut = 0;
        for(int i = 0; i < this.getVoima(); i++) {
            int heitto = random.nextInt(6) + 1;
            if(heitto > 5) {
                iskut++;
            }
        }
        return iskut;
    }
    
    @Override
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
        return this.getNimi() + " (V: " + this.getVoima() + " E: " + this.getEnergia() + ")";
    }
}