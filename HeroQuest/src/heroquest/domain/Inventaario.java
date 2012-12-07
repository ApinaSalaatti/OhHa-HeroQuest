/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import heroquest.domain.kauppa.Tavara;
import java.util.List;
import java.util.ArrayList;
/**
 * Olentojen hallussa olevien tavaroiden ja varusteiden säilytykseen tarkoitettu luokka
 * 
 * @author Merioksan Mikko
 */
public class Inventaario {
    /**
     * Tavarat ArrayList-tietorakenteessa
     */
    private List<Tavara> tavarat;
    
    public Inventaario() {
        this.tavarat = new ArrayList<Tavara>();
    }
    
    /**
     * Palautetaan kaikki inventaariossa olevat tavarat.
     * 
     * @return tavarat List-rajapinnan toteuttavana listana
     */
    public List<Tavara> getTavarat() {
        return tavarat;
    }
    
    /**
     * Metodi joka lisää inventaarioon annetun tavaran
     * 
     * @param tavara lisättävä tavara
     */
    public void lisaaTavara(Tavara tavara) {
        if(tavara != null && tavara.getNimi().length() > 0) {
            tavarat.add(tavara);
        }
    }
    
    /**
     * Metodi jonka avulla poistetaan listasta haluttu tavara
     * 
     * @param tavara poistettava tavara
     */
    public boolean poistaTavara(Tavara tavara) {
        return tavarat.remove(tavara);
    }
    
    /**
     * Tallennetaan inventaarion sisältö, ts. palautetaan kaikki tavarat String-muotoisina.
     * 
     * @return kaikki inventaarion tavarat String-muuttujassa
     */
    public String tallenna() {
        String inv = "";
        for(int i = 0; i < tavarat.size(); i++) {
            if(i > 0) {
                inv += ";";
            }
            inv += tavarat.get(i).tallenna();
        }
        inv += "\n";
        return inv;
    }
}
