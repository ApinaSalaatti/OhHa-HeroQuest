/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import java.util.Random;
import java.util.List;

import heroquest.util.Nimilista;
/**
 * Abstrakti yläluokka, josta peritään kaikki pelin hahmot kuten pelaajahahmo ja monsterit.
 * 
 * @author Merioksan Mikko
 */
public abstract class Olento {
    /**
     * Hahmon nimi
     */
    private String nimi;
    /**
     * Hahmon voima, ts. kuinka montaa noppaa heitetään taistelussa.
     */
    private int voima;
    /**
     * Energia, eli hahmon elämänvoima, "health", voimapisteet, miten vaan. Energian pudotessa nollaan hahmo kuolee.
     */
    private int energia;
    /**
     * Hahmon nopeus, ts. kuinka montaa noppaa heitetään liikkuessa. Lisäksi nopeuden perusteella valitaan taistelun aloittaja.
     */
    private int nopeus;
    /**
     * Hahmon sijainti kartalla
     */
    private Karttapala sijainti;
    /**
     * Hahmon inventaario, johon varastoidaan sen kantamat tavarat ja varusteet
     */
    protected Inventaario inventaario;
    /**
     * Esim. nopanheittojen simuloinnissa käytetty Random-olio. Protected jotta aliluokat pääsevät käsiksi.
     */
    protected Random random;
    
    /**
     * Konstruktori jossa hahmolle anntetaan valmiina nimi
     * 
     * @param nimi hahmon nimi
     * @param voima hahmon voima
     * @param energia hahmon energia
     * @param nopeus hahmon nopeus
     */
    public Olento(String nimi, int voima, int energia, int nopeus) {
        random = new Random();
        inventaario = new Inventaario();
        
        setNimi(nimi);
        setVoima(voima);
        setEnergia(energia);
        setNopeus(nopeus);
    }
    /**
     * Konstruktori jossa hahmolle ei ole annettu valmiina nimeä. Tällöin setNimi-funktio arpoo hahmolle hauskan nimen.
     * 
     * @param voima hahmon voima
     * @param energia hahmon energia
     * @param nopeus hahmon nopeus
     */
    public Olento(int voima, int energia, int nopeus) {
        random = new Random();
        
        setNimi("");
        setVoima(voima);
        setEnergia(energia);
        setNopeus(nopeus);
    }
    
    /**
     * Metodi joka asettaa hahmolle nimen.
     * Nimi ei voi olla tyhjä tai pelkkää white spacea, eikä saa sen puoleen sisältää tyhmiä ylimääräisiä spaceja alussa eikä lopussa.
     * Mikäli nimi on epäkelpo, arvotaan hahmolle hauska nimi apuluokan Nimilista avulla.
     * 
     * @see heroquest.util.Nimilista#annaHauskaNimi()
     * 
     * @param nimi Käyttäjän antama (tai pelilogiikan muokkaama) syöte
     */
    public void setNimi(String nimi) {
        nimi = nimi.trim();
        if(nimi.length() > 0) {
            this.nimi = nimi;
        }
        else {
            this.nimi = Nimilista.getHauskaNimi();
        }
    }
    /**
     * @return hahmon nimi 
     */
    public String getNimi() {
        return nimi;
    }
    
    /**
     * Asetetaan hahmon voima. Voima ei voi olla alle 1.
     * 
     * @param voima käyttäjältä syötteenä saatu voima.
     */
    private void setVoima(int voima) {
        if(voima <= 0) {
            this.voima = 1;
        }
        else {
            this.voima = voima;
        }
    }
    /**
     * @return hahmon voima
     */
    public int getVoima() {
        return voima;
    }
    
    /**
     * Asetetaan hahmon energia
     * 
     * @param energia käyttäjältä syötteenä saatu energia.
     */
    private void setEnergia(int energia) {
        if(energia <= 0) {
            this.energia = 1;
        }
        else {
            this.energia = energia;
        }
    }
    /**
     * @return hahmon energia
     */
    public int getEnergia() {
        return energia;
    }
    /**
     * Metodi hahmon energian muuttamiseen, esimerkiksi jos otetaan vahinkoa.
     * 
     * @param muutos pelilogiikan laskema muutos energiaan. Esim. vahinkoa otettaessa muutos on negatiivinen.
     */
    public void muutaEnergia(int muutos) {
        this.energia += muutos;
    }
    
    /**
     * Asetetaan hahmolle nopeus. Nopeus ei voi olla alle 0.
     * 
     * @param nopeus logiikan laskema parametri, esim. pelaajan valitseman luokan perusteella.
     */
    public void setNopeus(int nopeus) {
        if(nopeus < 0) {
            this.nopeus = 0;
        }
        else {
            this.nopeus = nopeus;
        }
    }
    /**
     * @return hahmon nopeus
     */
    public int getNopeus() {
        return nopeus;
    }
    
    /**
     * Metodi joka asettaa pelaajan sijainnin tiettyyn karttapalaan.
     * 
     * @param pala logiikan tai käyttäjän päättämä karttapala, esim. kun pelaaja tai monsteri liikkuu
     * @return onnistuiko siirtyminen
     */
    public boolean setSijainti(Karttapala pala) {
        if(pala != null) {
            sijainti = pala;
            return true;
        }
        return false;
    }
    /**
     * @return hahmon sijainti
     */
    public Karttapala getSijainti() {
        return sijainti;
    }
    
    /**
     * @return hahmon inventaario
     */
    public Inventaario getInventaario() {
        return inventaario;
    }
    public void lisaaTavarat(List<String> tavarat) {
        for(String t : tavarat) {
            inventaario.lisaaTavara(t);
        }
    }
    
    /**
     * Abstrakti metodi johon perivä luokka toteuttaa olennon hyökkäyslogiikan.
     * 
     * @return hahmon tekemä vahinko
     */
    public abstract int hyokkaa();
    /**
     * Abstrakti metodi johon perivä luokka toteuttaa olennon puolustuslogiikan
     * 
     * @return hahmon tekemät torjunnat
     */
    public abstract int puolustaudu();
    /**
     * Abstrakti metodi, jonka toteutuksen tulee muuttaa hahmon energiaa otetun vahingon mukaisesti
     * 
     * @param vahinko pelilogiikan laskema vahinko
     * @return viesti käyttöliittymälle, joka vaihtelee sen mukaan onko vahinkoa ottava Olento pelaajahahmo vai monsteri.
     */
    public abstract String otaVahinkoa(int vahinko);
}
