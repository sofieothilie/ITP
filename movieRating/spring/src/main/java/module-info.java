module movieRating.spring {

    requires spring.web;
    requires movieRating.core;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.beans;

    opens gr2240.spring to spring.web;
    
}
