/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

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
    private List<String> tavarat;
    
    public Inventaario() {
        this.tavarat = new ArrayList<String>();
    }
    
    /**
     * @return tavarat List-rajapinnan toteuttavana listana
     */
    public List<String> getTavarat() {
        return tavarat;
    }
    /**
     * @return tavaroiden nimet String-taulukkona 
     */
    public String[] getTavaratTaulukkona() {
        String[] palautus = new String[tavarat.size()];
        int i = 0;
        for(String t : tavarat) {
            palautus[i] = t;
            i++;
        }
        return palautus;
    }
    
    /**
     * Metodi joka lisää inventaarioon annetun tavaran
     * 
     * @param tavara lisättävä tavara
     */
    public void lisaaTavara(String tavara) {
        tavarat.add(tavara);
    }
    
    /**
     * Metodi jonka avulla poistetaan listasta haluttu tavara
     * 
     * @param tavara poistettava tavara
     */
    public void poistaTavara(String tavara) {
        tavarat.remove(tavara);
    }
}
