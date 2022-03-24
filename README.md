# Running 
I forbindelse med tilbakemeldingen til oblig1, har vi blitt gjort oppmerksomme på at gruppelederne ikke klarer å kjøre programmet vårt på maskinene deres. Vår forelpige hypotese er at dette skyldes noe i forbindelse med Eclipse. Dersom vi ikke klarer å løse dette før fristen, er det derfor mulig at brukeren må kjøre programmet i Eclipse.

# Bugs
Det eksisterer en del bugs i spillet. En av bugsene som finnes er at dersom spilleren beveger seg for fort, kan det hende at collision-detection ikke fungerer. Dette problemet er ikke utbredt, men det kan forekomme.
De fleste bugsene er knyttet til synkronisering i forbindelse med multiplayer-funksjonen over nettverk. Spilleren som spiller på pc 1 vil oppleve at sin spiller er synkron med terrenget, men at motspilleren kan være usynkron med terrenget. Dette skjer typisk i forbindelse med de bevegelige plattformene. Når terrenget blir usynkront på de to ulike maskinene, kan det for spiller 1 fortone seg som at spiller 2 og den bevegelige plattformen hen står på er ute av synk. En vil likevel oppleve at sin egen spiller er synkron med det lokale terrenget.

