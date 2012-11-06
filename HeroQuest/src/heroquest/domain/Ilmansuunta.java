/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

import java.util.Random;
/**
 *
 * @author merioksa
 */
public enum Ilmansuunta {
    POHJOINEN(0),
    ITA(1),
    ETELA(2),
    LANSI(3);
    
    private int suuntanro;
    private static Random random = new Random();
    
    private Ilmansuunta(int nro) {
        this.suuntanro = nro;
    }
    
    public int getSuuntanro() {
        return suuntanro;
    }
    
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
    
    public static Ilmansuunta satunnainen() {
        int suunta = random.nextInt(4);
        return annaNumeronSuunta(suunta);
    }
}
