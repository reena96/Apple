import com.uber.h3core.H3Core;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        Date startTime = new Date();
        Date endTime = new Date();

        // obtain Resource list - given CSV of rides between input startTime and endTime,
        ResourceAddition resourceAddition = new ResourceAddition();
        List<Resource> resources = resourceAddition.readResourcesFromCSV("src/main/java/data/sample_data.csv");

        PreProcess loadHexagonData = new PreProcess();
        Map<String, Hexagon> hexagon_map = loadHexagonData.readHexagonsFromCSV("src/main/java/data/probability.csv");

        // Initialize cabs & obtain list of cabs
        int noOfCabs = 2000;
        CabCreation cabCreation = new CabCreation(noOfCabs, startTime);

        List<Cab> cabList = cabCreation.createCabs(hexagon_map);

        Simulation simulator = new Simulation();
        simulator.simulate(cabList, hexagon_map);

    }


    private static void updateCabs(Cab cab, double _lati, double _long) throws IOException {

        H3Core h3 = H3Core.newInstance();

        String _hex_id = h3.geoToH3Address(_lati, _long, 9);
        cab.current_hexagon_id = _hex_id;

    }


}


