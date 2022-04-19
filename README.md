# Beskrivelse av prosjektet
Spillet er et plattformspill hvor spilleren må hoppe fra plattfrom til plattform og unngå fiender for plukke opp en nøkkel og komme seg gjennom døren for å gå videre til neste level. Plattforme har en rekke ulike egenskaper, noen av dem beveger seg, mens andre skyter spilleren høyt opp i luften, det finnes også en plattform som forsivinner midlertidig dersom spilleren står på den for lenge, så pass på!

Spillet kan spilles av en eller flere spillere, slik at du kan konkurrere med en venn om å komme lengst mulig og skaffe flest mulig poeng.

# Produktoppsett 
Åpne din foretrukkne IDE, f.eks. Eclipse. Prosjektet vil bygges, kompileres og kjøres i din valgte IDE. Main-funksjonen ligger i "src/main/java/game/Main", og kjører den ved hjelp av din IDE. Menyen vil da poppe opp, og etter å trykket på dine foretrukkne instillinger åpnes et nytt vindu med spillet. Dersom du ønsker å spille multiplayer med en server og en client må du først starte en server (eller velge auto) og deretter kjøre programmet igjen og velge client (eller auto) for å koble deg til serveren.

# Bugs
Det eksisterer en del bugs i spillet.

De fleste bugsene er knyttet til synkronisering i forbindelse med multiplayer-funksjonen over nettverk. Spilleren som spiller på pc 1 vil oppleve at sin spiller er synkron med terrenget, men at motspilleren i noen tilfeller kan være usynkron med terrenget. Dette blir tydelig i forbindelse med de bevegelige plattformene. Det kan også oppstå problemer i tilfeller hvor den ene spilleren dør, eller når den ene spilleren fullfører levelet. Under normale forhold vil spillet selv gjøre et forsøk på å synkronisere seg, men dette fungerer ikke alltid under speiselle tilfeller.

Vi har også bugs knyttet til bruk av Linux. Dersom vi kjører programmet vårt i Ubuntu, fungerer alt bortsett fra nettverksløsningene våre. Sindre, gruppelederen vår, får likevel kjørt programmet på sin Linux-maskin, dersom han kjører det i Debug-mode i IntelliJ. 

# Debug mode
Under testing av spillet kan man benytte en debug/cheat-mode. Denne aktiveres ved å trykke C og H samtidig på tastaturet, og kan deretter deaktiveres ved å trykke X. Når cheat-mode er aktivert kan spilleren fly rundt med økt hastighet, noe som tillater raskere testing av hva som for eksempel skjer når man fullfører et level.

# Musikk
All musikk er komponert av antsy-antonym

