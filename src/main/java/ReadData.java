import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReadData {

    public List<Edge> readFile(String path) {

        List<Edge> result = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] nextRecord;
            reader.readNext();
            while ((nextRecord = reader.readNext()) != null) {
                Edge edge = new Edge();
                edge.setCompany(nextRecord[2]);
                edge.setLine(nextRecord[3]);
                edge.setStartStop(new Stop(nextRecord[6]));
                edge.setEndStop(new Stop(nextRecord[7]));
                DateFormat df = new SimpleDateFormat("HH:mm:ss");
                edge.setDepartureTime(df.parse(nextRecord[4]));
                edge.setArrivalTime(df.parse(nextRecord[5]));
                edge.calculateDuration();
                result.add(edge);
            }
        } catch (IOException e) {
            System.err.println(e);
        } catch (ParseException e) {
            System.err.println(e);
        }
        return result;
    }


}
