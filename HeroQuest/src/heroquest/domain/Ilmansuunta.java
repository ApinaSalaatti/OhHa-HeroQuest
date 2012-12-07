/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import java.util.Random;
/**
 * Luokka avuksi kartalla liikkumiseen.
 * Kartan ilmansuuntiin liittyy numero, jota hyödynnetään mm. karttapalojen naapurien tallentamisessa taulukkoon.
 * 
 * @author Merioksan Mikko
 */
public enum Ilmansuunta {
    POHJOINEN(0),
    ITA(1),
    ETELA(2),
    LANSI(3);
    
    /**
     * Ilmansuuntaa vastaava numero.
     */
    private int suuntanro;
    /**
     * Satunnaisen ilmansuunnan palauttamiseen käytetty Random-olio.
     */
    private static Random random = new Random();
    
    private Ilmansuunta(int nro) {
        this.suuntanro = nro;
    }
    
    /**
     * Palautetaan ilmansuuntaan liittyvä numero
     * 
     * @return ilmansuuntaa vastaava numero
     */
    public int getSuuntanro() {
        return suuntanro;
    }
    
    /**
     * Palautetaan ilmansuunta, joka on kyseisen ilmansuuntaolion vastakohta.
     * 
     * @return ilmansuunnan vastakohta, esim. lännellä itä.
     */
    public Ilmansuunta vastakohta() {
        if(this == POHJOINEN) {
            return ETELA;
        }
        else if(this == ITA) {
            return LANSI;
        }
        else if(this == ETELA) {
            return POHJOINEN;
        }
        else {
            return ITA;
        }
    }
    
    /**
     * Palautetaan tähän ilmansuuntaan liikuttaessa tapahtuva x-koordinaatin muutos.
     * 
     * @return ilmansuuntaan liikkuessa aiheutuva x-koordinaatin muutos
     */
    public int xMuutos() {
        switch(this) {
            case POHJOINEN:
                return 0;
            case ITA:
                return 1;
            case ETELA:
                return 0;
            default:
                return -1;
        }
    }
    
    /**
     * Palautetaan tähän ilmansuuntaan liikuttaessa tapahtuva y-koordinaatin muutos.
     * 
     * @return ilmansuuntaan liikkuessa aiheutuva y-koordinaatin muutos
     */
    public int yMuutos() {
        switch(this) {
            case POHJOINEN:
                return -1;
            case ITA:
                return 0;
            case ETELA:
                return 1;
            default:
                return 0;
        }
    }
    
    /**
     * Metodi, joka palauttaa tiettyyn numeroon liittyvän suunnan.
     * 
     * @param suunta haluttu suunta
     * @return numeroon liittyvä suunta
     */
    public static Ilmansuunta annaNumeronSuunta(int suunta) {
        switch(suunta) {
            case 0:
                return POHJOINEN;
            case 1:
                return ITA;
            case 2:
                return ETELA;
            default:
                return LANSI;
        }
    }
    
    /**
     * Palautetaan yksi, satunnainen ilmansuunta.
     * 
     * @return satunnainen ilmansuunta
     */
    public static Ilmansuunta satunnainen() {
        int suunta = random.nextInt(4);
        return annaNumeronSuunta(suunta);
    }
}
