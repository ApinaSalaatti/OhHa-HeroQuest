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
    private int x;
    private int y;
    // palan naapurit taulukossa. Indeksi 0 = pohjoinen, 1 = itä, 2 = etelä ja 3 = länsi
    private Karttapala[] naapurit;
    boolean pelaajaPaikalla;
    boolean monsteriPaikalla;
    
    public Karttapala() {
        naapurit = new Karttapala[4];
        pelaajaPaikalla = false;
    }
    
    public void setSijainti(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    
    public void setNaapuri(Karttapala pala, Ilmansuunta suunta) {
        naapurit[suunta.getSuuntanro()] = pala;
    }
    public Karttapala getNaapuri(Ilmansuunta suunta) {
        return naapurit[suunta.getSuuntanro()];
    }
    
    public void pelaajaSaapuu() {
        pelaajaPaikalla = true;
    }
    public void pelaajaPoistuu() {
        pelaajaPaikalla = false;
    }
    public boolean pelaajaPaikalla() {
        return pelaajaPaikalla;
    }
    
    public void monsteriSaapuu() {
        monsteriPaikalla = true;
    }
    public void monsteriPoistuu() {
        monsteriPaikalla = false;
    }
    public boolean monsteriPaikalla() {
        return monsteriPaikalla;
    }
    
    @Override
    public String toString() {
        StringBuilder rakentaja = new StringBuilder();
        
        rakentaja.append("Hiljainen, tyhjä karttapala koordinaateissa ");
        String koordinaatti = "(" + x + ", " + y + ")";
        rakentaja.append(koordinaatti);
        rakentaja.append(".\n");
        rakentaja.append("Sillä on naapuripalat\n");
        
        // tulostetaan pelaajalle palan naapurien ilmansuunnat
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
