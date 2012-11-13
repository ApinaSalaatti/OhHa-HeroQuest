/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.domain;

/**
 *
 * @author Merioksan Mikko
 */
public class Pelaaja extends Olento {
    private String luokka;
    private int liikkeet;
    
    public Pelaaja(String nimi, int voima, int energia, String luokka) {
        super(nimi, voima, energia);
        setLuokka(luokka);
        liikkeet = 0;
    }
    public Pelaaja(int voima, int energia, String luokka) {
        super(voima, energia);
        setLuokka(luokka);
    }
    
    private void setLuokka(String luokka) {
        luokka = luokka.trim();
        if(luokka.length() > 0) {
            this.luokka = luokka;
        }
        else {
            this.luokka = "Defaultsoturi";
        }
    }
    public String getLuokka() {
        return luokka;
    }
    
    public void setLiikkeet(int l) {
        liikkeet = l;
    }
    public int getLiikkeet() {
        return liikkeet;
    }
    public void liiku(Ilmansuunta suunta) {
        Karttapala vanha = this.getSijainti();
        if(this.setSijainti(vanha.getNaapuri(suunta))) {
            vanha.pelaajaPoistuu();
            this.getSijainti().pelaajaSaapuu();
            liikkeet--;
        }
    }
    
    
    /*
     * Taistelumetodit:
     *  - hyökätessä pelaaja heittää voimansa verran noppia ja osuu jos noppa on viisi tai kuusi
     *  - puolustaessa pelaaja heittää voimansa verran noppia ja puolusutus onnistuu jos noppa on viisi tai kuusi
     */
    @Override
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
    
    @Override
    public int puolustaudu() {
        int torjunnat = 0;
        for(int i = 0; i < this.getVoima(); i++) {
            int heitto = random.nextInt(6) + 1;
            if(heitto > 4) {
                torjunnat++;
            }
        }
        return torjunnat;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNimi() + " (" + this.getLuokka() + ")\n");
        sb.append("Voima: " + this.getVoima() + "\n");
        sb.append("Energia: ");
        for(int i = 0; i < this.getEnergia(); i++) {
            sb.append("*");
        }
        
        return sb.toString();
    }
    
}
