package sample;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class AddUser {
    public void add(String firstname, String lastname, String phone){
        try {

            URL url = new URL("http://159.89.31.34/user");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");


            String user =  "{" +"firstname" + ":" + firstname + "," + "lastname" + ":" + lastname + ","+ "phone" + ":" + phone + "}";

            JSONObject object = new JSONObject(user);
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

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
