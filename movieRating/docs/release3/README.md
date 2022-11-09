# Movie Rating Release 3

## The app's functionalities

1. The user must be able to log in or create a new user
    - Create a new user
    - If the user is not registered, they must be given a choice to create a new user.
    - When the user is logged in or logs out, feedback must be given that the action was successful.
2. User must be able to search for movies and see the rating regardless of whether the user is logged in or not.
    - The movie(s) that satisfy the search should appear
    - If you are logged in, it should be possible to rate the movie you click on
    - If the movie is not registered, it must be possible to add the movie with the attributes "title", "genre" and eventually a "rating" at the end. This is saved to a file.
    - If the user has already rated this film, feedback must be given that it is not possible to rate again **MISSING**
3. User should be able to select a genre from a drop-down menu and see a list of movies of that genre.
    - If you are logged in, it should be possible to rate the film you click on
    - If the user has already rated this film, feedback must be given that it is not possible to rate again **MISSING**
4. Rating
    - From a drop-down menu, the user must be able to give a rating to the selected movie from 1 and up to 5.
    - The user should then see an updated rating for the film
    - The rating is saved in two files
        1. User - all the users are stored here with associated movies that they have rated
        2. MovieRegister - all the movies are stored here with genre and what these have been rated.

## Possible extension **To be removed, everything is either implemented or not to be implemented?**

1. Sorting of the film register
    - Rating, several drop-down menus based on theme, year, actors, etc.
2. Overview of the user's rated films, sorted from highest rated to lowest
3. Expand with physical stars as a rating
4. Expand so that partial searches come up, for example by a matching word