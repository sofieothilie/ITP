module movieRating.core {
  exports core;
  exports core.json;

  opens core to com.fasterxml.jackson.databind;

  requires transitive com.fasterxml.jackson.databind;
}
