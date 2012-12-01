/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.domain.kauppa;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import java.io.File;
import java.util.Scanner;

import heroquest.Peli;
import heroquest.util.Tiedostoapuri;
import heroquest.domain.Ilmansuunta;

/**
 * Olentojen inventaarioissa ja pelaajan kodin kaupassa majailevia tavaroita kuvaava luokka.
 * 
 * @author Merioksan Mikko
 */
public class Tavara {
    /**
     * Tiedosto josta tavara luettiin (tallentamista varten)
     */
    private String tiedosto;
    /**
     * Tavaran nimi
     */
    private String nimi;
    /**
     * Tavaran arvo kultaduplooneina.
     */
    private int arvo;
    /**
     * Tavaran vaikutus, kun sitä käytetään.
     */
    private String toiminto;
    /**
     * Teksti, joka näytetään pelaajalle tavaraa käytettäessä.
     */
    private String palautus;
    /**
     * Tieto siitä, poistetaanko tavara inventaariosta käytettäessä.
     */
    boolean kkayttoinen;
    
    public Tavara(String tiedosto) {
        this.tiedosto = tiedosto;
        
        Scanner lukija = Tiedostoapuri.tiedostoLukijaan("src/tavarat/" + tiedosto);
        
        this.nimi = lukija.nextLine();
        this.arvo = Integer.parseInt(lukija.nextLine());
        toiminto = lukija.nextLine();
        palautus = lukija.nextLine();
        
        kkayttoinen = Boolean.parseBoolean(lukija.nextLine());
    }
    
    public int getArvo() {
        return arvo;
    }
    public void setArvo(int a) {
        arvo = a;
    }
    
    public String getNimi() {
        return nimi;
    }
    public void setNimi(String n) {
        nimi = n;
    }
    
    /**
     * Palauttaa tiedon, onko tavara kertakäyttöinen vai ei.
     * 
     * @return tavaran kertakäyttöisyys
     */
    public boolean kkayttoinen() {
        return kkayttoinen;
    }
    
    /**
     * Tavaran käyttämisen toteuttava metodi.
     * Tavarat voivat vaikuttaa mihin tahansa pelin elementtiin, minkä vuoksi metodille annetaan parametrina kontrolleri.
     * Tavaran aiheuttamat vaikutukset luetaan String-muuttujasta skriptinä.
     * 
     * @param pc kontrolleri, johon tavara vaikuttaa
     * @return tavaran käytön seurauksia kuvaileva viesti
     */
    public String kayta(Peli p) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        
        try {
            // asetetaan scriptaus-moottorille muuttujia. Ilmansuunnat annetaan, jotta skriptit voivat vaikuttaa myös viereisiin ruutuihin.
            engine.put("etela", Ilmansuunta.ETELA);
            engine.put("pohjoinen", Ilmansuunta.POHJOINEN);
            engine.put("ita", Ilmansuunta.ITA);
            engine.put("lansi", Ilmansuunta.LANSI);
            engine.put("peli", p);
            
            if(toiminto != null && !toiminto.equals("")) {
                engine.eval(new java.io.FileReader(toiminto));
            }
        }
        catch(Exception e) {
            System.out.println("Oi ei, tuli virhe: ");
            e.printStackTrace();
            System.exit(-1); 
        }
        
        return palautus;
    }
    
    @Override
    public String toString() {
        return nimi;
    }
    
    @Override
    public boolean equals(Object o) {
        if(!o.getClass().equals(this.getClass())) {
            return false;
        }
        
        Tavara t = (Tavara)o;
        
        return this.nimi.equals(t.nimi);
    }
    
    @Override
    public int hashCode() {
        return nimi.hashCode();
    }
    
    /**
     * Peliä tallentaessa, tallennetaan tiedosto, josta tavaran tiedot voi lukea.
     */
    public String tallenna() {
        return tiedosto;
    }
}
