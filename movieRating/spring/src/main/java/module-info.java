module movieRating.spring {

    requires movieRating.core;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    //requires spring.beans;
    requires spring.web;

    opens gr2240.spring to spring.web;
    
}
