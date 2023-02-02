# Movie Rating Release 3

## The app's functionalities

1. The user can log in or create a new user.
    - If the user is not registered, feedback is given, and they can choose to create a new user.
    - When the user is logged in or logs out, feedback is given that the action was successful.
    - When a user logs in, the user is able to see an overview of all the movies rated by this user.

2. User must be able to search for movies and see the rating regardless of whether the user is logged in or not.
    - The movie(s) that satisfy the search appears in a listveiw
    - If you are logged in, it is possible to rate the movie you click on
    - If the movie is not registered, it is possible to add the movie with the attributes "title", "genre" and eventually a "rating" at the end. This is saved to a file. And the movie shows up in the overview of the movies this user has rated.
    - If the user has already rated this movie, feedback is given that it is not possible to rate again, but that the user can delete the movie and then add it again with a new rating.

3. User is able to select a genre from a drop-down menu, and see a list of movies of that genre.
    - If you are logged in, it is possible to rate the movie you click on.
    - If the user has already rated this movie, feedback is given that it is not possible to rate.   
    again, but that the user can delete the movie and then add it again with a new rating.  

4. User is able to search afte a movie by typing in the title and select a genre from a drop-down menu
    - If you are logged in, it should be possible to rate the movie you click on
    - If the user has already rated this movie, feedback is given that it is not possible to rate.   
again, but that the user can delete the movie and then add it again with a new rating.  

5. User is able to delete a movie from their register
    - When deleting a movie, the movie is deleted from the users rated movies in the UserRegistry file, and the rating from this user is removed from the movie in the MovieRegistry file

6. Rating
    - From a drop-down menu, the user is able to give a rating to the selected movie from 1 and up to 5.
    - The user can then see an updated rating for the movie
    - The rating is saved in two files
        1. UserRegistry - all the users are stored here with associated movies that they have rated
        2. MovieRegistry - all the movies are stored here with genre and what these have been rated.

