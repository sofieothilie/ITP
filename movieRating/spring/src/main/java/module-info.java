module movieRating.spring {

    requires movieRating.core;
    requires movieRating.data;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    //requires spring.beans;
    requires spring.web;

    opens spring to spring.web;
    
}
