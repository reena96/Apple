import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulation {

    private int[] indexNeighbourArr;

    public void simulate(List<Cab> cabList, Map<String, Hexagon> hexagonDataMap) {

        int[] sixNeigbourIndices = new int[6];
        for (int idx = 0; idx < 6; idx++)
            sixNeigbourIndices[idx] = idx;

        for (int i = 0; i < cabList.size(); i++) {

            Cab cab = cabList.get(i);
            String cab_hex_id = cab.current_hexagon_id;
            Hexagon current_hexagon = hexagonDataMap.get(cab_hex_id);
            List<String> neighbours = current_hexagon.neighbor;

            if (neighbours.size() == 6)
                indexNeighbourArr = sixNeigbourIndices;
            else
                for (int l = 0; l < neighbours.size(); l++)
                    indexNeighbourArr[l] = l;

            Map<String, Integer> expectedNumbers = new HashMap<>();

            // can be updated to CLOCK TIME
            String current_time = cabList.get(i).current_time;

            for (int j = 0; j < neighbours.size(); j++) {
                String neighbour = neighbours.get(j);
                if(hexagonDataMap.get(neighbour)!=null && hexagonDataMap.get(neighbour).getTimestamps()!=null){
                    expectedNumbers.put(neighbour,
                            (hexagonDataMap.get(neighbour).getTimestamps().getOrDefault(current_time, 0))+1);
                }
                else
                    expectedNumbers.put(neighbour, 1);
            }

//            for (int j = 0; j < neighbours.size(); j++) {
//                expectedNumbers.put(neighbours.get(j), c++);
//            }
            //        System.out.println(a[findCeil(a, 4, 0, a.length - 1)]);

            int chosenIndex = RandomNumberGenerator.customizedRandom(indexNeighbourArr, expectedNumbers, neighbours);
            String destination_hex_id = neighbours.get(chosenIndex);
            if (hexagonDataMap.get(destination_hex_id) != null) {

                Location destination = hexagonDataMap.get(neighbours.get(chosenIndex)).center;

                int milliSeconds = Graphhopper.time(current_hexagon.center.latitude, current_hexagon.center.longitude, destination.latitude, destination.longitude);

                cab.setDestination(destination_hex_id, 0, milliSeconds / (1000.0 * 60));
//                System.out.println("travel time: " + cab.current_travel_time);
            }
        }
    }
}

