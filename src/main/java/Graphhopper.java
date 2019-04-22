import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Graphhopper {
    public static int time(double source_lat, double source_long, double dest_lat, double dest_long) {
        try {
            URL url = new URL("http://localhost:8989/route?point=" + source_lat + "%2C" + source_long + "&point=" + dest_lat + "%2C" + dest_long + "&type=json&locale=en-US&vehicle=car&weighting=fastest&elevation=false");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp = "";
            String k = "";
            while (null != (strTemp = br.readLine())) {
                k = strTemp;
            }
            JSONObject myObject = new JSONObject(k);
            JSONArray nested = myObject.getJSONArray("paths");
            JSONObject rec = nested.getJSONObject(0);
            int time = rec.getInt("time");
            return time;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}