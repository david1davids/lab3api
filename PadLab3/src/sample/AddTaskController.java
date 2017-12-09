package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddTaskController {

    ArrayList arrayList = new ArrayList();

    @FXML
    private Button add;

    @FXML
    private TextField taskname;

    @FXML
    private Button back;

    @FXML
    private CheckBox completed;

    @FXML
    private TextField assign;

    @FXML
    void onClickAdd() {
        AddTask addTask = new AddTask();
        if (completed.isSelected()){
            arrayList.add(assign.getText());
            addTask.add(taskname.getText(), true, arrayList);
        }
        else {
            arrayList.add(assign.getText());
            addTask.add(taskname.getText(), false, arrayList);
        }
    }

    @FXML
    void onClickBack() {
        Stage primaryStage = (Stage) back.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../sample/tasks.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        primaryStage.setTitle("Tasks");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
