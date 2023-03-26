import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {

    public List<Object> startApp(){

        BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Podaj przystanek startowy");
            String start = s.readLine();
            System.out.println("Podaj przystanek końcowy");
            String end = s.readLine();
            System.out.println("Podaj godzine startu");
            String time = s.readLine();
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            Date data = df.parse(time);
            s.close();
            return List.of(start.toUpperCase(), end.toUpperCase(), data);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public String app(Stop start, Stop end, Date time){

        Map<Stop, Map<Stop, Date>> mapOfStops = fillData();


    }

    public Map<Stop, Map<Stop, Date>> fillData(List<Stop> listOfStops, List<Edge> listOfEdges){

        Map<Stop, Map<Stop, Date>> mapOfStops = new HashMap<>();

        listOfStops.forEach(obj -> mapOfStops.put(obj, new HashMap<>()));

        for (Edge connection : listOfEdges){
            if(!mapOfStops.containsKey(connection.getStartStop())){
                mapOfStops.put(connection.)
            }
        }


    }
}
