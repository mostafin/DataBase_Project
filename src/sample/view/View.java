package sample.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.controller.*;

import javax.swing.*;


public class View extends Parent {
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnFirst;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnPrev;
    @FXML
    private Button btnLast;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label imageLabel;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private DatePicker dateField;


    private Controller controller;
    private Stage stage;
    private String imgPath = "";
    private ImageChoosingListener imageChoosingListener;
    private DataBaseConnection dataBaseConnection;
    private InsertListner insertListner;
    private UpdateListner updateListner;
    private DeleteListener deleteListener;

    void setDeleteListener(DeleteListener deleteListener) {
        this.deleteListener = deleteListener;
    }

    void setUpdateListner(UpdateListner updateListner) {
        this.updateListner = updateListner;
    }

    void setInsertListner(InsertListner insertListner) {
        this.insertListner = insertListner;
    }

    void setDataBaseConnection(DataBaseConnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    void setImageChoosingListener(ImageChoosingListener imageChoosingListener) {
        this.imageChoosingListener = imageChoosingListener;
    }

    public View() {
        System.out.println("view konstrutor");
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setScene(new Scene(this.gridPane));
    }

    private void closeOpenConnectionInit(){
        dataBaseConnection.onStageShowing(this.stage);
        dataBaseConnection.onStageClosing(this.stage);
    }
    void setInit() {
        buttonsGraphicsInit();
        closeOpenConnectionInit();

    }

    public void chooseImgButtonListener() {

        this.imgPath= imageChoosingListener.chooseImage(this.imageLabel,this.stage);
    }


    private void buttonsGraphicsInit() {
        btnInsert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/insert.png"), 25, 25, true, true)));
        btnUpdate.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/update.png"), 30, 30, true, true)));
        btnDelete.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/delete.png"), 30, 30, true, true)));

        ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/Images/last.png"), 30, 30, true, true));
        img.setRotate(180);
        btnFirst.setGraphic(img);

        btnNext.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/next.png"), 30, 30, true, true)));

        img = new ImageView(new Image(getClass().getResourceAsStream("/Images/next.png"), 30, 30, true, true));
        img.setRotate(180);
        btnPrev.setGraphic(img);

        btnLast.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/last.png"), 30, 30, true, true)));

    }
    public void onInsertListener(ActionEvent actionEvent) {
        try {
            insertListner.Insert(nameField.getText(),priceField.getText(),dateField.getValue().toString(),this.imgPath);
           // JOptionPane.showMessageDialog(null,"Data inserted");
            Alert alert = AlertFactory.createAlert(AlertFactory.INFORMATION,"Data inserted successfully");
            alert.showAndWait();
        } catch (InputException e) {
           // JOptionPane.showMessageDialog(null,e.getMessage());
            Alert alert = AlertFactory.createAlert(AlertFactory.WARNING,e.getMessage());
            alert.showAndWait();
        }
    }

    public void onUpdateListener(ActionEvent actionEvent) {
        try {
            updateListner.update(idField.getText(), nameField.getText(), priceField.getText(), dateField.getValue().toString(), this.imgPath);
            //JOptionPane.showMessageDialog(null,"Data updated");
            Alert alert = AlertFactory.createAlert(AlertFactory.INFORMATION, "Data updated successfully");
            alert.showAndWait();

        } catch (IdNotFoundException e) {
            Alert alert = AlertFactory.createAlert(AlertFactory.ERROR, e.getMessage());
            alert.showAndWait();

        } catch (InputException e) {
            // JOptionPane.showMessageDialog(null,e.getMessage());
            Alert alert = AlertFactory.createAlert(AlertFactory.WARNING, e.getMessage());
            alert.showAndWait();
        }
    }

    public void onDeleteListener(ActionEvent actionEvent) {
        try {
            deleteListener.delete(idField.getText());
            Alert alert = AlertFactory.createAlert(AlertFactory.INFORMATION, "Data at ID ="+idField.getText() +" deleted successfully");
            alert.showAndWait();
        } catch (IdNotFoundException e) {
            Alert alert = AlertFactory.createAlert(AlertFactory.ERROR, e.getMessage());
            alert.showAndWait();
        } catch (InputException e) {
            Alert alert = AlertFactory.createAlert(AlertFactory.WARNING, e.getMessage());
            alert.showAndWait();
        }
    }
}
