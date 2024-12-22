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
            
            // Crear el DAO para manejar usuarios
            UserDAO userDAO = new UserDAO(conn);
            
            // Crear un nuevo usuario y guardarlo en la base de datos
            User newUser = new User();
            newUser.setName("Juan Pérez");
            newUser.setEmail("juan.perez@example.com");
            newUser.setPassword("123456");

            boolean isInserted = userDAO.insertUser(newUser);
            if (isInserted) {
                System.out.println("Usuario insertado exitosamente.");
            } else {
                System.out.println("Error al insertar usuario.");
            }
            
            // Obtener todos los usuarios y mostrarlos
            List<User> users = userDAO.getAllUsers();
            if (users.isEmpty()) {
                System.out.println("No hay usuarios en la base de datos.");
            } else {
                System.out.println("Usuarios en la base de datos:");
                for (User user : users) {
                    System.out.println(user);
                }
            }
            
            
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos:");
            e.printStackTrace();
        }
    }
}
