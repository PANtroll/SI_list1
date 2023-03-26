import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

    private Utils() {
    }

    public static List<Stop> getDistinctStops(List<Edge> list){
        List<Stop> result = new ArrayList<>();

        for(Edge edge : list){
            if(!result.contains(edge.getStartStop())){
                result.add(edge.getStartStop());
            }
            else if(!result.contains(edge.getEndStop())){
                result.add(edge.getEndStop());
            }
        }
        return result;
    }

    public static Stop getStopByName(String name, List<Stop> stops){
        String nameUC = name.toUpperCase();
        List<Stop> result = stops.stream().filter(stop -> stop.getName().equals(nameUC)).toList();
        return result.isEmpty() ? null : result.get(0);

    }


}
