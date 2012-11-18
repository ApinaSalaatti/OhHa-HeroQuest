/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Luokka joka kuvaa yhden palan kartasta.
 * Yhdellä karttapalalla voi sijaita yksi aarre, yksi pelaaja ja yksi monsteri kerrallaan.
 * Karttapalalla on x- ja y-koordinaatit, sekä tiedot sen naapuripaloista. Tietyn karttapalan voi siis löytää joko koordinaattien tai naapureiden avulla.
 * 
 * @author Merioksan Mikko
 */
public class Karttapala {
    /**
     * Palan x-koordinaatti.
     */
    private int x;
    /**
     * Palan y-koordinaatti
     */
    private int y;
    /**
     * Palan naapuripalat taulukossa
     */
    private Karttapala[] naapurit;
    /**
     * Ruudussa sijaitsevat tavarat
     */
    private List<String> tavarat;
    /** 
     * Muuttuja joka kertoo onko pelaaja juuri tällä hetkellä ruudussa
     */
    private boolean pelaajaPaikalla;
    /**
     * Ruudussa mahdollisesti oleileva monsteri. Null = ei monsteria.
     */
    private Monsteri monsteri;
    
    public Karttapala() {
        naapurit = new Karttapala[4];
        tavarat = new ArrayList<String>();
        pelaajaPaikalla = false;
        monsteri = null;
    }
    
    /**
     * Metodi joka asettaa kartan sijainnin koordinaatistossa.
     * 
     * @param x pelilogiikan määrittelemä palan x-koordinaatti
     * @param y pelilogiikan määrittelemä palan y-koordinaatti
     */
    public void setSijainti(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * @return palan x-koordinaatti
     */
    public int getX() {
        return x;
    }
    /**
     * @return kartan y-koordinaatti
     */
    public int getY() {
        return y;
    }
    
    /**
     * Asettaa karttapalalle toisen palan naapuriksi.
     * 
     * @param pala pala joka asetetaan naapuriksi
     * @param suunta suunta johon naapuri sijoitetaan
     */
    public void setNaapuri(Karttapala pala, Ilmansuunta suunta) {
        naapurit[suunta.getSuuntanro()] = pala;
    }
    /**
     * Palauttaa kartan naapuripalan
     * 
     * @param suunta suunta josta naapuripala haetaan
     * @return haettu naapuri. Mikäli palalla ei ole annetussa suunnassa naapuria palautuu null.
     */
    public Karttapala getNaapuri(Ilmansuunta suunta) {
        return naapurit[suunta.getSuuntanro()];
    }
    
    /**
     * Lisää tavaran Karttapalan inventaarioon
     * 
     * @param t lisättävä tavara
     */
    public void addTavara(String t) {
        tavarat.add(t);
    }
    /**
     * @return Karttapalassa majailevat tavarat
     */
    public List<String> getTavarat() {
        return tavarat;
    }
    /**
     * Metodi joka poistuu aarteen ruudusta, esim. pelaajan poimiessa sen.
     */
    public List<String> poimiTavarat() {
        List<String> lista = new ArrayList<String>();
        Iterator<String> iter = tavarat.listIterator();
        while(iter.hasNext()) {
            String tavara = iter.next();
            lista.add(tavara);
            iter.remove();
        }
        return lista;
    }
    
    /**
     * Metodi, joka pelaajan liikkuessa ruutuun asettaa tiedon karttapalaan.
     */
    public void pelaajaSaapuu() {
        pelaajaPaikalla = true;
    }
    /**
     * Pelaajan poistuessa ruudusta, poistetaan tieto pelaajasta karttapalasta.
     */
    public void pelaajaPoistuu() {
        pelaajaPaikalla = false;
    }
    /**
     * @return tieto siitä, onko pelaaja juuri kyseisessä ruudussa
     */
    public boolean pelaajaPaikalla() {
        return pelaajaPaikalla;
    }
    
    /**
     * Metodi, joka monsterin saapuessa ruutuun asettaa monsterin ruudussa sijaitsevaksi.
     * 
     * @param m ruutuun saapuva monsteri
     */
    public void monsteriSaapuu(Monsteri m) {
        monsteri = m;
    }
    /**
     * Monsterin poistuessa poistetaan monsteri ruudusta.
     */
    public void monsteriPoistuu() {
        monsteri = null;
    }
    /**
     * @return tieto siitä, onko ruudussa monsteria
     */
    public boolean monsteriPaikalla() {
        return monsteri != null;
    }
    /**
     * @return palassa majaileva monsteri
     */
    public Monsteri getMonsteri() {
        return monsteri;
    }
    /**
     * @return tieto siitä, onko ruudussa aarre
     */
    public boolean aarrePaikalla() {
        return tavarat.contains("Arvokas aarre");
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
        
        if(monsteriPaikalla()) {
            rakentaja.append("Ruudussa on kamala monsteri: " + monsteri.toString() + "!!!\n");
        }
        
        if(aarrePaikalla()) {
            rakentaja.append("Ruudussa sijaitsee suunnattoman arvokas kulta-aarre!\n");
        }
        
        return rakentaja.toString();
    }
    
    /**
     * Metodi, joka palauttaa palan "statuksen", eli mitä kaikkea ruudusta löytyy! Lähinnä että käyttöliittymä tietää mitä piirtää.
     * 
     * @return ennaltamääritelty arvo palan "statukseksi".
     */
    public int status() {
        if(pelaajaPaikalla() && !monsteriPaikalla()) {
            return 2;
        }
        else if(pelaajaPaikalla() && monsteriPaikalla()) {
            return 4;
        }
        else if(!pelaajaPaikalla() && monsteriPaikalla()) {
            return 3;
        }
        else if(aarrePaikalla()) {
            return 5;
        }
        else {
            return 1;
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if(!o.getClass().equals(this.getClass())) {
            return false;
        }
        Karttapala toinen = (Karttapala)o;
        if(toinen.getX() == this.getX() && toinen.getY() == this.getY()) {
            return true;
        }
        
        return false;
    }
}
