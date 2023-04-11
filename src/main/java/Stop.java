import java.util.Objects;

public class Stop {
    private String name;
    private double latitude;
    private double longitude;

    public Stop(String name) {
        this.name = name.toUpperCase();
    }

    public Stop(String name, double latitude, double longitude) {
        this.name = name.toUpperCase();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stop stop)) return false;
        return Objects.equals(getName(), stop.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Stop {" +
                "name='" + name + '\'' +
                '}';
    }
}
