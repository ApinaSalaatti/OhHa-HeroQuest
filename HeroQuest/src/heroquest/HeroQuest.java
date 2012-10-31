package heroquest;

import heroquest.domain.*;
/**
 *
 * @author Merioksan Mikko
 */
public class HeroQuest {

    public static void main(String[] args) {
        Karttapala pala1 = new Karttapala();
        Karttapala pala2 = new Karttapala();
        Karttapala pala3 = new Karttapala();
        
        pala1.setNaapuri(pala2, Ilmansuunta.ETELA);
        pala1.setNaapuri(pala3, Ilmansuunta.ITA);
        
        Kartta kartta = new Kartta(6);
        
        Olento olento = new Olento(6, 5);
        
        System.out.println(olento.getNimi());
    }
}
