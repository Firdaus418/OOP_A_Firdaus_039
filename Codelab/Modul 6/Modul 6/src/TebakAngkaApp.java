import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Random;

public class TebakAngkaApp extends Application {
    private int targetNumber;
    private int attemptCount;
    private Random random = new Random();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // UI Elements
        TextField inputField = new TextField();
        Button guessButton = new Button("Coba Tebak!");
        Label feedbackLabel = new Label("Masukkan angka dari 1 sampai 100.");
        Label attemptLabel = new Label("Jumlah percobaan: 0");

        // Layouts
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        HBox inputBox = new HBox(10, new Label("Tebakan Anda:"), inputField);
        root.getChildren().addAll(inputBox, guessButton, feedbackLabel, attemptLabel);

        // Game logic initialization
        generateRandomNumber();

        // Event handler
        guessButton.setOnAction(e -> {
            if (guessButton.getText().equals("Main Lagi")) {
                generateRandomNumber();
                feedbackLabel.setText("Masukkan angka dari 1 sampai 100.");
                attemptCount = 0;
                attemptLabel.setText("Jumlah percobaan: 0");
                guessButton.setText("Coba Tebak!");
                inputField.clear();
                return;
            }

            String inputText = inputField.getText().trim();
            try {
                int guess = Integer.parseInt(inputText);
                attemptCount++;
                attemptLabel.setText("Jumlah percobaan: " + attemptCount);

                if (guess < targetNumber) {
                    feedbackLabel.setText("Terlalu kecil!");
                } else if (guess > targetNumber) {
                    feedbackLabel.setText("Terlalu besar!");
                } else {
                    feedbackLabel.setText("Tebakan benar!");
                    guessButton.setText("Main Lagi");
                }

            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Masukkan angka valid!");
            }

            inputField.clear();
        });

        primaryStage.setTitle("Game Tebak Angka");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

    private void generateRandomNumber() {
        targetNumber = random.nextInt(100) + 1;
        attemptCount = 0;
    }
}
