# Movie Rating Release 3

## Appens funksjonaliteter

1. Bruker skal kunne logge seg inn eller lage ny bruker
    - Lage ny bruker
    - Dersom bruker ikke er registrert skal den få et valg om å opprette en ny bruker.
    - Når bruker er logget inn eller logger ut skal det gis feedback på at dette var vellykket.
2. En skal kunne søke på filmer og se ratingen uansett om man er logget inn eller ikke.
    - Filmen(e) som tilfredstiller søket skal dukke opp
    - Dersom man er logget inn skal det være mulig å rate filmen man trykker på
    - Dersom man ikke er logget inn gis det feedback om det. **MANGLER**
    - Dersom filmen ikke er registrert skal det være mulig å legge til filmen med attributtene "title", "genre" og til slutt en "rating". Dette lagres til fil.
    - Dersom brukeren allerede har ratet denne filmen, skal det gis feedback på at det ikke er mulig å rate igjen **MANGLER**
3. En skal kunne velge en sjanger fra en nedtrekksmeny og se en liste med filmer av den sjangeren.
    - Dersom man er logget inn skal det være mulig å rate filmen man trykker på
    - Dersom man ikke er logget inn gis det beskjed om det. **MANGLER**
    - Dersom brukeren allerede har ratet denne filmen, skal det gis feedback på at det ikke er mulig å rate igjen **MANGLER**
4. Rating
    - Fra en nedtrekksmeny skal brukeren kunne gi rating til valgte film fra 1-5.
    - Deretter skal bruker få opp oppdatert rating på filmen
    - Ratingen lagres i to filer
        1. User - her samles alle users med tilhørende filmer vedkommende har ratet
        2. MovieRegister - her lagres filmer med sjanger og hva disse har blitt ratet.

## Mulige utvidelse **Skal fjernes, alt er enten implementert eller skal ikke implementeres?**

1. Sortering av filmregisteret
    - Rating, flere nedtrekksmenyer basert på tema, år, skuespillere osv.
2. Oversikt over brukerens ratede filmer, sortert fra høyest rate til lavest
3. Utvide med fysiske stjerner som rating
4. Utvide slik at delvis søk kommer opp, for eksempel ved et matchende ord el.
