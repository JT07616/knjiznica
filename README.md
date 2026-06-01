# Knjiznica

Knjiznica je web aplikacija za upravljanje radom male knjižnice. Aplikacija omogućuje vođenje evidencije knjiga, članova knjižnice i posudbi kroz jednostavno web sučelje izrađeno u Spring Bootu i Thymeleafu.

Kroz aplikaciju se mogu dodavati, uređivati, pregledavati i brisati zapisi. Posudbe povezuju knjige i članove, pri čemu se prati je li knjiga trenutno dostupna ili posuđena.

## Način rada aplikacije

- Kod nove posudbe mogu se odabrati samo dostupne knjige.
- Knjiga koja je posuđena označava se kao nedostupna.
- Vraćanjem knjige ona ponovno postaje dostupna.
- Knjiga ili član ne mogu se obrisati ako imaju evidentirane posudbe.
- Brisanje se potvrđuje na posebnoj stranici.

## Funkcionalnosti

| Cjelina | Funkcionalnost |
|---|---|
| Knjige | Pregled popisa knjiga |
| Knjige | Detalji knjige |
| Knjige | Dodavanje nove knjige |
| Knjige | Uređivanje knjige |
| Knjige | Brisanje knjige uz potvrdu |
| Knjige | Pretraga knjiga po naslovu |
| Članovi | Pregled popisa članova |
| Članovi | Detalji člana |
| Članovi | Dodavanje novog člana |
| Članovi | Uređivanje člana |
| Članovi | Brisanje člana uz potvrdu |
| Članovi | Pretraga člana po emailu |
| Posudbe | Pregled svih posudbi |
| Posudbe | Dodavanje nove posudbe |
| Posudbe | Uređivanje člana na aktivnoj posudbi |
| Posudbe | Označavanje knjige kao vraćene |
| Posudbe | Filtriranje aktivnih i vraćenih posudbi |
| Početna stranica | Prikaz statistike i trenutno posuđenih knjiga |

## Tehnologije

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Thymeleaf
- MySQL
- Maven