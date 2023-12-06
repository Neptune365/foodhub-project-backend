package com.project.FoodHub.email;

public interface EmailSender {

    void send(String to, String email, String subject);
    void enviarConfirmacionCuenta(String to, String nombre, String link);
}
