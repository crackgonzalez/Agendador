package dao;

import model.Appointment;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    
    // Método para listar todas las citas
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(resultSet.getInt("id"));
                appointment.setUserId(resultSet.getInt("user_id"));
                appointment.setDateTime(resultSet.getTimestamp("date_time").toLocalDateTime());
                appointment.setDescription(resultSet.getString("description"));
                appointment.setStatus(resultSet.getString("status"));

                appointments.add(appointment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
}
