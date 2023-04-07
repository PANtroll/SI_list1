import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ParseException {

//        String pathToFile = "src/main/resources/connection_graph.csv";
        String pathToFile = "src/main/resources/testData.csv";

        ReadData reader = new ReadData();
        List<Edge> listOfConnections = reader.readFile(pathToFile);
        List<Stop> listOfStops = Utils.getDistinctStops(listOfConnections);

        App app = new App();
        Map<Stop, Map<Stop, Map<String, List<Date>>>> data = app.fillData(listOfStops, listOfConnections);

//        List<Object> parameters = app.startApp();
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        List<Object> parameters = List.of("PL. JANA PAWŁA II", "pl. Strzegomski (Muzeum Współczesne)", df.parse("3:50:00"), "t");

        System.out.println("Najlepsza scieżka:");
        System.out.println(app.app(Utils.getStopByName(parameters.get(0).toString(), listOfStops),
                Utils.getStopByName(parameters.get(1).toString(), listOfStops),
                (Date) parameters.get(2),
                (String) parameters.get(3),
                data));

    }

}
