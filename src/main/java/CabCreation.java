import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CabCreation {

    int noOfCabs;
    Date start_time;
    Utilities helper = new Utilities();

    CabCreation(int noOfCabs, Date start_time) {
        this.noOfCabs = noOfCabs;
        this.start_time = start_time;
    }

    List<Cab> createCabs(Map<String, Hexagon> hexagon_map) throws ParseException {
        List<Cab> cabList = new ArrayList<>();

        // initialize cabList current_locations to randomized hexagons
        int equidistant = noOfCabs / hexagon_map.size();

        for (String hexagon_id : hexagon_map.keySet()) {

            Location current = hexagon_map.get(hexagon_id).center;
            String current_time = helper.getTimeStamp(new Date());
            int i = 1;
            while (i % equidistant != 0) {
                Cab cab = new Cab(hexagon_id, current_time, current);
                cabList.add(cab);
                i++;
            }
        }
        return cabList;
    }
}
