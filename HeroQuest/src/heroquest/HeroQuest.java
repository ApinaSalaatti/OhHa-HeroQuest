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
        SwingUtilities.invokeLater(kali);
    }
}
