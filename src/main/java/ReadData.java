import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReadData {

    public List<Edge> readFile(String path) {

        List<Edge> result = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] nextRecord = null;
            while ((nextRecord = reader.readNext()) != null) {
                Edge edge = new Edge();
                edge.setStartStop(nextRecord[0]);
                edge.setEndStop(nextRecord[1]);
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateFormat.getInstance().parse(nextRecord[2]));
                edge.setStartTime(cal);
                cal.clear();
                cal.setTime(DateFormat.getInstance().parse(nextRecord[2]));
                edge.setStartTime(cal);

                result.add(edge);
            }
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        } catch (ParseException e) {
            System.out.println(e.getStackTrace());
        }
        return result;
    }


}
