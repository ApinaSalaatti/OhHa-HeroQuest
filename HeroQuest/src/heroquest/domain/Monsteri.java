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
     * Monsterin kuollessa pelaajan tienaamat kokemuspisteet.
     */
    private int expArvo;
    /**
     * Monsterin aivot, joiden avulla lasketaan suunta joka on lyhin reitti kohti pelaajaa.
     */
    private MonsterinAivot aivot;
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
        aivot = new MonsterinAivot();
        expArvo = 100;
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
    public Monsteri(int voima, int energia, int nopeus, int arvo) {
        super(voima, energia, nopeus);
        aivot = new MonsterinAivot();
        String nimi = Nimilista.getPelottavaNimi();
        this.setNimi(nimi);
        this.setExpArvo(arvo);
    }
    
    public int getExpArvo() {
        return expArvo;
    }
    public void setExpArvo(int arvo) {
        expArvo = arvo;
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
     * Metodi joka liikuttaa Monsteria yhden askeleen kohti pelaajaa. Oikea suunta lasketaan nerokkaan Aivo-luokan avulla.
     * 
     * @param p pelaaja jota kohti syöksytään
     */
    public void liiku(Pelaaja p, Karttapala[][] kartta) {
        Ilmansuunta suunta = aivot.annaSuunta(this, p, kartta);
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