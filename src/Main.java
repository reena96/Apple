import java.text.ParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ParseException {
        Date startTime = new Date();
        Date endTime = new Date();

        // obtain Resource list - given CSV of rides between input startTime and endTime,
        ResourceAddition resourceAddition = new ResourceAddition();
        List<Resource> resources = resourceAddition.readResourcesFromCSV("src/data/sample_data.csv");

        // Initialize cabs & obtain list of cabs
        int noOfCabs = 100;
        CabCreation cabCreation = new CabCreation(noOfCabs, startTime);

        PreProcess loadHexagonData = new PreProcess();
        Map<String, Hexagon> hexagon_map = loadHexagonData.readHexagonsFromCSV("src/data/probability.csv");

        List<Cab> cabList = cabCreation.createCabs(hexagon_map);
    }
}
