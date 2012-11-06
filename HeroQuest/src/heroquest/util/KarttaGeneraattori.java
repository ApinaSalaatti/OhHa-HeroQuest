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
    private Karttapala[][] kartta;
    private Random random;
    
    public KarttaGeneraattori() {
        random = new Random();
    }
    
    // luo kartta annetun lähdekartan pohjalta. 0 = seinä, 1 = käytävä
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
    
    // luodaan annetun kokoinen kartta satunnaisesti
    public void luoKartta(int koko) {
        // TODO: toteuta tämä :-)
    }
    
    public Karttapala[][] getKartta() {
        return kartta;
    }
}
