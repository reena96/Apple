import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CabCreation {
    int noOfCabs;
    Date start_time;
    Cab cab;

    CabCreation(int noOfCabs, Date start_time) {
        this.noOfCabs = noOfCabs;
        this.start_time = start_time;
    }


    Map<String, HexagonData> readProbabilitiesFromCSV(String fileName) {
        Map<String, HexagonData> hexagon_map = new HashMap<>();
        Path pathToFile = Paths.get(fileName);
        Map<String, List> map = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();
            line = br.readLine();
            // loop until all lines are read
            while (line != null) {
//                System.out.println(line);
                // use string.split to load a string array with the values from
                // each line of the file, using a comma as the delimiter

                line = line.replace("\"", "");
                String[] attributes = line.split(",");
                String hexagon_id = attributes[0];

                HexagonData hexagonData = createHexagonData(hexagon_id, attributes, hexagon_map.get(hexagon_id));

                // adding book into ArrayList
                hexagon_map.put(hexagon_id, hexagonData);

                // read next line before looping // if end of file reached, line would be null
                line = br.readLine();

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hexagon_map;
    }

    List<Cab> createCabs(Map<String, HexagonData> hexagon_map) {
        List<Cab> cabList = new ArrayList<>();
        // initialize cabList current_locations to randomized hexagons
        int equidistance = noOfCabs / hexagon_map.size();


        for (String hexagon_id : hexagon_map.keySet()) {
            int i = 1;
            while (i % equidistance != 0) {
                cab = new Cab(hexagon_id, start_time);
                cabList.add(cab);
                i++;
            }
        }

        return cabList;
    }


    static HexagonData createHexagonData(String hexagon_id, String[] metadata, HexagonData hexagonDataOld) throws ParseException {


        Date start_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(metadata[1]);
        double probability = Double.parseDouble(metadata[2]);
        String day_of_week = metadata[3];

//        Map<Date,List<Object>> timestamp_map = new HashMap<>();

        if (hexagonDataOld == null) {
            HexagonData hexagonData = new HexagonData(day_of_week);
            if (hexagonData.getTimestamps() == null) {
                Map<Date, Double> map = new HashMap<>();
                map.put(start_time, probability);
                hexagonData.setTimestamps(map);
            } else {
                hexagonData.getTimestamps().put(start_time, probability);
            }
            return hexagonData;
        }
        hexagonDataOld.getTimestamps().put(start_time, probability);
        // create and return resource of this metadata
        return hexagonDataOld;
    }
}
