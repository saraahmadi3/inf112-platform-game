# Obligatorisk oppgave 1

Vi har delt inn dette dokumentet i fem deler, på samme måte som selve oppgaven
er delt inn i fem deloppgaver. Det er likevel en del overlapp i oppgaveteksten
mellom ulike oppgavedeler, og vi har forsøkt å unngå å svare to ulike steder på de samme tingene.

## Innhold:
##### Deloppgave 1-5
Deloppgave 1: Organisering av teamet  
Deloppgave 2: Prosessen  
Deloppgave 3: Oversikt over forventet produktet/Brukerhistorier  
Deloppgave 4: Kode   
Deloppgave 5: Oppsummering  

##### Setup
Maven Setup  
Running  
Jar Files  
Eclipse Setup  




## Deloppgave 1:
Gruppenavn: antsy-antonym

Kartlegging av kompetanse til medlemmene:
Kristian: Går andre året på Datateknologi. På ungdomsskole hadde 
han et valgfag som het "Programmering". På vgs hadde han IT1 og IT2, 
"programmering og modellering x".
HTML, Database, CSS, JavaScript. Har erfaring med Python, Java og Haskell.

#### Sara:
Har gått Data Science (2. året). Har jobbet med Python tidligere. 
Brukte Python på vgs i forbindelse med matematikk(modellering).

#### Adrian:
Går Data Science 3. året. Har erfaring med Python, Java og Haskell. 
Har prøvd seg på å lage websider for en kunde (database-basert).

#### Dani: 
Går andre året på Datateknologi. Har jobbet som lektor i 7 år før han 
begynte på Datateknologi. Ingen erfaring med programmering før han begynte på 
Datateknologi.

### Rollefordeling:
Aministrativ leder: Dani. Oppgaver: Koordinere møter med de øvrige medlemmene. Sekretær.
Testleder: Adrian. Oppgaver: Overordnet ansvar for testing.
Kodesjef: Kristian: Overordnet oppsyn med dokumentering av kode.
Git-sjef: Sara: Ansvar for Git, arbeidsfordeling.

## Deloppgave 2:
Organisering:
Her vil vi gjøre en løpende vurdering underveis i prosjektet, men vi vil forsøke å ha en metodikk 
med utgangspunkt i Kanban-metoden. Vi møtes ukentlig og oppdaterer hverandre på fullførte oppgaver,
løpende oppgaver og hvordan arbeidsoppgavene skal fordeles. Vi vil også benytte av Trello-verktøyet som en visuell hjelp til Kanban-metoden.

Vi har ikke bestemt oss for noe plott til spillet 
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

## Deloppgave 3:
Det overordnede målet er i første omgang å lage et minimum viable product med de tre første punktene oppgitt i deloppgave 4. 
Dvs at vi har et spillbrett, en spiller som vises på spillbrettet, i tillegg til muligheten til å bevege denne spilleren.
den kan bevege seg på.

### Brukerhistorier i prioritert rekkefølge for innlevering av obligatorisk oppgave 1:
1) Som spiller forventer jeg å se en verden/spillbrett som utgjør selve spillet.
Akseptansekriterier: Vi skal ha en klar definert nedre del av spillbrettet, og minst en plattform en annen plass på spillbrettet.

2) Som spiller forventer jeg å se en spiller på spillbrettet, som jeg kan manipulere.
Akseptansekriterier: Det skal plasseres en spiller på brettet i det spillet starter.

3) Som spiller forventer jeg å kunne flytte figuren fra side til side, og opp og ned. 
Akseptansekriterier: Figuren kan flyttes fra side til side (såfremt det ikke er objekter i veien) og hoppe. Figuren blir påvirket
av tyngdekraften, og beveger seg nedover etter et hopp, og hvis spilleren beveger seg utenfor en kant.

4) Som spiller forventer jeg at spillkarakteren ikke kan gå gjennom fysiske objekter. 
Akseptansekriterier: Dersom en karakter hopper opp og treffer en plattform, skal hen stoppes av plattformen og falle ned igjen. 

5) Som spiller forventer jeg at spillet aldri kræsjer.
Akseptansekriterier: Spillet skal ikke kræsje.

### Øvrige brukerhistorier/Backlog:
Jeg forventer at bakgrunn og forgrunn i spillet er lett å skille fra hverandre.
Akseptansekriterier: Fargene i forgrunn og bakgrunn må lett kunne skilles fra hverandre. Fargekombinasjoner som grønn og rød bør unngås 
som kontraster på forgrunn og bakgrunn (fargeblindhet). Teksturer hjelper også med å skille ulike elementer på skjermen.

Som spiller forventer jeg at spillet er intuitivt å spille.
Akseptansekriterier: Det skal være lett å skjønne hvordan spilleren kan bevege seg på skjermen.


## Deloppgave 5 
Oppsummering:

