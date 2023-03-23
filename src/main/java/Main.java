import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String pathToFile = "src/main/resources/connection_graph.csv";

        ReadData reader = new ReadData();
        List<Edge> listOfConnections = reader.readFile(pathToFile);
        List<String> listOfStops = Utils.getDistinctStops(listOfConnections);
        App app = new App();
        List<Object> parameters = app.startApp();
        System.out.println(parameters);

    }

}
