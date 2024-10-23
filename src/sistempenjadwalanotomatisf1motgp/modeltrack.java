package sistempenjadwalanotomatisf1motgp;

public class modeltrack {
    private String trackName;
    private String country;
    private double length;

    public modeltrack(String trackName, String country, double length) {
        this.trackName = trackName;
        this.country = country;
        this.length = length;
    }

    // Getters and Setters
    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "ModelTrack{" +
                "trackName='" + trackName + '\'' +
                ", country='" + country + '\'' +
                ", length=" + length +
                '}';
    }
}
