module movieRating.data {
    requires transitive movieRating.core;
    exports data;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    //requires com.fasterxml.jackson.core.JsonGenerator;
}
