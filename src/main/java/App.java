import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

}
