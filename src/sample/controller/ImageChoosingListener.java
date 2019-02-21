package sample.controller;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public interface ImageChoosingListener {

    String chooseImage(Label label, Stage stage);

    ImageView ResizeImage(String imagePath, byte[] pic, Label label);
}
