### Harjoitustyön dokumentaatio


#### Aiheen kuvaus
Harjoitustyön aiheena oli uutissivusto, jossa käyttäjä voi selata uutisia kategorioittain, sekä muokata uutissivuston näkymää haluamanlaisekseen. Tämän lisäksi ylläpitäjä voi lisätä sivustolle uutisia, muokata olemassaolevia uutisia ja poistaa niitä. Ylläpitäjä voi myös lisätä ja poistaa kategorioita. 

#### Käyttötapaukset

```gherkin
Scenario: Käyttäjä avaa valitsemansa uutisen esittelysivun
Given: Käyttäjä on etusivulla
When: Käyttäjä klikkaa valitsemaansa uutista
Then: Sivusto ohjautuu käyttäjän valitseman uutisen esittelysivulle

Scenario: Käyttäjä lisää kategorian navigaatiopalkkiin
Given Käyttäjä on etusivulla
When: Käyttäjä klikkaa "Muokkaa näkymää"-linkkiä
And: Käyttäjä valitsee haluamansa kategorian ja klikkaa "Pin to navbar"-linkkiä
Then: Valittu kategoria lisätään navigaatiopalkkiin

Scenario: Käyttäjä kirjautuu järjestelmään
Given: Käyttäjä on etusivulla ja luonut aikaisemmin tunnuksen
When: Käyttäjä klikkaa "Kirjaudu sisään"-linkkiä
And: Käyttäjä syöttää validin käyttäjänimen ja salasanan avautuvaan lomakkeeseen
Then: Käyttäjä kirjataan sisään järjestelmään ja hänet ohjataan takaisin etusivulle

Scenario: Ylläpitäjä lisää uutisen sivustolle
Given: Ylläpitäjä on etusivulla ja kirjautuneena
When: Ylläpitäjä klikkaa "Lisää uutinen"-linkkiä
And: Ylläpitäjä syöttää validit tiedot avautuvaan lisäyslomakkeeseen
Then Valittu uutinen lisätään sivustolle ja viesti lisäyksestä näytetään

Scenario: Ylläpitäjä lisää kategorian sivustolle
Given: Ylläpitäjä on etusivulla ja kirjautuneena
When: Ylläpitäjä klikkaa "Lisää kategoria"-linkkiä
And: Ylläpitäjä syöttää validin kategorian nimen avautuvaan lomakkeeseen
Then: Valittu kategoria lisätään sivustolle ja viesti lisäyksestä näytetään
```


