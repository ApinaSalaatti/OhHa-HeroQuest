/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.lang.StringBuilder;

import heroquest.PeliController;
import heroquest.domain.kauppa.Tavara;

/**
 * Luokka, joka pitää kirjaa pelaajan suorittamista saavutuksista. Kaikkihan tykkää saavutuksista!
 * 
 * @author Merioksan Mikko
 */
public class Saavutusmanageri {
    /**
     * Saavutukset näppärästi listattuna. Avaimena toimii saavutuksen nimi.
     */
    private Map<String, Saavutus> saavutukset;
    /**
     * PeliController-luokan olio josta tarkkaillaan monenlaisia tilastoja.
     */
    private PeliController controller;
    
    public Saavutusmanageri(PeliController pc) {
        saavutukset = new HashMap<String, Saavutus>();
        controller = pc;
        alustaSaavutukset();
    }
    
    private void alustaSaavutukset() {
        saavutukset.put("Aloita peli", new Saavutus("Aloita peli"));
        saavutukset.put("Astu synkkään luolaan", new Saavutus("Astu synkkään luolaan"));
        saavutukset.put("Murhaa kammottava hirviö", new Saavutus("Murhaa kammottava hirviö"));
        saavutukset.put("Kerää ensimmäinen aarteesi", new Saavutus("Kerää ensimmäinen aarteesi"));
    }
    
    public Collection<Saavutus> getSaavutukset() {
        return saavutukset.values();
    }
    
    public String tarkistaSaavutukset(String tapahtuma) {
        StringBuilder sb = new StringBuilder();
        
        if(controller.getPeli().pelaajaKotona() && !saavutukset.get("Aloita peli").saavutettu()) {
            sb.append(saavutukset.get("Aloita peli").saavuta());
        }
        if(!controller.getPeli().pelaajaKotona() && !saavutukset.get("Astu synkkään luolaan").saavutettu()) {
            sb.append(saavutukset.get("Astu synkkään luolaan").saavuta());
        }
        if(tapahtuma.contains("Osuma!") && !saavutukset.get("Murhaa kammottava hirviö").saavutettu()) {
            sb.append(saavutukset.get("Murhaa kammottava hirviö").saavuta());
        }
        if(controller.getPeli().getPelaaja().getInventaario().getTavarat().contains(new Tavara("arvokasaarre.hqt")) && !saavutukset.get("Kerää ensimmäinen aarteesi").saavutettu()) {
            sb.append(saavutukset.get("Kerää ensimmäinen aarteesi").saavuta());
        }
        
        return sb.toString();
    }
}
