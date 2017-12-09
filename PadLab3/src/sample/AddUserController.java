package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddUserController {
    @FXML
    private Button add;

    @FXML
    private TextField firstname;

    @FXML
    private TextField phone;

    @FXML
    private Button back;

    @FXML
    private TextField lastname;

    @FXML
    void onClickAdd() {
        AddUser addUser = new AddUser();
        addUser.add(firstname.getText(), lastname.getText(), phone.getText());
    }

    @FXML
    void onClickBack() {
        Stage primaryStage = (Stage) back.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../sample/users.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        primaryStage.setTitle("User");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
