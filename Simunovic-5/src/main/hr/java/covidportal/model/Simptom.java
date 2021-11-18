package main.hr.java.covidportal.model;

import main.hr.java.covidportal.enumeracija.VrijednostiSimptoma;

/**
 * Klasa Simptom za kreiranje instance simptoma
 * nasljeđuje ImenovaniEntitet i njenu varijablu naziv
 */
public class Simptom extends ImenovaniEntitet {
    private VrijednostiSimptoma vrijednost;

    public Simptom(String naziv, VrijednostiSimptoma vrijednost) {
        super(naziv);
        this.vrijednost = vrijednost;
    }

    public VrijednostiSimptoma getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(VrijednostiSimptoma vrijednost) {
        this.vrijednost = vrijednost;
    }
}
