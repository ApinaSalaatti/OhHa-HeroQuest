/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.util;

import java.util.Random;
/**
 *
 * @author merioksa
 */
public class Nimilista {
    private static String[] hauskatNimet = { "Esa Pakarinen", "Lauri Viita", "Simo Salminen", "Lasse Viren", 
                                             "Seppo Lehto" };
    private static String[] pelottavatNimet = { "Hirmuhirviö", "Mörkömöykky", "Kauhumies", "Kummituskaveri",
                                                "Timo Soini" };
    private static Random random = new Random();
    
    public static String getHauskaNimi() {
        int indeksi = random.nextInt(hauskatNimet.length);
        return hauskatNimet[indeksi];
    }
    
    public static String getPelottavaNimi() {
        int indeksi = random.nextInt(pelottavatNimet.length);
        return pelottavatNimet[indeksi];
    }
}
