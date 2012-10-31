/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heroquest.domain;

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
    
    private Ilmansuunta(int nro) {
        this.suuntanro = nro;
    }
    
    public int getSuuntanro() {
        return suuntanro;
    }
}
