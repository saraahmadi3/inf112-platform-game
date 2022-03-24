# Oblig 2 

* Team: *antsy-antonym* (Gruppe 8): *Sara Ahmadi, Kristian Nore, Dani Marås og Adrian Clopan.*



## Innhold
**[Deloppgave 1](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave2.md#deloppgave-1)<br>**


### Deloppgave 1: Team og prosjekt

Referatene våre er samlet i egen mappe, og kan leses her: **[Referatene](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/Referatene)<br>**

### Brukerhistorier i prioritert rekkefølge for innlevering av obligatorisk oppgave 2:
1) Jeg forventer at jeg skal kunne spille mot en annen spiller over et lokalt nettverk, uten at forsinkelsen blir for stor.
Akseptansekriterier: En spiller på en maskin skal kunne kobles til en annen maskin, og disse skal kunne spille mot hverandre.

2) Jeg forventer at brukergrensesnittet er intuitivt.
Akseptansekriterier: Det skal være en knapp for en-spiller/flerspiller-funksjon med i GUI.

3) Jeg forventer at spillet har musikk, for å skape en helthetlig musikkopplevelse.
Akseptansekriterier: Det spilles musikk når spillet starter.

### Bugs 
Det eksisterer en del bugs i spillet. En av bugsene som finnes er at dersom spilleren beveger seg for fort, kan det hende at collision-detection ikke fungerer. Dette problemet er ikke utbredt, men det kan forekomme.

De fleste bugsene er knyttet til synkronisering i forbindelse med multiplayer-funksjonen over nettverk. Spilleren som spiller på pc 1 vil oppleve at sin spiller er synkron med terrenget, men at motspilleren kan være usynkron med terrenget. Dette skjer typisk i forbindelse med de bevegelige plattformene. Når terrenget blir usynkront på de to ulike maskinene, kan det for spiller 1 fortone seg som at spiller 2 og den bevegelige plattformen hen står på er ute av synk. En vil likevel oppleve at sin egen spiller er synkron med det lokale terrenget.

### Oppsummering

##### Roller
Rollene i teamet fungerer veldig bra. Vi har ikke sett behov for å definere nye roller, selv om vi ser et tydelig behov for å hjelpe hverandre på tvers av de definerte rollene. Eksempelvis må antakeligvis flere av oss hjelpe til med å skrive tester i forbindelse med nettverksimplementeringen vår. Dette temaet er nytt for oss alle, og vi har hatt mer enn nok med å få det til å fungere. Vi har derfor gått bort fra den testdrevne utviklingen i forbindelse med dette. 

Dersom vi likevel skal bestemme oss for å legge til en ny rolle, kunne dette kanskje vært en UX-ansvarlig. Denne rollen er det kanskje naturlig å gi til Sara eller Dani, eventuelt å splitte opp UX-ansvaret i for eksempel lyd og grafikk.

##### Gruppedynamikk
Gruppedynamikken er god. Vi kommuniserer godt med hverandre, og folk er flinke til å følge opp oppgavene vi blir tildelt. Siden sist har vi også forsøkt oss på å dele oss opp i to grupper for å jobbe med enkelte oppgaver. Dette har falt seg veldig naturlig, da vi har ulike timeplaner. Dette var både fordi vi var uenige om hvilken løsning som ville fungere best, men også fordi at det virket fornuftig å forsøke å løse oppgaven på ulike måter. Ingen av oss hadde erfaring med slike nettverksløsninger, og det var tydelig at dette kunne gjøres på ulike måter. Istedenfor at alle jobbet med samme løsning, virket det derfor mer naturlig at vi forsøkte ulike ting.	

##### Status quo
Til nå har vi klart å lage et plattformbasert spill som fungerer godt dersom multiplayer er begrenset til å kjøre på en maskin. Nettverksløsningen vår per dags dato fungerer også dersom det er begrenset til en maskin. Med andre ord finner maskinen localhost. Dersom vi forsøker å kjøre spillet på to ulike maskiner, fungerer ikke nettverksløsningen ennå.

Vi har så smått begynt å legge til musikk til spillet. Inntil videre har vi bare lagt til en lydfil funnet på freesound.org, for å se om det fungerer. Denne vil byttes ut senere med noe som passer spillet bedre. På sikt vil vi også legge til lydfiler som akkompagnerer spillet som f. eks. hopp, boost, "fiende blir tatt", "spiller blir tatt av fiende" etc.

##### U-committet kode
I forbindelse med nettverksimplementeringen vår, har vi skrevet veldig mye kode som ikke har blitt committet. Dette er av den enkle grunn at vi har forsøkt på ting som ikke har fungert. I disse tilfellene har vi slettet kode lokalt uten å committe det.

##### Prosjektmetodikk
Siden forrige gang har vi endret noe på prosjektmetodikken. Vi benytter oss fremdeles av Kanban-metoden, men har gått vekk fra bruken av Trello som verktøy. Istedenfor har vi benyttet oss av "Issues", som er en egen feature på github. Dette virket som en mer praktisk løsning, siden vi allerede bruker github. Det må likevel innvendes at vi ikke har vært flinke nok til faktisk å bruke det, og at arbeidsfordelingen har fortsatt å skje på en muntlig måte.
