package sample.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.controller.Controller;

import java.io.IOException;

public class Main extends Application {

    public Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Database");
        this.primaryStage.setResizable(false);

        //initializeControllerSettings();
        initializeViewControllerSettings();
    }
    public void initializeControllerSettings(){
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
//
//            GridPane gridPane = fxmlLoader.load();
//
//            //Setting scene
////            Scene scene = new Scene(gridPane);
////            primaryStage.setScene(scene);
//
//            // Giving controller access to main application
//            Controller controller = fxmlLoader.getController();
//            controller.setStage(this.primaryStage);
//            primaryStage.show();
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    public void initializeViewControllerSettings(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));

            GridPane gridPane = fxmlLoader.load();

            //Setting scene
            Scene scene = new Scene(gridPane);
            primaryStage.setScene(scene);

            // Giving controller access to main application
            View view = (fxmlLoader.getController();
            view.setStage(this.primaryStage);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
