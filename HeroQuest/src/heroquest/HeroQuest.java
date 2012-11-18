package heroquest;

import java.util.Scanner;

import javax.swing.SwingUtilities;

import heroquest.kayttoliittyma.Kayttoliittyma;
/**
 * Tämä vain käynnistää pelin, ei tee oikeasti mitään.
 * 
 * @author Merioksan Mikko
 */
public class HeroQuest {

    public static void main(String[] args) {
        
        Kayttoliittyma kali = new Kayttoliittyma();
        PeliController controller = new PeliController(kali);
        kali.setController(controller);
        SwingUtilities.invokeLater(kali);
  
    }
}
