package main.hr.java.covidportal.model;

/**
 * Klasa Zupanija koja nasljeđuje klasu ImenovniEntitet
 */
public class Zupanija extends  ImenovaniEntitet{

    private Integer brojStanovnika;
    private Integer brojZarazenihOsoba;


    /**
     * @param naziv  varijabla tipa String koja definira naziv zupanije
     * @param brojStanovnika varijabla tipa Integer koja definira broj stanovnika u zupaniji
     */

    public Zupanija(String naziv, Integer brojStanovnika, Integer brojZarazenihOsoba) {
        super(naziv);
        this.brojStanovnika = brojStanovnika;
        this.brojZarazenihOsoba = brojZarazenihOsoba;
    }


    public Integer getBrojZarazenihOsoba() {
        return brojZarazenihOsoba;
    }

    public void setBrojZarazenihOsoba(Integer brojZarazenihOsoba) {
        this.brojZarazenihOsoba = brojZarazenihOsoba;
    }

    public Integer getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(Integer brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }
}
