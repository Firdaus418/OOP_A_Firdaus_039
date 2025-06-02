package com.praktikum.GUI;

import com.praktikum.users.Admin;
import com.praktikum.users.User;
import com.praktikum.app.LoginSystem;
import com.praktikum.users.Mahasiswa;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPane {
    public void show(Stage stage) {
        TextField usernameField = new TextField();
        usernameField.setPromptText("Masukkan username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Masukkan password");

        Label message = new Label();
        Button loginButton = new Button("Login");

        loginButton.setOnAction(e -> {
            String user = usernameField.getText();
            String pass = passwordField.getText();

            if (user.equals("admin") && pass.equals("111")) {
                new AdminDashboard().show(new Stage());
                stage.close();
            } else if (isValidMahasiswaLogin(user, pass)) {
                new MahasiswaDashboard().show(new Stage());
                stage.close();
            } else {
                message.setText("Login gagal");
            }
        });

        VBox root = new VBox(10, usernameField, passwordField, loginButton, message);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        stage.setScene(new Scene(root, 300, 250));
        stage.setTitle("Login");
        stage.show();
    }

    private boolean isValidMahasiswaLogin(String username, String password) {
        for (User u : LoginSystem.userList) {
            if (u instanceof Mahasiswa) {
                Mahasiswa m = (Mahasiswa) u;
                if (m.getName().equalsIgnoreCase(username) && m.getNim().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}