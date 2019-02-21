package sample.controller;

public interface UpdateListner {

    void update(String id, String name, String price, String date, String imgPath) throws InputException, IdNotFoundException;

    boolean updateValidate(String id , String name , String price, String date, String imgPath) throws InputException;
}
