package sample.controller;

public interface InsertListner {
    void Insert(String name, String price, String date, String imgPath) throws InputException;
    boolean insertValidate(String name , String price, String date, String imgPath) throws InputException;
}
