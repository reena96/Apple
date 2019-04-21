import java.util.Date;

public class Cab {
    double current_latitude;
    double current_longitude;
    String current_hexagon_id;
    Date current_time;

    Cab(String current_hexagon_id, Date current_time){
        this.current_hexagon_id = current_hexagon_id;
        this.current_time = current_time;
    }
    Cab(){

    }

}
