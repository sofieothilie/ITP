module movieRating.ui {
  requires movieRating.core;
  requires movieRating.data;
  requires movieRating.restAPI;
  requires javafx.controls;
  requires javafx.fxml;

  opens ui to javafx.graphics, javafx.fxml;
}
