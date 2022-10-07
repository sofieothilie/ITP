module movieRating.data {
    requires movieRating.core;
    exports data;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    //requires com.fasterxml.jackson.core.JsonGenerator;
}
