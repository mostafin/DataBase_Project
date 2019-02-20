package sample.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/*
 * One DAO class person table
 * CRUD.
 */
public class ProductDAO {

    public void add(Product product) throws SQLException, FileNotFoundException {
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement p = conn.prepareStatement("INSERT INTO products(name,price,add_date,image)"
                + "values(?,?,?,?)");

        p.setString(1,product.getName());
        p.setFloat(2,product.getPrice());

        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //p.setString(3, dateFormat.format(product.getDate()));
        p.setString(3,product.getDate().toString());

        //System.out.println(product.getImgPath());

        InputStream img = new FileInputStream(new File(product.getImgPath()));
        p.setBlob(4,img);
        p.execute();

    };
    public Product get(int id){
        return null;
    }
    public List<Product> getProducts(){
        return  null;
    }

    public void update(Product product){

    }

    public void delete(int id){}

}
