package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditTaskController implements Initializable{


    TaskController taskController = new TaskController();
    Task task = taskController.currentSelectTask;

    @FXML
    private Button edit;

    @FXML
    private TextField taskname;

    @FXML
    private Button back;

    @FXML
    private CheckBox completed;

    @FXML
    private Label labelAssign;

    public EditTaskController() throws IOException, JSONException {
    }

    @FXML
    void onClickEdit(ActionEvent event) throws IOException, JSONException {
        Patch patch = new Patch();
        if (completed.isSelected()){
            patch.editDataTask(task.id, task.etag, taskname.getText(),true);
        }
        else {
            patch.editDataTask(task.id, task.etag, taskname.getText(),false);

        }
    }

    @FXML
    void onClickBack(ActionEvent event) {
        Stage primaryStage = (Stage) back.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../sample/task.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        primaryStage.setTitle("Task list");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(task);
        taskname.setText(task.taskname);
    }
}
