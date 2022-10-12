# Oppsett av app

`User` 
- `String username`
- `String password`
- `List RatedMovies`
  
`Movie`
- `String name`
- `String genre`
- `Double rating`
  
`MovieRating`
- `User user` 
- `Movie movie`
  
`Filer:`
- `Brukerregister `
  - `alle brukere med filmer`
- `med MovieRating, `
  - `filmnavn`
  - `rating`

