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
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TaskController implements Initializable{

    public static final ObservableList<String> data = FXCollections.observableArrayList();
    public static final ObservableList<String> dataTasks = FXCollections.observableArrayList();
    ArrayList<User> users = Get.returnUsers(Get.getContent("http://159.89.31.34/user"));
    ArrayList<Task> tasks = Get.returnTasks(Get.getContent("http://159.89.31.34/tasks"));
    ArrayList<JSONArray> assignList = new ArrayList();
    ArrayList<String> idAssign = new ArrayList<>();

    public static int Count = 10;
    public static Task currentSelectTask;

    @FXML
    private Button next;

    @FXML
    private Button add;

    @FXML
    private Button previous;

    @FXML
    private Button edit;

    @FXML
    private ListView<String> dataTask;

    @FXML
    private Button back;

    @FXML
    private ListView<String> list;

    @FXML
    private Button delete;

    public TaskController() throws IOException, JSONException {
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
            Task task = tasks.get(i);
            data.add(task.taskname);
        }
        list.setItems(data);
    }

    @FXML
    void onClickNext() {
        previous.setDisable(false);
        Count += 10;
        if (Count > tasks.size()){
            data.clear();
            for (int i = Count-10; i < tasks.size(); i++){
                Task task = tasks.get(i);
                data.add(task.taskname);
            }
            list.setItems(data);
            next.setDisable(true);
        }
        else {
            data.clear();
            for (int i = Count-10; i < Count; i++){
                Task task = tasks.get(i);
                data.add(task.taskname);
            }
            list.setItems(data);
        }
    }

    @FXML
    void onClickAdd() {
        Stage primaryStage = (Stage) add.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../sample/addTask.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        primaryStage.setTitle("Add Task");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void onClickDelete() throws IOException, JSONException {
        Delete delete = new Delete();
        delete.deleteData("http://159.89.31.34/tasks", currentSelectTask.id, currentSelectTask.etag);
        tasks = Get.returnTasks(Get.getContent("http://159.89.31.34/tasks"));
        data.clear();
        for (int i = Count-10; i < tasks.size(); i++){
            Task task = tasks.get(i);
            data.add(task.taskname);
        }
        list.setItems(data);
        dataTasks.clear();
    }

    @FXML
    void onClickEdit() {
        Stage primaryStage = (Stage) edit.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../sample/editTask.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        primaryStage.setTitle("Edit Task");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        previous.setDisable(true);
        data.clear();
        for (int i = 0; i < Count; i++){
            Task task = tasks.get(i);
            data.add(task.taskname);
            assignList.addAll(task.assign);
        }

        list.setItems(data);
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dataTasks.clear();
                String selectTask = list.getSelectionModel().getSelectedItem().toString();
                for (int i = 0; i < tasks.size(); i++){
                    String s = tasks.get(i).taskname;
                    if (selectTask.contains(s)){
                        currentSelectTask = tasks.get(i);
                        for (int j = 0; j < users.size(); j++){
                            for (int k = 0; k < currentSelectTask.assign.get(0).length(); k++){
                                try {
                                    if (currentSelectTask.assign.get(0).get(k).toString().contains(users.get(j).id)){
                                        idAssign.add(users.get(j).firstname + " " + users.get(j).lastname);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        dataTasks.add("Taskname:        " + tasks.get(i).taskname + "\n" +
                                "Created:           " + tasks.get(i).created+ "\n" +
                                "Completed:       " + tasks.get(i).completed + "\n" +
                                "Task ID:            " + tasks.get(i).id+ "\n" +
                                "Etag:                 " + tasks.get(i).etag + "\n" +
                                "Assign-to:        "  + getAssignUser(idAssign) );
                        }
                        dataTask.setItems(dataTasks);
                        idAssign.clear();
                }
            }
        });
    }
    public String getAssignUser(ArrayList<String> list){
        String s = "";
        for (int i = 0; i < list.size(); i++){
            s = s + " " +list.get(i) + "\n                        ";
        }
        return s;
    }
}
