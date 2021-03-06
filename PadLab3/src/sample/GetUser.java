package sample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetUser {

    public ArrayList getUser(){
        ArrayList<String> users = new ArrayList<>();
        String jsonText = null;
        try {

            URL url = new URL("http://159.89.31.34/user");
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

            //System.out.println(jsonText);
            JSONObject jsonObject = new JSONObject(jsonText);

            JSONArray values = jsonObject.getJSONArray("_items");

            for (int i = 0; i < values.length(); i++){
                JSONObject _updated = values.getJSONObject(i);

                String firstname = _updated.getString("firstname");
                String lastname = _updated.getString("lastname");
                String phone = _updated.getString("phone");

                users.add(firstname + " " + lastname + " " + phone);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }
}
