package sample.controller;

import java.io.IOException;

public class InputException extends IOException {

    public InputException(String message){
        super(message);
    }
}
