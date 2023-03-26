import java.util.Objects;

public class Stop {

    private String name;

    public Stop(String name) {
        this.name = name.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
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
