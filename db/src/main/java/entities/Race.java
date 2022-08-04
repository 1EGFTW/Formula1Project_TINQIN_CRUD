package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "race")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_race;

    private String circuitName;

    private Integer year;

    private Boolean isCompleted;

    @ManyToOne
    private Driver winner;

    @Temporal(TemporalType.DATE)
    private Date date;

    private Integer numberOfLaps;

    private Double distancePerLap;

    private Double latitude;

    private Double longitude;

    @OneToMany(mappedBy = "race")
    private Set<RaceSeason> raceSeasons;

    public Race() {
    }

    public Race(Long id_race, String circuitName, Integer year, Boolean isCompleted, Driver winner, Date date, Integer numberOfLaps, Double distancePerLap, Double latitude, Double longitude, Set<RaceSeason> raceSeasons) {
        this.id_race = id_race;
        this.circuitName = circuitName;
        this.year = year;
        this.isCompleted = isCompleted;
        this.winner = winner;
        this.date = date;
        this.numberOfLaps = numberOfLaps;
        this.distancePerLap = distancePerLap;
        this.latitude = latitude;
        this.longitude = longitude;
        this.raceSeasons = raceSeasons;
    }

    public Long getId_race() {
        return id_race;
    }

    public void setId_race(Long id_race) {
        this.id_race = id_race;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Driver getWinner() {
        return winner;
    }

    public void setWinner(Driver winner) {
        this.winner = winner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumberOfLaps() {
        return numberOfLaps;
    }

    public void setNumberOfLaps(Integer numberOfLaps) {
        this.numberOfLaps = numberOfLaps;
    }

    public Double getDistancePerLap() {
        return distancePerLap;
    }

    public void setDistancePerLap(Double distancePerLap) {
        this.distancePerLap = distancePerLap;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Set<RaceSeason> getRaceSeasons() {
        return raceSeasons;
    }

    public void setRaceSeasons(Set<RaceSeason> raceSeasons) {
        this.raceSeasons = raceSeasons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return Objects.equals(id_race, race.id_race) && Objects.equals(circuitName, race.circuitName) && Objects.equals(year, race.year) && Objects.equals(isCompleted, race.isCompleted) && Objects.equals(winner, race.winner) && Objects.equals(date, race.date) && Objects.equals(numberOfLaps, race.numberOfLaps) && Objects.equals(distancePerLap, race.distancePerLap) && Objects.equals(latitude, race.latitude) && Objects.equals(longitude, race.longitude) && Objects.equals(raceSeasons, race.raceSeasons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_race, circuitName, year, isCompleted, winner, date, numberOfLaps, distancePerLap, latitude, longitude, raceSeasons);
    }
}
