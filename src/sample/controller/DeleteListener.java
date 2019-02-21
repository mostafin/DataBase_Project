package sample.controller;

public interface DeleteListener {
    int delete(String id ) throws IdNotFoundException, InputException;
    boolean deleteValidate(String id) throws InputException;
}
