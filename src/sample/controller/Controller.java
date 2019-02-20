package sample.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.model.Database;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Controller extends Parent implements DataBaseConnection{
    private Stage stage;
    private Database db;
    private  ImageView myImage;

    @FXML
    private Label imageLabel;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button btnFirst;

    public Controller(){
        System.out.println("kontruktor");
        actionInitialization();
    }
    public void actionInitialization(){
        //onStageShowing();
        //onStageClosing();

    }

//    public void buttonsGraphicsInit(){
//        btnFirst.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/next.png"),30,30,true,true)));
//    }
    public void setStage(Stage stage) {
       this.stage = stage;
       this.stage.setScene(new Scene(gridPane));
    }

    @Override
    public void onStageShowing() {
        stage.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                db = Database.getInstance();
                db.connect();
            }
        });
    }

    @Override
    public void onStageClosing() {
        stage.setOnCloseRequest(event -> db.disconnect());

    }

    public ImageView ResizeImage(String imagePath,byte[] pic){
        ImageView tempImg = null;
        if(imagePath != null){
            tempImg = new ImageView(imagePath);
        }else{
            tempImg = new ImageView(String.valueOf(pic));
        }
        tempImg.setFitHeight(imageLabel.getHeight());
        tempImg.setFitWidth(imageLabel.getWidth());
        tempImg.setPreserveRatio(true);

        return tempImg;
    }

    public void chooseImgButtonListener(){
        System.out.println("Click");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        List<String> extenstionsList = new ArrayList<>(); //= List.of("*.jpg","*.png");
        extenstionsList.add("*.jpg");
        extenstionsList.add("*.png");

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image files (jpg,png)",extenstionsList);

        fileChooser.getExtensionFilters().add(filter);

        File result = fileChooser.showOpenDialog(stage);
        System.out.println(result.getAbsolutePath());
        if(result != null){
          //  myImage = new ImageView(result.toURI().toString());
            myImage = ResizeImage(result.toURI().toString(),null);
            Platform.runLater(() -> {
                imageLabel.setGraphic(myImage);

            });
        }
        else{
            System.out.println("No file Selected");
        }
    }
}
