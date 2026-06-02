# Knjižnica

Knjižnica je web aplikacija za upravljanje radom male knjižnice. Aplikacija omogućuje vođenje evidencije knjiga, članova knjižnice i posudbi kroz jednostavno web sučelje izrađeno u Spring Bootu i Thymeleafu.

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

## Baza podataka

Aplikacija koristi MySQL bazu podataka. Prije pokretanja aplikacije potrebno je kreirati bazu podataka i korisnika koji se koristi u `application.properties`.

U MySQL Workbenchu ili terminalu potrebno je pokrenuti:

```sql
CREATE DATABASE knjiznica_db;

CREATE USER 'knjiznica_user'@'localhost' IDENTIFIED BY 'knjiznica_user';

GRANT ALL PRIVILEGES ON knjiznica_db.* TO 'knjiznica_user'@'localhost';

FLUSH PRIVILEGES;
```

Postavke za spajanje na bazu nalaze se u datoteci `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/knjiznica_db
spring.datasource.username=knjiznica_user
spring.datasource.password=knjiznica_user
spring.jpa.hibernate.ddl-auto=update
```

Tablice nije potrebno ručno kreirati jer ih aplikacija kreira automatski pomoću JPA/Hibernate konfiguracije.

Nakon prvog pokretanja aplikacije, u bazi bi trebale biti kreirane tablice:

- `book`
- `member`
- `loan`

Tablice se mogu provjeriti u MySQL Workbenchu ili terminalu naredbama:

```sql
USE knjiznica_db;
SHOW TABLES;
```

## Pokretanje aplikacije

Aplikaciju je moguće pokrenuti iz IntelliJ IDEA okruženja pokretanjem glavne klase:

```text
KnjiznicaSpringApplication
```

Aplikaciju je moguće pokrenuti i pomoću Maven wrappera iz terminala, iz glavnog direktorija projekta:

```bash
./mvnw spring-boot:run
```

Na Windowsu:

```bash
mvnw.cmd spring-boot:run
```

Nakon pokretanja aplikacija je dostupna u pregledniku na adresi:

```text
http://localhost:8080
```

