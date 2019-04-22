import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulation {

    int[] indexNeigbourArr;

    public void simulate(List<Cab> cabList, Map<String, Hexagon> hexagonDataMap) {
        int[] sixNeigbourIndices = new int[6];
        for (int idx = 0; idx < 6; idx++)
            sixNeigbourIndices[idx] = idx;

        for (int i = 0; i < cabList.size(); i++) {

            String cab_hex_id = cabList.get(i).current_hexagon_id;
            List<String> neighbours = hexagonDataMap.get(cab_hex_id).neighbor;

            if (neighbours.size() == 6)
                indexNeigbourArr = sixNeigbourIndices;
            else
                for (int l = 0; l < neighbours.size(); l++)
                    indexNeigbourArr[l] = l;


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

            int chosenIndex = RandomNumberGenerator.customizedRandom(indexNeigbourArr, expectedNumbers, neighbours);
//            cabList.get(i).destination_hex = neighbours.get(chosenIndex);

            System.out.println(chosenIndex);

        }
    }
}

