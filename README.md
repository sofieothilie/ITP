# Movie Rating

[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2240/gr2240)

## Beskrivelse

Vårt prosjekt Movierating ligger inne i mappen movieRating. Her kjøres mvn clean install i terminalen. Inne i denne mappen ligger det tre moduler:

- **core** - Her ligger klassene som er kjernen av prosjektet. User.java og Movie.java
- **data** - Her ligger klassene med filhåndtering. MovieHandler.java, MovieRegister.java, UserHandler.java og UserRegister.java.
- **ui** - Her ligger alt av brukergrensesnitt, og her kjøres mvn javafx:run og mvn test. Mappen inneholder MovieRatingController.java, MovieRatingApp.java. I resources ligger MovieRating.fxml filen. Her kjøres mvn javafx:run og mvn test.
  
**NB:** Applikasjonen åpnes ikke automatisk. Etter å ha kjørt mvn javafx:run i ui, må man gå inn i "Remote Explorer" og trykke på open browser for port 6080.
