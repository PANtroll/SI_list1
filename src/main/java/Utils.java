import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

    private Utils() {
    }

    public static List<String> getDistinctStops(List<Edge> list){
        List<String> result = new ArrayList<>();

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


}
