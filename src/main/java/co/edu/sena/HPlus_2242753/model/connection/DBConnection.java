package co.edu.sena.HPlus_2242753.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("ALL")
public class DBConnection
{
    public static Connection getConnectionToDatabase()
    {
        Connection connection = null;

        try {
            // Cargar la clase Driver
            /*Class.forName("com.mysql.jdbc.Driver");
            System.out.println("MySQL JDBC Driver registrado");*/

            // Obtener DriverManager
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hplus","root", "");
        } catch (SQLException e) {
            System.out.println("Fallo la conexion. Verifique");
            e.printStackTrace();
        } /*catch (ClassNotFoundException e){
            System.out.println("No se encuentra la clase driver");
            e.printStackTrace();
        }*/

        if (connection != null)
        {
            System.out.println("Conexion exitosa");
        }

        return connection;
    }
}
