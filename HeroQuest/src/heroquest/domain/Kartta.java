/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import heroquest.domain.Monsteri;
import heroquest.domain.kauppa.Tavara;
import heroquest.util.KarttaGeneraattori;
/**
 * Luokka, johon on kapseloitu yksittäiset karttapalat sisältävä kaksiulotteinen taulukko.
 * Sisältää myös kartan käsittelyyn käytettäviä metodeja.
 * 
 * @author Merioksan Mikko
 */
public class Kartta {
    /**
     * Tiedoston nimi, josta kartta luettiin.
     */
    private String nimi;
    /**
     * Kaikki karttaan liittyvät karttapalat taulukossa.
     */
    private Karttapala[][] kartta;
    /**
     * Pelaajan löytämät karttapalat. Nämä määritellään yksinkertaisen line of sightin perusteella.
     */
    private Karttapala[][] nahty;
    /**
     * Kartalla sijaitsevat monsterit ArrayListissä. 
     */
    private ArrayList<Monsteri> monsterit;
    
    public Kartta(int[][] lahde, String nimi) {
        this.nimi = nimi;
        kartta = new Karttapala[lahde.length][lahde[0].length];
        
        KarttaGeneraattori generaattori = new KarttaGeneraattori();
        generaattori.luoKartta(lahde);
        kartta = generaattori.getKartta();
        nahty = new Karttapala[kartta.length][kartta[0].length];
        monsterit = new ArrayList<Monsteri>();
    }
    
    public String getNimi() {
        return nimi;
    }
    
    /**
     * @return pala josta peli aloitetaan. Aina vasen ylänurkka.
     */
    public Karttapala getAloituspala() {
        return kartta[1][1];
    }
    
    /**
     * @return kartta taulukkona
     */
    public Karttapala[][] getKarttapalat() {
        return kartta;
    }
    
    /**
     * @return pelaajan löytämät palat taulukkona
     */
    public Karttapala[][] getNahdytPalat() {
        return nahty;
    }
    
    /**
     * Poimii kaikki tavarat parametrina annetusta Karttapalasta
     * 
     * @param pala tyhjennettävä karttapala
     */
    public List<Tavara> poimiTavarat(Karttapala pala) {
        return pala.poimiTavarat();
    }
    /**
     * @return kartalla jäljellä olevien aarteiden määrä
     */
    public int getAarteet() {
        int lkm = 0;
        for(int y = 0; y < kartta.length; y++) {
            for(int x = 0; x < kartta.length; x++) {
                if(kartta[y][x] != null && kartta[y][x].aarrePaikalla()) {
                    lkm++;
                }
            }
        }
        return lkm;
    }
    
    public List<Monsteri> getMonsterit() {
        return monsterit;
    }
    /**
     * Lisätään kartalle yksi Monsteri annettuun Karttapalaan.
     * 
     * @param m lisättävä Monsteri
     * @param pala Karttapala, johon monsteri lisätään
     */
    public void lisaaMonsteri(Monsteri m, int y, int x) {
        m.setSijainti(kartta[y][x]);
        kartta[y][x].monsteriSaapuu(m);
        monsterit.add(m);
    }
    /**
     * Poistaa annetun Monsterin kartalta, esim kun Pelaaja tappaa sen.
     * 
     * @param m poistettava Monsteri
     */
    public void poistaMonsteri(Monsteri m) {
        m.getSijainti().monsteriPoistuu();
        monsterit.remove(m);
    }
    /**
     * Metodi, joka poistaa kartalta kaikki kuolleet Monsterit.
     */
    public List<Monsteri> poistaKuolleetMonsterit() {
        List<Monsteri> palautus = new ArrayList<Monsteri>();
        
        Iterator<Monsteri> iter = monsterit.listIterator();
        while(iter.hasNext()) {
            Monsteri m = iter.next();
            if(m.getEnergia() <= 0) {
                m.getSijainti().monsteriPoistuu();
                palautus.add(m);
                iter.remove();
            }
        }
        
        return palautus;
    }
    /**
     * Metodi, joka liikuttaa kaikkia pelissä olevia Monstereita.
     * Tämä tehdään yleensä kun pelaaja on käyttäny kaikki liikkeensä.
     */
    public void monsterienLiike(boolean taistelu, Pelaaja p) {
        // hirviöt liikkuvat vain jos pelaaja ei ole taistelussa
        if(!taistelu) {
            for(Monsteri m : monsterit) {
                m.liiku(p, getKarttapalat());
            }
        }
    }
    
