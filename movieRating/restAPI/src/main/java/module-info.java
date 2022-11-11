module movieRating.restAPI {
    exports restAPI;
    requires transitive movieRating.core;
    requires movieRating.data;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    //requires spring.beans;
    requires spring.web;

    opens restAPI to spring.web;
    
}
