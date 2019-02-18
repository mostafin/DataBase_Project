package sample.model;

import java.time.LocalDate;

public class Product {
    private int id;
    private String name;
    private float price;
    private LocalDate date;
    private byte[] img;

    public Product(int id, String name, float price, LocalDate date, byte[] img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.img = img;
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

    public byte[] getImg() {
        return img;
    }
}
