/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.domain;

import java.util.PriorityQueue;
/**
 * Luokka, joka kontrolloi pelottavien monstereiden liikettä. Sisältää häkellyttävän monimutkaista tekoälyohjelmointia.
 *
 * @author Merioksan Mikko
 */
public class MonsterinAivot {
    /**
     * Apuluokka, joka kuvaa karttakoordinaattia ja kyseisen koordinaatin etäisyyttä annetusta pisteestä.
     */
    private class Paikka implements Comparable {
        public Paikka(int y, int x) {
            this.y = y;
            this. x = x;
        }
        public Paikka(int y, int x, int a) {
            this.y = y;
            this. x = x;
            this.alkuun = a;
        }
        public int x;
        public int y;
        public int alkuun;

        @Override
        public int compareTo(Object o) {
            Paikka paikka = (Paikka)o;
            return this.alkuun - paikka.alkuun;
        }
    }
    
    /**
     * Metodi, joka laskee lyhimmän reitin annetun Monsterin sijainnista Pelaajan sijaintiin, ja palauttaa suunnan johon Monsterin tulee edetä.
     * 
     * @param m liikutettava Monsteri
     * @param p pelaaja jota jahdataan
     * @param kartta kartta jolla Monsteri ja Pelaaja oleilevat
     * @return suunta johon Monsterin on ruudustaan edettävä lähestyäkseen pelaajaa
     */
    public Ilmansuunta annaSuunta(Monsteri m, Pelaaja p, Karttapala[][] kartta) {
        Karttapala lahto = m.getSijainti();
        Karttapala kohde = p.getSijainti();
        Paikka[][] paikat = new Paikka[kartta.length][kartta[0].length];
        PriorityQueue<Paikka> jono = new PriorityQueue<Paikka>();
        Ilmansuunta[][] suunnat = new Ilmansuunta[kartta.length][kartta[0].length];

        suunnat[lahto.getY()][lahto.getX()] = null;
        paikat[lahto.getY()][lahto.getX()] = new Paikka(lahto.getY(), lahto.getX(), 0);
        jono.add(paikat[lahto.getY()][lahto.getX()]);
        while(!jono.isEmpty()) {
            Paikka paikka = jono.poll();
            
            relaksoi(kartta, paikat, suunnat, jono, paikka, Ilmansuunta.POHJOINEN);
            relaksoi(kartta, paikat, suunnat, jono, paikka, Ilmansuunta.ITA);
            relaksoi(kartta, paikat, suunnat, jono, paikka, Ilmansuunta.ETELA);
            relaksoi(kartta, paikat, suunnat, jono, paikka, Ilmansuunta.LANSI);
        }

        Ilmansuunta suunta = etsiReitti(kohde, suunnat);
        
        return suunta;
    }
    
    /**
     * Päivitetään annetun Karttapalan naapuripalojen etäisyys lähtöpalasta (Monsterin sijainti)
     * 
     * @param kartta kartta jonka paloja tarkastellaan
     * @param paikat taulukko Karttapaloista jotka on jo aiemmin käyty läpi, ja niiden etäisyyksistä
     * @param suunnat taulukko suunnista, joihin kustakin palasta tulee edetä
     * @param jono jono, johon Kartan koordinaatteja talletetaan sitä mukaan kun ne otetaan käsittelyyn, ja poistetaan kun ne on käsitelty
     * @param paikka paikka jonka naapureita ollaan päivittämässä
     * @param suunta suunta, johon tällä iteraatiokerralla ollaan etenemässä parametristä "paikka"
     */
    private void relaksoi(Karttapala[][] kartta, Paikka[][] paikat, Ilmansuunta[][]suunnat, PriorityQueue<Paikka> jono, Paikka paikka, Ilmansuunta suunta) {
        int x = paikka.x + suunta.xMuutos();
        int y = paikka.y + suunta.yMuutos();
        if(x < 0 || y < 0 || x >= paikat[0].length || y >= paikat.length || kartta[y][x] == null) {
            return;
        }
        
        int uusiE = paikka.alkuun + 1;
        if(paikat[y][x] == null) {
            paikat[y][x] = new Paikka(y, x, uusiE);
            suunnat[y][x] = suunta;
            jono.add(paikat[y][x]);
        }
        else if(uusiE < paikat[y][x].alkuun) {
            jono.remove(paikat[y][x]);
            paikat[y][x] = new Paikka(y, x, uusiE);
            suunnat[y][x] = suunta;
            jono.add(paikat[y][x]);
        }
    }
    
    /**
     * Kuljetaan läpi taulukko, joka pitää sisällään tiedon siitä mihin suuntaan kustakin ruudusta edetään.
     * Koska talletettuna on suunta kohti kohdetta ja lähdemme kohteesta, tarkastellaan aina ruudussa olevan Ilmansuunnan vastakohtaa.
     * 
     * @param suunnat taulukko, johon edettävät suunnat on talletettu
     * @return suunta, johon Monsterin tulee sijainnistaan edetä
     */
    private Ilmansuunta etsiReitti(Karttapala kohde, Ilmansuunta[][] suunnat) {
        Ilmansuunta nyt = suunnat[kohde.getY()][kohde.getX()];
        int y = kohde.getY();
        int x = kohde.getX();
        Ilmansuunta edell = null;
        while(nyt != null) {
            edell = nyt;
            y += nyt.vastakohta().yMuutos();
            x += nyt.vastakohta().xMuutos();
            nyt = suunnat[y][x];
        }
        
        return edell;
    }
}
