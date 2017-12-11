### Harjoitustyön dokumentaatio


#### Aiheen kuvaus
Harjoitustyön aiheena oli uutissivusto, jossa käyttäjä voi selata uutisia kategorioittain, sekä muokata uutissivuston näkymää lisäämällä valikkoon kategorioita. Sivustolla tulee myös olla listattuna sekä uusimmat että viikon luetuimmat uutiset. Tämän lisäksi ylläpitäjä voi lisätä sivustolla sekä poistaa niitä. Ylläpitäjä voi myös lisätä kategorioita ja kirjoittajia uutisille.

Harjoitustyö mahdollistaa tällä hetkellä vain yhden käyttäjän kirjautumisen.

#### Käyttötapaukset

```gherkin
Scenario: Käyttäjä avaa valitsemansa uutisen esittelysivun
Given Käyttäjä on etusivulla
When Käyttäjä klikkaa valitsemaansa uutista
Then: Sivusto ohjautuu käyttäjän valitseman uutisen esittelysivulle

Scenario: Käyttäjä lisää kategorian navigaatiopalkkiin
Given Käyttäjä on etusivulla
When Käyttäjä klikkaa "Muokkaa näkymää"-linkkiä
And Käyttäjä valitsee haluamansa kategorian ja klikkaa "Pin to navbar"-linkkiä
Then Valittu kategoria lisätään navigaatiopalkkiin

Scenario: Käyttäjä kirjautuu järjestelmään
Given Käyttäjä on etusivulla ja luonut aikaisemmin tunnuksen
When Käyttäjä klikkaa "Kirjaudu sisään"-linkkiä
And Käyttäjä syöttää validin käyttäjänimen ja salasanan avautuvaan lomakkeeseen
Then Käyttäjä kirjataan sisään järjestelmään ja hänet ohjataan takaisin etusivulle

Scenario: Ylläpitäjä lisää uutisen sivustolle
Give: Ylläpitäjä on etusivulla ja kirjautuneena
When Ylläpitäjä klikkaa "Lisää uutinen"-linkkiä
And Ylläpitäjä syöttää validit tiedot avautuvaan lisäyslomakkeeseen
Then Valittu uutinen lisätään sivustolle ja viesti lisäyksestä näytetään

Scenario: Ylläpitäjä lisää kategorian sivustolle
Given Ylläpitäjä on etusivulla ja kirjautuneena
When Ylläpitäjä klikkaa "Lisää kategoria"-linkkiä
And Ylläpitäjä syöttää validin kategorian nimen avautuvaan lomakkeeseen
Then Valittu kategoria lisätään sivustolle ja viesti lisäyksestä näytetään
```

### Ominaisuudet

- Uutisia voi lisätä ja poistaa
- Uutiset luokitellaan kategorioittain
- Uutisia voi lukea, jokaisella uutisella on oma esittelysivu
- Uutisia voi muokata
- Kategorioita voi lisätä, samoin kirjoittajia
- Sovellukseen voi kirjautua ja rekisteröityä (huom. vain yksi käyttäjä)


### Tietokannan skeema

![Tietokannan skeema](https://github.com/laimikko1/Springnewz/blob/master/documentation/springnewz-database.png)


### Käyttöohje

Ohjaa itsesi sovelluksen osoitteeseen ja rupea klikkailemaan. Linkki alla:
[Springnewz](https://springnewz.herokuapp.com/)


### Asennusohje
Voit käyttää sovellusta joko yllämainitusti selaimen kautta tai vaihtoehtoisesti
lataa projektin (tai kloonata gitin kautta) ja napsutella terminaaliin projektin juurikansiossa:
```bash
mvn spring-boot:run
```
Tämän jälkeen sovellus on käytettävissä osoitteessa:
www.localhost:8080




### TODO
- Sovellus tukee vain yhtä kirjautujaa tällä hetkellä
- Kategorioita ja kirjoittajia ei voi poistaa/muokata
- Uutisia ei voi hakea, eikä niitä sivuteta
- Sovellus ei validoi lisätyn uutiskuvan kokoa
- Käyttäjätasoja ei ole
- Metoditason autorisointi on puutteellista
