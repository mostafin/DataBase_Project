package sample.controller;

import java.time.LocalDate;

public interface UpdateListner {

    void Update(String id, String name, String price, LocalDate date, String imgPath) throws InputException, IdNotFoundException;

    boolean UpdateValidate(String id , String name , String price, LocalDate date, String imgPath) throws InputException;
}
