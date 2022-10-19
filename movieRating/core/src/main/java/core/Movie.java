package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Movie {
    private String title; //TODO: bør settes til final
    private String genre; //TODO: endre til final
    private List<Integer> allRatings = new ArrayList<>(); 
    private static List<String> GENRES = Arrays.asList("action", "comedy", "drama", "fantasy", "horror", "mystery", "romance", "thriller");

    //TODO: fjerne metoden
//public Movie() { // konstruktør til filhåndtering
//    super();
//}
    public Movie(String title, String genre, List<Integer> allRatings) { // konstruktør til filhåndtering
        this(title, genre);
        this.allRatings = new ArrayList<>(allRatings);
    }

    public Movie(String title, String genre, Integer rating) { // TODO: SJkeke om vi trenger denne til filhåndtering
        this(title, genre);
        this.allRatings.add(rating);
    }
    
    public Movie(String title, String genre) { // oppretter Movie-objekt
        if(title.isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
        if(! GENRES.contains(genre)){
            throw new IllegalArgumentException("Not a valid genre");
        }
        this.genre = genre;
    }

    public String getTitle() { //returnerer tittelen
        return title;
    }

    public String getGenre() { //returnerer sjangeren
        return genre;
    }

    public List<Integer> getAllRatings(){ //returnerer en kopi av listen med alle ratings
        List <Integer> copyAllRatings = new ArrayList<Integer>(allRatings);
        return copyAllRatings;
    }

    public void addRating(int rating){ //legger til en rating i liste over alle ratings og legger til 
        if(rating < 1 || rating > 5){ //sjekker om rating er mellom 1 og 5
            throw new IllegalArgumentException("Not a valid rating");
        }
        allRatings.add(rating); 
    }

    public double averageRating() { // TODO: endre navn, + @JsonIgnore over metoden (beregner gjennomsnittet av alle ratinger for denne filmen)
        Integer sum = 0;
        for (int rating=0; rating < allRatings.size(); rating++) {
              sum += allRatings.get(rating);
        }
        double average = (sum.doubleValue() / allRatings.size());
        return average;
        
    }

    public String toString(){ //returnerer en string med tittel og sjanger og gjennomsnittsrating
        return ""+ this.getTitle() + "; " + this.getGenre() + "; " + String.format("%.2f",this.averageRating());
    }

    @Override
    public boolean equals(Object object){ //Sjekker om et objekt er likt, TODO: skrive hva metoden gjør
        if (object instanceof Movie){
            if (this.getTitle().equals(((Movie) object).getTitle())){
                if (this.getGenre().equals(((Movie) object).getGenre())){
                    return true;
                }}
        }
        return false;
    }

    @Override
    public int hashCode() { //TODO: spørre studass om spotbugs, hashcode
        //SpotBugs demand override of hashCode with override of equals. 
        //This was their own fix when it isn't to be used.
        return 43;
    }

}

