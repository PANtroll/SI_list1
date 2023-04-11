
import org.apache.commons.lang3.SerializationUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Manager {

    public static void main(String[] args) throws ParseException {

        String pathToFile = "src/main/resources/connection_graph.csv";
//        String pathToFile = "src/main/resources/testData.csv";

        ReadData reader = new ReadData();
        List<Edge> listOfConnections = reader.readFile(pathToFile);
        List<Stop> listOfStops = Utils.getDistinctStops(listOfConnections);

        Map<Stop, Map<Stop, Map<String, List<Date>>>> data = Utils.fillData(listOfStops, listOfConnections);

        App app = new App();
//        List<Object> parameters = app.startApp();
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
//        List<Object> parameters = List.of("orla", "pl. Strzegomski (Muzeum Współczesne)", df.parse("13:50:00"));
        List<Object> parameters = List.of("rynek", "Litewska", df.parse("17:37:00"));

        System.out.println("___Dijkstra___");
        Date time = new Date();
        System.out.print(app.dijkstra(
                (Date) parameters.get(2),
                Utils.getStopByName(parameters.get(0).toString(), listOfStops),
                Utils.getStopByName(parameters.get(1).toString(), listOfStops),
                data));
        System.out.println("time: " + (new Date().getTime() - time.getTime()));


        System.out.println("____A Star____");
        time = new Date();
        System.out.print(app.aStar(
                (Date) parameters.get(2),
                Utils.getStopByName(parameters.get(0).toString(), listOfStops),
                Utils.getStopByName(parameters.get(1).toString(), listOfStops),
                data));
        System.out.println("time: " + (new Date().getTime() - time.getTime()));


        System.out.println("____A Star przesiadki____");
        time = new Date();
        System.out.print(app.aStarStops(
                (Date) parameters.get(2),
                Utils.getStopByName(parameters.get(0).toString(), listOfStops),
                Utils.getStopByName(parameters.get(1).toString(), listOfStops),
                data));
        System.out.println("time: " + (new Date().getTime() - time.getTime()));
    }

}
