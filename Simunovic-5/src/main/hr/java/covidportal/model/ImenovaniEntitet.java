package main.hr.java.covidportal.model;

/**
 * Apstraktna klasa ImenovaniEntitet
 * prosljeđuje varijablu naziv klasama Bolest, Simptom, Zupanija
 */
public abstract class ImenovaniEntitet {
    String naziv;

    public ImenovaniEntitet(String naziv) {
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
