# JavaPetaVjezba

5.Peta laboratorijska vježba

5.1. TEMA VJEŽBE
Svrha laboratorijske vježbe je korištenje dvije paradigme programskog
jezika Java: generičkog i funkcionalnog programiranja. Generičko
programiranje će se implementirati kroz korištenje parametriziranih
klasa, a u sklopu funkcionalnog programiranja se koriste lambda izrazi.
5. 2. ZADATAK ZA PRIPREMU
Proširiti rješenje treće laboratorijske vježbe na način da se kopira
rješenje te preimenuje u naziv koji sadrži indeks „5“, umjesto „4“. Osim
same mape s projektom, potrebno je promijeniti i naziv projekta unutar
IntelliJ-a korištenjem opcije „Refactor->Rename“. Program je potrebno
proširiti na sljedeći način:
1. Kreirati paket „hr.java.covidportal.genericsi“.
2. Unutar paketa „hr.java.covidportal.genericsi“ kreirati
parametriziranu klasu „KlinikaZaInfektivneBolesti“ s parametrom „T“
koji može biti samo klasa koja je instanca klase „Virus“ ili koja
nasljeđuje klasu „Virus“ te parametrom „S“ koja može je instanca
klase „Osoba“ i koja nasljeđuje klasu „Osoba“. Klasa mora sadržavati
parametriziranu listu koja sadrži sve viruse koji su uneseni u
aplikaciju. Osim toga klasa mora sadržavati i parametriziranu listu
koja sadrži sve osobe koje su zaražene unesenim virusima. U klasi
implementirati konstruktor koji instancira navedene liste, metode za
dodavanje novih elemenata u liste te metode za dohvaćanje listi.
3. Korištenjem lambda izraza i klase po želji koja će sortirati objekte
klase „Virus“ po nazivu suprotno od poretka abecede, sortirati objekte
klase „Virus“ iz klase „KlinikaZaInfektivneBolesti“ te ih sortirane
ispisati na kraju programa.
4. Sortiranje implementirano u trećem koraku implementirati i pomoću
liste bez lambda izraza, izmjeriti vrijeme trajanja sortiranja s i bez
korištenja lambdi te ih usporediti i ispisati na zaslon. Kod mjerenja
vremena potrebno je koristiti klasu „Instant“ kako je navedeno na 
ovom linku: https://stackoverflow.com/questions/3382954/measureexecution-time-for-a-java-method.
5. Korištenjem lambda izraza implementirati filtriranje objekata klase
„Osoba“ po prezimenu prema zadanom nazivu te rezultate ispisati na
kraju programa. U slučaju da nije pronađena nijedna vrijednost kod
filtriranja, potrebno je vratiti objekt tipa „Optional“.
6. Korištenjem metode „map“ pomoću lambdi ispisati broj simptoma za
svaki virus ili bolest koja je unesena u aplikaciju.
Primjer izvođenja programa:
Unesite broj županija koje želite unijeti: 3
Unesite podatke o 3 županije:
Unesite naziv županije: GRAD ZAGREB
Unesite broj stanovnika: asb
Pogreška u formatu podataka, molimo ponovite unos!
Unesite broj stanovnika: 1000000
Unesite broj zaraženih stanovnika: 20000
Unesite naziv županije: MEĐIMURSKA
Unesite broj stanovnika: 100000
Unesite broj zaraženih stanovnika: 500
Unesite naziv županije: VARAŽDINSKA
Unesite broj stanovnika: 200000
Unesite broj zaraženih stanovnika: 10000
Unesite broj simptoma koje želite unijeti: 3
Unesite podatke o 3 simptoma:
Unesite naziv simptoma: VISOKA TEMPERATURA
Unesite vrijednost simptoma (RIJETKO, SREDNJE ILI ČESTO): ČESTO
Unesite naziv simptoma: GLAVOBOLJA
Unesite vrijednost simptoma (RIJETKO, SREDNJE ILI ČESTO): SREDNJE
Unesite naziv simptoma: GRLOBOLJA
Unesite vrijednost simptoma (RIJETKO, SREDNJE ILI ČESTO): RIJETKO
Unesite broj bolesti koje želite unijeti: 2
Unesite broj virusa koje želite unijeti: 2
Unesite podatke o 4 bolesti ili virusa:
Unosite li bolest ili virus?
1) BOLEST
2) VIRUS
ODABIR >> 1
Unesite naziv bolesti ili virusa: HERPES
Unesite broj simptoma: 1
Odaberite 1. simptom:
1. VISOKA TEMPERATURA ČESTO
2. GLAVOBOLJA SREDNJE
3. GRLOBOLJA RIJETKO
Odabir: 3
Unosite li bolest ili virus?
1) BOLEST
2) VIRUS
ODABIR >> 2
Unesite naziv bolesti ili virusa: Covid
Unesite broj simptoma: 1
Odaberite 1. simptom:
1. VISOKA TEMPERATURA ČESTO
2. GLAVOBOLJA SREDNJE
3. GRLOBOLJA RIJETKO
Odabir: 3
Pogrešan unos, već ste unijeli bolest ili virus s istim simptomima. Molimo
ponovite unos.
Unesite naziv bolesti ili virusa: Covid
Unesite broj simptoma: 1
Odaberite 1. simptom:
1. VISOKA TEMPERATURA ČESTO
2. GLAVOBOLJA SREDNJE
3. GRLOBOLJA RIJETKO
Odabir: 1
Unosite li bolest ili virus?
1) BOLEST
2) VIRUS
ODABIR >> 2
Unesite naziv bolesti ili virusa: Gripa
Unesite broj simptoma: 1
Odaberite 1. simptom:
1. VISOKA TEMPERATURA ČESTO
2. GLAVOBOLJA SREDNJE
3. GRLOBOLJA RIJETKO
Odabir: 2
Unosite li bolest ili virus?
1) BOLEST
2) VIRUS
ODABIR >> 2
Unesite naziv bolesti ili virusa: KONJUKTIVITIS
Unesite broj simptoma: 1
Odaberite 1. simptom:
1. VISOKA TEMPERATURA ČESTO
2. GLAVOBOLJA SREDNJE
3. GRLOBOLJA RIJETKO
Odabir: 2
Unesite 1. ime osobe: Pero
Unesite prezime osobe: Perić
Unesite starost osobe: 88
Unesite županiju prebivališta osobe:
1. GRAD ZAGREB
2. MEĐIMURSKA
3. VARAŽDINSKA
Odabir: 1
Unesite bolest ili virus osobe:
1. HERPES
2. Covid
3. Gripa
4. KONJUKTIVITIS
Odabir: 4
Unesite 2. ime osobe: Janko
Unesite prezime osobe: Janić
Unesite starost osobe: 77
Unesite županiju prebivališta osobe:
1. GRAD ZAGREB
2. MEĐIMURSKA
3. VARAŽDINSKA
Odabir: 2
Unesite bolest ili virus osobe:
1. HERPES
2. Covid
3. Gripa
4. KONJUKTIVITIS
Odabir: 2
Unesite broj osoba koje su bile u kontaktu s tom osobom: 2
Neispravan unos, možete odabrani maksimalno 1 osobu.
Unesite broj osoba koje su bile u kontaktu s tom osobom: 1
Unesite osobe koje su bile u kontaktu s tom osobom:
Odaberite 1. osobu:
1. Pero Perić
Odabir: 1
Unesite 3. ime osobe: Marica
Unesite prezime osobe: Ždrerić
Unesite starost osobe: 44
Unesite županiju prebivališta osobe:
1. GRAD ZAGREB
2. MEĐIMURSKA
3. VARAŽDINSKA
Odabir: 3
Unesite bolest ili virus osobe:
1. HERPES
2. Covid
3. Gripa
4. KONJUKTIVITIS
Odabir: 3
Unesite broj osoba koje su bile u kontaktu s tom osobom: 2
Unesite osobe koje su bile u kontaktu s tom osobom: 
Odaberite 1. osobu:
1. Pero Perić
2. Janko Janić
Odabir: 2
Odaberite 2. osobu:
1. Pero Perić
2. Janko Janić
Odabir: 2
Odabrana osoba se već nalazi među kontaktiranim osobama. Molimo Vas da
odaberete neku drugu osobu.
Odaberite 2. osobu:
1. Pero Perić
2. Janko Janić
Odabir: 1
Popis osoba:
Ime i prezime: Pero Perić
Starost: 88
Županija prebivališta: GRAD ZAGREB
Zaražen bolešću: Gripa
Kontaktirane osobe:
Nema kontaktiranih osoba.
Ime i prezime: Janko Janić
Starost: 77
Županija prebivališta: MEĐIMURSKA
Zaražen bolešću: Gripa
Kontaktirane osobe:
Pero Perić
Ime i prezime: Marica Ždrerić
Starost: 44
Županija prebivališta: VARAŽDINSKA
Zaražen bolešću: Gripa
Kontaktirane osobe:
Pero Perić
Janko Janić
Od virusa Gripa boluju: Pero Perić, Janko Jankić, Marica Žderić
Najviše zaraženih osoba ima u županiji VARAŽDINSKA: 5%.
Virusi sortirani po nazivu suprotno od poretka abecede:
1. KONJUKTIVITIS
2. GRIPA
3. COVID
Sortiranje objekata korištenjem lambdi traje X milisekundi, a bez lambda traje
X milisekundi.
Unesite string za pretragu po prezimenu: ić
Osobe čije prezime sadrži “ić” su sljedeće:
Pero Perić
Janko Janić
Marina Žderić
COVID ima 1 simptoma.
GRIPA ima 1 simptoma.
KONJUKTIVITIS ima 1 simptoma.
