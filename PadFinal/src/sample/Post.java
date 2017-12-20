package sample;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Post {
    public void sendData(String text, String urlContent) throws IOException, JSONException {
        URL url = new URL(urlContent);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type","application/json");

        JSONObject object = new JSONObject(text);
        byte[] send = object.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8);
        int length = send.length;

        conn.setDoOutput(true);
        conn.setFixedLengthStreamingMode(length);

        OutputStream wr = conn.getOutputStream();
        wr.write(send);
        wr.flush();
        wr.close();

        InputStream is = conn.getInputStream();
        System.out.println(is);

        conn.disconnect();
    }

    public String addUser(String firstname, String lastname, String phone){
        String user =  "{" +"firstname" + ":" + firstname + "," + "lastname" + ":" + lastname + ","+ "phone" + ":" + '"' + phone + '"' + "}";
        return user;
    }

    public String addTask(String taskname, boolean completed, ArrayList assigned){
        String task =  "{" +"taskname" + ":" + taskname + "," + "completed" + ":" + completed + "," +  "assigned-to" + ":" + assigned + "}";
        return task;
    }
}
