package sample.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.model.Database;
import sample.model.Product;
import sample.model.ProductDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller  implements DataBaseConnection,ImageChoosingListener,InsertListner{
    private Database db;
    public static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
    private ProductDAO  productDAO = new ProductDAO();

    public Controller(){
        System.out.println("konstruktor");
    }

    @Override
    public void onStageShowing(Stage stage) {
        stage.setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                db = Database.getInstance();
                db.connect();
            }
        });
    }

    @Override
    public void onStageClosing(Stage stage) {
        stage.setOnCloseRequest(event -> db.disconnect());

    }
    @Override
    public String chooseImage(Label label, Stage stage) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        List<String> extenstionsList = new ArrayList<>(); //= List.of("*.jpg","*.png");
        extenstionsList.add("*.jpg");
        extenstionsList.add("*.png");

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image files (jpg,png)",extenstionsList);

        fileChooser.getExtensionFilters().add(filter);

        File result = fileChooser.showOpenDialog(stage);
        String imgPath = "";
        //System.out.println(result.getPath().replace("\\","\\\\"));
        if(result != null){
            imgPath = result.getPath().replace("\\","\\\\");
            ImageView myImage = this.ResizeImage(result.toURI().toString(),null,label);

            Platform.runLater(() -> {
                label.setGraphic(myImage);
            });

        }
        else{
            System.out.println("No file Selected");
        }
        return imgPath;
    }
    @Override
    public ImageView ResizeImage(String imagePath, byte[] pic, Label label) {
        ImageView tempImg = null;
        if(imagePath != null){
            tempImg = new ImageView(imagePath);
        }else{
            tempImg = new ImageView(String.valueOf(pic));
        }
        tempImg.setFitHeight(label.getHeight());
        tempImg.setFitWidth(label.getWidth());
        tempImg.setPreserveRatio(true);

        return tempImg;
    }

    @Override
    public void Insert(String name, String price, LocalDate date, String imgPath) throws InputException {
        if(name == null
                || price == null
                || date == null
                || imgPath == null)
                throw new InputException("Prosze wypełnic wszystkie pola!");
        else{
            try {
                productDAO.add(new Product(name,Float.parseFloat(price),date,imgPath));
            } catch (SQLException e) {
                LOGGER.log(Level.INFO,e.getClass() + e.getMessage());
            } catch (FileNotFoundException e) {
                LOGGER.log(Level.INFO,e.getClass() + e.getMessage());
            }
        }

    }
}
