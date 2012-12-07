package heroquest.domain;

/**
 * Olento-luokan perivä luokka, joka kuvaa pelaajan hahmoa
 *
 * @author Merioksan Mikko
 */
public class Pelaaja extends Olento {
    /**
     * Pelaajan hahmoluokka.
     */
    private String luokka;
    /**
     * Pelaajalla jäljellä olevat liikkeet.
     */
    private int liikkeet;
    /**
     * Pelaajan tappamien monsterien määrä.
     */
    private int tapot;
    /**
     * Pelaajan varakkuus. Rahalla voi ostaa kaikkea kivaa!
     */
    private int varat;
    /**
     * Kuvatiedoston nimi, jonka pelaaja on valinnut lärvikseen.
     */
    private String kuva;
    
    public Pelaaja(String nimi, int voima, int energia, int nopeus, String luokka) {
        super(nimi, voima, energia, nopeus);
        setLuokka(luokka);
        liikkeet = 0;
        tapot = 0;
        varat = 100;
    }
    public Pelaaja(int voima, int energia, int nopeus, String luokka) {
        super(voima, energia, nopeus);
        setLuokka(luokka);
        liikkeet = 0;
        tapot = 0;
        varat = 100;
    }
    
    /**
     * Asetetaan pelaajan luokka.
     * 
     * @param luokka pelaajan valitsema luokka
     */
    private void setLuokka(String luokka) {
        luokka = luokka.trim();
        if(luokka.length() > 0) {
            this.luokka = luokka;
        }
        else {
            this.luokka = "Defaultsoturi";
        }
    }
    /**
     * Palautetaan pelaajan hahmoluokka.
     * 
     * @return hahmon hahmoluokka
     */
    public String getLuokka() {
        return luokka;
    }
    
    /**
     * Asetetaan pelaajalle liikkeet.
     * 
     * @param l pelaajan esim. nopanheitolla tienaamat liikkeet
     */
    public void setLiikkeet(int l) {
        liikkeet = l;
    }
    /**
     * Palautetaan pelaajan jäljellä olevat likkeet.
     * 
     * @return pelaajan jäljellä olevat liikkeet
     */
    public int getLiikkeet() {
        return liikkeet;
    }
    /**
     * Metodi, joka liikuttaa pelaajaa haluttuun suuntaan ja vähentää jäljellä olevia liikkeitä.
     * Jos liikkuminen ei onnistu, liikkeet eivät vähene.
     * 
     * @param suunta haluttu suunta
     */
    public void liiku(Ilmansuunta suunta) {
        Karttapala vanha = this.getSijainti();
        if(this.setSijainti(vanha.getNaapuri(suunta))) {
            vanha.pelaajaPoistuu();
            this.getSijainti().pelaajaSaapuu();
            liikkeet--;
        }
    }
    
    /**
     * Lisää pelaajalle tappoja. Jes!
     * 
     * @param t tappojen määrä
     */
    public void lisaaTapot(int t) {
        tapot += t;
        if(tapot > 2 && !this.getNimi().contains("Mörkökiller")) {
            this.setNimi("\"Mörkökiller\" " + this.getNimi());
        }
    }
    /**
     * Palautetaan pelaajan tappamien Monsterien määrä.
     * 
     * @return pelaajan tappamien Monsterien määrä
     */
    public int getTapot() {
        return tapot;
    }
    
    /**
     * Asetetaan pelaajan varallisuus.
     * 
     * @param v summa joksi pelaajan rahamäärä muutetaan
     */
    public void setVarat(int v) {
        varat = v;
    }
    /**
     * Palautetaan pelaajan varallisuus.
     * 
     * @return pelaajan varakkuus
     */
    public int getVarat() {
        return varat;
    }
    
    /**
     * Asetetaan kuvatiedoston nimi, josta pelaajan kuva löytyy.
     * 
     * @param kuva kuvatiedoston nimi.
     */
    public void setKuva(String kuva) {
        this.kuva = kuva;
    }
    /**
     * Palautetaan kuvatiedoston nimi, josta pelaajan kuva löytyy.
     * 
     * @return kuvatiedoston nimi
     */
    public String getKuva() {
        return kuva;
    }
    
    /*
     * Taistelumetodit:
     *  - hyökätessä pelaaja heittää voimansa verran noppia ja osuu jos noppa on viisi tai kuusi
     *  - puolustaessa pelaaja heittää voimansa verran noppia ja puolusutus onnistuu jos noppa on viisi tai kuusi
     */
    @Override
    public int hyokkaa() {
        int iskut = 0;
        for(int i = 0; i < this.getVoima(); i++) {
            int heitto = random.nextInt(6) + 1;
            if(heitto > 4) {
                iskut++;
            }
        }
        return iskut;
    }
    
    @Override
    public int puolustaudu() {
        int torjunnat = 0;
        for(int i = 0; i < this.getVoima(); i++) {
            int heitto = random.nextInt(6) + 1;
            if(heitto > 4) {
                torjunnat++;
            }
        }
        return torjunnat;
    }
    
    @Override
    public String otaVahinkoa(int vahinko) {
        muutaEnergia(-vahinko);
        return "Voi ei! Otit " + vahinko + " pistettä vahinkoa!\n";
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNimi() + " (" + this.getLuokka() + ")\n");
        sb.append("Voima: " + this.getVoima() + "\n");
        sb.append("Energia: ");
        for(int i = 0; i < this.getEnergia(); i++) {
            sb.append("*");
        }
        sb.append("\n");
        sb.append("Nopeus: " + getNopeus() + "\n");
        sb.append("Kultaduplooneja: " + getVarat() + "\n");
        sb.append("Kokemustaso: " + getTaso() + " (XP: " + getExp() + ")\n");
        
        return sb.toString();
    }
    
    /**
     * Metodi, joka lähettää pelaajan tiedot yhtenä Stringinä tallennettavaksi tiedostoon.
     * 
     * @return pelaajan strategiset mitat
     */
    @Override
    public String tallenna() {
        return super.tallenna() + ";" + getLuokka() + ";" + getTapot() + ";" + getVarat() + ";" + getKuva() + "\n";
        
    }
}
