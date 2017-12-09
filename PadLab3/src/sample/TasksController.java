package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TasksController implements Initializable{

    public static final ObservableList<String> data = FXCollections.observableArrayList();

    @FXML
    private Button add;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private ListView<String> list;

    @FXML
    void onClickAdd() {
        Stage primaryStage = (Stage) add.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../sample/addtask.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        primaryStage.setTitle("User");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void onClickDelete() {

    }

    @FXML
    void onClickEdit() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GetTask task = new GetTask();
        data.addAll(task.getTask());
        list.setItems(data);
    }
}
