package sample.model;


import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleFloatProperty  price;
    private SimpleStringProperty date;
    private SimpleStringProperty imgPath;

    public Product(){}

    public Product(String name, float price, String date, String imgPath) {
        this.name =  new SimpleStringProperty(name);
        this.price = new SimpleFloatProperty(price);
        this.date = new SimpleStringProperty(date);
        this.imgPath = new SimpleStringProperty(imgPath);
    }

    public Product(int id , String name,float price, String date, String imgPath) {
        this(name,price,date,imgPath);
        this.id = new SimpleIntegerProperty(id);
    }

    public Product(int id, String name, float price, String date) {
        this.id = new SimpleIntegerProperty(id);
        this.name =  new SimpleStringProperty(name);
        this.price = new SimpleFloatProperty(price);
        this.date = new SimpleStringProperty(date);

    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public float getPrice() {
        return price.get();
    }

    public SimpleFloatProperty priceProperty() {
        return price;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public String getImgPath() {
        return imgPath.get();
    }

    public SimpleStringProperty imgPathProperty() {
        return imgPath;
    }
}
