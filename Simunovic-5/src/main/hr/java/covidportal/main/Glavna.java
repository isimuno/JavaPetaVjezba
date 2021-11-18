package main.hr.java.covidportal.main;

import main.hr.java.covidportal.enumeracija.VrijednostiSimptoma;
import main.hr.java.covidportal.genericsi.KlinikaZaInfektivneBolesti;
import main.hr.java.covidportal.iznimke.BolestIstihSimptomaException;
import main.hr.java.covidportal.iznimke.DuplikatKontaktiraneOsobeException;
import main.hr.java.covidportal.model.*;
import main.hr.java.covidportal.sortiranje.CovidSorter;
import main.hr.java.covidportal.sortiranje.VirusSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Klasa Glavna predstavlja glavnu klasu programa
 *
 * @author isimun2
 */

public class Glavna {
    public static final Integer BROJ_OSOBA = 4;
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * @param args polje tipa String
     */
    public static void main(String[] args) {
        logger.info("Aplikacija pokrenuta! ");
        Scanner unos = new Scanner(System.in);
        Set<Zupanija> zupanije = new LinkedHashSet<>();
        Integer brojZupanija = 0;
        boolean pogreskaKodUnosa = false;
        do {
            pogreskaKodUnosa = true;
            try {
                System.out.print("Unesite broj županija koje želite unijeti: ");
                brojZupanija = unos.nextInt();
                pogreskaKodUnosa = false;
            } catch (InputMismatchException e) {
                System.out.println("Pogreska, molimo unesite brojcanu vrijednost!");
            }
            unos.nextLine();
        } while (pogreskaKodUnosa);
        for (int i = 0; i < brojZupanija; i++) {
            zupanije.add(unesiZupaniju(i, unos));
        }

        Set<Simptom> simptomi = new LinkedHashSet<>();
        Integer brojSimptoma = 0;
        do {
            pogreskaKodUnosa = true;
            try {
                System.out.print("Unesite broj simptoma koje želite unijeti: ");
                brojSimptoma = unos.nextInt();
                pogreskaKodUnosa = false;
            } catch (InputMismatchException e) {
                System.out.println("Pogreska, molimo unesite brojcanu vrijednost!");
            }
            unos.nextLine();
        } while (pogreskaKodUnosa);

        for (int i = 0; i < brojSimptoma; i++) {
            simptomi.add(unesiSimptome(i, unos));
        }

        Set<Bolest> bolesti = new LinkedHashSet<>();
        Integer brojBolesti = 0;
        do {
            pogreskaKodUnosa = true;
            try {
                System.out.print("Unesite broj bolesti koje želite unijeti: ");
                brojBolesti = unos.nextInt();
                pogreskaKodUnosa = false;
            } catch (InputMismatchException e) {
                System.out.println("Pogreska, molimo unesite brojcanu vrijednost!");
            }
            unos.nextLine();
        } while (pogreskaKodUnosa);


        for (int i = 0; i < brojBolesti; i++) {
            boolean ponoviUnos = false;
            do {
                ponoviUnos = false;
                try {
                    bolesti.add(unesiBolesti(i, unos, simptomi, bolesti));
                } catch (BolestIstihSimptomaException e) {
                    logger.info(e.getMessage());
                    logger.warn("Aktivirana iznimka: " + e);
                    logger.error(e.getMessage(), e);
                    ponoviUnos = true;
                }
            } while (ponoviUnos);
        }

        Integer brojOsoba = 0;
        do {
            pogreskaKodUnosa = true;
            try {
                System.out.print("Unesite broj osoba koje želite unijeti: ");
                brojOsoba = unos.nextInt();
                pogreskaKodUnosa = false;
            } catch (InputMismatchException e) {
                System.out.println("Pogreska, molimo unesite brojcanu vrijednost!");
            }
            unos.nextLine();
        } while (pogreskaKodUnosa);


        List<Osoba> osobe = new ArrayList<>();

        for (int i = 0; i < brojOsoba; i++) {
            try {
                osobe.add(unesiOsobe(i, unos, bolesti, zupanije, osobe));
            } catch (DuplikatKontaktiraneOsobeException ex) {
                logger.info(ex.getMessage());
                logger.warn("Aktivirana iznimka" + ex.getMessage());
                System.out.println("Odabrana osoba se već nalazi među kontaktiranim osobama. Molimo Vas da odaberete neku drugu osobu !");
            }
        }


        HashMap<Bolest, List<Osoba>> mapaOsobaIBolesti = new HashMap<>();
        List<Bolest> listaBolesti = new ArrayList<>();
        Bolest bolest = null;
        for (int i = 0; i < osobe.size(); i++) {
            if (i < 1) {
                bolest = new Bolest(osobe.get(i).getZarazenBolescu().getNaziv(), osobe.get(i).getZarazenBolescu().getSimptomi());
                listaBolesti.add(bolest);
            } else {
                if (bolest.getNaziv().equals(osobe.get(i).getZarazenBolescu().getNaziv())) {
                    continue;
                } else {
                    bolest = new Bolest(osobe.get(i).getZarazenBolescu().getNaziv(), osobe.get(i).getZarazenBolescu().getSimptomi());
                    listaBolesti.add(bolest);
                }
            }
        }

        Set<Bolest> set = new LinkedHashSet<>(listaBolesti);
        listaBolesti.clear();
        listaBolesti.addAll(set);


        for (Bolest b : listaBolesti) {
            System.out.println(b.getNaziv());
        }

        ArrayList<Osoba> listaBolesnihOsoba = null;
        for (int i = 0; i < listaBolesti.size(); i++) {
            listaBolesnihOsoba = new ArrayList<>();
            for (Osoba o : osobe) {
                if (listaBolesti.get(i).getNaziv().equals(o.getZarazenBolescu().getNaziv())) {
                    listaBolesnihOsoba.add(o);
                }
            }
            mapaOsobaIBolesti.put(listaBolesti.get(i), listaBolesnihOsoba);
        }


        System.out.println("Unesene osobe: ");
        for (int i = 0; i < brojOsoba; i++) {
            System.out.println("================================================================");
            System.out.println("Ime i prezime: " + osobe.get(i).getIme() + "  " + osobe.get(i).getPrezime());
            System.out.println("Starost: " + osobe.get(i).getStarost());
            System.out.println("Županija prebivališta: " + osobe.get(i).getZupanija().getNaziv());
            System.out.println("Zaražen bolešću: " + osobe.get(i).getZarazenBolescu().getNaziv());
            if (osobe.get(i).getKontaktiraneOsobe() != null) {
                System.out.print("Kontakt osobe: ");
                for (int o = 0; o < osobe.get(i).getKontaktiraneOsobe().size(); o++)
                    if (osobe.get(i).getKontaktiraneOsobe().get(o) != null) {
                        System.out.print(osobe.get(i).getKontaktiraneOsobe().get(o).getIme() + " " + osobe.get(i).getKontaktiraneOsobe().get(o).getPrezime() + ", ");
                    }
                System.out.println("");
            } else {
                System.out.println("Kontakt osobe:  Nema kontaktiranih osoba!");
            }
        }
        System.out.println("================================================================");
        System.out.println("================================================================");
        System.out.println("Ispis mape: ");
        for (Bolest b : listaBolesti) {
            System.out.print("Od bolesti " + b.getNaziv() + " boluju: ");
            for (Map.Entry<Bolest, List<Osoba>> entry : mapaOsobaIBolesti.entrySet()) {
                if (entry.getKey().getNaziv().equals(b.getNaziv())) {
                    for (Osoba o : mapaOsobaIBolesti.get(b)) {
                        System.out.print(o.getIme() + " " + o.getPrezime() + ",  ");
                    }
                }
            }
            System.out.println("");
        }
        System.out.println("================================================================");

        List<Zupanija> listZupanija = new ArrayList<>(zupanije);
        Collections.sort(listZupanija, new CovidSorter());
        Double brStanovnikaZ1 = listZupanija.get(0).getBrojStanovnika().doubleValue();
        Double brStanovnikaZ2 = listZupanija.get(listZupanija.size() - 1).getBrojStanovnika().doubleValue();
        Double brZarazenihZ1 = listZupanija.get(0).getBrojZarazenihOsoba().doubleValue();
        Double brZarazenihZ2 = listZupanija.get(listZupanija.size() - 1).getBrojZarazenihOsoba().doubleValue();
        Double brojZarazenihZupanija1 = (brZarazenihZ1 / (brStanovnikaZ1));
        Double brojZarazenihZupanija2 = (brZarazenihZ2 / (brStanovnikaZ2));

        System.out.println("Najviše zaraženih ima u zupaniji " + listZupanija.get(0).getNaziv() + " " + brojZarazenihZupanija1 * 100 + " %");
        System.out.println("Najmanje zaraženih ima u zupaniji " + listZupanija.get(listZupanija.size() - 1).getNaziv() + " " + brojZarazenihZupanija2 * 100 + " %");

        System.out.println("================================================================");

        List<Virus> uneseniVirusi = new ArrayList<>();
        List<Osoba> oboljeleOsobe = new ArrayList<>();
        for (Bolest b : bolesti) {
            if (b instanceof Virus) {
                uneseniVirusi.add((Virus) b);
                for (Osoba o : osobe) {
                    if (o.getZarazenBolescu() instanceof Virus && o.getZarazenBolescu().getNaziv().equals(b.getNaziv())) {
                        oboljeleOsobe.add(o);
                    }
                }
            }
        }

        KlinikaZaInfektivneBolesti klinika = new KlinikaZaInfektivneBolesti(uneseniVirusi, oboljeleOsobe);

        Instant pocetakMjerenja1 = Instant.now();
        Collections.sort(klinika.getListaVirusa(), new VirusSorter());
        Instant krajMjerenja1 = Instant.now();
        System.out.println("Sortirani virusi : ");
        for (int i = 0; i < klinika.getListaVirusa().size(); i++) {
            System.out.println(((Virus) klinika.getListaVirusa().get(i)).getNaziv());
        }
        System.out.println("Ukupno trajanje sortiranja pomoću klase VirusSorter je : " + Duration.between(pocetakMjerenja1, krajMjerenja1).toMillis() +" milisekundi.");


        System.out.println("================================================================");

        Instant pocetakMjerenja2 = Instant.now();
        Collections.sort(klinika.getListaVirusa(), (Virus v1, Virus v2) -> v2.getNaziv().compareTo(v1.getNaziv()));
        Instant krajMjerenja2 = Instant.now();
        System.out.println("Sortirani virusi : ");
        for (int i = 0; i < klinika.getListaVirusa().size(); i++) {
            System.out.println(((Virus) klinika.getListaVirusa().get(i)).getNaziv());
        }
        System.out.println("Ukupno trajanje sortiranja pomoću lambda funkcije je : " + Duration.between(pocetakMjerenja2, krajMjerenja2).toMillis() +" milisekundi.");
        long saLambda = Duration.between(pocetakMjerenja2, krajMjerenja2).toMillis();

        System.out.println("================================================================");

        Instant pocetakMjerenja3 = Instant.now();
        klinika.getListaVirusa().sort(Comparator.comparing(Virus::getNaziv).reversed());
        Instant krajMjerenja3 = Instant.now();
        long bezLambda = Duration.between(pocetakMjerenja3, krajMjerenja3).toMillis();
        System.out.println("Sortirani virusi : ");
        for (int i = 0; i < klinika.getListaVirusa().size(); i++) {
            System.out.println(((Virus) klinika.getListaVirusa().get(i)).getNaziv());
        }
        System.out.println("Ukupno trajanje sortiranja pomoću metode sort(): " + Duration.between(pocetakMjerenja3, krajMjerenja3).toMillis() +" milisekundi.");

        System.out.println("================================================================");

        System.out.println("Sortiranje objekata korištenjem lambdi traje "+ saLambda +" milisekundi, a bez lambda traje " +
                bezLambda + " milisekundi.");

        System.out.println("================================================================");

        System.out.print("Unesite string za pretragu po prezimenu: ");
        String uvjetPretrage = unos.nextLine();
        System.out.println("Osobe čije prezime sadrži " + uvjetPretrage + " su sljedeće:");

        Optional<List<Osoba>> optional = Optional.ofNullable(osobe);
        optional.get().stream()
                .filter(o -> o.getPrezime().contains(uvjetPretrage))
                .map(o -> o.getIme() + " " + o.getPrezime())
                .forEach(System.out::println);


        System.out.println("================================================================");

        bolesti.stream()
                .map(b -> b.getNaziv() + " ima " + b.getSimptomi().stream().count() + " simptoma.")
                .forEach(System.out::println);


    }