    public boolean ulosKartalta(Karttapala pala, Ilmansuunta suunta) {
        int uusiX = pala.getX() + suunta.xMuutos();
        int uusiY = pala.getY() + suunta.yMuutos();
        
        if(uusiX < 0 || uusiX >= kartta[0].length || uusiY < 0 || uusiY >= kartta.length) {
            return true;
        }
        
        return false;   
    }
    
    /**
     * Päivitetään pelaajan näkökenttä annetusta palasta lähtien.
     * Line of sight on hyvin yksinkertainen, pelaaja vain näkee kaikki ruudut suorassa linjassa seuraavaan seinään tai kartan reunaan asti.
     * 
     * @param pala pala, josta line of sight lasketaan, yleensä pelaajan sijainti
     */
    public void paivitaNahdyt(Karttapala pala) {
        int y = pala.getY();
        int x = pala.getX();
        
        asetaNakyvaksi(x, y, 0, -1);
        asetaNakyvaksi(x, y, 0, 1);
        asetaNakyvaksi(x, y, -1, 0);
        asetaNakyvaksi(x, y, 1, 0);
    }
   /**
    * Asetetaan annetuissa koordinaateissa oleva pala näkyväksi ja siirrytään rekursiivisesti seuraavaan.
    * Palataan kun törmätään seinään tai syöksytään ulos kartan reunalta.
    * 
    * @param x näkyväksi asetettavan palan x-koordinaatti
    * @param y näkyväksi asetettavan palan y-koordinaatti
    * @param seurX suunta, johon x-akselilla liikutaan annetuista koordinaateista
    * @param seurY suunta, johon y-akselilla liikutaan annetuista koordinaateista
    */
    public void asetaNakyvaksi(int x, int y, int seurX, int seurY) {
        if(x < 0 || x >= kartta[0].length || y < 0 || y >= kartta.length || kartta[y][x] == null) {
            return;
        }
        nahty[y][x] = kartta[y][x];
        
        if(seurX != 0 || seurY != 0) {
            asetaNakyvaksi(x+seurX, y+seurY, seurX, seurY);
        }
    }
    
    /**
     * Metodi, joka paljastaa kartan kaikki ansat.
     */
    public void paljastaAnsat() {
        for(int y = 0; y < kartta.length; y++) {
            for(int x = 0; x < kartta[0].length; x++) {
                if(kartta[y][x] != null && kartta[y][x].ansaPaikalla()) {
                    kartta[y][x].paljastaAnsa();
                }
            }
        }
    }
    
    /**
     * Palautetaan kaikki karttadata tallentamista varten tiedostona.
     * 
     * @return karttadata String-muotoisena
     */
    public String tallenna() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(nimi + "\n");
        
        // kartan koko
        sb.append(kartta.length + "\n");
        sb.append(kartta[0].length + "\n");
        
        // itse karttapalat
        sb.append(tallennaData("karttapalat"));
        sb.append(tallennaData("monsterit"));
        sb.append(tallennaData("aarrekartta"));
        sb.append(tallennaData("nahdyt"));
        
        return sb.toString();
    }
    
    public String tallennaData(String data) {
        StringBuilder sb = new StringBuilder();
        
        for(int y = 0; y < kartta.length; y++) {
            for(int x = 0; x < kartta[0].length; x++) {
                if(data.equals("karttapalat")) {
                    sb.append((kartta[y][x] != null) ? "1" : "0");
                }
                else if(data.equals("monsterit")) {
                    if(kartta[y][x] != null) {
                        if(kartta[y][x].monsteriPaikalla()) {
                            sb.append("1");
                        }
                        else if(kartta[y][x].ansaPaikalla()) {
                            sb.append("2");
                        }
                        else {
                            sb.append("0");
                        }
                    }
                    else {
                        sb.append("0");
                    }
                }
                else if(data.equals("aarrekartta")) {
                    if(kartta[y][x] != null) {
                        sb.append((kartta[y][x].aarrePaikalla()) ? "1" : "0");
                    }
                    else {
                        sb.append("0");
                    }
                }
                else if(data.equals("nahdyt")) {
                    sb.append((nahty[y][x] != null) ? "1" : "0");
                }
            }
            sb.append("\n");
        }
        
        sb.append("\n");
        
        return sb.toString();
    }
}
