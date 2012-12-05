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
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import heroquest.PeliController;
import heroquest.domain.kauppa.Tavara;
/**
 * Lähes kaikki käyttöliittymän näppäimet sisältävä paneeli. Esimerkiksi liikkuminen ja inventaarion käyttä tapahtuu täältä.
 * 
 * @author Merioksan Mikko
 */
public class Nappipaneeli extends JPanel {
    PeliController controller;
    private Liikenappipaneeli liikenappiPanel;
    JList inventaario;
    JButton inventaarionappi;
    JButton myyntinappi;
    JButton poimintanappi;
    
    public Nappipaneeli(PeliController pc) {
        this.controller = pc;
        luoKomponentit();
    }
    
    private void luoKomponentit() {
        this.setLayout(new GridLayout(1, 2));

        poimintanappi = new JButton("Poimi tavarat ruudusta");
        
        inventaarionappi = new JButton("Käytä esinettä");
        myyntinappi = new JButton("Myy esine");
        inventaario = new JList();
        inventaario.setPreferredSize(new Dimension(250, 110));
        
        poimintanappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.tavaroidenPoiminta();
            }
        });
        
        liikenappiPanel = new Liikenappipaneeli(controller);
        
        JPanel napitJaNopat = new JPanel(new GridLayout(2, 1));
        napitJaNopat.add(liikenappiPanel);
        napitJaNopat.add(poimintanappi);
        
        JPanel inventaariopaneeli = new JPanel();
        JPanel inventaarionappipaneeli = new JPanel(new GridLayout(1, 2));
        inventaarionappipaneeli.add(inventaarionappi);
        inventaarionappipaneeli.add(myyntinappi);
        inventaariopaneeli.add(new JLabel("Inventaario:"), BorderLayout.NORTH);
        inventaariopaneeli.add(inventaario);
        inventaariopaneeli.add(inventaarionappipaneeli, BorderLayout.SOUTH);
        
        inventaarionappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.kaytaTavaraa((Tavara)inventaario.getSelectedValue());
            }
        });
        
        myyntinappi.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               controller.getKoti().myy((Tavara)inventaario.getSelectedValue());
           }
        });
        
        this.add(napitJaNopat);
        this.add(inventaariopaneeli);
    }
    
    // enabloidaan/disabloidaan oikean nappulat ja namiskat käyttöliittymästä sen mukaan, missä tilassa peli on
    public void paivita(String tila) {
        liikenappiPanel.paivita(tila);
        
        if(tila.equals("taistelu")) {
            poimintanappi.setEnabled(false);
            myyntinappi.setEnabled(false);
        }
        else if(tila.equals("liikenoppa") || tila.equals("liike")) {
            poimintanappi.setEnabled(true);
            myyntinappi.setEnabled(false);
        }
        else if(tila.equals("koti")) {
            poimintanappi.setEnabled(false);
            myyntinappi.setEnabled(true);
        }
        
        List<Tavara> tavarat = controller.getPeli().getPelaaja().getInventaario().getTavarat();
        
        inventaario.setListData(tavarat.toArray());
    }
}
