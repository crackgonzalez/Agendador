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
    
    // Método para filtrar citas por usuario o estado
    public List<Appointment> getAppointmentsByFilter(Integer userId, String status) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE 1=1";

        // Construcción dinámica de la consulta
        if (userId != null) {
            sql += " AND user_id = ?";
        }
        if (status != null && !status.isEmpty()) {
            sql += " AND status = ?";
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int paramIndex = 1;
            if (userId != null) {
                statement.setInt(paramIndex++, userId);
            }
            if (status != null && !status.isEmpty()) {
                statement.setString(paramIndex, status);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setId(resultSet.getInt("id"));
                    appointment.setUserId(resultSet.getInt("user_id"));
                    appointment.setDateTime(resultSet.getTimestamp("date_time").toLocalDateTime());
                    appointment.setDescription(resultSet.getString("description"));
                    appointment.setStatus(resultSet.getString("status"));

                    appointments.add(appointment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
    
    // Método para actualizar el estado de una cita con validación
    public boolean updateAppointmentStatus(int appointmentId, String newStatus) {
        // Lista de estados válidos
        List<String> validStatuses = Arrays.asList("pending", "confirmed", "cancelled");

        // Validar que el nuevo estado sea válido
        if (!validStatuses.contains(newStatus)) {
            System.err.println("Estado inválido: " + newStatus);
            return false;
        }

        String sql = "UPDATE appointments SET status = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newStatus);
            statement.setInt(2, appointmentId);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
