import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hexagon {

    List<String> neighbor;
    Location center = new Location();
    Map<String, Integer> probabilities;

    double probability;
    int day_of_week;

    Hexagon(int day_of_week, List<String> neighbor, double latitude, double longitude) {
        this.day_of_week = day_of_week;
        this.neighbor = neighbor;
        this.center.latitude = latitude;
        this.center.longitude = longitude;
    }

    public Map<String, Integer> getTimestamps() {
        return probabilities;
    }

    public void setTimestamps(Map<String, Integer> timestamps) {
        this.probabilities = timestamps;
    }

}
