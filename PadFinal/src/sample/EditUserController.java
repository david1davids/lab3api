package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditUserController implements Initializable{
    UserController userController = new UserController();
    User user = userController.currentSelectUser;

    @FXML
    private TextField firstname;

    @FXML
    private TextField phone;

    @FXML
    private Button edit;

    @FXML
    private Button back;

    @FXML
    private TextField lastname;

    public EditUserController() throws IOException, JSONException {
    }

    @FXML
    void onClickEdit() throws IOException, JSONException {
        Patch patch = new Patch();
        patch.editDataUser(user.id, user.etag, firstname.getText(), lastname.getText(), phone.getText());
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        firstname.setText(user.firstname);
        lastname.setText(user.lastname);
        phone.setText(user.phone);

    }
}
