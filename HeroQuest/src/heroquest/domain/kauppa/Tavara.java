/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.domain.kauppa;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import java.io.File;
import java.util.Scanner;

import heroquest.PeliController;
import heroquest.util.Tiedostoapuri;

/**
 * Olentojen inventaarioissa ja pelaajan kodin kaupassa majailevia tavaroita kuvaava luokka.
 * 
 * @author Merioksan Mikko
 */
public class Tavara {
    private String nimi;
    private int arvo;
    private String toiminto;
    private String palautus;
    
    public Tavara(String tiedosto) {
        Scanner lukija = Tiedostoapuri.tiedostoLukijaan("src/tavarat/" + tiedosto);
        
        this.nimi = lukija.nextLine();
        this.arvo = Integer.parseInt(lukija.nextLine());
        toiminto = lukija.nextLine();
        palautus = lukija.nextLine();
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
     * Tavaran käyttämisen toteuttava metodi.
     * Tavarat voivat vaikuttaa mihin tahansa pelin elementtiin, minkä vuoksi metodille annetaan parametrina kontrolleri.
     * 
     * @param pc kontrolleri, johon tavara vaikuttaa
     * @return tavaran käytön seurauksia kuvaileva viesti
     */
    public String kayta(PeliController pc) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        
        try {
            engine.put("controller", pc);
            engine.eval(toiminto);
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
}
