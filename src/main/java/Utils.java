import java.util.*;

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

    public static Map<Stop, Map<Stop, Map<String, List<Date>>>> fillData(List<Stop> listOfStops, List<Edge> listOfEdges) {

        Map<Stop, Map<Stop, Map<String, List<Date>>>> mapOfStops = new HashMap<>();

        listOfStops.forEach(obj -> mapOfStops.put(obj, new HashMap<>()));

        for (Edge connection : listOfEdges) {
            Stop startStop = connection.getStartStop();
            Stop endStop = connection.getEndStop();
            if (startStop == null || endStop == null) {
                throw new IllegalArgumentException();
            }
            if (!mapOfStops.containsKey(startStop)) {
                mapOfStops.put(startStop, new HashMap<>());
            }
            if (!mapOfStops.get(startStop).containsKey(endStop)) {
                mapOfStops.get(startStop).put(endStop, new HashMap<>());
            }
            if (!mapOfStops.get(startStop).get(endStop).containsKey(connection.getLine())) {
                mapOfStops.get(startStop).get(endStop).put(connection.getLine(), new ArrayList<>());
            }
            mapOfStops.get(startStop).get(endStop).get(connection.getLine()).add(connection.getDepartureTime());

        }
        return mapOfStops;
    }


}
