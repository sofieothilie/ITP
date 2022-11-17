# Movie Rating

[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2240/gr2240)

## Modules, and build / run the project 

To build the project, run `mvn clean install` in the project folder (**movieRating**). To run the app, type in `mvn javafx:run` in the **ui** module. 

**Modules**

- **core** - Here are the classes that are the core of the project. User.java and Movie.java. There is another folder "json". Here are MovieDeserializerForUser.java, MovieSerializerForUser.java
- **data** - Here are the classes with file handling. MovieHandler.java, MovieRegister.java, UserHandler.java and UserRegister.java.
- **docs** - This module includes releases for the documentation for each assignment. 
- **spring** - This module contains files used for communicating with the REST api. The logic in data and spring are similar except for data writing to file and spring writing to a server.
- **ui** - All user interfaces are stored here, and mvn javafx:run and mvn test are run here. The folder contains MovieRatingController.java, MovieRatingApp.java. In resources is the MovieRating.fxml file.
  
 **NB**:
In gitpod: The application **does not** open automatically. After running mvn `javafx:run` in ui, you have to go into "Remote Explorer" and press open browser for port 6080

## All documents
- [Table of contents](./movieRating/docs/release3/TABLE_OF_CONTENTS.md) includes a list of all doucment files and a small descripition of what each file includes. 
