# Vaatimusmäärittely

## Sovelluksen tarkoitus
Tarjoituksena olisi luoda sovellus joka helpottaisi ja nopeuttaisi osakehintojen selaus ja omien oskakkeiden tuottamat voitot. Sovellus tulee tarjota diagrammit sekä taulukot
jotka tekevät osakehintojen seuranta erittäin helpoksi.

## Käyttäjäluokat
Alkuvaihessa sovelluksella on ainoastaan yksi käyttäjärooli, eli peruskäyttäjä. Myöhemmin saatan lisätä myös jonkunmoinen moderaattorirooli, jos aika riittää.

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista
- Käyttäjä voi luoda käyttäjätunnuksen
- Käyttäjä voi kirjautua sisään omilla käyttötunnuksilla

### Kirjautumisen jälkeen
- Käyttäjä voi selailla osakekurssit helposti diagrammien avulla
- Käyttäjä voi tallentaa osakkeet omaan pörssiin
	* Tarkkailu tehdään helpommaksi diagrammien ja taulukoiden avulla
- Käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideoita
- Mahdollisesti voisi lisätä myös toiminnallisuus tarkkailla osakekurssit ilman sisäänkirjautumista
- Käyttäjä voi tarkkailla omat osakeostokset ja nähdä niitten voitot/tappiot
    * Tämä oli tarkoitus olla jo perusversiossa, mutta API:n kyselyrajoituksien myötä ei ollut järkevää implementoida
- Tulevaisuudessa olisi järkevää koodata jotain joka tallentaa jo haettuja osakekursseja, esim. tietokantaan, ettei tarvitsisi hakea uutta dataa API:sta kun halutaan nähdän toinen osakekurssi.
