import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HexagonData {
    String[] k_ring;
    double center_lat, center_long;
    Map<Date,Double> timestamps;

    double probability;
    String day_of_week;
    HexagonData(String day_of_week ){
        this.day_of_week = day_of_week;
    }

    public Map<Date,Double> getTimestamps(){
        return timestamps;
    }

    public void setTimestamps(Map<Date,Double> timestamps){
        this.timestamps = timestamps;
    }

}
