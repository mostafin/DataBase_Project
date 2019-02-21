package sample.controller;


public class IdNotFoundException extends Exception {
    IdNotFoundException(String reason) {
        super(reason);
    }
}
