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
    private static String[] hauskatNimet = {"Esa Pakarinen", "Lauri Viita", "Simo Salminen", "Lasse Viren", 
                                            "Seppo Lehto"};
    private static Random random = new Random();
    
    public static String getHauskaNimi() {
        int indeksi = random.nextInt(hauskatNimet.length);
        return hauskatNimet[indeksi];
    }
}
