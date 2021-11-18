package main.hr.java.covidportal.enumeracija;

public enum VrijednostiSimptoma {
    VRIJEDNOSTI_SIMPTOMA1("ČESTO"),
    VRIJEDNOSTI_SIMPTOMA2("SREDNJE"),
    VRIJEDNOSTI_SIMPTOMA3("RIJETKO");

    public final String naziv;

    private VrijednostiSimptoma(String naziv){
        this.naziv=naziv;
    }

    public String getNaziv() {
        return naziv;
    }
}
