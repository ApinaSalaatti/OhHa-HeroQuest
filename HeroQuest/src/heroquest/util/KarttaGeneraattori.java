/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.util;

import java.util.ArrayList;
import java.util.Random;

import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
import heroquest.domain.Ilmansuunta;
/**
 *
 * @author Merioksan Mikko
 */
public class KarttaGeneraattori {
    private static int[][] testiKartta = {  {0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 1, 1, 1, 1, 1, 1, 0},
                                            {0, 1, 0, 0, 0, 0, 0, 0},
                                            {0, 1, 1, 1, 0, 0, 0, 0},
                                            {0, 1, 0, 1, 0, 0, 0, 0},
                                            {0, 1, 0, 1, 1, 1, 1, 0},
                                            {0, 1, 1, 1, 0, 0, 1, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0}};
    private Karttapala[][] kartta;
    private Random random;
    
    public KarttaGeneraattori() {
        random = new Random();
    }
    
    // TODO: poista tämä huono versio!!
    // Kartan satunnaisluonti tulossa, nyt palautetaan aina sama kartta
    public void luoKartta(int koko) {
        koko = 8;
        kartta = new Karttapala[koko][koko];
        for(int i = 0; i < koko; i++) {
            for(int j = 0; j < koko; j++) {
                if(testiKartta[i][j] == 1) {
                    Karttapala pala = new Karttapala();
                    pala.setSijainti(j, i);
                    kartta[i][j] = pala;
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
    
    public Karttapala[][] getKartta() {
        return kartta;
    }
}
