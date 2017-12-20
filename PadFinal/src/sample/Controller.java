package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Button task;

    @FXML
    private Button user;

    @FXML
    void onClickUser() {
        Stage primaryStage = (Stage) user.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../sample/users.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        primaryStage.setTitle("User List");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void onClickTask() {
        Stage primaryStage = (Stage) task.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../sample/task.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        primaryStage.setTitle("Task List");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
