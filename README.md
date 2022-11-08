# Movie Rating

[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2240/gr2240)

## Description

Our project Movierating is inside the folder movieRating. Here "mvn clean install" is run in the terminal. Inside this folder there are four modules:

- **core** - Here are the classes that are the core of the project. User.java and Movie.java. There is another folder "json". Here are MovieDeserializerForUser.java, MovieSerializerForUser.java
- **data** - Here are the classes with file handling. MovieHandler.java, MovieRegister.java, UserHandler.java and UserRegister.java.
- **spring** - *Thea fikser denne!!*
- **ui** - All user interfaces are stored here, and mvn javafx:run and mvn test are run here. The folder contains MovieRatingController.java, MovieRatingApp.java. In resources is the MovieRating.fxml file. In this module "mvn javafx:run" and "mvn test" are run.
  
**NB**: In gitpod: The application does not open automatically. After running mvn javafx:run in ui, you have to go into "Remote Explorer" and press open browser for port 6080
