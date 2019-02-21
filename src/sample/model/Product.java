package sample.model;

import java.time.LocalDate;


public class Product {
    private int id;
    private String name;
    private float price;
    private LocalDate date;
    private String imgPath;

    public Product(){}

    public Product(String name, float price, LocalDate date, String imgPath) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.imgPath = imgPath;
    }

    public Product(int id , String name, float price, LocalDate date, String imgPath) {
        this(name,price,date,imgPath);
        this.id = id;
    }

    public Product(int id, String name, float price, LocalDate date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    float getPrice() {
        return price;
    }

    LocalDate getDate() {
        return date;
    }

    String getImgPath() {
        return imgPath;
    }
}
