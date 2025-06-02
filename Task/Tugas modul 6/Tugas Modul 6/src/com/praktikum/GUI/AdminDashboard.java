// File: com/praktikum/GUI/AdminDashboard.java
package com.praktikum.GUI;

import com.praktikum.app.LoginSystem;
import com.praktikum.data.Item;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Iterator;

public class AdminDashboard {
    public void show(Stage stage) {
        Button manageItemsBtn = new Button("Kelola Barang");
        Button manageUsersBtn = new Button("Kelola Pengguna");
        Button logoutBtn = new Button("Logout");

        manageItemsBtn.setOnAction(e -> showManageItems(stage));
        manageUsersBtn.setOnAction(e -> showManageUsers(stage));
        logoutBtn.setOnAction(e -> {
            new LoginPane().show(new Stage());
            stage.close();
        });

        VBox root = new VBox(10, manageItemsBtn, manageUsersBtn, logoutBtn);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-alignment: center");

        stage.setScene(new Scene(root, 300, 200));
        stage.setTitle("Admin Dashboard");
        stage.show();
    }

    private void showManageItems(Stage stage) {
        TableView<Item> table = new TableView<>();
        ObservableList<Item> itemList = FXCollections.observableArrayList(LoginSystem.reportedItems);

        TableColumn<Item, String> nameCol = new TableColumn<>("Nama Barang");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<Item, String> descCol = new TableColumn<>("Deskripsi");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Item, String> locCol = new TableColumn<>("Lokasi");
        locCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<Item, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.setItems(itemList);
        table.getColumns().addAll(nameCol, descCol, locCol, statusCol);

        Button claimBtn = new Button("Tandai Diambil");
        Button backBtn = new Button("Kembali");

        claimBtn.setOnAction(e -> {
            Item selected = table.getSelectionModel().getSelectedItem();
            if (selected != null && "Reported".equals(selected.getStatus())) {
                selected.setStatus("Claimed");
                table.refresh();
            }
        });

        backBtn.setOnAction(e -> show(stage));

        VBox layout = new VBox(10, table, claimBtn, backBtn);
        layout.setPadding(new Insets(20));

        stage.setScene(new Scene(layout, 600, 400));
    }

    private void showManageUsers(Stage stage) {
        ListView<String> userListView = new ListView<>();
        refreshUserList(userListView);

        TextField nameField = new TextField();
        nameField.setPromptText("Nama Mahasiswa");
        TextField nimField = new TextField();
        nimField.setPromptText("NIM");

        Button addBtn = new Button("Tambah");
        Button delBtn = new Button("Hapus");
        Button backBtn = new Button("Kembali");

        addBtn.setOnAction(e -> {
            String name = nameField.getText();
            String nim = nimField.getText();
            if (!name.isEmpty() && !nim.isEmpty()) {
                LoginSystem.userList.add(new Mahasiswa(name, nim));
                refreshUserList(userListView);
                nameField.clear();
                nimField.clear();
            }
        });

        delBtn.setOnAction(e -> {
            String selected = userListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                String nim = selected.split(" - ")[1];
                Iterator<User> iter = LoginSystem.userList.iterator();
                while (iter.hasNext()) {
                    User u = iter.next();
                    if (u instanceof Mahasiswa m && m.getNim().equals(nim)) {
                        iter.remove();
                        break;
                    }
                }
                refreshUserList(userListView);
            }
        });

        backBtn.setOnAction(e -> show(stage));

        VBox layout = new VBox(10, userListView, nameField, nimField, addBtn, delBtn, backBtn);
        layout.setPadding(new Insets(20));

        stage.setScene(new Scene(layout, 400, 500));
    }

    private void refreshUserList(ListView<String> listView) {
        ObservableList<String> data = FXCollections.observableArrayList();
        for (User user : LoginSystem.userList) {
            if (user instanceof Mahasiswa m) {
                data.add(m.getName() + " - " + m.getNim());
            }
        }
        listView.setItems(data);
    }
}