    /**
     * Predstavlja metodu za unos Osoba u polje osobe
     *
     * @param i        predstavlja brojac Osoba tipa Integer
     * @param unos     instanca klase za unos podataka
     * @param bolesti  zbirka tipa set bolesti
     * @param zupanije zbirka tipa set zupanija
     * @param osobe    lista osoba
     * @return instanca Osobe
     * @throws DuplikatKontaktiraneOsobeException iznimka
     */

    private static Osoba unesiOsobe(int i, Scanner unos, Set<Bolest> bolesti, Set<Zupanija> zupanije, List<Osoba> osobe) throws DuplikatKontaktiraneOsobeException {
        String ime, prezime;
        Integer starost = 0;
        Zupanija zupanijaPrebivalista = new Zupanija("Naziv", 0, 0);
        Bolest zarazenBolescu = new Bolest("Naziv", null);
        List<Osoba> kontaktOsobe = new ArrayList<>();
        System.out.print("Unesite ime " + (i + 1) + ". osobe: ");
        ime = unos.nextLine();
        System.out.print("Unesite prezime " + (i + 1) + ". osobe: ");
        prezime = unos.nextLine();
        boolean ponoviUnos = false;
        do {
            ponoviUnos = false;
            try {
                System.out.print("Unesite starost " + (i + 1) + ". osobe: ");
                starost = unos.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Pogreška, molimo unesite brojčanu vrijednost !");
                ponoviUnos = true;
                logger.info("Pokušaj unosa nedozvoljene vrijednosti za starost! ");
                logger.warn("Aktivirana iznimka: ", ex);
                logger.error(ex.getMessage(), ex);
            }
            unos.nextLine();
        } while (ponoviUnos);
        Zupanija[] poljeZupanije = zupanije.toArray(new Zupanija[zupanije.size()]);
        do {
            try {
                System.out.println("Odaberite zupaniju prebivališta: ");
                for (int z = 0; z < poljeZupanije.length; z++) {
                    System.out.println((z + 1) + ". " + poljeZupanije[z].getNaziv());
                }
                System.out.print("Odabir: ");
                Integer odabranaZupanija = unos.nextInt();
                if (odabranaZupanija > zupanije.size() || odabranaZupanija < 1) {
                    System.out.println("Pograšan unos!");
                    ponoviUnos = true;
                } else {
                    ponoviUnos = false;
                    zupanijaPrebivalista = poljeZupanije[odabranaZupanija - 1];
                }
            } catch (InputMismatchException ex) {
                System.out.println("Pogreška, molimo unesite brojčanu vrijednost !");
                ponoviUnos = true;
                logger.info("Pokušaj unosa nedozvoljene vrijednosti pri odabiru zupanije! ");
                logger.warn("Aktivirana iznimka: ", ex);
                logger.error(ex.getMessage(), ex);
            }
            unos.nextLine();
        } while (ponoviUnos);

        do {
            try {
                ArrayList<Bolest> listaBolesti = new ArrayList<>(bolesti);
                System.out.println("Odaberite bolest osobe: ");
                for (int b = 0; b < bolesti.size(); b++) {
                    System.out.println((b + 1) + ". " + listaBolesti.get(b).getNaziv());
                }
                System.out.print("Odabir: ");
                Integer odabranaBolest = unos.nextInt();
                if (odabranaBolest < 1 || odabranaBolest > bolesti.size()) {
                    System.out.println("Pogrešan unos!");
                    ponoviUnos = true;
                } else {
                    zarazenBolescu = listaBolesti.get(odabranaBolest - 1);
                    ponoviUnos = false;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Pogreška, molimo unesite brojčanu vrijednost !");
                ponoviUnos = true;
                logger.info("Pokušaj unosa nedozvoljene vrijednosti pri odabiru bolesti! ");
                logger.warn("Aktivirana iznimka: ", ex);
                logger.error(ex.getMessage(), ex);
            }
            unos.nextLine();
        } while (ponoviUnos);

        Integer brojKontaktOsoba = 0;
        if (i < 1) {
            Osoba novaOsoba = new Osoba.Builder()
                    .seZove(ime)
                    .sePreziva(prezime)
                    .imaGodina(starost)
                    .pripadaZupaniji(zupanijaPrebivalista)
                    .imaBolest(zarazenBolescu)
                    .build();
            return novaOsoba;
        } else {
            do {
                try {
                    System.out.print("Unesite broj kontaktiranih osoba: ");
                    brojKontaktOsoba = unos.nextInt();
                    if (brojKontaktOsoba > i) {
                        System.out.println("Broj kontaktiranih osoba ne može biti veći od broja unesenih osoba ! ");
                        ponoviUnos = true;
                    } else {
                        ponoviUnos = false;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Pogreška, molimo unesite brojčanu vrijednost !");
                    ponoviUnos = true;
                }
                unos.nextLine();
            } while (ponoviUnos);

            boolean dupliKorisnik = false;
            for (int o = 0; o < brojKontaktOsoba; o++) {
                do {
                    ponoviUnos = false;
                    try {
                        System.out.println("Odaberite " + (o + 1) + ". kontakt osobu: ");
                        for (int kontOsob = 0; kontOsob < osobe.size(); kontOsob++) {
                            if (osobe.get(kontOsob) != null) {
                                System.out.println((kontOsob + 1) + ". " + osobe.get(kontOsob).getIme() + " " + osobe.get(kontOsob).getPrezime());
                            }
                        }
                        System.out.print("Odabir: ");
                        Integer odabranaKontaktOsoba = unos.nextInt();
                        if (odabranaKontaktOsoba < 1 || odabranaKontaktOsoba > osobe.size()) {
                            System.out.println("Pogrešan unos! ");
                            ponoviUnos = true;
                        } else {
                            dupliKorisnik = DuplikatKontaktOsoba(kontaktOsobe, osobe.get(odabranaKontaktOsoba - 1), o);
                            if (dupliKorisnik == false) {
                                kontaktOsobe.add(osobe.get(odabranaKontaktOsoba - 1));
                                ponoviUnos = false;
                            } else if (dupliKorisnik == true) {
                                System.out.println("Odabrana osoba se već nalazi među kontaktiranim osobama. Molimo Vas da odaberete neku drugu osobu.");
                                ponoviUnos = true;
                            }
                        }
                    } catch (InputMismatchException ex) {
                        System.out.println("Pogreška, molimo unesite brojčanu vrijednost !");
                        ponoviUnos = true;
                        logger.info("Pokušaj unosa nedozvoljene vrijednosti za odabir kontaktirane osobe! ");
                        logger.warn("Aktivirana iznimka: ", ex);
                    } catch (DuplikatKontaktiraneOsobeException e) {
                        System.out.println("Odabrana osoba se već nalazi među kontaktiranim osobama. Molimo Vas da odaberete neku drugu osobu.");
                        logger.info(e.getMessage());
                        logger.warn("Aktivirana iznimka: ", e);
                        logger.error(e.getMessage(), e);
                        ponoviUnos = true;
                    }
                    unos.nextLine();
                } while (ponoviUnos);


            }
            Osoba novaOsoba = new Osoba.Builder()
                    .seZove(ime)
                    .sePreziva(prezime)
                    .imaGodina(starost)
                    .pripadaZupaniji(zupanijaPrebivalista)
                    .imaBolest(zarazenBolescu)
                    .kontaktiraneOsobe(kontaktOsobe)
                    .build();
            return novaOsoba;
        }
    }

    /**
     * @param kontaktOsobe polje kontaktiranih osoba
     * @param osoba        objekt Osoba
     * @param o            brojač tipa Integer
     * @return boolean vrijednost
     * @throws DuplikatKontaktiraneOsobeException
     */
    private static boolean DuplikatKontaktOsoba(List<Osoba> kontaktOsobe, Osoba osoba, int o) throws
            DuplikatKontaktiraneOsobeException {
        boolean postojiDuplikat = false;
        for (int i = 0; i < o; i++) {
            if (kontaktOsobe.get(i).equals(osoba)) {
                postojiDuplikat = true;
            }
        }
        if (postojiDuplikat == true) {
            throw new DuplikatKontaktiraneOsobeException("Odabrana osoba se već nalazi među kontaktiranim osobama. " +
                    "Molimo Vas da odaberete neku drugu osobu !");
        }
        return postojiDuplikat;
    }

    /**
     * @param i        brojač bolesti tipa Integer
     * @param unos     instanca klase za unos podataka
     * @param simptomi zbirk kreiranih simptoma tipa set
     * @param bolesti  zbirka tipa set koja predstavlja bolesti
     * @return instanca Bolest
     */
    private static Bolest unesiBolesti(int i, Scanner unos, Set<Simptom> simptomi, Set<Bolest> bolesti) {
        boolean ponoviUnos = false;
        Set<Simptom> simptomiBolesti = new LinkedHashSet<>();
        ArrayList<Simptom> listaSimptoma = new ArrayList<>(simptomi);
        Integer vrstaBolesti = 0;
        do {
            ponoviUnos = false;
            try {
                System.out.println("Unosite li bolest ili virus ?");
                System.out.println("1. BOLEST");
                System.out.println("2. VIRUS");
                System.out.print("Odabir: ");
                vrstaBolesti = unos.nextInt();
                if (vrstaBolesti == 1 || vrstaBolesti == 2) {
                    ponoviUnos = false;
                } else {
                    System.out.println("Pogrešan unos! Pokušajte ponovno!");
                    ponoviUnos = true;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Molimo unesite brojčanu vrijednost! ");
                ponoviUnos = true;
                logger.info("Pokušaj unosa nedozvoljene vrijednosti odabiru vrste bolesti ! ");
                logger.warn("Aktivirana iznimka: ", ex);
            }
            unos.nextLine();
        } while (ponoviUnos);

        if (vrstaBolesti == 1) {
            String nazivBolesti;
            Integer brojSimptoma = 0;
            System.out.print("Unesite naziv bolesti: ");
            nazivBolesti = unos.nextLine();
            do {
                ponoviUnos = false;
                try {
                    System.out.print("Unesite broj simptoma: ");
                    brojSimptoma = unos.nextInt();

                } catch (InputMismatchException ex) {
                    System.out.println("Pogreška, molimo unesite brojčanu vrjednost!");
                    ponoviUnos = true;
                    logger.info("Pokušaj unosa nedozvoljene vrijednosti unosu broja simptoma! ");
                    logger.warn("Aktivirana iznimka: ", ex);
                    logger.error(ex.getMessage(), ex);
                }
                unos.nextLine();
            } while (ponoviUnos);


            Integer odabraniSimptom;
            for (int j = 0; j < brojSimptoma; j++) {
                do {
                    try {
                        System.out.println("Odaberite " + (j + 1) + ". simptom: ");
                        for (int s = 0; s < simptomi.size(); s++) {
                            System.out.println((s + 1) + ". " + listaSimptoma.get(s).getNaziv() + " " + listaSimptoma.get(s).getVrijednost().naziv);
                        }
                        System.out.print("Odabir: ");
                        odabraniSimptom = unos.nextInt();
                        if (odabraniSimptom < 1 || odabraniSimptom > simptomi.size()) {
                            System.out.println("Pogrešan unos!");
                            ponoviUnos = true;
                        } else {
                            ponoviUnos = false;
                            simptomiBolesti.add(listaSimptoma.get(odabraniSimptom - 1));
                        }

                    } catch (InputMismatchException ex) {
                        System.out.println("Pogreška, molimo unesite brojčanu vrijednost !");
                        ponoviUnos = true;
                        logger.info("Pokušaj unosa nedozvoljene vrijednosti pri odabiru simptoma bolesti ! ");
                        logger.warn("Aktivirana iznimka: ", ex);
                        logger.error(ex.getMessage(), ex);
                    }
                    unos.nextLine();
                } while (ponoviUnos);
            }
            DuplikatBolesti(bolesti, simptomiBolesti, nazivBolesti, i);
            Bolest novaBolest = new Bolest(nazivBolesti, simptomiBolesti);
            return novaBolest;
        } else {
            String nazivVirusa;
            Integer brojSimptoma = 0;
            System.out.print("Unesite naziv virusa: ");
            nazivVirusa = unos.nextLine();
            do {
                ponoviUnos = false;
                try {
                    System.out.print("Unesite broj simptoma: ");
                    brojSimptoma = unos.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Pogreška, molimo unesite brojčanu vrjednost!");
                    logger.info("Pokušaj unosa nedozvoljene vrijednosti pri odabiru vrste bolesti! ");
                    logger.warn("Aktivirana iznimka: ", ex);
                    ponoviUnos = true;
                }
                unos.nextLine();
            } while (ponoviUnos);

            Integer odabraniSimptom;
            for (int j = 0; j < brojSimptoma; j++) {
                do {
                    try {
                        System.out.println("Odaberite " + (j + 1) + ". simptom: ");
                        for (int s = 0; s < simptomi.size(); s++) {
                            System.out.println((s + 1) + ". " + listaSimptoma.get(s).getNaziv() + " " + listaSimptoma.get(s).getVrijednost().naziv);
                        }
                        System.out.print("Odabir: ");
                        odabraniSimptom = unos.nextInt();
                        if (odabraniSimptom < 1 || odabraniSimptom > simptomi.size()) {
                            System.out.println("Pogrešan unos!");
                            ponoviUnos = true;
                        } else {
                            ponoviUnos = false;
                            simptomiBolesti.add(listaSimptoma.get(odabraniSimptom - 1));
                        }
                    } catch (InputMismatchException ex) {
                        System.out.println("Pogreška, molimo unesite brojčanu vrijednost!");
                        logger.info("Pokušaj unosa nedozvoljene vrijednosti pri odabiru simptoma virusa! ");
                        logger.warn("Aktivirana iznimka: ", ex);
                        logger.error(ex.getMessage(), ex);
                        ponoviUnos = true;
                    }
                    unos.nextLine();
                } while (ponoviUnos);
            }
            DuplikatBolesti(bolesti, simptomiBolesti, nazivVirusa, i);
            Bolest novaBolest = new Virus(nazivVirusa, simptomiBolesti);
            return novaBolest;
        }
    }

    /**
     * @param bolesti         zbirka tipa set unesenih bolesti
     * @param simptomiBolesti polje unesenih simptoma bolesti
     * @param nazivBolesti    varijabla koja sadrzi nazivBolesti
     * @param i               brojac tipa Integer
     * @return boolean vrijednost postoji duplikat ili ne
     */
    private static boolean DuplikatBolesti(Set<Bolest> bolesti, Set<Simptom> simptomiBolesti, String nazivBolesti, int i) {
        ArrayList<Simptom> listaSimptoma = new ArrayList<>(simptomiBolesti);
        ArrayList<Bolest> listaBolesti = new ArrayList<>(bolesti);
        boolean postojiDuplikat = false;
        for (int j = 0; j < i; j++) {
            if (listaBolesti.get(j) != null) {
                if (listaBolesti.get(j).getNaziv().equals(nazivBolesti)) {
                    for (int s = 0; s < listaBolesti.get(j).getSimptomi().size(); s++) {
                        for (int s2 = 0; s2 < simptomiBolesti.size(); s2++) {
                            ArrayList<Simptom> listaSimptomaBolesti = new ArrayList<>(listaBolesti.get(j).getSimptomi());
                            if (listaSimptomaBolesti.get(s).getVrijednost().equals(listaSimptoma.get(s2).getVrijednost()) && s == s2) {
                                if (simptomiBolesti.size() == listaBolesti.get(j).getSimptomi().size()) {
                                    postojiDuplikat = true;
                                }

                            }
                        }
                    }
                }
            }
        }
        if (postojiDuplikat == true) {
            System.out.println("Pogrešan unos, već ste unijeli bolest ili virus s istim simptomima." +
                    " Molimo ponovite unos!");
            throw new BolestIstihSimptomaException("Unešena bolest/virus s istim simptomima.");
        }
        return postojiDuplikat;
    }

    /**
     * @param i    brojac Simptoma tipa Integer
     * @param unos instanca klase za unos podataka
     * @return instanca Simptoma
     */
    private static Simptom unesiSimptome(int i, Scanner unos) {
        String naziv, vrijednost;
        VrijednostiSimptoma vrijednostiSimptoma = null;
        System.out.print("Unesite naziv " + (i + 1) + ". simptoma: ");
        naziv = unos.nextLine();
        boolean ponoviUnos = false;
        do {
            System.out.print("Unesite vrijednost simptoma (RIJETKO, SREDNJE, ČESTO): ");
            vrijednost = unos.nextLine();
            if (vrijednost.equals(VrijednostiSimptoma.VRIJEDNOSTI_SIMPTOMA1.naziv)) {
                vrijednostiSimptoma = VrijednostiSimptoma.VRIJEDNOSTI_SIMPTOMA1;
                ponoviUnos = false;
            } else if (vrijednost.equals(VrijednostiSimptoma.VRIJEDNOSTI_SIMPTOMA2.naziv)) {
                vrijednostiSimptoma = VrijednostiSimptoma.VRIJEDNOSTI_SIMPTOMA2;
                ponoviUnos = false;
            } else if (vrijednost.equals(VrijednostiSimptoma.VRIJEDNOSTI_SIMPTOMA3.naziv)) {
                vrijednostiSimptoma = VrijednostiSimptoma.VRIJEDNOSTI_SIMPTOMA3;
                ponoviUnos = false;
            } else {
                System.out.println("Molimo koristite samo zadane vrijednosti (RIJETKO;SREDNJE;ČESTO)!");
                ponoviUnos = true;
            }
        } while (ponoviUnos);
        Simptom noviSimptom = new Simptom(naziv, vrijednostiSimptoma);
        return noviSimptom;
    }

    /**
     * @param i    brojač Zupanija tipa Integer
     * @param unos instanca klase za unos podataka
     * @return instanca Zupanije
     */
    private static Zupanija unesiZupaniju(int i, Scanner unos) {
        String naziv;
        Integer brojStanovnika = 0;
        Integer brojzarazenihStanovnika = 0;
        System.out.print("Unesite naziv " + (i + 1) + ". županije: ");
        naziv = unos.nextLine();
        boolean nastaviUnos = false;
        do {
            nastaviUnos = false;
            try {
                System.out.print("Unesite broj stanovnika: ");
                brojStanovnika = unos.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Pogreška, molimo unesite broj");
                logger.info("Pokušaj unosa nedozvoljene vrijednosti pri unosu broja stanovnika zupanije! ");
                logger.warn("Aktivirana iznimka: ", ex);
                logger.error(ex.getMessage(), ex);
                nastaviUnos = true;
            }
            unos.nextLine();
        } while (nastaviUnos == true);
        do {
            nastaviUnos = false;
            try {
                System.out.print("Unesite broj zarazenih stanovnika: ");
                brojzarazenihStanovnika = unos.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Pogreška, molimo unesite broj");
                logger.info("Pokušaj unosa nedozvoljene vrijednosti pri unosu broja stanovnika zupanije! ");
                logger.warn("Aktivirana iznimka: ", ex);
                logger.error(ex.getMessage(), ex);
                nastaviUnos = true;
            }
            unos.nextLine();
        } while (nastaviUnos == true);

        Zupanija novaZupanija = new Zupanija(naziv, brojStanovnika, brojzarazenihStanovnika);
        return novaZupanija;
    }

}
