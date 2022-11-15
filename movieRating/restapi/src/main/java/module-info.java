module movieRating.restapi {
    exports restapi;
    requires transitive movieRating.core;
    requires movieRating.data;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    //requires spring.beans;
    requires spring.web;

    opens restapi to spring.web;
    
}
