import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utilities {

    public String getTimeStamp(Date date) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int minutes = calendar.get(Calendar.MINUTE);
        int mod = minutes % 5;
        mod = mod < 3 ? -mod : (5 - mod);
        calendar.set(Calendar.MINUTE, minutes + mod);
        calendar.set(Calendar.SECOND, 00);

        String strDateFormat = "hh:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(calendar.getTime());
        return formattedDate;
    }
    public List<String> readNeighborsList(String hexagon_id, String neighbors) {

        neighbors = neighbors.replace("{", "");
        neighbors = neighbors.replace("}", "");
        neighbors = neighbors.replace("'", "");
        neighbors = neighbors.replace(" ", "");
        List<String> neighbors_list = new ArrayList<>();
        String[] list = neighbors.split(":");

        for (int i = 0; i < list.length; i++) {
            if (Objects.equals(list[i], hexagon_id))
                continue;
            neighbors_list.add(list[i]);
        }
        return neighbors_list;
    }
}
