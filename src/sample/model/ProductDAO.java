package sample.model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
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

                p.setString(1, product.getName());
                p.setFloat(2, product.getPrice());

                //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                //p.setString(3, dateFormat.format(product.getDate()));
                p.setString(3, product.getDate());

                System.out.println(product.getImgPath());

                InputStream img = new FileInputStream(new File(product.getImgPath()));
                p.setBlob(4, img);
                p.execute();
                p.close();

            }

            public Product get(int id) {
                return null;
            }

            public List<Product> getProducts() throws SQLException {
                ArrayList<Product> productArrayList = new ArrayList<>();
                Connection conn = Database.getInstance().getConnection();
                String query = "SELECT * FROM products";
                Statement statement;
                if(conn != null) {
                     statement = conn.createStatement();
                }else{
                    throw new SQLException("conn is null");
                }

                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    productArrayList.add(new Product(resultSet.getInt("id"),resultSet.getString("name")
                           ,resultSet.getFloat("price"),resultSet.getString("add_date")
                            , Base64.getEncoder().encodeToString(resultSet.getBytes("image"))));
                }
                return productArrayList;
            }

            public int updateWithImg(Product product) throws SQLException, FileNotFoundException {

                Connection conn = Database.getInstance().getConnection();
                PreparedStatement p = conn.prepareStatement("UPDATE products SET name = ?, price = ?"
                        + ", add_date = ?,image = ? WHERE id = ?");

                p.setString(1, product.getName());
                p.setFloat(2, product.getPrice());

                //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                p.setString(3, product.getDate());

                InputStream img = new FileInputStream(new File(product.getImgPath()));

                p.setBlob(4, img);
                p.setInt(5, product.getId());
                int result = p.executeUpdate();
                p.close();
                return result;
            }

            public int updateWithoutImg(Product product) throws SQLException {
                Connection conn = Database.getInstance().getConnection();
                PreparedStatement p = conn.prepareStatement("UPDATE products SET name = ?, price = ?"
                        + ", add_date = ? WHERE id = ?");

                p.setString(1, product.getName());
                p.setFloat(2, product.getPrice());

                //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                p.setString(3, product.getDate());

                p.setInt(4, product.getId());
                int result = p.executeUpdate();
                p.close();
                return  result;
            }

            public int delete(int id) throws SQLException {
                Connection conn = Database.getInstance().getConnection();
                PreparedStatement p = conn.prepareStatement("DELETE FROM products WHERE id = ?");
                p.setInt(1,id);
                int result = p.executeUpdate();
                p.close();
                return  result;
            }
        }
