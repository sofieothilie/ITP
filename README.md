# Movie Rating

[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2240/gr2240)

## Modules

- **core** - Here are the classes that are the core of the project. User.java and Movie.java. There is another folder "json". Here are MovieDeserializerForUser.java, MovieSerializerForUser.java
- **data** - Here are the classes with file handling. MovieHandler.java, MovieRegister.java, UserHandler.java and UserRegister.java.
- **docs** - This module includes releases for the documentation for each assignment. 
- **restapi** - This module contains files used for communicating with the server. The logic in data and spring are similar except for data writing to file and spring writing to a server. Contains MovieRatingApplication.java and MovieRatingSpringController.java
- **ui** - All user interfaces are stored here: MovieRatingController.java, MovieRatingApp.java. In addition this folder contains file for remote data access; RemoteMovieRatingAccess.java.  In resources is the MovieRating.fxml file.

## Run the project
Always start with running the following command in **gr2240/movieRating**: `mvn clean install -DskipTests`. After this, the server can be started by in **gr2240/movieRating/restapi**, by running: `mvn spring-boot:run`. Once the server is started, `mvn test` can be run in all folders, and the application can be started by entering **gr2240/movieRating/ui** and running: `mvn javafx:run`.

 **NB**:
In gitpod: The application **does not** open automatically. After running mvn `javafx:run` in **ui**, you have to go into "Remote Explorer" and press open browser for port 6080

## How to download the application with jlink and jpackage
After running `mvn clean install -DskipTests`, the application can be downloaded using the following commands in **gr2240/movieRating**: 
- mvn javafx:jlink -f./ui/pom.xml 
- mvn jpackage:jpackage -f ./ui/pom.xml
In order to properly use the application, the server must be started after this. See above.

## All documents
- [Table of contents](./movieRating/docs/release3/TABLE_OF_CONTENTS.md) includes a list of all doucment files and a small descripition of what each file includes. 
