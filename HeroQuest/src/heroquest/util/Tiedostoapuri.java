/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.util;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import heroquest.Peli;
import heroquest.domain.Karttapala;

/**
 * Apuluokka helpottamaan tiedostojen avaamista, lukemista ja kirjoittamista.
 * 
 * @author Merioksan Mikko
 * 
 */
public class Tiedostoapuri {
    
    /**
     * @param polku kansio jonka tiedostot halutaan tulostaa
     *
     * @return String-taulu joka sisältää kansion sisältämien tiedostojen nimet
     */
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
    
    /**
     * Metodi, joka palauttaa Scanner-olion johon on luettu parametrina annetun tiedoston sisältö
     * 
     * @param polku tiedosto joka luetaan
     * @return Scanner-olio, johon tiedoston sisältö on luettu.
     */
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
    
    /**
     * Kirjoitettaa annetut tiedot annetun nimiseen tiedostoon. Tiedosto sijoitetaan tallennukset-kansioon.
     * 
     * @param kirjoitettava pelidata joka tiedostoon kirjoitetaan
     * @param tiedostonimi tallennettavan tiedoston nimi
     */
    public static void tallennaPeli(String kirjoitettava, String tiedostonimi) {
        String polku = "tallennukset/" + tiedostonimi + ".hqs";
        try {
            FileWriter fw = new FileWriter(polku);
            fw.write(kirjoitettava);
            fw.close();
        }
        catch(Exception e) {
            System.out.println("Tallennus epäonnistui!");
        }
    }
   
}
