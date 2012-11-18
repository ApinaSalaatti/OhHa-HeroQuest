/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heroquest.kayttoliittyma;

import java.util.List;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import heroquest.PeliController;
import heroquest.domain.Ilmansuunta;
import heroquest.kayttoliittyma.kuuntelijat.LiikenappiKuuntelija;
import heroquest.kayttoliittyma.kuuntelijat.LiikenoppaKuuntelija;
import heroquest.kayttoliittyma.kuuntelijat.TaistelunoppaKuuntelija;
/**
 *
 * @author Merioksan Mikko
 */
public class Nappipaneeli extends JPanel {
    PeliController controller;
    JButton ylos;
    JButton alas;
    JButton vasen;
    JButton oikea;
    JButton liikenoppa;
    JButton taistelunoppa;
    JList inventaario;
    JButton inventaarionappi;
    JButton poimintanappi;
    
    public Nappipaneeli(PeliController pc) {
        this.controller = pc;
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        this.setLayout(new GridLayout(1, 2));
        JPanel liikkumisnapit = new JPanel();
        JPanel nopat = new JPanel();
        ylos = new JButton("^");
        alas = new JButton("V");
        vasen = new JButton("<");
        oikea = new JButton(">");
        liikenoppa = new JButton("Liiku!");
        taistelunoppa = new JButton("Taistele!");
        poimintanappi = new JButton("Poimi tavarat ruudusta");
        
        inventaarionappi = new JButton("Käytä esinettä");
        inventaario = new JList();
        inventaario.setPreferredSize(new Dimension(250, 110));
        
        ylos.addActionListener(new LiikenappiKuuntelija(controller));
        alas.addActionListener(new LiikenappiKuuntelija(controller));
        vasen.addActionListener(new LiikenappiKuuntelija(controller));
        oikea.addActionListener(new LiikenappiKuuntelija(controller));
        liikenoppa.addActionListener(new LiikenoppaKuuntelija(controller));
        taistelunoppa.addActionListener(new TaistelunoppaKuuntelija(controller));
        
        poimintanappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.tavaroidenPoiminta();
            }
        });
        
        liikkumisnapit.add(ylos, BorderLayout.NORTH);
        liikkumisnapit.add(alas, BorderLayout.SOUTH);
        liikkumisnapit.add(vasen, BorderLayout.WEST);
        liikkumisnapit.add(oikea, BorderLayout.EAST);
        nopat.add(new JLabel("Heitä noppaa:"), BorderLayout.NORTH);
        nopat.add(liikenoppa);
        nopat.add(taistelunoppa, BorderLayout.SOUTH);
        JPanel napitJaNopat = new JPanel(new GridLayout(3, 1));
        napitJaNopat.add(liikkumisnapit);
        napitJaNopat.add(nopat);
        napitJaNopat.add(poimintanappi);
        
        JPanel inventaariopaneeli = new JPanel();
        inventaariopaneeli.add(new JLabel("Inventaario:"), BorderLayout.NORTH);
        inventaariopaneeli.add(inventaario);
        inventaariopaneeli.add(inventaarionappi, BorderLayout.SOUTH);
        
        this.add(napitJaNopat);
        this.add(inventaariopaneeli);
    }
    
    // enabloidaan/disabloidaan oikean nappulat ja namiskat käyttöliittymästä sen mukaan, missä tilassa peli on
    public void paivita(String tila) {
        if(tila.equals("taistelu")) {
            liikeNapit(false);
            liikenoppa.setEnabled(false);
            taistelunoppa.setEnabled(true);
        }
        else if(tila.equals("liikenoppa")) {
            liikeNapit(false);
            liikenoppa.setEnabled(true);
            taistelunoppa.setEnabled(false);
        }
        else if(tila.equals("liike")) {
            liikeNapit(true);
            liikenoppa.setEnabled(false);
            taistelunoppa.setEnabled(false);
        }
        
        String[] tavarat = controller.getPeli().getPelaaja().getInventaario().getTavaratTaulukkona();
        
        inventaario.setListData(tavarat);
    }
    
    private void liikeNapit(boolean b) {
        if(b) {
            ylos.setEnabled(true);
            alas.setEnabled(true);
            vasen.setEnabled(true);
            oikea.setEnabled(true);
        }
        else {
            ylos.setEnabled(false);
            alas.setEnabled(false);
            vasen.setEnabled(false);
            oikea.setEnabled(false);
        }
    }
}
