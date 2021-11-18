package main.hr.java.covidportal.model;

import java.util.Set;

/**
 * Klasa Bolest nasljeđuje klasu imenovani entitet
 * služi za kreiranje instance Bolesti
 */

public class Bolest extends ImenovaniEntitet{
    private Set<Simptom> simptomi;

    /**
     * @param naziv varijabla naziva bolesti
     * @param simptomi polje simptoma bolesti
     */
    public Bolest(String naziv, Set<Simptom> simptomi) {
        super(naziv);
        this.simptomi = simptomi;
    }

    public Set<Simptom> getSimptomi() {
        return simptomi;
    }

    public void setSimptomi(Set<Simptom> simptomi) {
        this.simptomi = simptomi;
    }
}
