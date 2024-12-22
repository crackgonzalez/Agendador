package dao;

import model.Appointment;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointmentDAO {
    private Connection connection;

    public AppointmentDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    // Método para crear una nueva cita
    public boolean createAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (user_id, date_time, description, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointment.getUserId());
            statement.setObject(2, appointment.getDateTime());
            statement.setString(3, appointment.getDescription());
            statement.setString(4, appointment.getStatus());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
