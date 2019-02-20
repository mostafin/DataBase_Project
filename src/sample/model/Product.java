package sample.model;

import java.time.LocalDate;


public class Product {
    private int id;
    private String name;
    private float price;
    private LocalDate date;
    private String imgPath;

    public Product(){};
    public Product(String name, float price, LocalDate date, String imgPath) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.imgPath = imgPath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getImgPath() {
        return imgPath;
    }
}
