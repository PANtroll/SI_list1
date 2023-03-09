import java.util.List;

public class Main {

    public static void main(String[] args) {

        String pathToFile = "src/main/resources/connection_graph.csv";

        ReadData reader = new ReadData();
        List<Edge> list = reader.readFile(pathToFile);

    }

}
