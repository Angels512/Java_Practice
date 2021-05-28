package co.edu.sena.HPlus_2242753.model.dao;

import co.edu.sena.HPlus_2242753.model.beans.Product;
import co.edu.sena.HPlus_2242753.model.beans.User;
import co.edu.sena.HPlus_2242753.model.connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDao
{
    public List<Product> searchProducts(String searchString)
    {
        Product product = null;
        List<Product> products = new ArrayList<>();

        try
        {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "select * from products where product_name like '%"+searchString+"%'";
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(sql);

            while(set.next()){
                product= new Product();
                product.setProductId(set.getInt("product_id"));
                product.setProductImgPath(set.getString("image_path"));
                product.setProductName(set.getString("product_name"));
                products.add(product);
            }
        } catch(SQLException exception){
            exception.printStackTrace();
        } // Fin Try Catch

        return products;
    } // Fin searchProducts


    public int registerUser(User user)
    {
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try
        {
            // Obtener conexion a BD
            Connection connection = DBConnection.getConnectionToDatabase();

            // Escribir insert query
            String insertQuery = "insert into users values(?,AES_ENCRYPT(?, 'AES'),?,?,?,?)";

            // Definir los parametros de la consulta (setter) PreparedStatement
            java.sql.PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getAge());
            statement.setString(6, user.getActivity());

            // Ejecutar el query
            rowsAffected = statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        } // Fin Try-Catch

        return rowsAffected;
    } // Fin registerUser


    public boolean validateUser(String username, String password)
    {
        boolean isValidUser = false;

        try
        {
            // Obtener conexion a BD
            Connection connection = DBConnection.getConnectionToDatabase();

            // Consulta SQL
            String sql = "SELECT * FROM users WHERE username=? AND password=AES_ENCRYPT(?, 'AES')";

            // Pasar los parametros necesarios para la consulta
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // Ejecutar la consulta y confirmar si el usuario existe
            ResultSet set = statement.executeQuery();

            while(set.next())
            {
                isValidUser = true;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return isValidUser;
    }

} // Fin Class ApplicationDao
