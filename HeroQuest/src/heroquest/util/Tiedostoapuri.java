/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.util;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;



import heroquest.Peli;
import heroquest.PeliController;
import heroquest.domain.kauppa.Kauppa;
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
    public static Scanner tiedostoLukijaan(String polku) throws Exception {
        File tiedosto = new File(polku);
        Scanner lukija = new Scanner(tiedosto);
        return lukija;
    }
    
    /**
     * Kirjoitettaa annetut tiedot annetun nimiseen tiedostoon. Tiedosto sijoitetaan tallennukset-kansioon.
     * 
     * @param pc PeliController, jonka antamat tiedot tulostetaan
     * @param tiedostonimi tallennettavan tiedoston nimi
     */
    public static void tallennaPeli(PeliController pc, String tiedostonimi) {
        String turvallinen = turvallista(tiedostonimi);
        Peli peli = pc.getPeli();
        Kauppa kauppa = pc.getKoti().getKauppa();
        String kohde = "tallennukset/" + turvallinen + "/";
        new File(kohde).mkdir();
        new File(kohde + "kartat/").mkdir();
        try {
            // pelaajan tiedot
            FileWriter fw = new FileWriter(kohde + "pelaaja.hqs");
            fw.write(peli.getPelaaja().tallenna());
            fw.close();
            
            // kaupan tiedot
            fw = new FileWriter(kohde + "kauppa.hqs");
            fw.write(kauppa.tallenna());
            fw.close();
            
            // karttojen tiedot
            String[] kartat = kansioTauluksi("pelidata/kartat/");
            for(String kartta : kartat) {
                fw = new FileWriter(kohde + "kartat/" + kartta);
                Scanner lukija = tiedostoLukijaan("pelidata/kartat/" + kartta);
                while(lukija.hasNextLine()) {
                    fw.write(lukija.nextLine() + "\n");
                }
                fw.close();
            }
        }
        catch(Exception e) {
            System.out.println("Kammottava virhe!");
            e.printStackTrace();
        }
    }
    
    /**
     * Metodi, joka muokkaa annetun merkkijonon "turvalliseksi", eli poistaa siitä esim merkit joita ei voi käyttää kansion nimessä
     * 
     * @param str muokattava merkkijono
     * @return muokattu merkkijono
     */
    public static String turvallista(String str) {
        str = str.replace('!', '_');
        str = str.replace('?', '_');
        str = str.replace('<', '_');
        str = str.replace('>', '_');
        str = str.replace('"', '_');
        str = str.replace('/', '_');
        str = str.replace('\\', '_');
        str = str.replace(':', '_');
        str = str.replace('*', '_');
        str = str.replace('|', '_');
        
        return str;
    }
    
    /**
     * Tallennettaan väliaikainen pelidata.
     */
    public static void tallennaData(Peli peli) {
        if(peli.getKartta() != null) {
            Scanner lukija = new Scanner(peli.getKartta().tallenna());
            String kohde = "pelidata/kartat/" + lukija.nextLine();

            try {
                FileWriter fw = new FileWriter(kohde);
                while(lukija.hasNextLine()) {
                    fw.write(lukija.nextLine() + "\n");
                }
                fw.close();

            }
            catch(Exception e) {
                System.out.println("Kammottava virhe!");
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Kopioidaan karttojen ja pelaajan alkudata "pelidata"-nimiseen tallennuskansioon.
     * Jos parametri "tallennus" on tyhjä, luetaan data alkuperäistiedostoista.
     */
    public static void kopioiData(Peli peli, String tallennus) {
        String kohde = "pelidata/";
        String polku;
        if(tallennus.equals("")) {
            polku = "src/kartat/";
        }
        else {
            polku = "tallennukset/" + tallennus + "/kartat/";
        }
        
        String[] kartat = kansioTauluksi(polku);
        
        try {
            FileWriter fw = new FileWriter(kohde + "pelaaja.hqs");
            fw.write(peli.getPelaaja().tallenna());
            fw.close();
            
            for(String kartta : kartat) {
                fw = new FileWriter(kohde + "kartat/" + kartta);
                Scanner lukija = tiedostoLukijaan(polku + kartta);
                while(lukija.hasNextLine()) {
                    fw.write(lukija.nextLine() + "\n");
                }
                fw.close();
            }
        }
        catch(Exception e) {
            System.out.println("Kammottava virhe!");
            e.printStackTrace();
        }
    }
   
}
