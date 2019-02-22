package sample.controller;

import javafx.stage.Stage;

import java.util.concurrent.locks.ReentrantLock;

public interface DataBaseConnection {
    void onStageShowing(Stage stage, ReentrantLock lock);

    void onStageClosing(Stage stage);
}
