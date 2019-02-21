package sample.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.controller.Controller;


import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Database");
        this.primaryStage.setResizable(false);

        //initializeControllerSettings();
       initializeViewControllerSettings();
    }
//    public void initializeControllerSettings(){
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
//    }
    private void initializeViewControllerSettings(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));

            GridPane gridPane = fxmlLoader.load();

            //Setting scene
           // Scene scene = new Scene(gridPane);
           // primaryStage.setScene(scene);

            /// Creating controller
            Controller controller = new Controller();

            // Giving controller access to main application
            View view = fxmlLoader.getController();
            view.setController(controller);
            view.setStage(this.primaryStage);
            view.setImageChoosingListener(controller);
            view.setDataBaseConnection(controller);
            view.setInsertListner(controller);
            view.setUpdateListner(controller);
            view.setInit();
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
