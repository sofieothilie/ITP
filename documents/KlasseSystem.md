# Oppsett av app

User

- String username
- String password
- List<Movie> RatedMovies;

Movie

- String name:
- String genre;
- double rating;

MovieRating

- User user
- Movie movie;

Filer:

- Brukerregister
    - alle brukere med filmer
- med MovieRating,
    - filmnavn
    - rating