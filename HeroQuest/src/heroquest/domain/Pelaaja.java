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
    
    public Pelaaja(String nimi, int voima, int energia, String luokka) {
        super(nimi, voima, energia);
        setLuokka(luokka);
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
    
    public void liiku(Ilmansuunta suunta) {
        Karttapala vanha = this.getSijainti();
        if(this.setSijainti(this.getSijainti().getNaapuri(suunta))) {
            vanha.pelaajaPoistuu();
            this.getSijainti().pelaajaSaapuu();
        }
    }
}
