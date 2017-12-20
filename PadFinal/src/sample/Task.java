package sample;

import org.json.JSONArray;

import java.util.ArrayList;

public class Task {

    String taskname;
    boolean completed;
    String id;
    String etag;
    ArrayList<JSONArray> assign;
    String created;


    public Task(){}


    @Override
    public String toString() {
        return "Task{" +
                "taskname='" + taskname + '\'' +
                ", completed=" + completed +
                ", id='" + id + '\'' +
                ", etag='" + etag + '\'' +
                ", assign=" + assign +
                ", created='" + created + '\'' +
                '}';
    }

    public Task(String taskname, boolean completed, String id, String etag, ArrayList<JSONArray> assign, String created) {
        this.taskname = taskname;
        this.completed = completed;
        this.id = id;
        this.etag = etag;
        this.assign = assign;
        this.created = created;
    }
}
