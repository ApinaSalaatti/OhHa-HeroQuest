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
    
    public void setLuokka(String luokka) {
        luokka = luokka.trim();
        if(luokka.length() > 0) {
            this.luokka = luokka;
        }
        else {
            this.luokka = "Defaultluokka";
        }
    }
    public String getLuokka() {
        return luokka;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNimi() + " (" + this.getLuokka() + ")\n");
        sb.append("Voima: " + this.getVoima());
        
        return sb.toString();
    }
}
