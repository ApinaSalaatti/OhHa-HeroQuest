/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.util;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Apuluokka, jossa metodit kuvien käsittelyyn kuten tiedostosta lataamiseen ja croppaamiseen.
 * 
 * @author Merioksan Mikko
 */
public class Kuvienkasittely {

    /**
     * Ladataan kuva parametrina annetulla tiedostonimellä.
     * 
     * @param nimi kuvan tiedostonimi
     * @return ladattu kuva BufferedImage-formaatissa
     */
    public static BufferedImage lataaKuva(String nimi) {
        String polku = "src/kuvat/" + nimi;
        BufferedImage kuva = null;
        try {
            kuva = ImageIO.read(new File(polku));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return kuva;
    }
}
