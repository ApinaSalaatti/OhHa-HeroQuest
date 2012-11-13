package heroquest;

import java.util.Scanner;

import javax.swing.SwingUtilities;

import heroquest.kayttoliittyma.Kayttoliittyma;
/**
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
