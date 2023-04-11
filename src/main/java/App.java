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

    public String dijkstra(Date startTime, Stop start, Stop end, Map<Stop, Map<Stop, Map<String, List<Date>>>> data) {

        Map<Stop, Map<Stop, Map<String, List<Date>>>> dataCopy = new HashMap<>(data);
        Map<Stop, NodeResult> parents= new HashMap<>();
        Map<Stop, Boolean> visited = new HashMap<>();
        List<Stop> stopListAll = data.keySet().stream().toList();
        for (int i = 0; i < stopListAll.size(); i++) {
            visited.put(stopListAll.get(i), false);
        }
        visited.put(start, true);
        parents.put(start, new NodeResult(start, start, startTime, null));
        List<Stop> nodes;
        List<Stop> nodesCopy = new ArrayList<>();
        nodesCopy.add(start);
        while (!dataCopy.isEmpty()) {
            nodes = new ArrayList<>(nodesCopy);
            nodesCopy.clear();
            for(Stop node: nodes) {
                if(!parents.containsKey(node)){
                    continue;
                }
                Date dateNow = parents.get(node).getTime();
                for (Stop neighbour : data.get(node).keySet()) {
                    int min = Integer.MAX_VALUE;
                    String minLine = "";
                    Date minDate = null;
                    for (String line : data.get(node).get(neighbour).keySet()) {
                        for (Date date : data.get(node).get(neighbour).get(line)) {
                            if(date.after(dateNow)){// && (!parents.containsKey(neighbour) || parents.get(neighbour).getTime().after(date))) {
                                int distance = calculateTime(date, dateNow);
                                if (distance < min) {
                                    min = distance;
                                    minLine = line;
                                    minDate = date;
                                }
                            }
                        }
                    }
                    if(minDate != null && (!parents.containsKey(neighbour) || parents.get(neighbour).getTime().after(minDate))) {
                        NodeResult result = new NodeResult(node, neighbour, minDate, minLine);
                        parents.put(neighbour, result);
                    }
                }
                visited.put(node, true);
                Set<Stop> stops = data.get(node).keySet();
                nodesCopy.addAll(stops.stream().filter(obj -> !visited.get(obj) && dataCopy.containsKey(node)).toList());
                dataCopy.remove(node);
            }
            if(nodesCopy.isEmpty())
                break;
        }

        List<String> resultStrings = new ArrayList<>();
        resultStrings.add(parents.get(end).toString());
        Stop parent = parents.get(end).getFrom();
        do{
            resultStrings.add(parents.get(parent).toString());
            parent = parents.get(parent).getFrom();
        }while(!parent.equals(start));
        String resultString = "";
        for (int i = resultStrings.size(); i > 0; i--) {
            resultString += resultStrings.get(i-1) + '\n';
        }
        return resultString;
    }

    public String app(Stop start, Stop end, Date time, String type, Map<Stop, Map<Stop, Map<String, List<Date>>>> data) {

        List<NodeResult> result = new ArrayList<>();
        int accCost = Integer.MAX_VALUE;
        int minCost = Integer.MAX_VALUE;

        if (data.get(start).containsKey(end)) {
            for (String line : data.get(start).get(end).keySet()) {
                for (Date date : data.get(start).get(end).get(line)) {
                    if (date.after(time) || date.after(new Date(time.getTime() - 24 * 60 * 60 * 1000))) {
                        minCost = getMinCost(date, time, minCost, result, start, end, line);
                    }
                }
            }
        }
        else{
            return dijkstra(time, start, end, data);
        }


        return result.toString();
    }

    private int getMinCost(Date date, Date time, int minCost, List<NodeResult> result, Stop start, Stop end, String line) {
        int accCost = calculateTime(date, time);
        if (accCost < minCost) {
            minCost = accCost;
            if (!result.isEmpty())
                result.remove(result.size() - 1);
            result.add(new NodeResult(start, end, date, line));
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
