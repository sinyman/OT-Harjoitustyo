# StocksPal Osakeseurantajärjestelmä (OT Harjoitustyö)
Sovelluksen avulla on mahdollista seurata osakesijoitukset ja selailla pörssiä kätevästi menemättä nettiin. Sovellukseen on mahdollista luoda käyttäjätilin, ja sen avulla voidaan selata osakket ja talleta ne omaan tiliin. Käyttäjä voi sitten sovelluksesta tarkastaa esim. oman pörssin rahallinen arvo ja mahdolliset sijoitusmahdollisuudet.

<b>HUOM!</b> Huomasin viime devausviikossa, että käyttämäni API:ssa on kyselyrajoitus. Eli ei saa enemmän kuin 5 kyselyä/minuutti, ja maksimissaan 500 kyselyä/päivä. Tämä esittää projektiini käyttämiseen vaikeuksia, joten jos käytössä jotkut osakekurssit ei näy kaavioissa, on varmasti rajoitus ylitty. Sitten on vaan odotettava minuutin, ja voidaan jatkaa ohjelman käyttö taas.

## Dokumentaatio
- [Käyttöohje](https://www.github.com/sinyman/OT-Harjoitustyo/blob/master/harjoitustyo/dokumentaatio/kayttoohje.md)
- [Vaatimusmäärittely](https://www.github.com/sinyman/OT-Harjoitustyo/blob/master/harjoitustyo/dokumentaatio/vaatimusmaarittely.md)
- [Tuntikirjanpito](https://www.github.com/sinyman/OT-Harjoitustyo/blob/master/harjoitustyo/dokumentaatio/tuntikirjanpito.md)


## Releaset

[Loppupalautus](#)

## Komentorivitoiminnot
### Jar-tiedoston generointi

Komento

```
mvn package
```

generoi hakemistoon /target jar-tiedoston minkä voit sen jälkeen suorittaa komennolla

```
java -jar StocksPal-1.0-SNAPSHOT
```

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Suorittamalla komento

```
 mvn jxr:jxr checkstyle:checkstyle
```

voit luoda checkstyle-raportti, joka voit sen jälkeen tarkastaa selaimella. Sieltä ilmestyy mahdollisia tyylivirheitä. Raportti luodaan hakemistoon /target/site ja se on html-tiedosto joka voit avata web-selaimella. Tiedosto on nimeltään "Checkstyle.html"
