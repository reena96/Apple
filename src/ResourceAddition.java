import com.sun.tools.javac.util.ArrayUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ResourceAddition {
    List<Resource> readResourcesFromCSV(String fileName) {
        List<Resource> resources = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        Map<String,List> map = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();
            line = br.readLine();
            // loop until all lines are read
            while (line != null) {
                // use string.split to load a string array with the values from
                // each line of the file, using a comma as the delimiter

                line = line.replace("\"","");
                String[] attributes = line.split(",");


                Resource resource = createResource(attributes);

                // adding book into ArrayList

                resources.add(resource);

                // read next line before looping // if end of file reached, line would be null

                line = br.readLine();

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resources;
    }


    static Resource createResource(String[] metadata) throws ParseException {
        Location pickup_location = new Location();
        pickup_location.latitude = Double.parseDouble(metadata[16]);
        pickup_location.longitude = Double.parseDouble(metadata[15]);

        Location drop_location = new Location();
        drop_location.latitude = Double.parseDouble(metadata[17]);
        drop_location.longitude = Double.parseDouble(metadata[18]);

        Date pickup_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(metadata[1]);
        Date drop_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(metadata[2]);

//        String pickup_time = metadata[1];
//        String drop_time = metadata[2];
        String hexagon_id = metadata[19];

        // create and return resource of this metadata
        return new Resource(pickup_location, pickup_time, drop_location, drop_time, hexagon_id);
    }
}
