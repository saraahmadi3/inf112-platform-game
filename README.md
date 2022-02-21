# Obligatorisk oppgave 1

Vi har delt inn dette dokumentet i fem deler, på samme måte som selve oppgaven
er delt inn i fem deloppgaver. Det er likevel en del overlapp i oppgaveteksten
mellom ulike oppgavedeler, og vi har forsøkt å unngå å svare to ulike steder på de samme tingene. Setupen som er gitt i oppgaveteksten, er også tatt med på bunnen av dokumentet.

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
Hvorfor? Dani har jobbet lenge i norsk skole, og har erfaring med å koordinere både kolleger og elever i forbindelse med prosjekter og lignende.  

Testleder: Adrian. Oppgaver: Overordnet ansvar for testing.
Hvorfor? Adrian er den av oss som har kommet lengst i studieløpet, og har mest erfaring med tester.  

Kodesjef: Kristian: Overordnet oppsyn med dokumentering av kode.
Hvorfor? Kristian har erfaring med spillutvikling tidligere.    


Git-sjef: Sara: Ansvar for Git, arbeidsfordeling.
Hvorfor? Sara har en allsidig kompetanse, og er kjapp med å ta i bruk nye verktøyer. Vi kommer til å støte på problemer med git, og da trenger vi at en kjenner dette systemet godt.  

## Deloppgave 2:
Organisering:
Her vil vi gjøre en løpende vurdering underveis i prosjektet, men vi vil forsøke å ha en metodikk 
med utgangspunkt i Kanban-metoden. Vi møtes ukentlig og oppdaterer hverandre på fullførte oppgaver,
løpende oppgaver og hvordan arbeidsoppgavene skal fordeles. Vi vil benytte Trello-verktøyet som en visuell hjelp til Kanban-metoden.

![Trello](https://git.app.uib.no/inf112-oblig/inf112.22v.libgdx-template/-/blob/master/2022-02-20_20_57_35-Oblig___Trello.jpg)

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
1) Som spiller forventer jeg å se en verden/spillbrett som utgjør selve spillet.  
Akseptansekriterier: Vi skal ha en klar definert nedre del av spillbrettet, og minst èn plattform en annen plass på spillbrettet. Det skal også finnes en dør/portal eller lignende, som viser hvor karakteren skal ende for å komme til neste level/nivå.

2) Som spiller forventer jeg å se en karakter på spillbrettet.  
Akseptansekriterier: Det skal plasseres en spiller på brettet i det spillet starter. Denne spilleren skal ha et ansikt med tydelige trekk.

3) Som spiller forventer jeg å kunne flytte figuren fra side til side, og opp og ned.  
Akseptansekriterier: Figuren kan flyttes fra side til side ved hjelp av piltaster og hoppe. Figuren blir påvirket av tyngdekraften, og beveger seg nedover etter et hopp, eller hvis spilleren beveger seg utenfor en kant. Forklaringer og tips til hvordan en beveger karakteren ruller over skjermen underveis i spillet.

4) Som spiller forventer jeg at spillkarakteren ikke kan gå gjennom fysiske objekter.  
Akseptansekriterier: Dersom en karakter hopper opp og lander på en plattform, skal hen bli stående på plattformen. Dersom karakteren hopper opp og treffer en plattform nedenfra, skal hen ikke gå gjennom plattformen, men miste momentet og falle ned igjen. Karakteren skal stoppe dersom hen går inn i en vegg.

5) Som spiller forventer jeg at spillet aldri kræsjer.  
Akseptansekriterier: Spillet skal ikke kræsje. (Dette er egentlig et helt umulig kriterie å forholde seg til, siden et program alltid kan kræsje. Det er likevel essensielt med tanke på spilleropplevelsen, så vi vil ha det med som et punkt vi må forholde oss til hver gang vi legger til noe nytt i spillet)

### Øvrige brukerhistorier/Backlog:
Jeg forventer at bakgrunn og forgrunn i spillet er lett å skille fra hverandre.  
Akseptansekriterier: Fargene i forgrunn og bakgrunn må lett kunne skilles fra hverandre. Fargekombinasjoner som grønn og rød bør unngås 
som kontraster på forgrunn og bakgrunn (fargeblindhet). Teksturer hjelper også med å skille ulike elementer på skjermen.

Som spiller forventer jeg at spillet er intuitivt å spille.  
Akseptansekriterier: Det skal være lett å skjønne hvordan spilleren kan bevege seg på skjermen.

## Deloppgave 4
Kode:

Se egen mappe.


## Deloppgave 5 
Oppsummering:

Gruppen har en fin dynamikk, god kommunikasjon og en god størrelse. Vi har klart å fordele oppgaver, slik at ingen blir sittende arbeidsløse.
Takket være Kristians tidligere erfaring med å lage spill, har vi også kommet godt i gang med selve spillutviklingen (selv om det inntil videre bare innfrir de fire første kravene til mvp). Vi støtte på litt vanskeligheter med tanke på å skrive tester til programmet vårt. Libgdx var nytt for oss, og det ble vanskelig å skrive testene uten at noe av programmet var på plass ennå. Vi fulgte derfor ikke den test-drevne utviklingen i begynnelsen, men gjorde heller ting i motsatt rekkefølge; vi begynte med utvikling, og begynte deretter med testing. I fortsettelsen tror vi at testingen og utviklingen kommer til å være a jour med hverandre, og at det blir testdrevet utvikling i praksis så vel som på papiret.

I fortsettelsen må vi fordype oss i lib-gdx/Tiling og dets muligheter med tanke på spillkarakter og spillbrett. Vår foreløpige tanke er et plattformspill hvor hvert enkelt nivå er et eget spillbrett, og vi trenger derfor flere spillbrett. Spillopplevelsen til det ferdige produktet vårt avhenger derfor av hvor flink vi blir til å utvikle spillbrett.

Vi må også bli flinkere til å utnytte mulighetene git gir oss. Inntil videre har vi fordelt oppgaver slik at vi ikke skriver i samme filer. Ulike medlemmer har derfor fått i oppgave å jobbe med enkeltklasser, slik at vi kan committe uten at vi har måttet bekymre oss for merging-konflikter. Dette har fungert til nå, men vi ser at det vil bli en utfordring når prosjektet blir mer komplisert.

Til tross for at vi har valgt å benytte oss av Kanban-metoden kom vi ikke skikkelig i gang med å benytte oss av  det visuelle aspektet med denne metoden før helt mot slutten. Vi kom i gang med Trello i løpet av uke 7, men i praksis har ting blitt avtalt muntlig, da vi har møttes ofte. Den muntlige dialogen vi har hatt har fungert fint til nå, men etterhvert som prosjektet blir mer omfattende tror vi det vil være nødvendig å nyttiggjøre oss Trello-brettet mer aktivt. 



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
