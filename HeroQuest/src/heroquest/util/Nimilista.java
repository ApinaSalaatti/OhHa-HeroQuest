/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.util;

import java.util.Random;
/**
 * Apuluokka Monsterien ja pelaajan nimen arpomiseen
 * 
 * @author Merioksan Mikko
 */
public class Nimilista {
    /**
     * Lista hulvattoman hauskoista nimistä.
     */
    private static String[] hauskatNimet = { "Esa Pakarinen", "Lauri Viita", "Simo Salminen", "Lasse Viren", 
                                             "Seppo Lehto", "Sakke Pietilä" };
    /**
     * Lista selkäpiitä karmivan pelottavista nimistä.
     */
    private static String[] pelottavatNimet = { "Hirmuhirviö", "Mörkömöykky", "Kauhumies", "Kummituskaveri",
                                                "Timo Soini" };
    /**
     * Satunnaisuuden mahdollistava Random-olio.
     */
    private static Random random = new Random();
    
    /**
     * Palautetaan satunnainen hauska nimi.
     * 
     * @return satunnainen hauska nimi
     */
    public static String getHauskaNimi() {
        int indeksi = random.nextInt(hauskatNimet.length);
        return hauskatNimet[indeksi];
    }
    
    /**
     * Palautetaan satunnainen pelottava nimi.
     * 
     * @return satunnainen pelottava nimi
     */
    public static String getPelottavaNimi() {
        int indeksi = random.nextInt(pelottavatNimet.length);
        return pelottavatNimet[indeksi];
    }
}
