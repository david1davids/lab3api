package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserController implements Initializable{

    public static final ObservableList<String> data = FXCollections.observableArrayList();
    public static final ObservableList<String> dataUsers = FXCollections.observableArrayList();
    ArrayList <User> users = Get.returnUsers(Get.getContent("http://159.89.31.34/user"));
    public static User currentSelectUser;

    public static int Count = 10;

    public User getCurrentSelectUser() {
        return currentSelectUser;
    }

    @FXML
    private Button next;

    @FXML
    private Button previous;

    @FXML
    private Button back;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private ListView<String> list;

    @FXML
    private Button add;

    @FXML
    private ListView<String> dataUser;

    public UserController() throws IOException, JSONException {
    }

    @FXML
    void onClickBack() {
        Stage primaryStage = (Stage) back.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../sample/sample.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void onClickPrevious() {
        next.setDisable(false);
        Count -= 10;
        if (Count == 10){
            previous.setDisable(true);
        }
        data.clear();
        for (int i = Count -10; i < Count; i++){
            User user = users.get(i);
            data.add(user.firstname +" "+ user.lastname);
        }
        list.setItems(data);

    }

    @FXML
    void onClickNext() {
        previous.setDisable(false);
        Count += 10;
        if (Count > users.size()){
            data.clear();
            for (int i = Count-10; i < users.size(); i++){
                User user = users.get(i);
                data.add(user.firstname +" "+ user.lastname);
            }
            list.setItems(data);
            next.setDisable(true);
        }
        else {
            data.clear();
            for (int i = Count-10; i < Count; i++){
                User user = users.get(i);
                data.add(user.firstname +" "+ user.lastname);
            }
            list.setItems(data);
        }
    }

    @FXML
    void onClickAdd() {
        Stage primaryStage = (Stage) add.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../sample/addUser.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        primaryStage.setTitle("Add User");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void onClickDelete() throws IOException, JSONException {
        Delete delete = new Delete();
        delete.deleteData("http://159.89.31.34/user", currentSelectUser.id, currentSelectUser.etag);
        users = Get.returnUsers(Get.getContent("http://159.89.31.34/user"));
        data.clear();
        for (int i = Count-10; i < users.size(); i++){
            User user1 = users.get(i);
            data.add(user1.firstname +" "+ user1.lastname);
        }
        list.setItems(data);
        dataUsers.clear();
    }

    @FXML
    void onClickEdit() {
        Stage primaryStage = (Stage) edit.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../sample/editUser.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        primaryStage.setTitle("Edit User");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        previous.setDisable(true);
        data.clear();
        for (int i = 0; i < Count; i++){
            User user = users.get(i);
            data.add(user.firstname +" "+ user.lastname);
        }
        list.setItems(data);

        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dataUsers.clear();
                String selectUser = list.getSelectionModel().getSelectedItem().toString();
                for (int i = 0; i < users.size(); i++){
                    String s = users.get(i).firstname + " " + users.get(i).lastname;
                    if (selectUser.contains(s)){
                        currentSelectUser = users.get(i);
                        dataUsers.add("Firstname:        " + users.get(i).firstname + "\n" +
                                "Lastname:        " + users.get(i).lastname+ "\n" +
                                "Phone:            " + users.get(i).phone + "\n" +
                                "User ID:           " + users.get(i).id+ "\n" +
                                "Etag:               " + users.get(i).etag);
                        dataUser.setItems(dataUsers);
                    }
                }
            }
        });
    }
}
