package sample.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.model.Database;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Controller extends Parent implements DataBaseConnection{
    private Stage stage;
    private Database db;
    private  Image myImage;

    @FXML
    private Label imageLabel;

    public Controller(){
        //actionInitialization();

    }
    public void actionInitialization(){
        onStageShowing();
        onStageClosing();
    }


    public void setStage(Stage stage) {
       this.stage = stage;
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

    public Image ResizeImage(String imagePath,byte[] pic){
        Image tempImg = null;
        if(imagePath != null){
            tempImg = new Image(imagePath);
        }else{
            tempImg = new Image(String.valueOf(pic));
        }
        //ImageView imageView = new ImageView(myImage).s;
        Image img = tempImg;
        //Image img2  = img.getSc
        return  img;
    }

    public void chooseImgButtonListener(){
        System.out.println("Click");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        List<String> extenstionsList = List.of("*.jpg","*.png");

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image files (jpg,png)",extenstionsList);

        fileChooser.getExtensionFilters().add(filter);

        File result = fileChooser.showOpenDialog(stage);
        System.out.println(result.getAbsolutePath());
        if(result != null){

            System.out.println(result.getAbsolutePath());



//             myImage = new Image(result.getAbsolutePath());
//             imageLabel.setGraphic(new ImageView(myImage));
        }
        else{
            System.out.println("No file Selected");
        }
    }
}
