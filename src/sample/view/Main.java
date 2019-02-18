package sample.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.Controller;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Database");
        primaryStage.setScene(new Scene(root, 700, 435));
        primaryStage.setResizable(false);

        Controller controller = fxmlLoader.getController();
        controller.setStage(primaryStage);

        controller.actionInitialization();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
