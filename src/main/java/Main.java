import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Main {

    Utilities helper = new Utilities();

    public static void main(String[] args) throws ParseException, IOException {
        Date startTime = new Date();
        Date endTime = new Date();

        // obtain Resource list - given CSV of rides between input startTime and endTime,
        ResourceAddition resourceAddition = new ResourceAddition();
        Queue<Resource> resources = resourceAddition.readResourcesFromCSV("src/main/java/data/sample_data.csv");

        PreProcess loadHexagonData = new PreProcess();
        Map<String, Hexagon> hexagon_map = loadHexagonData.readHexagonsFromCSV("src/main/java/data/probability.csv");

        // Initialize cabs & obtain list of cabs
        int noOfCabs = 2000;
        CabCreation cabCreation = new CabCreation(noOfCabs, startTime);

        List<Cab> cabList = cabCreation.createCabs(hexagon_map);

        Simulation simulator = new Simulation();
        simulator.simulate(cabList, hexagon_map);

        Result result = new Result(300000);
        Allocation.allocate(resources,cabList,600000,result);
    }

    public void updateCurrentHex(Cab cab, double latitude, double longitude) throws IOException {

        cab.current_hexagon_id = helper.getHexFromGeo(latitude, longitude);
    }
}


