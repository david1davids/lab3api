package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddTaskController implements Initializable{

    public static final ObservableList<String> data = FXCollections.observableArrayList();
    ArrayList<User> users = Get.returnUsers(Get.getContent("http://159.89.31.34/user"));
    ArrayList<String> selectedUsers = new ArrayList<String>();
    ArrayList<String> assign = new ArrayList<>();
    ArrayList<String> userAssign = new ArrayList<>();

    @FXML
    private Button add;

    @FXML
    private TextField taskname;

    @FXML
    private Label labelAssign;

    @FXML
    private Button back;

    @FXML
    private CheckBox completed;

    @FXML
    private ListView<String> list;

    public AddTaskController() throws IOException, JSONException {
    }

    @FXML
    void onClickAdd() {
        Post post = new Post();
        if (completed.isSelected()){
            try {
                post.sendData(post.addTask(taskname.getText(), true, assign), "http://159.89.31.34/tasks");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                post.sendData(post.addTask(taskname.getText(), false, assign), "http://159.89.31.34/tasks");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        taskname.clear();
        assign.clear();
        labelAssign.setText("");
        userAssign.clear();

    }

    @FXML
    void onClickBack() {
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
        data.clear();
        for (int i = 0; i < users.size(); i++){
            User user = users.get(i);
            data.add(user.firstname +" "+ user.lastname);
        }
        list.setItems(data);

        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String selectedUser = list.getSelectionModel().getSelectedItem();
                for (int j = 0; j < users.size(); j++){
                    if (selectedUser.contains(users.get(j).firstname + " " + users.get(j).lastname)){
                        assign.add(users.get(j).id);
                        userAssign.add(users.get(j).firstname + " " + users.get(j).lastname);
                    }
                }

                labelAssign.setText(userAssign.toString());
            }
        });
    }
}
