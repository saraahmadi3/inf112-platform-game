# Oblig 4

* Team: *antsy-antonym* (Gruppe 8): *Sara Ahmadi, Kristian Nore, Dani Marås og Adrian Clopan.*



## Innhold
[Deloppgave 1](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave4.md#deloppgave-1)<br>
[Deloppgave 2](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave4.md#deloppgave-2)<br>
[Deloppgave 3](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/ObligatoriskOppgave4.md#deloppgave-3)<br>


### Deloppgave 1

**[Referatene](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/Deliverables/Referat)** våre er samlet i egen mappe, for å holde dette dokumentet mest mulig oversiktlig.

##### Roller
Vi har ikke diskutert nye roller i forbindelse med den siste innleveringen. Vi har i det hele tatt ikke lagt særlig vekk på disse rollene i løpet av prosjektet. 

##### Prosjektmetodikk
Det er fremdeles issues som er hovedutfordringen vår. Vi har blitt flinkere til å bruke det enn hva vi var i begynnelsen, men det er på ingen måte noen refleks for oss å lage issues knyttet til arbeidsoppgavene våre. Følgelig er det mange arbeidsoppgaver som har blitt gjort uten at de noensinne har vært et issue. Vår manglende evne til å skrive issues til alle nye arbeidsoppgaver har likevel ikke blitt opplevd som et stort problem for oss under dette gruppearbeidet, men ved et større prosjekt eller en mindre fungerende gruppe, ser vi at dette fort kunne blitt en utfordring. Til tross for at det ikke har blitt opplevd som et stort problem, har vi likevel opplevd at ulike gruppemedlemmer har gått løs på samme arbeidsoppgave, fordi vi ikke har vært fullt oppdatert på hva de andre jobber med (Kristian og Dani lagde for eksempel to ulike versjoner av level.GameOver). 

Vi har også innsett at vi i løpet av de siste månedene har levd under den tro at vi har jobbet etter Kanban-metodikken, men at vi kanskje egentlig har vært nærmere Scrum-metodikken. Kanban er en veldig minimalistisk måte å jobbe på, og bygger på to kriterier: Visualiser arbeidsflyten og begrens WIP (Work in progress). I forbindelse med det første punktet, burde vi visualisert arbeidsflyten bedre med en faktisk tavle eller lignende. GitHub er et fantastisk verktøy, men Issues-verktøyet deres kan ikke sies å være et godt visualiseringsverktøy.

I forbindelse med WIP har vi aldri definert en grense for hvor mange arbeidsoppgaver/issues som skal kunne være under "ToDo"-delen av "issues"-oversikten vår. På en måte kan en si at vi har lagt alle issues til "ToDo"-delen, fortløpende som vi har kommet på dem. I faktisk utførelse har vi likevel jobbet systematisk med enkelte arbeidsoppgaver, men det er nok fordi vi har forholdt oss mer til de prioriterte brukerhistoriene våre, og ikke arbeidstavlen vår på github. I retrospekt burde vi kanskje hatt et faktisk brett hvor vi skrev opp/hengte opp prioriterte arbeidsoppgaver, slik at det ville blitt tydeligere når tavlen var i ferd med å bli full.

Scrum-metodikken skiller seg fra Kanban ved at WIP er begrenset av tidsenheter, mens Kanban er begrenset av statusen til arbeidsflyten (workflow state). Siden vi har brukt  brukerhistoriene som nav til hva vi skal jobbe med, har vi indirekte vært begrenset av tidsenheter, siden dette har vært knyttet til innleveringene våre. I så måte kan en kanskje argumentere for at vi har jobbet etter en metodikk som er like mye Scrum-inspirert som Kanban-inspirert.



##### Gruppedynamikk og kommunikasjon
Gruppedynamikken er fortsatt god, og vi kommuniserer godt med hverandre. I forbindelse med dette punktet har vi kanskje også en fordel ved at de fleste av gruppemedlemmene jevnlig sitter på lesesalen. Dermed møtes vi ofte, slik at vi kan snakke om prosjektet, og be om hjelp om nødvendig. Forutenom den muntlige kommunikasjonen, har vi også en god kommunikasjon på Discord, hvor alle medlemmene er raske med å svare hverandre. 

I løpet av prosjektperioden har det ved enkelte tilfeller oppstått uenighet om videre veivalg og lignende. I disse tilfellene har vi gjerne laget en branch ut fra master, hvor man jobber med en alternativ måte å gjøre ting på, mens de øvrige medlemmene av gruppen jobber på master-branchen.

### Deloppgave 2
##### Brukerhistorier
I forbindelse med den siste innleveringen har vi prioritert følgende to brukerhistorier:

Som spiller skal jeg kunne velge å spille på nytt igjen dersom jeg blir ute, slik at jeg slipper å gå gjennom hele menyen på nytt. 
Akseptansekriterier: Dersom jeg blir ute av spillet skal game over-skjermen ha et alternativ, som lar deg begynne spillet på nytt igjen.<br>
Kommentar: Dette er nå mulig ved å trykke Space.

Som spiller skal jeg kunne navigere meg frem og tilbake på startmenyen, slik at det blir lett å spille selv om man gjør feil underveis i menyvalgene.
Akseptansekriterier: Dersom jeg trykker på noe på menyen, tar menyen meg til en ny meny, eller starter spillet. Ved tilfeller hvor du tas til en ny meny, skal det være mulig å trykke seg tilbake til forrige meny.<br> 
Kommentar: Dette er nå fikset, ved at det finnes "tilbake-piler" på dypere deler av menyen.

##### "Stretchgoal"
Stretchgoalet vårt i forbindelse med denne innleveringen har vært knyttet til menyen, og brukergrensesnittet til denne. Det er nå mulig å gå frem og tilbake i menyen dersom en angrer på et valg, det er mulig å starte spillet på nytt ved et tastetrykk dersom du blir ute og du har mulighet til å sette volumet til spillet før du begynner å spille.

Bortsett fra dette har arbeidsoppgavene vært å flikke på ting som allerede er implementert. Se "Fikset siden sist" for detaljer rundt dette.

##### Bugs
Det eksisterer noen bugs i spillet.

Bugsene knyttet til spillet er de samme som ved forrige innlevering. De fleste bugsene er nemlig knyttet til synkronisering i forbindelse med multiplayer-funksjonen over nettverk. Spilleren som spiller på pc 1 vil oppleve at sin spiller er synkron med terrenget, men at motspilleren kan være usynkron med terrenget i noen tilfeller. Dette blir ekstra tydelig i forbindelse med de bevegelige plattformene. Når terrenget blir usynkront på de to ulike maskinene, kan det for spiller 1 fortone seg som at spiller 2 beveger seg i løse luften uten at det ser ut som den står på en plattform. Sin egen spiller vil en likevel oppleve som synkron med det lokale terrenget.  

Spillet vårt fungerer ikke på Mac ennå.

Dersom spilleren bruker cheat-funksjonen er det også mulig å bevege seg så fort at du innimellom beveger deg gjennom plattformer.

### Deloppgave 3

##### Fikset siden sist 
- GameOver-funksjonen har blitt endret fra å være en funksjon som returnerer et bilde med poengsummen til spillerne, til å være et eget level, som tillater oss å bruke objekter fra spillet til dekor. 
- Et siste level har blitt lagt til.
- Mappestrukturen har blitt ryddet opp i.
- Alle effekter som har blitt gitt lydeffekter, har fått en unik lydeffekt.
- Vi har lagt til en glider på startmenyen som styrer volumet til spillet. 

##### Hva ville vi gjort annerledes?
I forbindelse med prosjektmetodikken tror vi at det hadde vært lurt å ha en fysisk tavle i tillegg til Issues-verktøyet på github. En tavle synliggjør at det blir for mye på tavlen, mens det er lett å bare legge til nye ting på en digital plattform med uendelig plass. 
Vi har allerede nevnt under prosjektmetodikk at arbeidstavlen vår har vært en hybrid av Issues på GitHub og brukerhistoriene vi har skrevet til hver sprint. Dersom vi hadde valgt i dag, ville vi heller hatt en hybrid av en fysisk tavle som hang på arbeidsplassen, og en digital arbeidstavle på GitHub.

I forbindelse med den første innleveringen definerte vi roller til prosjektet vårt. Disse rollene har likevel ikke blitt brukt noe særlig etter dette. Det er vanskelig å skulle si noe om hvorvidt vi ville gjort noe annerledes omkring dette dersom vi gjorde alt på nytt. Dersom dynamikken eller kommunikasjonen på gruppen hadde vært dårligere, hadde det kanskje vært greit å ha en "seniorkonsulent" som vi hadde blitt enig om skulle ha siste ordet. På samme måte ville det kanskje vært mer effektivt hvis gitte arbeidsoppgaver kunne delegeres til den ansvarlige personen. 

Til forskjell fra de andre gruppene vi har snakket med, har vi bygget opp grafikken fra bånn. Dette har gjort at det innimellom har oppstått utfordringer i forbindelse med biblioteker som ikke snakker så godt sammen med libgdx. Da gikk det fort mye tid på å lese seg opp på biblioteker som ikke var kjente for oss fra før av. Så vidt vi vet har vi klart å fikse de problemene som har oppstått pga dette, men vi ser for oss at vi hadde spart en del tid dersom vi brukte Tiles-verktøyet istedenfor. Tiles-verktøyet ville også gjort redigering av brettene våre enklere, da vi må spesifisere nøyaktige koordinater til de forskjellige objektene. Med det sagt så angrer vi ikke på måten vi har valgt å gjøre det på, og vi er ganske sikre på at grafikken vår ikke ligner på noen andres. Dersom vi likevel tillater oss å tenke som en kommersiell aktør, ville det vært tid å spare på å bruke en plattform som snakket bedre med libgdx.

Den tingen vi alle skulle ønske vi var tryggere på, er kanskje GitHub og kjennskap til alle dens verktøyer. For å unngå de store konfliktene, har vi forsøkt å fordele arbeidet på en slik måte at arbeidet ikke skal komme i konflikt med hverandre. Dette har fungert rimelig godt, men vi hadde kunnet vært mer fleksibel om vi var smidigere i GitHubs irrganger. For å komme rundt frustrasjonen over ikke å kunne pulle startet vi også med å endre tilstanden til "Assume Unchanged", pulle fra GitHub, for så å umiddelbart endre den tilbake til "No Assume Unchanged" etter pullet var vellykket. Ingen av oss har lest "GitHub for Dummies", men vi tipper at dette ikke er vanlig etikette..











