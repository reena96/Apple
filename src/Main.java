import java.util.*;

public class Main {
    public static void main(String[] args) {
        Date startTime = new Date();
        Date endTime = new Date();

        // obtain Resource list
        // given CSV of rides between input startTime and endTime,
        ResourceAddition resourceAddition = new ResourceAddition();
//        System.out.println(Calendar.getInstance().getTime());
        List<Resource> resources = resourceAddition.readResourcesFromCSV(
                "/Users/reenamaryputhota/Desktop/Apple/src/data/sample_data.csv");

         //let's print all the resources read from CSV file
//        for (Resource r : resources) {
//            System.out.print(r.pickup_location.latitude + ",");
//            System.out.print(r.drop_location.latitude + ",");
//            System.out.print(r.pickup_time + ",");
//            System.out.print(r.drop_time + ",");
//            System.out.println();
//        }



        // Initialize cabs
        // locations and obtain cabs list
        int noOfCabs = 100;
        CabCreation cabCreation = new CabCreation(noOfCabs,startTime);
        Map<String,HexagonData> hexagon_map = cabCreation.readProbabilitiesFromCSV(
                "/Users/reenamaryputhota/Desktop/Apple/src/data/probability.csv");
//        for(String id:hexagon_map.keySet())
//            System.out.println(id);
        List<Cab> cabList = cabCreation.createCabs(hexagon_map);


//        for(int i=0;i<cabList.size();i++)
//            System.out.println(cabList.get(i).current_hexagon_id);



//        //Begin simulation
//        Simulation simulation = new Simulation();
//        simulation.simulate(cabList,hexagon_map);
//
//        // Perform allocation
//        Allocation allocation = new Allocation();
//        allocation.allocate(resources,cabList,hexagon_map);



    }


}
