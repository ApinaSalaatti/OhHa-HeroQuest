/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest;

import java.util.Random;
import heroquest.domain.Olento;
import heroquest.domain.Monsteri;
import heroquest.domain.Kartta;
import heroquest.domain.Karttapala;
import heroquest.domain.Ilmansuunta;
import heroquest.kayttoliittyma.Kayttoliittyma;
/**
 *
 * @author Merioksan Mikko
 */
public class PeliController {
    private Random random;
    private Peli peli;
    private Kayttoliittyma kali;
    
    public PeliController(Kayttoliittyma k) {
        this.random = new Random();
        this.kali = k;
    }
    
    public void aloitaPeli(String nimi, String luokka, String kartanNimi) {
        PeliTehdas pt = new PeliTehdas();
        this.peli = pt.luoPeli(nimi, luokka, kartanNimi);

        paivitaKali("Sijaintisi:\n" + peli.getPelaaja().getSijainti());
        paivitaKali("Tervetuloa, urhea sankari, tähän maanmainioon seikkailuun!\n");
    }
    
    public void paivitaKali(String tapahtuma) {
        peli.getKartta().paivitaNahdyt(peli.getPelaaja().getSijainti());
        peli.tuleekoTaistelu();
        kali.paivita(tapahtuma);
    }
    
    public Kartta getKartta() {
        return peli.getKartta();
    }
    
    public void pelaajanLiike(Ilmansuunta suunta) {
        Karttapala vanha = peli.getPelaaja().getSijainti();
        peli.pelaajanLiike(suunta);
        Karttapala uusi = peli.getPelaaja().getSijainti();
        
        if(vanha.equals(uusi)) {
            paivitaKali("Ei sinne voi liikkua! >:-(\n");
        }
        else {
            paivitaKali("Siirryit onnistuneest! Sijaintisi nyt:\n" + peli.getPelaaja().getSijainti() + "\n");
        }
        
        if(peli.getPelaaja().getLiikkeet() == 0) {
            peli.lopetaVuoro();
            paivitaKali("Pelottavat monsterit liikkuvat pimeässä...\n");
        }
    }
    
    public void liikenopanHeitto() {
        int heitto = random.nextInt(6) + 1;
        peli.getPelaaja().setLiikkeet(heitto);
        paivitaKali("Mainio heitto: " + heitto + "\n");
    }
    
    public String pelaajanStatus() {
        return peli.getPelaaja().toString();
    }
    
    // palauttaa tilan jossa peli on, sen mukaan käyttöliittymä osaa näyttää oikeat komponentit
    public String getTila() {
        if(peli.taistelunAika()) {
            return "taistelu";
        }
        else if(peli.getPelaaja().getLiikkeet() > 0) {
            return "liike";
        }
        else {
            return "liikenoppa";
        }
    }
    
    // taistelussa ensin pelaaja heittää noppaa ja monsteri ottaa damagea
    // mikäli monsteri vielä tämän jälkeen on hengissä lyö se vuorostaan pelaajaa
    public void taistele() {
        // haetaan vastustaja
        Monsteri vastus = peli.getVastustaja();
        
        // jos vastustajaa ei jostain syystä ollutkaan, ei voida taistellakaan. :(
        if(vastus == null) {
            paivitaKali("Ei ole ketään ketä vastaan taistella (mitäs peliä tämä on??)!");
        }
        else {
            StringBuilder taisteluviesti = new StringBuilder();
            taisteluviesti.append("On taistelun aika!\n");
            taisteluviesti.append("Vastassasi on pelottava monsteri " + vastus.toString() + "\n");
            
            int vahinko = hyokkays(peli.getPelaaja(), vastus);
            if(vahinko > 0) {
                taisteluviesti.append("Teit vahinkoa! ");
                taisteluviesti.append(vastus.getNimi() + " otti vahinkoa " + vahinko + " pistettä!\n");
                vastus.otaVahinkoa(vahinko);
            }
            else {
                taisteluviesti.append("Et osunut! Kehveli!\n");
            }
            
            if(vastus.getEnergia() <= 0) {
                peli.poistaMonsteri(vastus);
                taisteluviesti.append("VOITTO ON SINUN!\n");
            }
            else {
                vahinko = hyokkays(vastus, peli.getPelaaja());
                if(vahinko > 0) {
                    taisteluviesti.append("Voi ei, sinuun osui " + vahinko + " pisteen edestä!!\n");
                    peli.getPelaaja().otaVahinkoa(vahinko);
                }
            }
            
            paivitaKali(taisteluviesti.toString());
        }
    }
    
    // lasketaan yksittäisen hyökkäyksen damage. Iik!
    public int hyokkays(Olento hyokkaaja, Olento puolustaja) {
        int hyokkays = hyokkaaja.hyokkaa();
        int puolustus = puolustaja.puolustaudu();
        
        return hyokkays - puolustus;
    }
}
