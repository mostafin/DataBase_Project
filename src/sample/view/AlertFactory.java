package sample.view;

import javafx.scene.control.Alert;

 abstract class AlertFactory {

    static final int INFORMATION = 0;
    static final int WARNING = 1;
    static final int ERROR = 2;

    static Alert createAlert(int alertType,String message){
        switch (alertType){
            case 0:
                return new Alert(Alert.AlertType.INFORMATION,message);
            case 1:
                return new Alert(Alert.AlertType.WARNING,message);
            case 2:
                return new Alert(Alert.AlertType.ERROR,message);
        }
        return null;
    }
}
