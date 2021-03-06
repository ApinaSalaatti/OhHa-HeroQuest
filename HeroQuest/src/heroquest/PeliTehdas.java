package heroquest;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
import heroquest.domain.Pelaaja;
import heroquest.domain.Monsteri;
import heroquest.domain.Ansa;
import heroquest.domain.kauppa.Tavara;
import heroquest.domain.kauppa.Kauppa;
import heroquest.util.Tiedostoapuri;
/**
 * Tehdasluokka joka luo pelin, pelaajan ja kartan annettujen parametrien pohjalta.
 * 
 * @author Merioksan Mikko
 * 
 */
public class PeliTehdas {
    /**
     * Kaikki karttapaloilla mahdollisesti oleilevat tavarat taulukossa. Indeksi 0 tarkoittaa "ei tavaraa", jota ei siis koskaan käytetä (toivottavasti)
     */
    private static String[] tavarat = { null, "arvokasaarre.hqt", "voimaputeli.hqt", "energiaputeli.hqt", "ansojenpaljastaja.hqt", "kotiportaali.hqt" };
    
    public PeliTehdas() {
    }
    
    /**
     * Metodi, jossa luodaan uusi peli ja pelaaja annetuilla parametreilla.
     * 
     * @param pelaajanNimi pelaajan hahmon nimi
     * @param pelaajanLuokka pelaajan hahmon hahmoluokka
     * @return rakennettu Peli-luokan olio
     */
    public Peli luoPeli(String pelaajanNimi, String pelaajanLuokka, String pelaajanKuva) {
        Pelaaja pelaaja = luoPelaaja(pelaajanNimi, pelaajanLuokka, pelaajanKuva);
        
        Peli peli = new Peli(pelaaja);
        
        // Pelin alussa kopioidaan pelidata kuten karttojen ja pelaajan tiedot pelidata-kansioon.
        Tiedostoapuri.kopioiData(peli, "");
        
        return peli;
    }
    
    /**
     * Metodi, joka pelaajan siirtyessä luolastoon luo kyseisen luolaston .hqm -karttatiedoston perusteella.
     * 
     * @param kartanNimi
     * @return valmis kartta
     */
    public Kartta luoLuolasto(String kartanNimi) throws Exception {
        Scanner lukija = Tiedostoapuri.tiedostoLukijaan("pelidata/kartat/" + kartanNimi);
        
        Kartta kartta = luoKartta(lukija, kartanNimi);
        lukija.nextLine();
        
        luoMonsterit(lukija, kartta);
        lukija.nextLine();
        
        sijoitaTavarat(lukija, kartta);
        
        if(lukija.hasNextLine()) {
            lukija.nextLine();
            lataaNahdyt(lukija, kartta);
        }
        
        return kartta;
    }
    
    /**
     * Metodi, joka lukee tallennetun pelin annetusta tiedostosta, ja rakentaa sen perusteella uuden pelin.
     * 
     * @param tiedostonimi tiedosto josta pelin tiedot luetaan
     * @return rakennettu Peli-luokan olio
     */
    public Peli lataaPeli(String tiedostonimi, PeliController pc) throws Exception {
        Scanner lukija = Tiedostoapuri.tiedostoLukijaan("tallennukset/" + tiedostonimi + "/pelaaja.hqs");
        Pelaaja pelaaja = lataaPelaaja(lukija);
        
        Peli peli = new Peli(pelaaja);
        
        lukija = Tiedostoapuri.tiedostoLukijaan("tallennukset/" + tiedostonimi + "/kauppa.hqs");
        Kauppa kauppa = lataaKauppa(lukija);
        pc.getKoti().setKauppa(kauppa);
        
        Tiedostoapuri.kopioiData(peli, tiedostonimi);
        
        return peli;
    }
    
    /**
     * Metodi, joka käyttää purkaa annetusta Scannerista pelaajan tiedot.
     * Käytetään vain peliä tallennuksesta ladatessa, uutta peliä luodessa luodaan aina uusi pelaaja "normaalisti".
     * 
     * @param lukija Scanner-olio josta tiedot luetaan
     * @return rakennettu Pelaaja-olio
     */
    private Pelaaja lataaPelaaja(Scanner lukija) {
        String inventaario = lukija.nextLine();
        String pelaaja = lukija.nextLine();
        String[] pelaajanTiedot = pelaaja.split(";");
        String nimi = pelaajanTiedot[0];
        int voima = Integer.parseInt(pelaajanTiedot[1]);
        int energia = Integer.parseInt(pelaajanTiedot[2]);
        int nopeus = Integer.parseInt(pelaajanTiedot[3]);
        int taso = Integer.parseInt(pelaajanTiedot[4]);
        int exp = Integer.parseInt(pelaajanTiedot[5]);
        String luokka = pelaajanTiedot[6];
        int tapot = Integer.parseInt(pelaajanTiedot[7]);
        int varat = Integer.parseInt(pelaajanTiedot[8]);
        String kuva = pelaajanTiedot[9];
        
        Pelaaja p = new Pelaaja(nimi, voima, energia, nopeus, luokka);
        p.setKuva(kuva);
        p.lisaaExp(exp);
        p.lisaaTapot(tapot);
        p.setVarat(varat);
        
        if(inventaario.length() > 0) {
            String[] tavarat = inventaario.split(";");
            for(String t : tavarat) {
                p.lisaaTavara(new Tavara(t));
            }
        }
        
        return p;
    }
    
