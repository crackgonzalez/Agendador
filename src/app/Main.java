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
            
            // Código dentro del main()
            UserDAO userDAO = new UserDAO(conn);

            // Registrar un nuevo usuario
            User newUser = new User();
            newUser.setUsername("admin");
            newUser.setPassword("12345");
            newUser.setRole("admin");

            boolean isRegistered = userDAO.registerUser(newUser);
            if (isRegistered) {
                System.out.println("Usuario registrado exitosamente.");
            } else {
                System.out.println("Error al registrar el usuario.");
            }

            // Autenticar un usuario
            User authenticatedUser = userDAO.authenticate("admin", "12345");
            if (authenticatedUser != null) {
                System.out.println("Autenticación exitosa: " + authenticatedUser);
            } else {
                System.out.println("Credenciales incorrectas.");
            }


            
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos:");
            e.printStackTrace();
        }
    }
}
