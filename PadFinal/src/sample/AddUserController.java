package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONException;

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
    void onClickAdd(ActionEvent event) {

        Post post = new Post();
        try {
            post.sendData(post.addUser(firstname.getText(), lastname.getText(), phone.getText()), "http://159.89.31.34/user");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        firstname.clear();
        lastname.clear();
        phone.clear();
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
        primaryStage.setTitle("Users");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
