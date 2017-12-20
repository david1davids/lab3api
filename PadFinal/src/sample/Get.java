package sample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Get {
    public static String getContent(String urlContent) throws IOException {
        String jsonText = null;
        URL url = new URL(urlContent);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            jsonText = output;
        }

        conn.disconnect();
        return jsonText;
    }

    public static ArrayList returnUsers(String jsonText) throws JSONException {

        ArrayList<User> users = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonText);

        JSONArray values = jsonObject.getJSONArray("_items");

        for (int i = 0; i < values.length(); i++){
            JSONObject _updated = values.getJSONObject(i);
            User user = new User();

            String firstname = _updated.getString("firstname");
            String lastname = _updated.getString("lastname");
            String phone = _updated.getString("phone");
            String etag = _updated.getString("_etag");
            String id = _updated.getString("_id");

            user.firstname = firstname;
            user.lastname = lastname;
            user.phone = phone;
            user.id = id;
            user.etag = etag;

            users.add(user);
        }
        return users;
    }

    public static ArrayList returnTasks(String jsonText) throws JSONException {

        ArrayList<Task> tasks = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonText);

        JSONArray values = jsonObject.getJSONArray("_items");

        for (int i = 0; i < values.length(); i++){
            JSONObject _updated = values.getJSONObject(i);
            ArrayList<JSONArray> assigned = new ArrayList<>();
            Task task = new Task();

            String taskname = _updated.getString("taskname");
            String _id = _updated.getString("_id");
            String _etag = _updated.getString("_etag");
            assigned.add(_updated.getJSONArray("assigned-to"));
            boolean completed = _updated.getBoolean("completed");
            String created = _updated.getString("_created");

            task.taskname = taskname;
            task.id = _id;
            task.etag = _etag;
            task.completed = completed;
            task.assign = assigned;
            task.created = created;

            tasks.add(task);
        }
        return tasks;
    }
}
