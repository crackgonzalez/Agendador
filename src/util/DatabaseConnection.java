package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase DatabaseConnection.
 * Proporciona una conexión centralizada a la base de datos MySQL.
 * Maneja errores de conexión y configura la URL, usuario y contraseña.
 */
public class DatabaseConnection {

    // URL de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/agendador";
    
    // Usuario y contraseña de la base de datos
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Método estático para obtener una conexión a la base de datos.
     * 
     * @return Connection - Conexión activa a la base de datos.
     * @throws SQLException - Si ocurre un error al conectar.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Carga del controlador de MySQL (opcional en JDBC 4.0+)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Controlador de MySQL no encontrado.");
            e.printStackTrace();
        }

        // Retorna la conexión
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}