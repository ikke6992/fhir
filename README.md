## fhir
Dit project is een versimpelde FHIR Patient Intake microservice, gemaakt in opdracht van Topicus om mijn vaardigheden te tonen.
De opdracht was om een vereenvoudigd datamodel te maken dat een deel van de data uit een NL-Core Patient opslaat en terug kan geven.
De minimumverwachting was de identifier, naam, geslacht en geboortedatum op te slaan. Daarnaast heb ik ook de contactgegevens (telefoon, email, 1 adres) en een boolean of de patient is overleden opgeslagen. 
Het plan was om ook contactpersonen en zorgorganisaties/huisartsen op een versimpelde manier op te slaan, maar door tijdsdruk ben ik daar niet aan toegekomen.

Aangezien om een versimpelde versie werd gevraagd heb ik ervoor gekozen andere identifiers dan het bsn weg te laten. Ook de meeste persoonlijke gegevens als religieuze overtuiging, burgerlijke staat en meerlingindicator heb ik niet gebruikt omdat deze in de meeste gevallen niet medisch relevant zijn,
Om de tijdsdruk te verlichten was het plan om voor zorgorganisaties en huisartsen een simpele gegenereerde identifier te gebruiken in plaats van de 5 of 6 verschillende identifiers die hiervoor bestaan, maar uiteindelijk ben ik aan dit hele onderdeel niet toegekomen.
Ook de activiteitsindicator is weggelaten, omdat deze kan wisselen afhankelijk van welke organisatie de informatie over de patient komt en het voor mijn systeem niet relevant is of de patient daar nu actief is.

Ik heb de structuur van de inkomende NL Core Patient van https://simplifier.net/nictizstu3-zib2017/nl-core-patient en heb om te testen of mijn versie naar behoren werkt https://build.fhir.org/ig/RIVO-Noord/zorgviewer-ig/Patient-eXxP1o8ZlOz21F6HXS9puLA3.json.html gebruikt.
Deze testdata heb ik indien nodig aangepast om te controleren of mijn validitychecks een aanvraag met missende informatie op de juiste manier blokkeert.

Ik kon helaas geen andere testdata van NL Core Patient vinden, dus ik ben me er bewust van dat bij data die niet specifiek hetzelfde model volgt als https://build.fhir.org/ig/RIVO-Noord/zorgviewer-ig/Patient-eXxP1o8ZlOz21F6HXS9puLA3.json.html het mogelijk is dat mijn systeem daar niet goed mee om gaat. 
Ik zou me graag meer verdiepen in hoe ik data uit verschillende modellen op kan halen, aangezien omgaan met variaties in FHIR de belangrijkste factor lijkt te zijn,
maar aangezien dit mijn eerste Kotlin-project is had ik al uitdaging genoeg aan een werkend systeem bouwen.
De meeste ervaring die ik heb is met Java, en bovendien ben ik in mijn eerdere projecten gewend zelf het model voor inkomende data te schrijven.
Hoewel ik heb geprobeerd om bepaalde aspecten anders uit te voeren, merkte ik dat ik als dat niet lukte vaak terug viel op wat ik van Java gewend was, terwijk Kotlin vast mooie mogelijkheden biedt om het anders aan te pakken.
Dus met meer tijd en ervaring had ik hier als eerste naar gekeken.

In dit project heb ik nu een PatientRequest-klasse met records die uit de inkomende data de informatie haalt die ik nodig heb om het in mijn model op te slaan. 
Ik heb naar https://simplifier.net/nictizstu3-zib2017/nl-core-patient gekeken hoe ik dat kan doen en naar mijn testdata indien het daar toch anders in stond dan verwacht.
Ik verwacht dat dit te hardcoded is om in een productie omgeving effectief met variaties om te gaan.