    /**
     * Ladataan annetusta Scannerista lista kaupan tavaroista ja luodaan niistä uusi kauppa.
     * 
     * @param lukija Scanner-olio josta tavarat luetaan
     * @return tavaroiden perusteella rakennettu kauppa.
     */
    private Kauppa lataaKauppa(Scanner lukija) {
        List<Tavara> tavarat = new ArrayList<Tavara>();
        if(lukija.hasNextLine()) {
            String[] tavaratStr = lukija.nextLine().split(";");
            for(String t : tavaratStr) {
                tavarat.add(new Tavara(t));
            }
        }
        
        return new Kauppa(tavarat);
    }
    
    /**
     * Metodi, jossa luetaan kartta Scannerista int[][] taulukkoon ja lähetetään se sitten Kartta-oliolle uuden kartan pohjaksi
     * 
     * @param lukija lukija joka sisältää karttadatan
     * @return rakennettu Kartta-luokan olio
     */
    private Kartta luoKartta(Scanner lukija, String nimi) throws Exception {
        int x = Integer.parseInt(lukija.nextLine());
        int y = Integer.parseInt(lukija.nextLine());
        int[][] kartta = new int[y][x];
        
        for(int rivi = 0; rivi < y; rivi++) {
            String riviStr = lukija.nextLine();
            for(int sarake = 0; sarake < x; sarake++) {
                if(riviStr.charAt(sarake) == '1') {
                    kartta[rivi][sarake] = 1;
                }
                else {
                    kartta[rivi][sarake] = 0;
                }
            }
        }
        
        return new Kartta(kartta, nimi);
    }
    
    /**
     * Metodi, joka asettaa nähdyiksi ne Karttapalat jotka pelaaja oli tallentaessaan nähnyt.
     * 
     * @param lukija Scanner-olio josta tiedot luetaan
     * @param kartta Kartta johon nähdyt palat asetetaan
     * 
     */
    private void lataaNahdyt(Scanner lukija, Kartta kartta) throws Exception {
        Karttapala[][] palat = kartta.getKarttapalat();
        
        for(int y = 0; y < palat.length; y++) {
            String riviStr = lukija.nextLine();
            for(int x = 0; x < palat[0].length; x++) {
                if(riviStr.charAt(x) == '1') {
                    kartta.asetaNakyvaksi(x, y, 0, 0);
                }
            }
        }
    }
    
    /**
     * Metodi, joka luo Pelaaja-luokan olion annettujen parametrien perusteella. 
     * 
     * @param nimi hahmon nimi
     * @param luokka hahmon hahmoluokka
     * @return rakennettu Pelaaja-luokan olio
     */
    private Pelaaja luoPelaaja(String nimi, String luokka, String kuva) {
        int voima = 0;
        int energia = 0;
        int nopeus = 0;
        
        if(luokka.equals("Taikamaagi")) {
            voima = 2;
            energia = 2;
            nopeus = 2;
        }
        else if(luokka.equals("Konna")) {
            voima = 8;
            energia = 2;
            nopeus = 4;
        }
        else if(luokka.equals("Kekkeruusi")) {
            voima = 100;
            energia = 100;
            nopeus = 100;
        }
        else {
            voima = 5;
            energia = 5;
            nopeus = 1;
        }
        
        Pelaaja p = new Pelaaja(nimi, voima, energia, nopeus, luokka);
        p.setKuva(kuva);
        return p;
    }
    
    /**
     * Metodi, joka lisää monsterit kartalle annetun Scanner-olion sisältämän karttadatan perusteella.
     * 
     * @param lukija karttadatan sisältävä Scanner-olio
     * @param kartta Kartta-olio johon monsterit lisätään.
     */
    private void luoMonsterit(Scanner lukija, Kartta kartta) throws Exception {
        Karttapala[][] palat = kartta.getKarttapalat();
        for(int y = 0; y < palat.length; y++) {
            String riviStr = lukija.nextLine();
            for(int x = 0; x < palat[0].length; x++) {
                if(riviStr.charAt(x) == '1') {
                    Monsteri m = new Monsteri(5, 1, 1, 100);
                    kartta.lisaaMonsteri(m, y, x);
                }
                if(riviStr.charAt(x) == '2') {
                    palat[y][x].viritaAnsa(new Ansa("Murhamiina", 10, "Astuit kammottavaan murhamiinaan!\n"));
                }
            }
        }
    }
    
    /**
     * Sijoitetaan tavarat kartalle annetun Scanner-olion sisältämän karttadatan perusteella.
     * 
     * @param lukija Scanner-olio jossa karttadata
     * @param kartta Kartta johon tavarat lisätään
     */
    private void sijoitaTavarat(Scanner lukija, Kartta kartta) throws Exception {
        Karttapala[][] palat = kartta.getKarttapalat();
        for(int y = 0; y < palat.length; y++) {
            String riviStr = lukija.nextLine();
            for(int x = 0; x < palat[0].length; x++) {
                if(riviStr.charAt(x) != '0') {
                    int tavaraIndx = Integer.parseInt(riviStr.charAt(x) + "");
                    palat[y][x].addTavara(new Tavara(tavarat[tavaraIndx]));
                }
            }
        }
    }
}
