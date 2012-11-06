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
    
    public Pelaaja(int voima, int energia, String luokka) {
        super(voima, energia);
        this.luokka = luokka;
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
