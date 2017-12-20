package sample;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Delete {
    public void deleteData(String urlContent, String id, String etag){
        try {

            URL url = new URL(urlContent + "/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type",
                    "application/json");
            conn.setRequestProperty("If-Match", etag);
            conn.setRequestMethod("DELETE");
            System.out.println(conn.getResponseCode());

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
