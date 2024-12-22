package model;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private int userId;
    private LocalDateTime dateTime;
    private String description;
    private String status;

    // Constructor vacío
    public Appointment() {}

    // Constructor con parámetros
    public Appointment(int userId, LocalDateTime dateTime, String description, String status) {
        this.userId = userId;
        this.dateTime = dateTime;
        this.description = description;
        this.status = status;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", userId=" + userId +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