Gruppen har en fin dynamikk, god kommunikasjon og en god størrelse. Vi har klart å fordele oppgaver, slik at ingen blir sittende arbeidsløs.
Takket være Kristians tidligere erfaring med å lage spill, har vi også kommet godt i gang med selve spillutviklingen (selv om det inntil videre bare innfrir de tre første kravene til mvp). Vi fulgte ikke den test-drevne utviklingen i begynnelsen, men gjorde heller ting i motsatt rekkefølge; vi begynte med utvikling, og begynte deretter med testing. Vi oppdaget også at det blir vanskelig å teste bevegelsene til spillkarakteren. Spillkarakteren beveger seg med utgangspunkt i x- og y-koordinater, men det er vanskelig å fastslå hvor langt et tastetrykk skal flytte karakteren. Det er derfor vanskelig å lage en test, som tester om karakteren flytter seg nøyaktig fra a til b. 

I fortsettelsen må vi fordype oss i lib-gdx/Tiling og dets muligheter med tanke på spillkarakter og spillbrett. Vår foreløpige tanke er et plattformspill hvor hvert enkelt nivå er et eget spillbrett, og vi treger derfor flere spillbrett. Spillopplevelsen til det ferdige produktet vårt avhenger derfor av hvor flink vi blir til å utvikle spillbrett.

Vi må også bli flinkere til å utnytte mulighetene git gir oss. Inntil videre har vi fordelt oppgaver slik at vi ikke skriver i samme filer. Ulike medlemmer har derfor fått i oppgave å jobbe med enkeltklasser, slik at vi kan committe uten at vi har måttet bekymre oss for merging-konflikter. Dette har fungert til nå, men vi ser at det vil bli en utfordring når prosjektet blir mer komplisert.

Til tross for at vi har valgt å benytte oss av Kanban-metoden har vi ikke kommet skikkelig i gang med å benytte oss av  det visuelle aspektet med denne metoden ennå. Vi har ikke kommet i gang med Trello ennå, og har tatt alt muntlig, da vi har møttes ofte. Dette vil vil endre på til neste gang. Den muntlige dialogen vi har hatt har fungert fint, men det er nok lettere å glemme ting vi har blitt enig om, når det ikke er notert en plass.

![Klassediagram](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/raw/67f497b2b0c8d12f5aa9e5a377cb41f8f4cf262b/IMG_1061.jpg)


Sara Ahmadi, Kristian Nore, Dani Marås og Adrian Clopan.


# INF112 libGDX + Maven template 
Simple skeleton with [libGDX](https://libgdx.com/). See the tutorial in [Filer / Tutorials](https://mitt.uib.no/courses/33532/files/folder/Tutorials) on Mitt UiB. 


# Maven Setup
This project comes with a working Maven `pom.xml` file. You should be able to import it into Eclipse using *File → Import → Maven → Existing Maven Projects* (or *Check out Maven Projects from SCM* to do Git cloning as well). You can also build the project from the command line with `mvn clean compile` and test it with `mvn clean test`.

Pay attention to these folders:
* `src/main/java` – Java source files go here (as usual for Maven)
* `src/main/resources` – data files go here, for example in an `assets` sub-folder
* `src/test/java` – JUnit tests
* `target/classes` – compiled Java class files

You should probably edit the `pom.xml` and fill in details such as the project `name` and `artifactId`:


```xml

	< !-- FIXME - set group id -->
	<groupId>inf112.skeleton.app</groupId>
	< !-- FIXME - set artifact name -->
	<artifactId>gdx-app</artifactId>
	<version>1.0-SNAPSHOT</version>
	<packaging>jar</packaging>

	< !-- FIXME - set app name -->
	<name>mvn-app</name>
	< !-- FIXME change it to the project's website -->
	<url>http://www.example.com</url>
```

	
## Running
You can run the project from Eclipse, or with Maven using `mvn exec:java`. Change the main class by modifying the `main.class` setting in `pom.xml`:

```
		<main.class>inf112.skeleton.app.Main</main.class>
```

If you run `mvn package` you'll get a everything bundled up into a JAR file
* `target/*.jar` – your compiled project, packaged in a JAR file

## Jar Files

If you run `mvn package` you get everything bundled up into a `.jar` file + a ‘fat’ Jar file where all the necessary dependencies have been added:

* `target/NAME-VERSION.jar` – your compiled project, packaged in a JAR file
* `target/NAME-VERSION-fat.jar` – your JAR file packaged with dependencies

Run Jar files with, for example, `java -jar target/javafx-app-1.0-SNAPSHOT-fat.jar`.

## Eclipse Setup

It's usually smart to change Eclipse's Maven settings so that it'll automatically download Javadocs and the source code for your dependencies:

![Turn on Download Sources and Javadoc](https://git.app.uib.no/inf112/22v/lectures/-/raw/master/img/eclipse-maven.png)
