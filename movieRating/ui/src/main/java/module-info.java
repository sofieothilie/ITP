module movieRating.ui {
  requires movieRating.core;
  requires movieRating.data;
  requires javafx.controls;
  requires javafx.fxml;
  requires java.net.http;
  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.annotation;
  
  opens ui to javafx.graphics, javafx.fxml;
}
