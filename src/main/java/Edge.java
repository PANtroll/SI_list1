import java.util.Date;
import java.util.Objects;

public class Edge {

    private String company;
    private String line;
    private Stop startStop;
    private Stop endStop;
    private Date departureTime;
    private Date arrivalTime;
    private long duration;

    public void calculateDuration(){
        this.duration = (arrivalTime.getTime() - departureTime.getTime())/60000;
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Stop getStartStop() {
        return startStop;
    }

    public void setStartStop(Stop startStop) {
        this.startStop = startStop;
    }

    public Stop getEndStop() {
        return endStop;
    }

    public void setEndStop(Stop endStop) {
        this.endStop = endStop;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "company='" + company + '\'' +
                ", line='" + line + '\'' +
                ", startStop='" + startStop + '\'' +
                ", endStop='" + endStop + '\'' +
                ", DepartureTime=" + departureTime +
                ", ArrivalTime=" + arrivalTime +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge edge)) return false;
        return getDuration() == edge.getDuration() && Objects.equals(getCompany(), edge.getCompany()) && Objects.equals(getLine(), edge.getLine()) && Objects.equals(getStartStop(), edge.getStartStop()) && Objects.equals(getEndStop(), edge.getEndStop()) && Objects.equals(getDepartureTime(), edge.getDepartureTime()) && Objects.equals(getArrivalTime(), edge.getArrivalTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompany(), getLine(), getStartStop(), getEndStop(), getDepartureTime(), getArrivalTime(), getDuration());
    }
}
