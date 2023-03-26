import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class NodeResult {

    private Stop stop;

    private Date time;

    private String line;

    public NodeResult(Stop stop, Date time, String line) {
        this.stop = stop;
        this.time = time;
        this.line = line;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NodeResult that)) return false;
        return Objects.equals(stop, that.stop) && Objects.equals(time, that.time) && Objects.equals(line, that.line);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stop, time, line);
    }

    @Override
    public String toString() {
        DateFormat dateFormat = DateFormat.getTimeInstance();
        return  "nazwa=" + stop +
                ", line='" + line + '\'' +
                ", czas=" + dateFormat.format(time);
    }
}
