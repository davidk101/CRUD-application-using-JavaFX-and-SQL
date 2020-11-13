package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Driver function
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("David Kumar's CRUD application");
        primaryStage.setScene(new Scene(root, 717, 456));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
