package com.praktikum.GUI;

import com.praktikum.app.LoginSystem;
import com.praktikum.data.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class MahasiswaDashboard {

    private TableView<Item> reportedTable = new TableView<>();
    private TableView<Item> claimedTable = new TableView<>();

    public void show(Stage stage) {
        Label welcomeLabel = new Label("Selamat datang di Dashboard Mahasiswa");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label reportedLabel = new Label("Barang Masih Belum Diklaim (Reported):");
        Label claimedLabel = new Label("Barang Sudah Diklaim (Claimed):");

        // Buat kolom untuk reportedTable
        TableColumn<Item, String> reportedNameCol = new TableColumn<>("Barang");
        reportedNameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<Item, String> reportedDescCol = new TableColumn<>("Deskripsi");
        reportedDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Item, String> reportedLocCol = new TableColumn<>("Lokasi");
        reportedLocCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        reportedTable.getColumns().addAll(reportedNameCol, reportedLocCol, reportedDescCol);
        reportedTable.setPrefHeight(200);
        reportedDescCol.setPrefWidth(400);  // sesuaikan angka dengan kebutuhan


        // Buat kolom untuk claimedTable (pisah supaya tidak error dan data muncul benar)
        TableColumn<Item, String> claimedNameCol = new TableColumn<>("Barang");
        claimedNameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<Item, String> claimedDescCol = new TableColumn<>("Deskripsi");
        claimedDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Item, String> claimedLocCol = new TableColumn<>("Lokasi");
        claimedLocCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        claimedTable.getColumns().addAll(claimedNameCol, claimedLocCol, claimedDescCol);
        claimedTable.setPrefHeight(200);
        claimedDescCol.setPrefWidth(400);

        refreshTables();

        // Tombol
        Button reportButton = new Button("Lapor Barang");
        reportButton.setOnAction(e -> showReportDialog(stage));

        Button refreshButton = new Button("Refresh List");
        refreshButton.setOnAction(e -> refreshTables());

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            new LoginPane().show(new Stage());
            stage.close();});

        HBox buttonBox = new HBox(10, reportButton, refreshButton, logoutButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox reportedBox = new VBox(5, reportedLabel, reportedTable);
        VBox claimedBox = new VBox(5, claimedLabel, claimedTable);

        VBox root = new VBox(15, welcomeLabel, reportedBox, claimedBox, buttonBox);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
        stage.setTitle("Mahasiswa Dashboard");
        stage.show();
    }

    private void refreshTables() {
        ObservableList<Item> reportedItems = FXCollections.observableArrayList();
        ObservableList<Item> claimedItems = FXCollections.observableArrayList();

        for (Item item : LoginSystem.reportedItems) {
            if ("Reported".equalsIgnoreCase(item.getStatus())) {
                reportedItems.add(item);
            } else if ("Claimed".equalsIgnoreCase(item.getStatus())) {
                claimedItems.add(item);
            }
        }

        reportedTable.setItems(reportedItems);
        claimedTable.setItems(claimedItems);
    }

    private void showReportDialog(Stage ownerStage) {
        Stage dialog = new Stage();
        dialog.initOwner(ownerStage);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.setTitle("Lapor Barang Baru");

        TextField nameField = new TextField();
        nameField.setPromptText("Nama Barang");

        TextField descArea = new TextField();
        descArea.setPromptText("Deskripsi");

        TextField locField = new TextField();
        locField.setPromptText("Lokasi");

        Button submitButton = new Button("Laporkan");
        Button cancelButton = new Button("Batal");

        HBox buttonBox = new HBox(10, submitButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        grid.add(nameField, 1, 0);
        grid.add(descArea, 1, 1);
        grid.add(locField, 1, 2);

        grid.add(buttonBox, 1, 3);

        submitButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String desc = descArea.getText().trim();
            String loc = locField.getText().trim();

            if (name.isEmpty() || desc.isEmpty() || loc.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Semua field harus diisi.", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            Item newItem = new Item(name, desc, loc);
            newItem.setStatus("Reported");
            LoginSystem.reportedItems.add(newItem);

            refreshTables();
            dialog.close();
        });

        cancelButton.setOnAction(e -> dialog.close());

        Scene dialogScene = new Scene(grid, 450, 250);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }
}
