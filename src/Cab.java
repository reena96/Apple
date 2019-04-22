import java.util.Date;

public class Cab {

    Location current;
    String current_hexagon_id;
    String current_time;
    int status;
    String destination_hex;
    double destination_distance;
    double current_travel_time;
    double penalty;
    double total_waiting_time;


    Cab(String current_hexagon_id, String current_time, Location current) {
        this.current_hexagon_id = current_hexagon_id;
        this.current_time = current_time;
        this.current = current;
        this.status = 0;
        this.current = current;
        this.current_travel_time = 0;
        this.destination_distance = 0;
        this.penalty = 0;
        this.total_waiting_time = 0;
    }

    void setDestination(String hex_id, double distance, double travel_time) {
        this.destination_hex = hex_id;
        this.destination_distance = distance;
        this.current_travel_time = travel_time;
    }
}
