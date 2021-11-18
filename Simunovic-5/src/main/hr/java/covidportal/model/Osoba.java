package main.hr.java.covidportal.model;

import checkers.oigj.quals.O;
import main.hr.java.covidportal.main.Glavna;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa Osoba služi za kreiranje instance osobe
 */
public class Osoba {
    private String ime;
    private String prezime;
    private Integer starost;
    private Zupanija zupanija;
    private Bolest zarazenBolescu;
    private List<Osoba> kontaktiraneOsobe;


    /**
     * @param ime varijabla tipa String (ime osobe)
     * @param prezime varijabla tipa String (prezime osobe)
     * @param starost varijabla tipa Intagre (starost osobe)
     * @param zupanija Objekt zupanije (zupanija prebivalista osobe)
     * @param zarazenBolescu Objekt bolesti (bolest osobe)
     * @param kontaktiraneOsobe polje osoba (kontaktirane osobe)
     */
    public Osoba(String ime, String prezime, Integer starost, Zupanija zupanija, Bolest zarazenBolescu, List<Osoba> kontaktiraneOsobe) {
        this.ime = ime;
        this.prezime = prezime;
        this.starost = starost;
        this.zupanija = zupanija;
        this.zarazenBolescu = zarazenBolescu;
        this.kontaktiraneOsobe = kontaktiraneOsobe;
        if (zarazenBolescu instanceof Virus) {
            Virus nazivVirusa = new Virus(zarazenBolescu.getNaziv(), zarazenBolescu.getSimptomi());
            if(kontaktiraneOsobe != null){
                for (int i = 0; i < kontaktiraneOsobe.size(); i++) {
                    nazivVirusa.prelazakZarazeNaOsobu(kontaktiraneOsobe.get(i));
                }
            }
        }
    }

    /**
     * Klasa za kreiranje osobe pomoću Builder Patterna - nove funkcionalnosti JAVE
     */
    public static class Builder {
        private String ime;
        private String prezime;
        private Integer starost;
        private Zupanija zupanija;
        private Bolest zarazenBolescu;
        private List<Osoba> kontaktiraneOsobe;

        public Builder() {
        }

        public Builder seZove(String ime) {
            this.ime = ime;
            return this;
        }

        public Builder sePreziva(String prezime) {
            this.prezime = prezime;
            return this;
        }

        public Builder imaGodina(Integer starost) {
            this.starost = starost;
            return this;
        }

        public Builder pripadaZupaniji(Zupanija zupanija) {
            this.zupanija = zupanija;
            return this;
        }

        public Builder imaBolest(Bolest zarazenBolescu) {
            this.zarazenBolescu = zarazenBolescu;
            return this;
        }

        public Builder kontaktiraneOsobe(List<Osoba> kontaktiraneOsobe) {
            this.kontaktiraneOsobe = kontaktiraneOsobe;
            return this;
        }

        public Osoba build() {
            return new Osoba(ime, prezime, starost, zupanija, zarazenBolescu, kontaktiraneOsobe);
        }
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Integer getStarost() {
        return starost;
    }

    public void setStarost(Integer starost) {
        this.starost = starost;
    }

    public Zupanija getZupanija() {
        return zupanija;
    }

    public void setZupanija(Zupanija zupanija) {
        this.zupanija = zupanija;
    }

    public Bolest getZarazenBolescu() {
        return zarazenBolescu;
    }

    public void setZarazenBolescu(Bolest zarazenBolescu) {
        this.zarazenBolescu = zarazenBolescu;
    }

    public List<Osoba> getKontaktiraneOsobe() {
        return kontaktiraneOsobe;
    }

    public void setKontaktiraneOsobe(List<Osoba> kontaktiraneOsobe) {
        this.kontaktiraneOsobe = kontaktiraneOsobe;
    }
}
