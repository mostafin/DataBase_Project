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
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller  implements DataBaseConnection,ImageChoosingListener,InsertListner,UpdateListner,DeleteListener{
    private Database db;
    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
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

            Platform.runLater(() -> label.setGraphic(myImage));

        }
        else{
            System.out.println("No file Selected");
        }
        return imgPath;
    }
    @Override
    public ImageView ResizeImage(String imagePath, byte[] pic, Label label) {
        ImageView tempImg;
        if(imagePath != null){
            tempImg = new ImageView(imagePath);
        }else{
            tempImg = new ImageView(pic.toString());
        }
        tempImg.setFitHeight(label.getHeight());
        tempImg.setFitWidth(label.getWidth());
        tempImg.setPreserveRatio(true);

        return tempImg;
    }

    @Override
    public void Insert(String name, String price, String date, String imgPath) throws InputException {
        if(insertValidate(name,price,date,imgPath))
        {
            try {
                productDAO.add(new Product(name,Float.parseFloat(price),date,imgPath));
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE,e.getClass() + e.getMessage());
            } catch (FileNotFoundException e) {
                LOGGER.log(Level.INFO,e.getClass() + e.getMessage());
            }
        }
    }

    @Override
    public boolean insertValidate(String name, String price, String date, String imgPath) throws InputException {
        if(name == null
                || price == null
                || date == null
                || imgPath == null) {
            throw new InputException("Prosze wypełnic wymagane pola!");
        }
        return true;
    }

    @Override
    public void update(String id, String name, String price, String date, String imgPath) throws InputException,IdNotFoundException {
        //System.out.println(imgPath);
        int updateResult = -1;
        try {
            if (imgPath.equals("")) {
                if (updateValidate(id, name, price, date, imgPath))
                    updateResult = productDAO.updateWithoutImg(new Product(Integer.parseInt(id), name, Float.parseFloat(price), date));
            } else {
                if (updateValidate(id, name, price, date, imgPath))
                    updateResult = productDAO.updateWithImg(new Product(Integer.parseInt(id), name, Float.parseFloat(price), date, imgPath));
            }
            if(updateResult == 0){
                throw new IdNotFoundException("Updated did not succed. Wrong data's ID!");
            }
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, e.getClass() + e.getMessage());
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.getClass() + e.getMessage());
        }
    }

    @Override
    public boolean updateValidate(String id, String name, String price, String date, String imgPath) throws InputException {
        if(     id == null
                || name == null
                || price == null
                || date == null
                || imgPath == null) {
            throw new InputException("Prosze wypełnic wymagane pola! id,name,price,date");
        }
        return true;
    }

    @Override
    public int delete(String id) throws IdNotFoundException, InputException {
        int deleteResult;
        if(deleteValidate(id)) {
            try {
                 deleteResult = productDAO.delete(Integer.parseInt(id));
                 if(deleteResult == 0)
                     throw new IdNotFoundException("Delete did not succeed. Wrong data's ID");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE,e.getClass() + e.getMessage());
            }
        }
        return 0;
    }

    @Override
    public boolean deleteValidate(String id) throws InputException {
        if(id.equals(""))
            throw new InputException("No ID given");
        return true;
    }
}
