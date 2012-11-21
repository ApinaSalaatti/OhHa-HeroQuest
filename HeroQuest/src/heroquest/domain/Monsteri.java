/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import heroquest.util.Nimilista;
/**
 * Olento-luokan perivä luokka, joka kuvaa kartalla liikkuvia vihaisia monstereita.
 * 
 * @author Merioksan Mikko
 */
public class Monsteri extends Olento {
    
    /**
     * Konstruktori, joka saa annettuna monsterin nimen.
     * 
     * @param nimi monsterin nimi
     * @param voima monsterin voima
     * @param energia monsterin energia
     * @param nopeus monsterin nopeus
     */
    public Monsteri(String nimi, int voima, int energia, int nopeus) {
        super(nimi, voima, energia, nopeus);
    }
    /**
     * Konstruktori, jolle ei anneta nimeä. Tällöin monsterin nimeksi arvotaan satunnainen pelottava nimi Nimilista-luokan avulla
     * 
     * @see heroquest.util.Nimilista#pelottavatNimet()
     * 
     * @param voima monsterin voima
     * @param energia monsterin energia
     * @param nopeus monsterin nopeus
     */
    public Monsteri(int voima, int energia, int nopeus) {
        super(voima, energia, nopeus);
        String nimi = Nimilista.getPelottavaNimi();
        this.setNimi(nimi);
    }
    
    /**
     * Monsteria yhden askeleen satunnaiseen suuntaan liikuttava metodi.
     * Mikäli valittuun suuntaan ei voi liikkua, ei monsteri tee mitään.
     */
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
    
    /**
     * Metodi, joka liikuttaa monsteria haluttuun suuntaan.
     * 
     * @param suunta suunta, johon monsterin tulee liikkua
     */
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
    public String otaVahinkoa(int vahinko) {
        muutaEnergia(-vahinko);
        return "Osuma! Kammottava monsteri " + getNimi() + " otti " + vahinko + " pistettä vahinkoa!\n";
    }
    
    @Override
    public String toString() {
        return this.getNimi() + " (V: " + this.getVoima() + " E: " + this.getEnergia() + ")";
    }
    
    @Override
    public String tallenna() {
        return super.tallenna() + "\n";
    }
}