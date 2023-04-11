import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class NodeResult {

    private Stop from;
    private Stop to;

    private Date time;

    private String line;

    public NodeResult(Stop from, Stop to, Date time, String line) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.line = line;
    }

    public Stop getFrom() {
        return from;
    }

    public void setFrom(Stop from) {
        this.from = from;
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

    public Stop getTo() {
        return to;
    }

    public void setTo(Stop to) {
        this.to = to;
    }

    public void swapStops(){
        Stop temp = to;
        to = from;
        from = temp;
    }

    @Override
    public String toString() {
        return "From: " + from +
                " -> to: " + to +
                ", time=" + time.toString() +
                ", line='" + line + '\'';
    }
    public StringBuilder toString(StringBuilder builder) {
        return builder.append(this.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeResult that = (NodeResult) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(time, that.time) && Objects.equals(line, that.line);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, time, line);
    }
}
