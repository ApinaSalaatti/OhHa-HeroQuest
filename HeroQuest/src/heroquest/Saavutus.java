package heroquest;

/**
 * Yhtä pelaajan tavoittelemaa saavutusta kuvaava luokka.
 * 
 * @author Merioksan Mikko
 */
public class Saavutus {
    /**
     * Saavutuksen nimi, jotain kuvaavaa kuten "Silvo 1000 monsteria".
     */
    private String nimi;
    /**
     * Tieto, onko saavutus saavutettu
     */
    private boolean saavutettu;
    
    public Saavutus(String n) {
        this.nimi = n;
        this.saavutettu = false;
    }
    
    /**
     * Asetetaan saavutus saavutetuksi.
     */
    public String saavuta() {
        saavutettu = true;
        return "Saavutus avattu: " + nimi + "!";
    }
    /**
     * Palautetaan tieto siitä, onko saavutus jo saavutettu
     * 
     * @return saavutuksen tila
     */
    public boolean saavutettu() {
        return saavutettu;
    }
    
    @Override
    public String toString() {
        if(saavutettu) {
            return nimi + ": SAAVUTETTU!";
        }
        else {
            return nimi;
        }
    }

}
