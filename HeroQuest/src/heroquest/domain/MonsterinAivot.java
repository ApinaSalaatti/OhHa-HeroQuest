/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.domain;

/**
 *
 * @author Merioksan Mikko
 */
public class MonsterinAivot {
    public Ilmansuunta annaSuunta(Monsteri m, Pelaaja p, Karttapala[][] kartta) {
        Karttapala lahto = m.getSijainti();
        Karttapala kohde = p.getSijainti();
        int[][] alkuun = new int[kartta.length][kartta[0].length];
        int[][] loppuun = new int[kartta.length][kartta[0].length];
        int[][] polku = new int[kartta.length][kartta[0].length];
        
        for(int i = 0; i < kartta.length; i++) {
            for(int j = 0; j < kartta[0].length; j++) {
                alkuun[i][j] = Integer.MAX_VALUE;
                loppuun[i][j] = laskeEtaisyys(i, j, kohde.getY(), kohde.getX());
            }
        }
        
        return null;
    }
    
    private int laskeEtaisyys(int y1, int x1, int y2, int x2) {
        int etaisyys = (y1 - y2) + (x1 - x2);
        etaisyys = Math.abs(etaisyys);
        return etaisyys;
    }
}
