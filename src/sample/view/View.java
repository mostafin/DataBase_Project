package sample.view;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.controller.*;
import sample.model.Product;

import java.util.concurrent.locks.ReentrantLock;


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
    public Label imageLabel;
    @FXML
    public TextField idField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField priceField;
    @FXML
    public DatePicker dateField;
    @FXML
    public TableView<Product> productsTable;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn priceColumn;
    @FXML
    private TableColumn dateColumn;

    private Stage stage;
    private String imgPath = "";
    private ImageChoosingListener imageChoosingListener;
    private DataBaseConnection dataBaseConnection;
    private InsertListner insertListner;
    private UpdateListner updateListner;
    private DeleteListener deleteListener;
    private FetchDataListener fetchDataListener;
    private SelectListener selectListener;
    private MoveListener moveListener;
    private final ReentrantLock lock = new ReentrantLock();
    private ObservableList<Product> productList;

    void setMoveListener(MoveListener moveListener) {
        this.moveListener = moveListener;
    }

    void setSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }

    void setFetchDataListener(FetchDataListener fetchDataListener) {
        this.fetchDataListener = fetchDataListener;
    }
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

    public View() {}


    void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setScene(new Scene(this.gridPane));
    }

    private void closeOpenConnectionInit(){
        this.lock.lock();
            System.out.println("Otwieranie połaczenia");
            dataBaseConnection.onStageShowing(this.stage,this.lock);

        dataBaseConnection.onStageClosing(this.stage);
    }
    void setInit() {
        buttonsGraphicsInit();
        tableColumnsInit();
        closeOpenConnectionInit();
        populateTable();
    }

    public void chooseImgButtonListener(ActionEvent actionEvent) {
        if(imageChoosingListener != null)
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
        if(insertListner != null) {
            try {
                insertListner.Insert(nameField.getText(), priceField.getText(), dateField.getValue().toString(), this.imgPath);
                populateTable();
                // JOptionPane.showMessageDialog(null,"Data inserted");
                Alert alert = AlertFactory.createAlert(AlertFactory.INFORMATION, "Data inserted successfully");
                alert.showAndWait();
            } catch (InputException e) {
                // JOptionPane.showMessageDialog(null,e.getMessage());
                Alert alert = AlertFactory.createAlert(AlertFactory.WARNING, e.getMessage());
                alert.showAndWait();
            }
        }
    }

    public void onUpdateListener(ActionEvent actionEvent) {
        if(updateListner != null) {
            try {
                updateListner.update(idField.getText(), nameField.getText(), priceField.getText(), dateField.getValue().toString(), this.imgPath);
                //JOptionPane.showMessageDialog(null,"Data updated");
                populateTable();
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
    }

    public void onDeleteListener(ActionEvent actionEvent) {
        if(deleteListener !=null) {
            try {
                deleteListener.delete(idField.getText());
                populateTable();
                Alert alert = AlertFactory.createAlert(AlertFactory.INFORMATION, "Data at ID =" + idField.getText() + " deleted successfully");
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
    private void populateTable(){

        Platform.runLater(() -> {
            //System.out.println("LOCK : " + this.lock.isLocked());
            this.lock.tryLock();
            //System.out.println("LOCK : " + this.lock.isLocked());
            try {
                System.out.println("Update Tabeli");
                productList = FXCollections.observableArrayList(fetchDataListener.fetchData());
                productsTable.setItems(productList);
                productsTable.refresh();
            }finally {
                this.lock.unlock();
                //System.out.println("LOCK : " + this.lock.isLocked());
            }
        });
    }
    private void tableColumnsInit(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product,Float>("price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Product,String>("date"));
        productsTable.setItems(null);
    }

    public void onSelectListener(MouseEvent mouseEvent) {
        if(selectListener != null)
            selectListener.select();
    }

    public void onMoveListener(ActionEvent actionEvent){
        if(selectListener != null && moveListener != null) {
            moveListener.move(((Button) actionEvent.getSource()).getId());
            selectListener.select();
        }
    }
}
