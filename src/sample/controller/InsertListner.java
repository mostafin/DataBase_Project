package sample.controller;

import java.time.LocalDate;

public interface InsertListner {
    void Insert(String name, String price, LocalDate date, String imgPath) throws InputException;
    boolean insertValidate(String name ,String price, LocalDate date, String imgPath) throws InputException;
}
