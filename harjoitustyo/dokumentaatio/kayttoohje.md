# Käyttöohje
Lataa viimeinen release githubista, eli tiedosto StocksPal.jar

## Konfigurointi
Ohjelma olettaa pari asiaa
 1. Sinulla on SQLite3 asennettuna koneellesi. Ohjelma käyttää sqlite-tietokanta joka pitää sisällään käyttäjätiedot sekä osaketiedot. Eli SQLite on välttämätöntä.
 2. Käynnistyshakemistossa on oltava tietokanta-tiedosto nimeltään "StocksPalDB.db"

Tietokanta luodaan helpolla komennolla
```
touch StocksPalDB.db
```
Ei ole tarvetta luoda tietokantaan taulukkoja, siksi ohjelma hoitaa tietokannan setup itse, kun ohjelma käynnistyy.

## Ohjelman käynnistäminen
Ohjelma käynnistetään komennolla

```
java -jar StocksPal.jar
```

## Kirjautuminen
Sovellus käynnistyy kirjautumisnäkymään. Syötä käyttäjätunnus ja salasana ja paina login

## Uuden käyttäjätilin luominen
Syötä tekstikenttään haluttu nimi ja salasanakenttään haluttu salasana. Klikkaa "Register user"-painiketta. Ohjelma ilmoittaa jos rekisteröinti onnistui vai ei. Sen jälkeen voit kirjautua sisään.

## Ohjelman käyttö
Päänäkymässä voit tarkistaa osakehinnat taulukon ja viivakaavion avulla.
"My Stocks"-näkymässä voit tallenna omat osakkeesi.