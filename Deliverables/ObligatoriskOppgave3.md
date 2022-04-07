# Oblig 3

* Team: *antsy-antonym* (Gruppe 8): *Sara Ahmadi, Kristian Nore, Dani Marås og Adrian Clopan.*



## Innhold
**[Deloppgave 1](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave3.md#deloppgave-1)<br>
[Deloppgave 2](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave3.md#deloppgave-2)<br>
[Deloppgave 3](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave3.md#deloppgave-3)<br>
[Fikset siden sist](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave3.md#fikset-siden-sist)**


### Deloppgave 1

**[Referatene](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/Referat)** våre er samlet i egen mappe, for å holde dette dokumentet mest mulig oversiktlig.

##### Roller
Forrige gang nevnte vi at det muligens kunne vært behov for en UX-ansvarlig i forbindelse med lydeffekter og lignende. Dette ansvaret har vi ikke definert eksplisitt, men alle har bidratt med noe i forbindelse med dette. Den virkelig store utfordringen har vært knyttet til å få nettverksimplementeringen til å fungere på Linux. Arbeidsoppgavene vi har hatt siden sist har derfor på ulike vis vært knyttet til dette. Istedenfor å opprette en UX-ansvarlig som nevnt sist, ville det derfor kanskje hatt mer for seg å oppnevne en av oss som ansvarlig for skjerm/meny og en som ansvarlig for nettverk. I praksis har vi likevel sett hva som må ses på, og definert arbeidsoppgaver underveis.



##### Prosjektmetodikk
Forrige gang nevnte vi at vi måtte bli flinkere til å skrive issues, og å oppdatere oversikten over disse når de ble ferdigstilt. Dette opplever vi selv at vi har blitt flinkere til. En forbedring kunne kanskje vært å dele opp issuesene i mindre issues, slik at det er lettere å se at arbeidet går fremover. På samme måte som hovedmål og delmål, kunne vi operert med hoved-issue og del-issues.

##### Gruppedynamikk og kommunikasjon
Gruppedynamikken er god, og vi kommuniserer godt med hverandre. Forrige gang delte vi oss i to, da det var naturlig i forbindelse med oppgavene vi hadde. Til denne sprinten har vi i all hovedsak jobbet med å fikse ting knyttet til spillet vårt, og vi har derfor samarbeidet om til dels de samme oppgavene. Vi har derfor i stor grad opptrådt som rubber ducks for hverandre, når vi har forsøkt å komme til bunns i hvorfor enkelte ting ikke har fungert på Linux.

### Deloppgave 2
##### Brukerhistorier
I forbindelse med denne innleveringen har vi prioritert to brukerhistorier:

1) Jeg forventer at jeg skal kunne spille mot en annen spiller over et lokalt nettverk, selv om plattformen er Linux.  
Akseptansekriterier: En spiller på en maskin med Linux som operativsystem skal kunne kobles til en annen maskin, og disse skal kunne spille mot hverandre.

2) Jeg forventer at spillet har egenkomponert musikk og passende lydeffekter, for å skape en helthetlig musikkopplevelse.  
Akseptansekriterier: Det spilles av egenkomponert musikk som snakker til sjelen når spillet startes. Sentrale hendelser i spillet (fiende dør, hopping, ferdig med level etc) skal også ha egne, unike lydeffekter.

##### "Stretchgoal" og veien videre
Multiplayer-funksjonen vår fungerer dersom brukerne bruker Windows, og spillerne skriver inn IP-adressen til serveren manuelt. Vi har ikke fått testet om funksjonen vår for å knytte til serveren automatisk, fungerer på et hjemmenettverk. Vi har en teori om at eduroam-nettverket er det som hindrer løsningen fra å fungere på Høyteknologisenteret.

Det vi har jobbet mest med i forbindelse med nettverksløsningen vår denne sprinten, er likevel å få den til å fungere på Linux. Multiplayer på egen skjerm fungerer også på Linux, men forsøk på å knyttes opp mot en server fører til kræsj. Vi har likevel en teori om at dette kan skyldes et problem knyttet til hvordan den siste menyskjermen (som spør brukeren om hvilket nettverksvalg hen ønsker) blir disposet. Vi har begynt å implementere en løsning som vi håper skal fikse dette, men vi er ikke sikre på om denne blir ferdig tidsnok til tidsfristen for denne sprinten.

I forbindelse med brukerhistorie 2, har vi laget en temasang samt noen lydeffekter. Vi har implementert en del lydeffekter, som alle får samme lyd. Vi skal lage mer passende lyder til siste innlevering. Siden grafikken til spillet er laget av oss, synes vi det er passende at vi også lager musikken.  

Til neste sprint ønsker vi derfor å få til en nettverksløsning, som også fungerer på Linux. Vi ønsker også åteste om løsningen vår for å oppgave en server automatisk, fungerer på et av hjemme-nettverkene våre. Vi ønsker også å lage unike lydeffekter til de ulike hendelsene i spillet. Til syvende og sist har vi på tampen av denne sprinten kommet på en idè som kanskje kan løse de siste Synkroniseringsutfordringene våre. Dette knytter seg til en klokke som skal sørge for at spillerne begynner nøyaktig likt ved bruk av nettverksløsningen vår. Dette vil bli en brukerhistorie til den siste innleveringen vår.

##### Bugs
Det eksisterer noen bugs i spillet.

Bugsene knyttet til spillet er i stor grad de samme som ved forrige innlevering. De fleste bugsene er nemlig knyttet til synkronisering i forbindelse med multiplayer-funksjonen over nettverk. Spilleren som spiller på pc 1 vil oppleve at sin spiller er synkron med terrenget, men at motspilleren kan være usynkron med terrenget i noen tilfeller. Dette blir ekstra tydlig i forbindelse med de bevegelige plattformene. Når terrenget blir usynkront på de to ulike maskinene, kan det for spiller 1 fortone seg som at spiller 2 beveger seg i løse luften uten at det ser ut som den står på en plattfrom. En vil likevel oppleve at sin egen spiller er synkron med det lokale terrenget.  

Graden av synkroniseringsfeil har likevel endret seg. Vi oppdaget at vi sendte unødvendig mye data over nettverket, og klarte derfor å moderere mengden data som sendes. Dette ga synlige utslag på synkroniseringsfeil. Selv om dette problemet har bedret seg, oppstår det likevel synkroniseringsfeil.

Synkroniseringen får også problemer dersom den ene spilleren dør i multiplayer-funksjonen, eller hvis den ene spilleren fullfører levelet.

Spillet vårt fungerer ikke på Mac ennå.

### Deloppgave 3
Vi har ennå ikke fått tilbakemelding på den andre innleveringen vår, så vi har ikke mulighet til å kommentere ting vi har fått trekk på der.

[Klassediagrammet](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/Images/KlassediagramOblig3.png) er lastet opp som en png-fil, for å lettere kunne zoome inn på det. Merk at du må åpne det i en ny fane for å kunne zoome i det.

### Fikset siden sist 
- Referater er skrevet, og lagt til i en egen mappe i Deliverables.
- Vi har komponert musikk til spillet, og lagt til lydeffekter i spillet.
- Vi har redusert mengden data som sendes over nettverket, og på denne måten forbedret synkroniseringen ved bruk av nettverksløsning.
- Vi har utvidet GUIen vår, slik at den lar brukeren bestemme nettverksløsning (merk at vi er i gang med å forbedre denne, da vi tror at det er denne som gjør at det ikke lar seg kjøre på Linux).
- Vi har lagt til mynter (Coin) i spillet.
- Vi har fikset scoreboardet på slutten av spillet, slik at scoren til spillerne er synkronisert.



