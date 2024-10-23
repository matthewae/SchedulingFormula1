package sistempenjadwalanotomatisf1motgp;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;

public class modelrace {
    private int id;
    private String raceName;
    private String location;
    private String raceType;
    private Date raceDate;
    private int lapCount;
    private BigDecimal circuitLengthKm;
    private Time startTime;
    private Time endTime;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String imagePath;

    // Constructor
    public modelrace(int id, String raceName, String location, String raceType, Date raceDate, int lapCount,
                     BigDecimal circuitLengthKm, Time startTime, Time endTime) {
        this.id = id;
        this.raceName = raceName;
        this.location = location;
        this.raceType = raceType;
        this.raceDate = raceDate;
        this.lapCount = lapCount;
        this.circuitLengthKm = circuitLengthKm;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRaceName() { return raceName; }
    public void setRaceName(String raceName) { this.raceName = raceName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getRaceType() { return raceType; }
    public void setRaceType(String raceType) { this.raceType = raceType; }

    public Date getRaceDate() { return raceDate; }
    public void setRaceDate(Date raceDate) { this.raceDate = raceDate; }

    public int getLapCount() { return lapCount; }
    public void setLapCount(int lapCount) { this.lapCount = lapCount; }

    public BigDecimal getCircuitLengthKm() { return circuitLengthKm; }
    public void setCircuitLengthKm(BigDecimal circuitLengthKm) { this.circuitLengthKm = circuitLengthKm; }

    public Time getStartTime() { return startTime; }
    public void setStartTime(Time startTime) { this.startTime = startTime; }

    public Time getEndTime() { return endTime; }
    public void setEndTime(Time endTime) { this.endTime = endTime; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
