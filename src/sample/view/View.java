package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class View {
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

    private Stage stage;
    public View(){
        System.out.println("view konstrutor");
        buttonsGraphicsInit();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void buttonsGraphicsInit(){
        btnInsert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/insert.png"),30,30,true,true)));
        btnUpdate.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/update.png"),30,30,true,true)));
        btnDelete.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/delete.png"),30,30,true,true)));

        btnFirst.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/last.png"),30,30,true,true)));
        btnNext.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/next.png"),30,30,true,true)));
        btnPrev.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/next.png"),30,30,true,true)));
        btnLast.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/last.png"),30,30,true,true)));

    }
}
