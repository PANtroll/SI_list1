import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadData {

    public List<Edge> readFile(String path){

        List<Edge> result = new ArrayList<>();

        try(CSVReader reader = new CSVReader(new FileReader(path))){

        }
        catch(IOException e){
            System.err.println(e.getStackTrace());
        }
        return result;
    }


}
