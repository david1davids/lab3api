package sample;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Patch {

    public void editDataUser(String id, String etag, String firstname, String lastname, String phone) throws IOException, JSONException {
        URL url = new URL("http://159.89.31.34/user/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("If-Match", etag);

        String user =  "{" +"firstname" + ":" + firstname + "," + "lastname" + ":" + lastname + ","+ "phone" + ":" + '"' + phone + '"' + "}";
        JSONObject object = new JSONObject(user);
        byte[] send = object.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8);

        conn.setDoOutput(true);

        OutputStream wr = conn.getOutputStream();
        wr.write(send);
        wr.flush();
        wr.close();
        InputStream inputStream = conn.getInputStream();
        System.out.println(inputStream);
    }

    public void editDataTask(String id, String etag, String taskname, Boolean completed) throws IOException, JSONException {
        URL url = new URL("http://159.89.31.34/tasks/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("If-Match", etag);

        String user =  "{" +"taskname" + ":" + taskname + "," + "completed" + ":" + completed +"}";
        JSONObject object = new JSONObject(user);
        byte[] send = object.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8);

        conn.setDoOutput(true);

        OutputStream wr = conn.getOutputStream();
        wr.write(send);
        wr.flush();
        wr.close();
        InputStream inputStream = conn.getInputStream();
        System.out.println(inputStream);
    }
}
