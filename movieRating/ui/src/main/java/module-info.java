module movieRating.ui {
  requires movieRating.core;
  requires movieRating.data;
  requires movieRating.restapi;
  requires javafx.controls;
  requires javafx.fxml;

  opens ui to javafx.graphics, javafx.fxml;
}
