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
     * @return tavarat List-rajapinnan toteuttavana listana
     */
    public List<Tavara> getTavarat() {
        return tavarat;
    }
    /**
     * @return tavaroiden nimet String-taulukkona 
     */
    public String[] getTavaratTaulukkona() {
        String[] palautus = new String[tavarat.size()];
        int i = 0;
        for(Tavara t : tavarat) {
            palautus[i] = t.toString();
            i++;
        }
        return palautus;
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
    public void poistaTavara(Tavara tavara) {
        tavarat.remove(tavara);
    }
}
