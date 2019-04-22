import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Allocation {

    public static void allocate(Queue<Resource> resources, List<Cab> cabList, int mlt, Result result) {

        int res_count = resources.size();
        int cab_count = cabList.size();
        int min_time = 2147483647;
        int count = 0;

        while (count < res_count) {

//            System.out.println("count: "+count + " resource size: "+resources.size());
            Resource r = resources.poll();
//            System.out.println("resource hex: " + r.hexagon_id);
            int cab_id = -1;

            if (r.wait_time >= mlt) {
                System.out.println("Dropped cab: " + r.hexagon_id + " because of wait_time: " + r.wait_time);
                result.total_dropped += 1;
                result.wait_time += r.wait_time;
                count++;
                continue; // resource dropped from queue
            }

            double src_lat = r.pickup_location.latitude;
            double src_long = r.pickup_location.longitude;

            for (int j = 0; j < cab_count; j++) {

                if (cabList.get(j).status == 0) {
                    double dest_lat = cabList.get(j).current.latitude;
                    double dest_long = cabList.get(j).current.longitude;
                    int curr_cab_time = Graphhopper.time(src_lat, src_long, dest_lat, dest_long);

                    // finding closest cab
                    if (curr_cab_time < min_time) {
                        min_time = curr_cab_time;
                        cab_id = j;
                    }
                }
            }

            // if cab is available and can reach before (mlt - already waited time)
            if (cab_id != -1 && (min_time + r.wait_time) <= mlt) {

                Cab assignedCab = cabList.get(cab_id);
                assignedCab.status = 1;
                System.out.println("Assigned cab:" + cab_id + " for resource at: " + r.hexagon_id);
                result.wait_time += r.wait_time;
                result.search_time += assignedCab.total_waiting_time;
                assignedCab.total_waiting_time = 0;
                count++;

            } else {
                resources.add(r);
                r.wait_time += result.global_time;
//                System.out.println("wait time: " + r.wait_time);
            }
        }

        System.out.println("Total Dropped Resources: " + result.total_dropped);
        System.out.println("Total Assigned Resources: " + (res_count - result.total_dropped));
        System.out.println("Total Wait Time: " + result.wait_time);
        System.out.println("Total Search Time: " + result.search_time);
    }

    public static void main(String[] args) {
        Queue<Resource> resourceQueue = new LinkedList<>();
        // obtain Resource list - given CSV of rides between input startTime and endTime,
        ResourceAddition resourceAddition = new ResourceAddition();
        Queue<Resource> resources = resourceAddition.readResourcesFromCSV("src/main/java/data/sample_data.csv");
    }
}