package app;

import dao.UserDAO;
import models.User;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

/**
 * Clase Main para probar la conexión a la base de datos.
 */
public class Main {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("¡Conexión exitosa a la base de datos!");
            }
            
           
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos:");
            e.printStackTrace();
        }
    }
}
