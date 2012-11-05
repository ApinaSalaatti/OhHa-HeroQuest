package heroquest;

import heroquest.domain.*;
/**
 *
 * @author Merioksan Mikko
 */
public class HeroQuest {

    public static void main(String[] args) {
        
        Kartta kartta = new Kartta(10);
        
        Olento olento = new Olento(6, 5);

        olento.setSijainti(kartta.getAloituspala());
        System.out.println(olento.getSijainti());
        
        kartta.tulosta();
    }
}
