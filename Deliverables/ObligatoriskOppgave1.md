# Obligatorisk oppgave 1

Vi har delt inn dette dokumentet i fem deler, på samme måte som selve oppgaven
er delt inn i fem deloppgaver. Det er likevel en del overlapp i oppgaveteksten
mellom ulike oppgavedeler, og vi har forsøkt å unngå å svare to ulike steder på de samme tingene.

## Innhold:
##### Deloppgave 1-5
**[Deloppgave 1: Organisering av teamet](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave1.md#deloppgave-1)<br>
[Deloppgave 2: Prosessen](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave1.md#deloppgave-2)<br>
[Deloppgave 3: Oversikt over forventet produktet/Brukerhistorier](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave1.md#deloppgave-3)<br>
[Deloppgave 4: Kode](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave1.md#deloppgave-4)<br>
[Deloppgave 5: Oppsummering](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave1.md#deloppgave-5)**



## Deloppgave 1:
**Gruppenavn: antsy-antonym**

### Kartlegging av kompetanse til medlemmene:

#### Kristian: 
Går andre året på Datateknologi. På ungdomsskole hadde 
han et valgfag som het "Programmering". På vgs hadde han IT1, IT2 og 
"programmering og modellering x". <br>
Har erfaring med Python, Java og Haskell, HTML, Databaser, CSS, JavaScript.
Har laget lignende spillkonsepter i JavaScript.

#### Sara:
Går Data Science (2. året). Har jobbet med Python tidligere. 
Brukte Python på vgs i forbindelse med matematikk(modellering).<br>
Har erfaring med Java fra tidligere semestere av Data Science.

#### Adrian:
Går Data Science 3. året. Har prøvd seg på å lage websider for en kunde (database-basert).<br>
Har erfaring med Python, Java og Haskell. 

#### Dani: 
Går andre året på Datateknologi. Har jobbet som lektor i 7 år før han 
begynte på Datateknologi. Ingen erfaring med programmering før han begynte på 
Datateknologi. <br>Har erfaring med Java, Python og Haskell fra tidligere semestere av Datateknologi.

### Rollefordeling:
**Aministrativ leder: Dani**. Oppgaver: Koordinere møter med de øvrige medlemmene. Sekretær.<br>
Hvorfor? Dani har jobbet lenge i norsk skole, og har erfaring med å koordinere både kolleger og elever i forbindelse med prosjekter og lignende.  

**Testleder: Adrian**. Oppgaver: Overordnet ansvar for testing.<br>
Hvorfor? Adrian er den av oss som har kommet lengst i studieløpet, og har mest erfaring med tester.  

**Kodesjef: Kristian**: Overordnet oppsyn av struktur og dokumentering av kode.<br>
Hvorfor? Kristian har erfaring med spillutvikling tidligere.    

**Git-sjef: Sara**: Ansvar for Git, arbeidsfordeling.<br>
Hvorfor? Sara har en allsidig kompetanse, og er kjapp med å ta i bruk nye verktøyer. Vi kommer til å støte på problemer med git, og da trenger vi at en kjenner dette systemet godt.  

## Deloppgave 2:
### Organisering:
Her vil vi gjøre en løpende vurdering underveis i prosjektet, men vi vil forsøke å ha en metodikk 
med utgangspunkt i Kanban-metoden. Vi møtes ukentlig og oppdaterer hverandre på fullførte oppgaver,
løpende oppgaver og hvordan arbeidsoppgavene skal fordeles. Vi vil benytte Trello-verktøyet som en visuell hjelp til Kanban-metoden.

![Trello](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/raw/master/2022-02-20_20_57_35-Oblig___Trello.jpg)

[Trello Link](https://trello.com/b/JBFh9DbA/oblig)

Vi vil benytte oss av testdrevet utvikling, og koble dette sammen med parprogrammering. Dette håper
vi skal passe oss veldig bra, da vi består av fire personer. Hvorvidt vi har faste par, eller om vi 
bytter underveis, vil vi ta stilling til når vi begynner med arbeidet. 

Vi møtes fast hver tirsdag fra 12:15-14:00, men gruppen har også mulighet til å møtes allerede klokken 10 denne
dagen. Gruppen har også mulighet til å treffes om torsdagen fra 12-14. Dersom vi skal treffes utenfor disse tidpsunktene,
i forbindelse med innleveringer eller lignende, møtes vi i subgrupper, da timeplanene våre kolliderer. Vi tror det er viktig
å treffes fysisk, da dette både er motiverende for arbeidet samtidig som det virker forpliktende på de ulike gruppemedlemmene.
Ikke minst forenkler det muligheten til å spørre om hjelp fra hverandre. Kommunikasjon mellom disse møtene skjer på Discord.

I forbindelse med innlevering av første deloppgave, har naturligvis det meste av planleggingen handlet om de punktene som utgjør 
kravene til denne obligen. Vi har likevel gjort oss mange tanker om den videre utviklingen, både med tanke på selve spillproduktet
og spillutviklingen. Eksempelvis ser vi at vi blir nødt til å fordype oss noe i mulighetene som ligger i git-hub og lib-gdx. 

## Deloppgave 3:
Det overordnede målet er i første omgang å lage et minimum viable product med de fire første punktene oppgitt i deloppgave 4. 
Dvs at vi har et spillbrett, en spiller som vises på spillbrettet, muligheten til å bevege denne spilleren, og muligheten til å interagere med terrenget. Vi har ikke bestemt selve plottet til spillet ennå, men vi ser for oss ulike plattformer i en verden hvor hvert level/nivå tar opp ett enkelt skjermbilde av gangen. 

### Brukerhistorier i prioritert rekkefølge for innlevering av obligatorisk oppgave 1:
1) Som spiller forventer jeg å se en verden/spillbrett som utgjør selve spillet, slik at jeg vet hvor jeg kan/bør bevege spilleren.<br>**Akseptansekriterier:** Vi skal ha en klar definert nedre del av spillbrettet, og minst èn plattform en annen plass på spillbrettet. Det skal også finnes en dør/portal eller lignende, som viser hvor karakteren skal ende for å komme til neste level/nivå.
<br>**Krav som dekkes:** krav 1 om å vise et spillebrett, og delvis også krav 8 om et mål for spillbrettet.
<br>**Arbeidsoppgaver:** I Main.js må vi lage et vindu. I GameLoop.js må vi tegne alle elementer for hver frame. Vi trenger en Platform.js klasse, og evetuelle andre klasser som implementerer et GameObjects.js interface og blir tegnet. Sara lager interfacet GameObjects.js, og eventuelle nødvendige klasser, Kristian hjelper med implementasjonsdetaljer og foreløpig grafikk for spillbrettet.  

2) Som spiller forventer jeg å se en karakter på spillbrettet, slik at jeg vet hvor på brettet spilleren befinner seg.
<br>**Akseptansekriterier:** Det skal plasseres en spiller på brettet i det spillet starter. Denne spilleren skal ha et ansikt med tydelige trekk.
<br>**Krav som dekkes:** Krav 2 om å vise en spiller.
<br>**Arbeidsoppgaver:** Vi må lage grafikk til spilleren. Vi må tegne spilleren hver frame i GameLoop.js. Kristian lager foreløpig grafikk i MS Paint, eventuelt kan Dani eller Adrian senere prøve å lage grafikk i Tiled.

3) Som spiller forventer jeg å kunne flytte figuren fra side til side, og opp og ned, slik at jeg kan bevege spilleren på brettet.
<br>**Akseptansekriterier:** Figuren kan flyttes fra side til side ved hjelp av piltaster og hoppe. Figuren blir påvirket av tyngdekraften, og beveger seg nedover etter et hopp, eller hvis spilleren beveger seg utenfor en kant. Forklaringer og tips til hvordan en beveger karakteren ruller over skjermen underveis i spillet.
<br>**Krav som dekkes:** Krav 3 om at spiller kan flyttes.
<br>**Arbeidsoppgaver:** For hver gang GameLoop::render() blir kalt må vi flytte spilleren. Dersom en tast er trykket inn må vi fange opp det og bevege spilleren i tilsvarende rettning. Spilleren må uavhengig av tastetrykk bli påvirket av tyngdekraften. Dani jobber med å fange opp tastetrykk, Kristian jobber med å logikken runt bevegelsen av spilleren.

4) Som spiller forventer jeg at spillkarakteren ikke kan gå gjennom fysiske objekter, slik at fysikken fungerer på en intuitiv måte.
<br>**Akseptansekriterier:** Dersom en karakter hopper opp og lander på en plattform, skal hen bli stående på plattformen. Dersom karakteren hopper opp og treffer en plattform nedenfra, skal hen ikke gå gjennom plattformen, men miste momentet og falle ned igjen. Karakteren skal stoppe dersom hen går inn i en vegg.
<br>**Krav som dekkes:** Krav 4 om at spiller interagerer med terrenget.
<br>**Aribeidsoppgaver:** Hver gang spilleren beveger seg må vi sjekke om den kolliderer med et fysisk objekt. Dersom det er kollisjon må vi håntere det på en måte som gjør at spilleren ikke beveger seg inn i eller gjennom objektet. Kristian og Sara jobber med kollisjonslogikken sammen.

5) Som spiller forventer jeg at spillet aldri kræsjer, slik at jeg aldri mister framgangen i spillet på grunn av en feil.<br>**Akseptansekriterier:** Spillet skal ikke kræsje. (Dette er egentlig et helt umulig kriterie å forholde seg til, siden et program alltid kan kræsje. Det er likevel essensielt med tanke på spilleropplevelsen, så vi vil ha det med som et punkt vi må forholde oss til hver gang vi legger til noe nytt i spillet)
<br>**Krav som dekkes:** Ikke et eksplisitt MVP krav, men noe vi mener er sentralt for en god spillopplevelese.
<br>**Arbeidsoppgaver:** Vi må hele tiden tenke på error handling, og hvordan vi kan unngå å komme i situvasjoner hvor Java kræsjer. Et eksempel er at vi gikk gjennom en liste over alle GameObjects i GameLoop, og dersom vi da trengte å legge til eller fjerne et objekt ville vi fått ConcurrentModificationException, dette løste vi med å i stedet legge til objekter i en venteliste, som legges til det faktiske listen etter iterasjonen er ferdig. Det er alle på gruppen sitt ansvar at koden de skriver tar høyde for ulike exceptions og enten ungår dem eller catcher dem.

### Øvrige brukerhistorier/Backlog:
Vi kommer tilbake til de følgende brukerhistoriene og arbeidsoppgavene når vi har kommet litt lengre og er i mål med alle brukerhistorer med høyere prioritet. 

Jeg forventer at bakgrunn og forgrunn i spillet er lett å skille fra hverandre, slik at jeg vet hvilke objekter spilleren kan interagere med.  
Akseptansekriterier: Fargene i forgrunn og bakgrunn må lett kunne skilles fra hverandre. Fargekombinasjoner som grønn og rød bør unngås 
som kontraster på forgrunn og bakgrunn (fargeblindhet). Teksturer hjelper også med å skille ulike elementer på skjermen.

Som spiller forventer jeg at spillet er intuitivt å spille, slik at jeg vet hvordan spillet fungerer.  
Akseptansekriterier: Det skal være lett å skjønne hvordan spilleren kan bevege seg på skjermen.

Som spiller ønsker jeg å kunne spille sammen med en venn, slik at vi kan ha det gøy sammen uten å måtte bytte på å spille.  
[]([url](url))Akseptansekriterier: Det skal være mulig for to spillere å spille samtidig.


## Deloppgave 4
Kode:

Se egen mappe for koden.


## Deloppgave 5 
Oppsummering:

Gruppen har en fin dynamikk, god kommunikasjon og en god størrelse. Vi har klart å fordele oppgaver, slik at ingen blir sittende arbeidsløse.
Takket være Kristians tidligere erfaring med å lage spill, har vi også kommet godt i gang med selve spillutviklingen (selv om det inntil videre bare innfrir de fire første kravene til mvp). Vi støtte på litt vanskeligheter med tanke på å skrive tester til programmet vårt. Libgdx var nytt for oss, og det ble vanskelig å skrive testene uten at noe av programmet var på plass ennå. Vi fulgte derfor ikke den test-drevne utviklingen i begynnelsen, men gjorde heller ting i motsatt rekkefølge; vi begynte med utvikling, og begynte deretter med testing. I fortsettelsen tror vi at testingen og utviklingen kommer til å være a jour med hverandre, og at det blir testdrevet utvikling i praksis så vel som på papiret. Vi har sålangt benyttet oss av visuell testing i form av at vi selv spiller spillet for å se om ting ser greit ut.

I fortsettelsen må vi fordype oss i lib-gdx/Tiling og dets muligheter med tanke på spillkarakter og spillbrett. Vår foreløpige tanke er et plattformspill hvor hvert enkelt nivå er et eget spillbrett, og vi trenger derfor flere spillbrett. Spillopplevelsen til det ferdige produktet vårt avhenger derfor av hvor flink vi blir til å utvikle spillbrett.

Vi må også bli flinkere til å utnytte mulighetene git gir oss. Inntil videre har vi fordelt oppgaver slik at vi ikke skriver i samme filer. Ulike medlemmer har derfor fått i oppgave å jobbe med enkeltklasser, slik at vi kan committe uten at vi har måttet bekymre oss for merging-konflikter. Dette har fungert til nå, men vi ser at det vil bli en utfordring når prosjektet blir mer komplisert.

Til tross for at vi har valgt å benytte oss av Kanban-metoden kom vi ikke skikkelig i gang med å benytte oss av  det visuelle aspektet med denne metoden før helt mot slutten. Vi kom i gang med Trello i løpet av uke 7, men i praksis har ting blitt avtalt muntlig, da vi har møttes ofte. Den muntlige dialogen vi har hatt har fungert fint til nå, men etterhvert som prosjektet blir mer omfattende tror vi det vil være nødvendig å nyttiggjøre oss av Trello-brettet mer aktivt. 



![Klassediagram](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/raw/67f497b2b0c8d12f5aa9e5a377cb41f8f4cf262b/IMG_1061.jpg)


Sara Ahmadi, Kristian Nore, Dani Marås og Adrian Clopan.
