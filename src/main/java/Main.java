import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.uber.h3core.*;

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
        int noOfCabs = 100;
        CabCreation cabCreation = new CabCreation(noOfCabs, startTime);


        List<Cab> cabList = cabCreation.createCabs(hexagon_map);

        }


    private static void updateCabs(Cab cab, double _lati, double _long) throws IOException {


//        H3Core h3 = H3Core.newInstance();
//
//        double lat = 37.775938728915946;
//        double lng = -122.41795063018799;
//        int res = 9;
//
//        String hexAddr = h3.geoToH3Address(lat, lng, res);

        H3Core h3 = H3Core.newInstance();

        String _hex_id = h3.geoToH3Address(_lati, _long, 9);
        cab.current_hexagon_id = _hex_id;

    }




    }


