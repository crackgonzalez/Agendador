package models;

import java.util.Date;

/**
 * Representa una cita en el sistema.
 */
public class Appointment {
    private int id;             // Identificador único de la cita
    private Date date;          // Fecha y hora de la cita
    private String clientName;  // Nombre del cliente
    private String description; // Descripción de la cita
    private int userId;         // ID del usuario asociado a la cita

    // Constructor vacío
    public Appointment() {
    }

    // Constructor con parámetros
    public Appointment(int id, Date date, String clientName, String description, int userId) {
        this.id = id;
        this.date = date;
        this.clientName = clientName;
        this.description = description;
        this.userId = userId;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Método toString
    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + date +
                ", clientName='" + clientName + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                '}';
    }
}
