/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import java.lang.StringBuilder;

/**
 * 
 * @author merioksa
 */
public class Karttapala {
    // palan naapurit taulukossa. Indeksi 0 = pohjoinen, 1 = itä, 2 = etelä ja 3 = länsi
    private Karttapala[] naapurit;
    
    public Karttapala() {
        naapurit = new Karttapala[4];
    }
    
    public void setNaapuri(Karttapala pala, Ilmansuunta suunta) {
        naapurit[suunta.getSuuntanro()] = pala;
    }
    public Karttapala getNaapuri(Ilmansuunta suunta) {
        return naapurit[suunta.getSuuntanro()];
    }
    
    @Override
    public String toString() {
        StringBuilder rakentaja = new StringBuilder();
        
        rakentaja.append("Hiljainen, tyhjä karttapala.\n");
        rakentaja.append("Sillä on naapuripalat\n");
        for(int i = 0; i < naapurit.length; i++) {
            if(naapurit[i] != null) {
                switch(i) {
                    case 0:
                        rakentaja.append(" - pohjoisessa\n");
                        break;
                    case 1:
                        rakentaja.append(" - idässä\n");
                        break;
                    case 2:
                        rakentaja.append(" - etelässä\n");
                        break;
                    case 3:
                        rakentaja.append(" - lännessä\n");
                        break;
                }
            }
        }
        return rakentaja.toString();
    }
}
