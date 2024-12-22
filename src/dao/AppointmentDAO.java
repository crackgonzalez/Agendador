package dao;

import models.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para la clase Appointment.
 * Realiza operaciones de base de datos relacionadas con las citas.
 */
public class AppointmentDAO {

    private Connection connection;

    // Constructor que recibe la conexión
    public AppointmentDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar una cita
    public boolean insertAppointment(Appointment appointment) {
        String query = "INSERT INTO appointments (date, client_name, description, user_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setTimestamp(1, new Timestamp(appointment.getDate().getTime()));
            stmt.setString(2, appointment.getClientName());
            stmt.setString(3, appointment.getDescription());
            stmt.setInt(4, appointment.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todas las citas
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("id"),
                        rs.getTimestamp("date"),
                        rs.getString("client_name"),
                        rs.getString("description"),
                        rs.getInt("user_id")
                );
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
}
