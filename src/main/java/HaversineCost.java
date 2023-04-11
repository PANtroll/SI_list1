import java.util.Comparator;

public class HaversineCost implements Comparator<Stop> {

    public double computeCost(Stop from, Stop to) {
        double R = 6372.8;

        double dLat = Math.toRadians(to.getLatitude() - from.getLatitude());
        double dLon = Math.toRadians(to.getLongitude() - from.getLongitude());
        double lat1 = Math.toRadians(from.getLatitude());
        double lat2 = Math.toRadians(to.getLatitude());

        double a = Math.pow(Math.sin(dLat / 2),2)
                + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

    @Override
    public int compare(Stop from, Stop to) {
        double dLat = Math.toRadians(to.getLatitude() - from.getLatitude());
        double dLon = Math.toRadians(to.getLongitude() - from.getLongitude());
        if(dLat == 0 && dLon == 0) return 0;
        double dLatLon = (dLat - dLon);
        return dLatLon > 0.0 ? -1 : 1;
    }
}
