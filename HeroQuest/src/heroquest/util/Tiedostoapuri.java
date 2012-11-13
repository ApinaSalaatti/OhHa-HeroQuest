/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.util;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Merioksan Mikko
 * 
 * Apuluokka helpottamaan tiedostojen avaamista, lukemista ja kirjoittamista.
 * 
 */
public class Tiedostoapuri {
    
    // palauttaa String-taulun joka sisältää kansion sisältämien tiedostojen nimet
    public static String[] kansioTauluksi(String polku) {
        File kansio = new File(polku);
        File[] tiedostot = kansio.listFiles();
        String[] tiedostotStr = new String[tiedostot.length];
        
        int indx = 0;
        for(File t : tiedostot) {
            tiedostotStr[indx] = t.getName();
            indx++;
        }
        
        return tiedostotStr;
    }
    
    // palauttaa Scanner-olion johon on luettu parametrina annetun tiedoston sisältö
    public static Scanner tiedostoLukijaan(String polku) {
        try {
            File tiedosto = new File(polku);
            Scanner lukija = new Scanner(tiedosto);
            return lukija;
        }
        catch(Exception e) {
            // jokin meni vikaan, sulkeudutaan :-(
            System.exit(-1);
        }
        return null;
    }
}
