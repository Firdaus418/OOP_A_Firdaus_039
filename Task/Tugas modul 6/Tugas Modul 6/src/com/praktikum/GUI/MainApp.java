import com.praktikum.GUI.LoginPane;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginPane loginPane = new LoginPane();
        loginPane.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
