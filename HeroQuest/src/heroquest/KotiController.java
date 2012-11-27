/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest;

import java.util.List;

import heroquest.domain.Kotikarttapala;
import heroquest.domain.Karttapala;
import heroquest.domain.kauppa.Kauppa;
import heroquest.domain.kauppa.MyyntiTavara;
import heroquest.domain.kauppa.Tavara;
/**
 *
 * @author Merioksan Mikko
 */
public class KotiController {
    /**
     * Peli-luokan olio, joka sisältää kaiken pelilogiikan.
     */
    private PeliController controller;
    /**
     * Kauppa-luokan olio, joka sisältää kaupan toiminnot kuten ostamisen ja myymisen
     */
    private Kauppa kauppa;
    
    public KotiController(PeliController pc) {
        this.controller = pc;
        this.kauppa = new Kauppa();
    }
    
    public Kauppa getKauppa() {
        return kauppa;
    }
    public void setKauppa(Kauppa k) {
        kauppa = k;
    }
    
    /**
     * @return kaupan valikoima List-oliossa
     */
    public List<MyyntiTavara> getKaupanTavarat() {
        return kauppa.getTavarat();
    }
    
    /**
     * Metodi tavaran ostamiseen kodin kaupasta. Päivittää käyttöliittymää ostamisen onnistumisen mukaisesti.
     * 
     * @param t ostettava tavara
     */
    public void osta(MyyntiTavara t) {
        if(t == null) {
            controller.paivitaKali("Valitse ihmeessä ensin jokin tavara...\n");
        }
        else {
            if(kauppa.myy(t, controller.getPeli().getPelaaja())) {
                controller.paivitaKali("Hyvät kaupat teit!\n");
            }
            else {
                controller.paivitaKali("Älä yritä, senkin köyhä!\n");
            }
        }
    }
    /**
     * Tavaroiden myymisen mahdollistava metodi.
     * 
     * @param t myytävä tavara
     */
    public void myy(Tavara t) {
        if(t == null) {
            controller.paivitaKali("Et ole valinnut myytävää tavaraa!\n");
        }
        else {
            if(kauppa.osta(t, controller.getPeli().getPelaaja())) {
                controller.paivitaKali("Myyty!\n");
            }
            else {
                controller.paivitaKali("Ei tullut kauppoja. :(\n");
            }
        }
    }
}
