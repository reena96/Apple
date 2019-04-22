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

        int i = 1;
        for (String hexagon_id : hexagon_map.keySet()) {

            Location current = hexagon_map.get(hexagon_id).center;
            String current_time = helper.getTimeStamp(new Date());

            Cab cab;
            while (i % equidistant + 1 != 0 && i <= noOfCabs) {
                cab = new Cab(hexagon_id, current_time, current);
                cabList.add(cab);
                i++;
            }
            i++;
        }

        System.out.println("hexagon-size: " + hexagon_map.size() + " equidistant: " + equidistant + " no of cabs: " + cabList.size());
        return cabList;
    }
}
