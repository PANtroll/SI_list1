import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {

    public List<Object> startApp() {

        BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Podaj przystanek startowy");
            String start = s.readLine();
            System.out.println("Podaj przystanek końcowy");
            String end = s.readLine();
            System.out.println("Podaj godzine startu");
            String time = s.readLine();
            System.out.println("Wpisz t/p (t- czas, p - ilość przstanków)");
            String type = s.readLine();
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            Date data = df.parse(time);
            s.close();
            return List.of(start.toUpperCase(), end.toUpperCase(), data, type);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void dijkstra(Date startTime, Stop start, Stop end, Map<Stop, Map<Stop, Map<String, List<Date>>>> data) {

        int[] acc = new int[data.size()];
        Stop[] parents = new Stop[data.size()];
        boolean[] visited = new boolean[data.size()];
        List<Stop> stopListAll = data.keySet().stream().toList();
        Map<Stop, Map<String, List<Date>>> toCheck = data.get(start);
        for (int i = 0; i < acc.length; i++) {
            acc[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        stopListAll.remove(start);
        while (!toCheck.isEmpty()) {
            for (Stop neighbour : data.get(start).keySet()) {
                if(neighbour.equals(end)){

                }
                int min = Integer.MAX_VALUE;
                String minLine = "";
                Date minDate = null;
                visited[0] = true;
                parents[0] = start;
                for (String line : data.get(start).get(neighbour).keySet()) {
                    for (Date date : data.get(start).get(neighbour).get(line)) {
                        int distance = calculateTime(startTime, date);
                        if (distance < min) {
                            min = distance;
                            minLine = line;
                            minDate = date;
                        }
                    }
                }
            }

        }


    }

    public String app(Stop start, Stop end, Date time, String type, Map<Stop, Map<Stop, Map<String, List<Date>>>> data) {

        List<NodeResult> result = new ArrayList<>();
        int accCost = Integer.MAX_VALUE;
        int minCost = Integer.MAX_VALUE;

        if (data.get(start).containsKey(end)) {
            for (String line : data.get(start).get(end).keySet()) {
                for (Date date : data.get(start).get(end).get(line)) {
                    if (date.after(time) || date.after(new Date(time.getTime() - 24 * 60 * 60 * 1000))) {
                        minCost = getMinCost(date, time, minCost, result, end, line);
                    }
                }
            }
        }


        return result.toString();
    }

    private int getMinCost(Date date, Date time, int minCost, List<NodeResult> result, Stop end, String line) {
        int accCost = calculateTime(date, time);
        if (accCost < minCost) {
            minCost = accCost;
            if (!result.isEmpty())
                result.remove(result.size() - 1);
            result.add(new NodeResult(end, date, line));
        }
        return minCost;
    }

    public Map<Stop, Map<Stop, Map<String, List<Date>>>> fillData(List<Stop> listOfStops, List<Edge> listOfEdges) {

        Map<Stop, Map<Stop, Map<String, List<Date>>>> mapOfStops = new HashMap<>();

        listOfStops.forEach(obj -> mapOfStops.put(obj, new HashMap<>()));

        for (Edge connection : listOfEdges) {
            Stop startStop = connection.getStartStop();
            Stop endStop = connection.getEndStop();
            if (startStop.getName().equals("Bierzyce")) {
                System.out.println();
            }
            if (startStop == null) {
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

    public int calculateTime(Date time1, Date time2) {
        return (int) (time1.getTime() - time2.getTime()) / 60000;
    }
}
