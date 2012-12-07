
package heroquest.util;

import java.util.ArrayList;

import heroquest.domain.Karttapala;
import heroquest.domain.Ilmansuunta;
/**
 * Apuluokka kartan luomiseen annetusta raakadatasta (int[][]-taulukko).
 * 
 * @author Merioksan Mikko
 * 
 */
public class KarttaGeneraattori {
    private Karttapala[][] kartta;
    
    public KarttaGeneraattori() {
    }
    
    /**
     * Metodi, joka luo kartan annetun lähdekartan pohjalta.
     * 
     * @param lahde karttadata int[][]-muodossa
     */
    public void luoKartta(int[][] lahde) {
        int y = lahde.length;
        int x = lahde[0].length;
        kartta = new Karttapala[y][x];
        
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                if(lahde[i][j] == 1) {
                    Karttapala pala = new Karttapala();
                    pala.setSijainti(j, i);
                    kartta[i][j] = pala;
                    
                    // asetetaan naapurit oikein
                    if(i > 0 && kartta[i-1][j] != null) {
                        pala.setNaapuri(kartta[i-1][j], Ilmansuunta.POHJOINEN);
                        kartta[i-1][j].setNaapuri(pala, Ilmansuunta.ETELA);
                    }
                    if(j < kartta.length-1 && kartta[i][j+1] != null) {
                        pala.setNaapuri(kartta[i][j+1], Ilmansuunta.ITA);
                        kartta[i][j+1].setNaapuri(pala, Ilmansuunta.LANSI);
                    }
                    if(i < kartta.length-1 && kartta[i+1][j] != null) {
                        pala.setNaapuri(kartta[i+1][j], Ilmansuunta.ETELA);
                        kartta[i+1][j].setNaapuri(pala, Ilmansuunta.POHJOINEN);
                    }
                    if(j > 0 && kartta[i][j-1] != null) {
                        pala.setNaapuri(kartta[i][j-1], Ilmansuunta.LANSI);
                        kartta[i][j-1].setNaapuri(pala, Ilmansuunta.ITA);
                    }
                }
            }
        }
    }
    
    /**
     * Palautetaan luodut karttapalat
     * 
     * @return luodut karttapalat taulukkona 
     */
    public Karttapala[][] getKartta() {
        return kartta;
    }
}
