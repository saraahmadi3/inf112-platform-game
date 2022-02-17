Vi har delt inn dette dokumentet i fem deler, på samme måte som selve oppgaven
er delt inn i fem deloppgaver. Det er likevel en del overlapp i oppgaveteksten
mellom ulike oppgavedeler, og vi har unngått å svare to ulike steder på de samme tingene.



Dette har vi ikke gjort ennå:
Project board.
Deling og oppbevaring av felles dokumenter, diagram og kodebase

Deloppgave 1:
Gruppenavn: antsy-antonym

Kartlegging av kompetanse til medlemmene:
Kristian: Går andre året på Datateknologi. På ungdomsskole hadde 
han et valgfag som het "Programmering". På vgs hadde han IT1 og IT2, 
"programmering og modellering x".
HTML, Database, CSS, JavaScript. Har erfaring med Python, Java og Haskell.

Sara:
Har gått Data Science (2. året). Har jobbet med Python tidligere. 
Brukte Python på vgs i forbindelse med matematikk(modellering).

Adrian:
Går Data Science 3. året. Har erfaring med Python, Java og Haskell. 
Har prøvd seg på å lage websider for en kunde (database-basert).

Dani: Går andre året på Datateknologi. Har jobbet som lektor i 7 år før han 
begynte på Datateknologi. Ingen erfaring med programmering før han begynte på 
Datateknologi.

Rollefordeling:
Aministrativ leder: Dani. Oppgaver: Koordinere møter med de øvrige medlemmene. Sekretær.
Testleder: Adrian. Oppgaver: Overordnet ansvar for testing.
Kodesjef: Kristian: Overordnet oppsyn med dokumentering av kode.
Git-sjef: Sara: Ansvar for Git, arbeidsfordeling.

Deloppgave 2:
Organisering:
Her vil vi gjøre en løpende vurdering underveis i prosjektet, men vi vil forsøke å ha en metodikk 
med utgangspunkt i Kanban-metoden. Vi møtes ukentlig og oppdaterer hverandre på fullførte oppgaver,
løpende oppgaver og hvordan arbeidsoppgavene skal fordeles. Vi har ikke bestemt oss for noe plott til spillet 
ennå, men vi ser for oss plattformer som tar opp et skjermbilde. Karakteren vår skal kunne bevege seg frem og tilbake,
hoppe - og til og med dobbelthoppe! - i tillegg til at tyngdekraften gjør at karakteren faller ned. Vi ser 
for oss et spillbrett hvor karakteren skal bevege seg oppover mot et mål på toppen av skjermen. På denne måten
kan vi utvikle hvert nivå som et eget spillbrett. Fordelen med dette er at selve spillbrettet da blir statisk, 
og at ulike gruppemedlemmer kan lage spillbrett uavhengig av hverandre.

Vi vil benytte oss av testdrevet utvikling, og koble dette sammen med parprogrammering. Dette håper
vi skal passe oss veldig bra, da vi består av fire personer. Hvorvidt vi har faste par, eller om vi 
bytter underveis, vil vi ta stilling til når vi begynner med arbeidet. 

Vi møtes fast hver tirsdag fra 12:15-14:00, men gruppen har også mulighet til å møtes allerede klokken 10 denne
dagen. Gruppen har også mulighet til å treffes om torsdagen fra 12-14. Dersom vi skal treffes utenfor disse tidpsunktene,
i forbindelse med innleveringer eller lignende, møtes vi i subgrupper, da timeplanene våre kolliderer. Vi tror det er viktig
å treffes fysisk, da dette både er motiverende for arbeidet samtidig som det virker forpliktende på de ulike gruppemedlemmene.
Ikke minst forenkler det muligheten til å spørre om hjelp fra hverandre. Kommunikasjonen mellom disse møtene skjer på Discord.

I forbindelse med innlevering av første deloppgave, har naturligvis det meste av planleggingen handlet om de punktene som utgjør 
kravene til denne obligen. Vi har likevel gjort oss mange tanker om den videre utviklingen, både med tanke på selve spillproduktet
og spillutviklingen. Eksempelvis ser vi at vi blir nødt til å fordype oss noe i git-hubs og lib-gdx' muligheter. 

Deloppgave 3:
Det overordnede målet er i første omgang å lage et minimum viable product med de tre første punktene oppgitt i deloppgave 4. 
Dvs at vi har et spillbrett, en spiller som vises på spillbrettet, i tillegg til muligheten til å bevege denne spilleren.
den kan bevege seg på.

Brukerhistorier i prioritert rekkefølge for innlevering av obligatorisk oppgave 1:
1) Som spiller forventer jeg å se en verden/spillbrett som utgjør selve spillet.
Akseptansekriterier: Vi skal ha en klar definert nedre del av spillbrettet, og minst en plattform en annen plass på spillbrettet.

2) Som spiller forventer jeg å se en spiller på spillbrettet, som jeg kan manipulere.
Akseptansekriterier: Det skal plasseres en spiller på brettet i det spillet starter.

3.) Som spiller forventer jeg å kunne flytte figuren fra side til side, og opp og ned. 
Akseptansekriterier: Figuren kan flyttes fra side til side (såfremt det ikke er objekter i veien) og hoppe. Figuren blir påvirket
av tyngdekraften, og beveger seg nedover etter et hopp, og hvis spilleren beveger seg utenfor en kant.

4) Som spiller forventer jeg at spillkarakteren ikke kan gå gjennom fysiske objekter. 
Akseptansekriterier: Dersom en karakter hopper opp og treffer en plattform, skal hen stoppes av plattformen og falle ned igjen. 

5) Som spiller forventer jeg at spillet aldri kræsjer.
Akseptansekriterier: Spillet skal ikke kræsje.

Øvrige brukerhistorier/Backlog:
Jeg forventer at bakgrunn og forgrunn i spillet er lett å skille fra hverandre.
Akseptansekriterier: Fargene i forgrunn og bakgrunn må lett kunne skilles fra hverandre. Fargekombinasjoner som grønn og rød bør unngås 
som kontraster på forgrunn og bakgrunn (fargeblindhet). Teksturer hjelper også med å skille ulike elementer på skjermen.

Som spiller forventer jeg at spillet er intuitivt å spille.
Akseptansekriterier: Det skal være lett å skjønne hvordan spilleren kan bevege seg på skjermen.


Deloppgave 5 
Oppsummering
Gruppen har en fin dynamikk, god Kommunikasjonen og en god størrelse. Vi har klart å fordele oppgaver, slik at ingen blir sittende arbeidsløs.
Takket være Kristians tidligere erfaring med å lage spill, har vi også kommet godt i gang med selve spillutviklingen (selv om det inntil viderebare innfrir de tre første kravene til mvp). Vi fulgte ikke den test-drevne utviklingen i begynnelsen, men gjorde heller ting i motsatt rekkefølge; vi begynte med utvikling, og begynte deretter med testing. Vi oppdaget også at det blir vanskelig å teste bevegelsene til spillkarakteren. Spillkarakteren beveger seg med utgangspunkt i x- og y-koordinater, men det er vanskelig å fastslå hvor langt et tastetrykk skal flytte karakteren. Det er derfor vanskelig å lage en test, som tester om karakteren flytter seg nøyaktig fra a til b. 

















