# Obligatorisk oppgave 2



## Innhold:
##### Deloppgave 1-5
**[Deloppgave 1: Organisering av teamet](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave1.md#deloppgave-1)<br>
[Deloppgave 2: Prosessen](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave1.md#deloppgave-2)<br>
[Deloppgave 3: Oversikt over forventet produktet/Brukerhistorier](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave1.md#deloppgave-3)<br>
[Deloppgave 4: Kode](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave1.md#deloppgave-4)<br>
[Deloppgave 5: Oppsummering](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave1.md#deloppgave-5)**

### Brukerhistorier i prioritert rekkefølge for innlevering av obligatorisk oppgave 1:
1) Jeg forventer at jeg skal kunne spille mot en annen spiller over et lokalt nettverk, uten at forsinkelsen blir for stor.
Akseptansekriterier: En spiller på en maskin skal kunne kobles til en annen maskin, og disse skal kunne spille mot hverandre.

2) Jeg forventer at brukergrensesnittet består av knapper, og er lett å forstå.
Akseptansekriterier: Det skal være en knapp for en-spiller/flerspiller-funksjon med i GUI.

3) Jeg forventer at spillet har musikk, for å skape en helthetlig musikkopplevelse.
Akseptansekriterier: Det spilles musikk når spillet starter.


Vi har ikke vært oppmerksomme på at det skal skrives referat fra hver gruppetime, så vi har ikke skrevet referat fra tidligere gruppetimer enn 15.03. I forbindelse med oppmøtet har alle gruppemedlemmene vært tilstede ved hver gruppetime til nå.

#### Referat 22.03.2022
Tilstede: Sara, Kristian; Adrian og Dani.

Halvparten av gruppen har problemer med å kjøre programmet i Eclipse/Intellij, tilsynelatende etter å ha kjørt koden i Visual Studio Code hvor den har blitt kompilert med en nyere versjon av Java.

Siden sist har gruppen delt seg i to for å jobbe med implementeringen av nettverk i forbindelse med multiplayer-funksjonen. Vi har angrepet problemet på to ulike måter. Sara og Adrian har jobbet med å implementere en selvstendig server, som de ulike klientene kan koble seg opp mot. Kristian og Dani har jobbet med løsningen hvor den ene klienten blir en server, som den andre spilleren knytter seg opp mot. Ingen av løsningene fungerer skikkelig ennå.


#### Referat 18.03.2022
Tilstede Adrian og Sara

Vi prøvde å jobbe på en bedre løsning for server client i branchen serverSplit, med målet å holde all bakgrunnslogikken i serveren for så å oppdatere dette i client. Har lagd noen planner for hva som må forandres med resten av programmet. Desverre gikk mesteparten av tiden git problemer.

#### Referat 17.03.2022
Tilstede Kristian og Dani

Vi hadde en ekstraøkt for å forsøke å komme videre med multiplayer over nettverk. Slik multiplayer fungerer nå, må klienten manuelt skrive inn ip-adressen til serveren. Dermed er "klient-spilleren" avhengig av å sitte ved siden av "server-spilleren". Dette ønsket vi å forbedre. Vi forsøkte først en løsning hvor vi ved hjelp av DatagramPacket og DatagramSocket-bibliotekene, broadcaster en pakke fra fra klient til server. Server sjekker at dette er fra klienten, og sender tilbake til serveren. Klienten får pakken fra serveren, og har med det også adressen. Dette viste seg ikke å fungere, da vi hadde misforstått hvordan send-metoden i DatagramSocket fungerte. Den ble ikke broadcastet slik vi så for oss.

Vi forsøkte da å benytte oss av Klient-klassen - arvet av kryonet - til å gjøre noe lignende. client.discoverHost skal gi oss adressen til serveren.

#### Referat 15.03.2022
Tilstede: Sara, Kristian; Adrian og Dani.

Vi jobber fremdeles med MultiPlayer-implementasjonen. 
Vi har fortsatt jobbingen med multiplayer-løsningen vår. Vi er litt usikre på om den løsningen vi allerede har, er innenfor kriteriene for hva som er godkjent. Løsningen vi har er multiplayer på èn maskin. Vi har likevel implementert klassene som skal tillate oss å spille over nettverk, og ønsker å få til en multiplayer over LAN eller online.

Cons med løsningen vi har nå:
Vi sender kun koordinatene til de ulike spillerne.
Synkroniseringen er ikke nøyaktig nok, og kan føre til ulike gameStates hos de to ulike spillerne. Eksempelvis kan den ene spilleren dø uten at den andre spilleren blir oppdatert på dette.

Vi brainstormet deretter rundt ulike løsninger på dette:
Mulig løsning: Vi kan sende gameState over serveren. Dette har blitt forsøkt, og endte med overflow.
Mulig løsning2: Vi sender ikke all infoen, men viktig info på ulike intervaller. Eksempelvis må spillerne sjekke for om den andre er død.
Mulig løsning3: Spillerne må vente på bekreftelse på at informasjonen. 

#### Oppsummering

##### Roller
Rollene i teamet fungerer veldig bra. Vi har ikke sett behov for å definere nye roller, selv om vi ser et tydelig behov for å hjelpe hverandre på tvers av de definerte rollene. Eksempelvis må antakeligvis flere av oss hjelpe til med å skrive tester i forbindelse med nettverksimplementeringen vår. Dette temaet er nytt for oss alle, og vi har hatt mer enn nok med å få det til å fungere. Vi har derfor gått bort fra den testdrevne utviklingen i forbindelse med dette. 

Dersom vi likevel skal bestemme oss for å legge til en ny rolle, kunne dette kanskje vært en UX-ansvarlig. Denne rollen er det kanskje naturlig å gi til Sara eller Dani, eventuelt å splitte opp UX-ansvaret i for eksempel lyd og grafikk.

##### Gruppedynamikk
Gruppedynamikken er god. Vi kommuniserer godt med hverandre, og folk er flinke til å følge opp oppgavene vi blir tildelt. Siden sist har vi også forsøkt oss på å dele oss opp i to grupper for å jobbe med enkelte oppgaver. Dette har falt seg veldig naturlig, da vi har ulike timeplaner. Dette var både fordi vi var uenige om hvilken løsning som ville fungere best, men også fordi at det virket fornuftig å forsøke å løse oppgaven på ulike måter. Ingen av oss hadde erfaring med slike nettverksløsninger, og det var tydelig at dette kunne gjøres på ulike måter. Istedenfor at alle jobbet med samme løsning, virket det derfor mer naturlig at vi forsøkte ulike ting.	

##### Status quo
Til nå har vi klart å lage et plattformbasert spill som fungerer godt dersom multiplayer er begrenset til å kjøre på en maskin. Nettverksløsningen vår per dags dato fungerer også dersom det er begrenset til en maskin. Med andre ord finner maskinen localhost. Dersom vi forsøker å kjøre spillet på to ulike maskiner, fungerer ikke nettverksløsningen ennå.

Vi har så smått begynt å legge til musikk til spillet. Inntil videre har vi bare lagt til en lydfil funnet på freesound.org, for å se om det fungerer. Denne vil byttes ut senere med noe som passer spillet bedre. På sikt vil vi også legge til lydfiler som akkompagnerer spillet som f. eks. hopp, boost, "fiende blir tatt", "spiller blir tatt av fiende" etc.
