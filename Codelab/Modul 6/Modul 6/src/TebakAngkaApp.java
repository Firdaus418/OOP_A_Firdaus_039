import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;


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
        inputField.setPromptText("Masukkan Angka Disini");
        inputField.setStyle("-fx-prompt-text-fill: gray;");
        Label instructionLabel = new Label("\uD83C\uDFAF Tebak Angka 1 - 100.");
        instructionLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: blue; -fx-font-weight: bold;");
        Label feedbackLabel = new Label("Masukkan Tebakanmu!");
        Button guessButton = new Button("\uD83C\uDFB2 Coba Tebak!");
        guessButton.setStyle("-fx-background-color: Green; -fx-text-fill: white;");

        Label attemptLabel = new Label("Jumlah percobaan: 0");

        // Layouts
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: lightblue;");
        root.setPadding(new Insets(15));
        HBox inputBox = new HBox(10, inputField, guessButton);
        inputBox.setAlignment(Pos.CENTER);
        root.getChildren().addAll(instructionLabel, feedbackLabel, inputBox, attemptLabel);

        // Game logic initialization
        generateRandomNumber();

        // Event handler
        guessButton.setOnAction(e -> {
            if (guessButton.getText().equals("Main Lagi")) {
                generateRandomNumber();
                feedbackLabel.setText("Masukkan Tebakanmu!");
                attemptCount = 0;
                attemptLabel.setText("Jumlah percobaan: 0");
                guessButton.setText("\uD83C\uDFB2 Coba Tebak!");
                guessButton.setStyle("-fx-background-color: Green; -fx-text-fill: white;");
                inputField.clear();
                return;
            }

            String inputText = inputField.getText().trim();
            try {
                int guess = Integer.parseInt(inputText);
                attemptCount++;
                attemptLabel.setText("Jumlah percobaan: " + attemptCount);

                if (guess < targetNumber) {
                    feedbackLabel.setText("↓ Terlalu kecil!");
                    feedbackLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: orange;");
                } else if (guess > targetNumber) {
                    feedbackLabel.setText("↑ Terlalu besar!");
                    feedbackLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: orange;");
                } else {
                    feedbackLabel.setText("✓ Tebakan benar!");
                    feedbackLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: green;");
                    guessButton.setText("↻ Main Lagi");
                    guessButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");

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
