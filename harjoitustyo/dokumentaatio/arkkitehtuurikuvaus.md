# Arkkitehtuuridokumentti
## Rakenne
Projektin rakenteen periaate on "Modulaarisuus", eli kaikki komponentit leivotaan näteihin moduuleihin jotka voidaan käyttää useissa eri tilanteissa
Esim. Kaikki liittyen autentikaatioon ja käyttäjätilin hallintaan on AuthenticationManager:in vastulla. Osaketietojen ja -hintojen haku hoitaa StocksDAO. DatabaseDAO hoitaa ohjelman ja tietokannat väliset transaktiot.

## Käyttöliittymä
Käyttöliittymä on kolmiosainen
- Kirjautumisnäkymä
- Päänäkymä(Missä kaikki osakehinnat ovat)
- "My Stocks/Omat osakkeeni"-näkymä missä on kaikki omaan tiliin tallennettu osakedataa

Jokainen näkymä on kirjoitettu XML-muodossa Java FXML-tiedostoina. Nämä tiedostot ladataan ohjelmaan FXMLLoaderin avulla ja niistä tulee Scene olioita, jotka voidaan asettaa Stage:en ja käyttää silloin näkyminä.
Käyttöliittymän hallinta ja datavirta(data flow?) hoitaa kolme kontrolleria, LoginSceneController, MainSceneController ja MyStocksController.

## Osakedatan hallinta
API/Tietorajapinta mikä olen päättänyt käyttää on AlphaVantagen osakedata-rajapinta, koska se on paras löytämäni maksuton ratkaisu. Huomasin tosin, liian möyhään, että AlphaVantagen rajapinnssa on kyselyrajoitus. Ei saa tehdä enemmän kuin 5 pyyntöä minuutissa ja ei 500 enempää päivässä. Tätä vähän estää sovelluksen toimivuus, mutta sillä voi kuitenkin elää. On huomattavan hastavaa löytää hyvä, ilmainen API missä ei olisi mitään tälläistä.

Osakedatan hankkiminen API:sta tehdään helpolla URLConnection:illa ja BufferedReader-lukijalla. API:sta saamamme data on JSON-muodossa, joka myöhemmin käsitellään ja välitetään kontrollereihin Googlen GSON-kirjaston avulla.

## Tiedostot & pysyväisyystallennus
Sovelluksen ainoa käyttämä tiedosto on SQLiten tietokanta-tiedosto. SQLite ei sovi hirveän hyvin suurempiin projekteihin, mutta päätin että se on tämän kurssin kannalta sopivin vaihtoehto jos halutaan tallentaa tietokantaan.

Tietokantayhteys on luotu DAO-sunnittelumallia noudattaen, ja luokkiin voi lisätä toiminnallisuus jos esim. datan talletustapa vaihtuu tai tarvitaan lisäta ja hankkia enemmän erityyppistä dataa tietokantaan ohjelman laajennettaessa.

Tietokannassa on nyt kaksi taulukkoam User & UserStock. User sisältää käyttäjätilien dataa ja UserStock on "tietofragmentti" mikä sisältää tietoa käyttäjätilien omistamista osakkeista. Jokainen UserStock-rivi liitetään toisiinsa viiteavaimella. Relaatio on ns. yksi-moneen, eli yhdellä käyttäjällä voi olla monta UserStock-fragmenttia jonka viiteavaimet osoittaa häneen. 

Tietokanta-tiedoston sisältö voidaan myös erikseen tarkistaa avaamalla sitä SQLite3 ohjelmalla, sitä tehdään seuraavalla komennolla

```
sqlite3 StocksPalDB.db
```
Ohjelma avautuu komentorivissä ja ohjelmaan voit syöttää SQL-kyselyjä nähdäksesi siihen tallennettua tietoa. Kirjoittamalla SQLite-komento ".schema" pääset näkemään kaikki tietokannan taulut ja rivit.

## Ohjelman oleellisimmat toiminnallisuudet sekvenssikaavioina
### Kirjautuminen
<img src="https://github.com/sinyman/OT-Harjoitustyo/blob/master/harjoitustyo/dokumentaatio/images/loginSequence.png" alt="loginSequence.png">
### Uuden käyttäjätilin luominen
<img src="https://github.com/sinyman/OT-Harjoitustyo/blob/master/harjoitustyo/dokumentaatio/images/registrationsequence.png" alt="RegistrationSequence.png">
### Osakekurssin datan hankkiminen ja esittäminen
<img src="https://github.com/sinyman/OT-Harjoitustyo/blob/master/harjoitustyo/dokumentaatio/images/checkStocksSequence.png" alt="StocksSearchSequence.png">
